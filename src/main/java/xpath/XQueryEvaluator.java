package xpath;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class XQueryEvaluator extends XQueryBaseVisitor<List<Node>> {

    // The document used to create new nodes (e.g. for tag constructors)
    private Document outputDoc;
    // A stack-based environment for variable bindings
    private Deque<Map<String, List<Node>>> env;
    // Delegate evaluator for absolute/relative XPath expressions
    private XPathEvaluator xpath;

    public XQueryEvaluator() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.outputDoc = builder.newDocument();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create output document", e);
        }
        this.env = new ArrayDeque<>();
        // Push an initial (empty) scope
        env.push(new HashMap<>());

        // Create a delegate XPath evaluator
        this.xpath = new XPathEvaluator();
    }

    // ---------------------------------------------------------
    //  XQuery Expression Visitors
    // ---------------------------------------------------------

    /**
     * xquery: var  # XQueryVariable
     */
    @Override
    public List<Node> visitXQueryVariable(XQueryParser.XQueryVariableContext ctx) {
        String varName = ctx.var().getText();  // e.g. "$x"
        // Look up in env stack (from top to bottom)
        for (Map<String, List<Node>> scope : env) {
            if (scope.containsKey(varName)) {
                return scope.get(varName);
            }
        }
        throw new RuntimeException("Undefined variable: " + varName);
    }

    /**
     * xquery: stringConstant  # XQueryConstant
     */
    @Override
    public List<Node> visitXQueryConstant(XQueryParser.XQueryConstantContext ctx) {
        // stringConstant -> StringConstant from XPath grammar
        String quoted = ctx.stringConstant().getText();  // e.g. "\"Hello\""
        // Strip surrounding quotes
        String unquoted = quoted.substring(1, quoted.length() - 1);
        // Create text node in our output document
        Text textNode = outputDoc.createTextNode(unquoted);
        return Collections.singletonList(textNode);
    }

    /**
     * xquery: absolutePath  # XQueryAbsolutePath
     *
     * Here, the "absolutePath" sub-tree actually belongs to the XPath grammar,
     * specifically:  XPathParser.AbsolutePathContext
     */
    @Override
    public List<Node> visitXQueryAbsolutePath(XQueryParser.XQueryAbsolutePathContext ctx) {
        // 1) Extract the text from the sub-tree, e.g. "doc(\"file.xml\")/PLAY"
        String pathText = ctx.getText();

        // 2) Parse that text using your XPath grammar
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(pathText));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        XPathParser.XpathContext xpathTree = parser.xpath();

        // 3) Evaluate it with your XPathEvaluator
        return xpath.visit(xpathTree);
    }


    /**
     * xquery: '(' xquery ')'  # XQueryParentheses
     */
    @Override
    public List<Node> visitXQueryParentheses(XQueryParser.XQueryParenthesesContext ctx) {
        List<Node> inside = visit(ctx.xquery());
        return (inside != null) ? inside : new ArrayList<>();
    }

    /**
     * xquery: xquery ',' xquery  # XQueryConcat
     */
    @Override
    public List<Node> visitXQueryConcat(XQueryParser.XQueryConcatContext ctx) {
        List<Node> left = visit(ctx.xquery(0));
        List<Node> right = visit(ctx.xquery(1));
        List<Node> result = new ArrayList<>();
        if (left != null) result.addAll(left);
        if (right != null) result.addAll(right);
        return result;
    }

    /**
     * xquery: xquery '/' relativePath  # XQueryPath
     */
    @Override
    public List<Node> visitXQueryPath(XQueryParser.XQueryPathContext ctx) {
        // Evaluate the left XQuery part
        List<Node> contextNodes = visit(ctx.xquery());
        if (contextNodes == null) contextNodes = new ArrayList<>();
        // Then apply the relative path using the XPath evaluator
        xpath.setCurrentContext(contextNodes);
        List<Node> result = xpath.visit(ctx.relativePath());
        return (result != null) ? result : new ArrayList<>();
    }

    /**
     * xquery: xquery '//' relativePath  # XQueryDoubleSlash
     *
     * Collect all descendants from the left, then evaluate the relative path.
     */
    @Override
    public List<Node> visitXQueryDoubleSlash(XQueryParser.XQueryDoubleSlashContext ctx) {
        List<Node> leftNodes = visit(ctx.xquery());
        if (leftNodes == null) leftNodes = new ArrayList<>();

        // Gather leftNodes plus all descendants
        List<Node> allDesc = new ArrayList<>(leftNodes);
        for (Node n : leftNodes) {
            getDescendants(n, allDesc);
        }

        // Now evaluate the relative path from that expanded set
        xpath.setCurrentContext(allDesc);
        List<Node> result = xpath.visit(ctx.relativePath());
        return (result != null) ? result : new ArrayList<>();
    }

    /**
     * xquery: '<' tagName '>' '{' xquery '}' '</' tagName '>'  # XQueryTag
     */
    @Override
    public List<Node> visitXQueryTag(XQueryParser.XQueryTagContext ctx) {
        String tag = ctx.tagName(0).getText();  // e.g. "result"
        Element elem = outputDoc.createElement(tag);

        // Evaluate the sub-xquery inside { ... }
        List<Node> children = visit(ctx.xquery());
        if (children != null) {
            for (Node child : children) {
                // Import node into our document
                Node imported = outputDoc.importNode(child, true);
                elem.appendChild(imported);
            }
        }
        return Collections.singletonList(elem);
    }

    /**
     * xquery: forClause letClause? whereClause? returnClause  # XQueryFLWR
     */
    @Override
    public List<Node> visitXQueryFLWR(XQueryParser.XQueryFLWRContext ctx) {
        // Evaluate for-clause to produce a list of variable bindings
        List<Map<String, List<Node>>> forBindings = evaluateForClause(ctx.forClause());
        List<Node> finalResults = new ArrayList<>();

        // Save the current (outer) environment
        Map<String, List<Node>> savedEnv = new HashMap<>(env.peek());

        try {
            // For each binding from the for-clause
            for (Map<String, List<Node>> binding : forBindings) {
                // Create a new scope on top of saved environment
                Map<String, List<Node>> newScope = new HashMap<>(savedEnv);
                newScope.putAll(binding);
                env.push(newScope);

                try {
                    // If there's a let-clause, evaluate it
                    if (ctx.letClause() != null) {
                        Map<String, List<Node>> letBindings = evaluateLetClause(ctx.letClause());
                        env.peek().putAll(letBindings);
                    }

                    // If there's a where-clause, check condition
                    boolean pass = true;
                    if (ctx.whereClause() != null) {
                        pass = evaluateCondition(ctx.whereClause().cond());
                    }

                    // If condition passes, evaluate the return-clause
                    if (pass) {
                        List<Node> ret = visit(ctx.returnClause().xquery());
                        if (ret != null) {
                            finalResults.addAll(ret);
                        }
                    }
                } finally {
                    // Pop this iteration's scope
                    env.pop();
                }
            }
        } finally {
            // Restore the original env scope
            env.peek().clear();
            env.peek().putAll(savedEnv);
        }

        return finalResults;
    }

    /**
     * xquery: letClause xquery  # XQueryLet
     */
    @Override
    public List<Node> visitXQueryLet(XQueryParser.XQueryLetContext ctx) {
        // Evaluate let-bindings
        Map<String, List<Node>> letBindings = evaluateLetClause(ctx.letClause());
        // Create a new scope that includes those bindings
        Map<String, List<Node>> newScope = new HashMap<>(env.peek());
        newScope.putAll(letBindings);

        env.push(newScope);
        try {
            // Then evaluate the following xquery
            List<Node> result = visit(ctx.xquery());
            return (result != null) ? result : new ArrayList<>();
        } finally {
            env.pop();
        }
    }

    // ---------------------------------------------------------
    //   Helper Methods for FLWR
    // ---------------------------------------------------------

    /**
     * Evaluate a forClause, returning a list of variable-binding maps.
     * Each map is { $varName -> single node } for one iteration.
     */
    private List<Map<String, List<Node>>> evaluateForClause(XQueryParser.ForClauseContext ctx) {
        List<Map<String, List<Node>>> currentBindings = new ArrayList<>();
        // Start with a single empty binding
        currentBindings.add(new HashMap<>());
    
        // for each "var in xquery"
        int nPairs = ctx.var().size(); // number of ($var in xquery) pairs
        for (int i = 0; i < nPairs; i++) {
            String varName = ctx.var(i).getText();
            List<Map<String, List<Node>>> newBindings = new ArrayList<>();
    
            // For each partial binding so far
            for (Map<String, List<Node>> binding : currentBindings) {
    
                // 1) Push this partial binding onto the env
                Map<String, List<Node>> savedScope = new HashMap<>(env.peek());
                // Make a new scope that merges the top scope with `binding`
                Map<String, List<Node>> mergedScope = new HashMap<>(savedScope);
                mergedScope.putAll(binding);
                env.push(mergedScope);
    
                // 2) Evaluate the xquery that might reference earlier variables
                List<Node> nodes = visit(ctx.xquery(i));
                // System.out.println("DEBUG forClause: var=" + varName + ", got " + nodes.size() + " nodes from " + ctx.xquery(i).getText());
                // 3) Pop the env back
                env.pop();
    
                // For each node in that result, create an extended binding
                for (Node n : nodes) {
                    Map<String, List<Node>> extended = new HashMap<>(binding);
                    extended.put(varName, Collections.singletonList(n));
                    newBindings.add(extended);
                }
            }
    
            currentBindings = newBindings;
        }
    
        return currentBindings;
    }

    /**
     * Evaluate a letClause -> a map of { varName -> list of nodes }.
     */
    private Map<String, List<Node>> evaluateLetClause(XQueryParser.LetClauseContext ctx) {
        Map<String, List<Node>> bindings = new HashMap<>();
        int count = ctx.var().size();
        for (int i = 0; i < count; i++) {
            String varName = ctx.var(i).getText();
            List<Node> val = visit(ctx.xquery(i));
            if (val == null) val = new ArrayList<>();
            bindings.put(varName, val);
        }
        return bindings;
    }

    // ---------------------------------------------------------
    //   Helpers for whereClause conditions
    // ---------------------------------------------------------

    /**
     * Evaluate any cond expression into a boolean.
     */
    private boolean evaluateCondition(XQueryParser.CondContext ctx) {
        if (ctx instanceof XQueryParser.XQueryValueEqualContext) {
            // xquery ('=' | 'eq') xquery
            XQueryParser.XQueryValueEqualContext c = (XQueryParser.XQueryValueEqualContext) ctx;
            List<Node> left = visit(c.xquery(0));
            List<Node> right = visit(c.xquery(1));
            if (left == null) left = new ArrayList<>();
            if (right == null) right = new ArrayList<>();
            return nodesValueEqual(left, right);

        } else if (ctx instanceof XQueryParser.XQueryIdentityEqualContext) {
            // xquery ('==' | 'is') xquery
            XQueryParser.XQueryIdentityEqualContext c = (XQueryParser.XQueryIdentityEqualContext) ctx;
            List<Node> left = visit(c.xquery(0));
            List<Node> right = visit(c.xquery(1));
            if (left == null) left = new ArrayList<>();
            if (right == null) right = new ArrayList<>();
            return nodesIdentityEqual(left, right);

        } else if (ctx instanceof XQueryParser.XQueryEmptyContext) {
            // empty(xquery)
            XQueryParser.XQueryEmptyContext c = (XQueryParser.XQueryEmptyContext) ctx;
            List<Node> check = visit(c.xquery());
            return (check == null || check.isEmpty());

        } else if (ctx instanceof XQueryParser.XQuerySomeContext) {
            // some $v in XQ, ... satisfies cond
            XQueryParser.XQuerySomeContext c = (XQueryParser.XQuerySomeContext) ctx;
            List<Map<String, List<Node>>> bindings = new ArrayList<>();
            bindings.add(new HashMap<>());

            // Build up all possible bindings from 'some' variables
            int nVars = c.var().size();
            for (int i = 0; i < nVars; i++) {
                String vName = c.var(i).getText();
                List<Node> possibleVals = visit(c.xquery(i));
                if (possibleVals == null) possibleVals = new ArrayList<>();

                List<Map<String, List<Node>>> newBindings = new ArrayList<>();
                for (Map<String, List<Node>> b : bindings) {
                    for (Node val : possibleVals) {
                        Map<String, List<Node>> extended = new HashMap<>(b);
                        extended.put(vName, Collections.singletonList(val));
                        newBindings.add(extended);
                    }
                }
                bindings = newBindings;
            }
            // Condition is satisfied if at least one binding yields true
            for (Map<String, List<Node>> b : bindings) {
                // Temporarily push a new scope with these bindings
                Map<String, List<Node>> saved = new HashMap<>(env.peek());
                Map<String, List<Node>> newScope = new HashMap<>(saved);
                newScope.putAll(b);
                env.push(newScope);
                try {
                    boolean sat = evaluateCondition(c.cond());
                    if (sat) {
                        return true;
                    }
                } finally {
                    env.pop();
                }
            }
            return false;

        } else if (ctx instanceof XQueryParser.XQueryCondParenthesesContext) {
            // (cond)
            return evaluateCondition(((XQueryParser.XQueryCondParenthesesContext) ctx).cond());

        } else if (ctx instanceof XQueryParser.XQueryCondAndContext) {
            // cond AND cond
            XQueryParser.XQueryCondAndContext c = (XQueryParser.XQueryCondAndContext) ctx;
            return evaluateCondition(c.cond(0)) && evaluateCondition(c.cond(1));

        } else if (ctx instanceof XQueryParser.XQueryCondOrContext) {
            // cond OR cond
            XQueryParser.XQueryCondOrContext c = (XQueryParser.XQueryCondOrContext) ctx;
            return evaluateCondition(c.cond(0)) || evaluateCondition(c.cond(1));

        } else if (ctx instanceof XQueryParser.XQueryCondNotContext) {
            // not cond
            XQueryParser.XQueryCondNotContext c = (XQueryParser.XQueryCondNotContext) ctx;
            return !evaluateCondition(c.cond());
        }

        // Default fallback
        return false;
    }

    /**
     * Value equality: check if each corresponding node's textContent matches.
     * (Very simplified notion of 'value equal')
     */
    private boolean nodesValueEqual(List<Node> left, List<Node> right) {
        // A common approach is: "some l in left, some r in right, l.textContent == r.textContent"
        // or you might want to compare them pairwise.  Adapt as needed.
        for (Node ln : left) {
            for (Node rn : right) {
                if (ln.getTextContent().equals(rn.getTextContent())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Identity equality: check if there is a pair of nodes that are the same DOM node.
     */
    private boolean nodesIdentityEqual(List<Node> left, List<Node> right) {
        for (Node ln : left) {
            for (Node rn : right) {
                // isSameNode() is a DOM method
                if (ln.isSameNode(rn)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ---------------------------------------------------------
    //    Utility to get descendants
    // ---------------------------------------------------------
    private void getDescendants(Node node, List<Node> result) {
        Node child = node.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                result.add(child);
                getDescendants(child, result);
            }
            child = child.getNextSibling();
        }
    }

    // ---------------------------------------------------------
    //    Default visitor aggregator overrides
    // ---------------------------------------------------------
    @Override
    protected List<Node> defaultResult() {
        return new ArrayList<>();
    }

    @Override
    protected List<Node> aggregateResult(List<Node> aggregate, List<Node> nextResult) {
        if (aggregate == null) {
            aggregate = new ArrayList<>();
        }
        if (nextResult != null) {
            aggregate.addAll(nextResult);
        }
        return aggregate;
    }
}
