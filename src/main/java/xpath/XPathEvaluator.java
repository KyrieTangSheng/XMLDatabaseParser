package xpath;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import xmlparser.XMLParser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class XPathEvaluator extends XPathBaseVisitor<LinkedList<Node>> {

    // This list holds the current set of nodes that the next step will operate on.
    private LinkedList<Node> currentContext;

    public XPathEvaluator() {
        this.currentContext = new LinkedList<>();
    }

    // ------------------
    // Entry Rule (optional)
    // ------------------
    @Override
    public LinkedList<Node> visitXpath(XPathParser.XpathContext ctx) {
        System.out.println("✅ visitXpath() called");
        if (ctx.absolutePath() != null) {
            System.out.println("Processing absolutePath...");
            return visit(ctx.absolutePath());
        } else {
            System.out.println("Processing relativePath...");
            return visit(ctx.relativePath());
        }
    }

    /**
     * Handles expressions of the form: 
     *    doc("fileName")/relativePath
     */
    @Override
    public LinkedList<Node> visitAbsoluteSlash(XPathParser.AbsoluteSlashContext ctx) {
        // Step 1: Load the XML document and get its root element.
        String fileName = ctx.fileName().getText().replace("\"", "");
        Document doc = XMLParser.parse(fileName);
        Node root = doc.getDocumentElement(); 

        // Set the current context to contain only the root.
        this.currentContext.clear();
        this.currentContext.add(root);

        // Step 2: Process the relative path on this context.
        return visit(ctx.relativePath());
    }

    /**
     * Handles expressions of the form:
     *    doc("fileName")//relativePath
     */
    @Override
    public LinkedList<Node> visitAbsoluteDoubleSlash(XPathParser.AbsoluteDoubleSlashContext ctx) {
        // Step 1: Load the XML document and get its root element.
        String fileName = ctx.fileName().getText().replace("\"", "");
        Document doc = XMLParser.parse(fileName);
        Node root = doc.getDocumentElement();

        // Set the current context to the root and all its descendant elements.
        this.currentContext.clear();
        this.currentContext.add(root);
        this.currentContext.addAll(getDescendants(root));

        // Step 2: Process the relative path on the updated context.
        return visit(ctx.relativePath());
    }

    /**
     * Processes a tag name match (e.g., PERSONA).
     */
    @Override
    public LinkedList<Node> visitTagNameMatch(XPathParser.TagNameMatchContext ctx) {
        String tagName = ctx.tagName().getText();
        LinkedList<Node> result = new LinkedList<>();
        // For each node in the current context, add its children that match.
        for (Node node : currentContext) {
            LinkedList<Node> children = getChildren(node);
            for(Node child : children){
                if(child.getNodeName().equals(tagName)){
                    result.add(child);
                }
            }
        }
        this.currentContext = result;
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitAllChildren(XPathParser.AllChildrenContext ctx) {
        LinkedList<Node> result = new LinkedList<>();
        // For each node in the current context, add its children that match.
        for (Node node : currentContext) {
            LinkedList<Node> children = getChildren(node);
            result.addAll(children);
        }
        this.currentContext = result;
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitSelf(XPathParser.SelfContext ctx){
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitParent(XPathParser.ParentContext ctx) {
        System.out.println("✅ visitParent() called!");
    
        LinkedList<Node> newContext = new LinkedList<>();
    
        // Traverse each node in the current context and collect their parent nodes
        for (Node node : currentContext) {
            Node parent = node.getParentNode();
            System.out.println("🔍 Checking parent of node: " + node.getNodeName());
    
            if (parent != null) {
                System.out.println("📌 Found parent: " + parent.getNodeName() + " (Type: " + parent.getNodeType() + ")");
    
                if (parent.getNodeType() == Node.ELEMENT_NODE) {
                    if (!newContext.contains(parent)) { // Ensure uniqueness
                        newContext.add(parent);
                        System.out.println("✅ Added parent: " + parent.getNodeName());
                    }
                } else {
                    System.out.println("❌ Skipping non-element parent: " + parent.getNodeName());
                }
            } else {
                System.out.println("❌ No parent found for node: " + node.getNodeName());
            }
        }
    
        System.out.println("🔍 Final Parent Nodes: " + newContext.size());
        
        // Update context
        this.currentContext = newContext;
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitTextFunc(XPathParser.TextFuncContext ctx) {
        LinkedList<Node> result = new LinkedList<>();
        for(Node node : currentContext){
            LinkedList<Node> children = getChildren(node);
            for(Node child: children){
                if(child.getNodeType() == Node.TEXT_NODE){
                    result.add(child);
                }
            }
        }
        this.currentContext = result;
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitAttribute(XPathParser.AttributeContext ctx) {
        LinkedList<Node> result = new LinkedList<>();
        String attName = ctx.getText();
        for(Node node : currentContext){
            if(node.hasAttributes()){
                Attr attribute = (Attr) node.getAttributes().getNamedItem(attName);
                if (attribute != null) {
                    result.add(attribute); // Add the attribute node itself
                }
            }
        }
        this.currentContext = result;
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitRpGrouping(XPathParser.RpGroupingContext ctx) {
        return visit(ctx.relativePath());
    }
    
    @Override
    public LinkedList<Node> visitRpSlash(XPathParser.RpSlashContext ctx) {
        visit(ctx.relativePath(0)); // Evaluate rp1 on current node set
        visit(ctx.relativePath(1)); // Evaluate rp2 on each node
        LinkedList<Node> result = getUnique(currentContext);
        this.currentContext = result;
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitRpDoubleSlash(XPathParser.RpDoubleSlashContext ctx){
        visit(ctx.relativePath(0)); 
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : currentContext) {
            temp.addAll(getDescendants(node));
        }
        this.currentContext = temp;
        visit(ctx.relativePath(1));
        this.currentContext = getUnique(this.currentContext);
        return this.currentContext;

    }

    @Override
    public LinkedList<Node> visitRpFilter(XPathParser.RpFilterContext ctx) {        
        // Evaluate relative path
        visit(ctx.relativePath());
        
        // For each node in current context, evaluate filter
        LinkedList<Node> result = new LinkedList<>();
        for (Node node : currentContext) {
            LinkedList<Node> temp = new LinkedList<>();
            temp.add(node);
            currentContext = temp;
            
            // If filter evaluates to true, add node to result
            if (!visit(ctx.filter()).isEmpty()) {
                result.add(node);
            }
        }
        
        // Restore original context and update with filtered results
        currentContext = result;
        return currentContext;
    }

    @Override
    public LinkedList<Node> visitRpConcat(XPathParser.RpConcatContext ctx){
        LinkedList<Node> temp = new LinkedList<>();
        LinkedList<Node> previousContext = this.currentContext;
        visit(ctx.relativePath(0));
        temp.addAll(this.currentContext); 
        this.currentContext = previousContext;
        visit(ctx.relativePath(1)); 
        this.currentContext.addAll(temp);
        return this.currentContext;
    }

    // f → rp
    // Returns true if relative path evaluates to non-empty result
    @Override
    public LinkedList<Node> visitRpInFilter(XPathParser.RpInFilterContext ctx) {
        // Save current context
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        // Evaluate relative path
        visit(ctx.relativePath());
        
        // Non-empty result means filter is true
        boolean result = !currentContext.isEmpty();
        
        // Restore context
        currentContext = originalContext;
        
        // Return original context if true, empty list if false
        return result ? currentContext : new LinkedList<>();
    }

    // f → rp1 = rp2 | rp1 eq rp2
    // Returns true if any node from rp1 is value-equal to any node from rp2
    @Override
    public LinkedList<Node> visitEqualityFilter(XPathParser.EqualityFilterContext ctx) {
        // Save original context
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        // Evaluate first path
        visit(ctx.relativePath(0));
        LinkedList<Node> leftResults = new LinkedList<>(currentContext);
        
        // Restore and evaluate second path
        currentContext = originalContext;
        visit(ctx.relativePath(1));
        LinkedList<Node> rightResults = currentContext;
        
        // Check if any pair of nodes are value-equal
        boolean isEqual = false;
        for (Node left : leftResults) {
            for (Node right : rightResults) {
                if (areNodesValueEqual(left, right)) {
                    isEqual = true;
                    break;
                }
            }
            if (isEqual) break;
        }
        
        // Restore original context
        currentContext = originalContext;
        return isEqual ? currentContext : new LinkedList<>();
    }

    // f → rp1 == rp2 | rp1 is rp2
    // Returns true if any node from rp1 is identical to any node from rp2
    @Override
    public LinkedList<Node> visitIdentityFilter(XPathParser.IdentityFilterContext ctx) {
        // Save original context
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        // Evaluate first path
        visit(ctx.relativePath(0));
        LinkedList<Node> leftResults = new LinkedList<>(currentContext);
        
        // Restore and evaluate second path
        currentContext = originalContext;
        visit(ctx.relativePath(1));
        LinkedList<Node> rightResults = currentContext;
        
        // Check if any pair of nodes are identical
        boolean isIdentical = false;
        for (Node left : leftResults) {
            for (Node right : rightResults) {
                if (left.isSameNode(right)) {
                    isIdentical = true;
                    break;
                }
            }
            if (isIdentical) break;
        }
        
        // Restore original context
        currentContext = originalContext;
        return isIdentical ? currentContext : new LinkedList<>();
    }

    // f → rp = StringConstant
    // Returns true if any node's text content equals the string constant
    @Override
    public LinkedList<Node> visitStringFilter(XPathParser.StringFilterContext ctx) {
        // Save original context
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        // Evaluate relative path
        visit(ctx.relativePath());
        
        // Get string constant (remove quotes)
        String constant = ctx.StringConstant().getText().replaceAll("\"", "");
        
        // Check if any node's text content matches the string constant
        boolean matches = false;
        for (Node node : currentContext) {
            if (node.getTextContent().equals(constant)) {
                matches = true;
                break;
            }
        }
        
        // Restore original context
        currentContext = originalContext;
        return matches ? currentContext : new LinkedList<>();
    }


    // f → (f)
    // Grouping filter expressions
    @Override
    public LinkedList<Node> visitFilterGrouping(XPathParser.FilterGroupingContext ctx) {
        return visit(ctx.filter());
    }

    // f → f1 and f2
    // Returns true if both filters are true
    @Override
    public LinkedList<Node> visitAndFilter(XPathParser.AndFilterContext ctx) {
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
    
        LinkedList<Node> leftResult = visit(ctx.filter(0));
        boolean leftTrue = !leftResult.isEmpty();
        
        currentContext = originalContext;
        LinkedList<Node> rightResult = visit(ctx.filter(1));
        boolean rightTrue = !rightResult.isEmpty();
        
        currentContext = originalContext;
        return (leftTrue && rightTrue) ? currentContext : new LinkedList<>();
    }

    // f → f1 or f2
    // Returns true if either filter is true
    @Override
    public LinkedList<Node> visitOrFilter(XPathParser.OrFilterContext ctx) {
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
    
        LinkedList<Node> leftResult = visit(ctx.filter(0));
        if (!leftResult.isEmpty()) {
            currentContext = originalContext;
            return currentContext;
        }
        
        currentContext = originalContext;
        LinkedList<Node> rightResult = visit(ctx.filter(1));
        
        currentContext = originalContext;
        return !rightResult.isEmpty() ? currentContext : new LinkedList<>();
    }

    // f → not f
    // Returns true if filter is false
    @Override
    public LinkedList<Node> visitNotFilter(XPathParser.NotFilterContext ctx) {
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        LinkedList<Node> result = visit(ctx.filter());
        
        currentContext = originalContext;
        return result.isEmpty() ? currentContext : new LinkedList<>();
    }

    // ----------------------------------------------------------------
    // Helper Methods
    // ----------------------------------------------------------------

    /**
     * Returns a LinkedList without duplicates 
     */
    private LinkedList<Node> getUnique(LinkedList<Node> nodeList){
        LinkedList<Node> uniqueList = new LinkedList<>();
        HashSet<Node> seenNodes = new HashSet<>();
        for (Node node : nodeList) {
            if (seenNodes.add(node)) { // HashSet ensures uniqueness
                uniqueList.add(node);
            }
        }
        return uniqueList;
    }

    /**
     * Returns all children of the nodes
     */

    private LinkedList<Node> getChildren(Node node) {
        LinkedList<Node> result = new LinkedList<>();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            result.add(child);
        }
        return result;
    }

    /**
     * Recursively collects all descendant element nodes (children, grandchildren, etc.).
     */
    private LinkedList<Node> getDescendants(Node node) {
        LinkedList<Node> result = new LinkedList<>();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                result.add(child);
                result.addAll(getDescendants(child));
            }
        }
        return result;
    }

    // Helper method for value equality
    private boolean areNodesValueEqual(Node n1, Node n2) {
        // Different node types can't be equal
        if (n1.getNodeType() != n2.getNodeType()) return false;
        
        switch (n1.getNodeType()) {
            case Node.TEXT_NODE:
                return n1.getNodeValue().equals(n2.getNodeValue());
            
            case Node.ELEMENT_NODE:
                // Check tag names
                if (!n1.getNodeName().equals(n2.getNodeName())) return false;
                
                // Check children count
                NodeList children1 = n1.getChildNodes();
                NodeList children2 = n2.getChildNodes();
                if (children1.getLength() != children2.getLength()) return false;
                
                // Recursively check children
                for (int i = 0; i < children1.getLength(); i++) {
                    if (!areNodesValueEqual(children1.item(i), children2.item(i))) {
                        return false;
                    }
                }
                return true;
            
            default:
                return n1.isEqualNode(n2);
        }
    }
}
