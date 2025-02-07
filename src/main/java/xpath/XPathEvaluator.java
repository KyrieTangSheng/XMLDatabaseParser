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

    @Override
    public LinkedList<Node> visitXpath(XPathParser.XpathContext ctx) {
        if (ctx.absolutePath() != null) {
            return visit(ctx.absolutePath());
        } else {
            return visit(ctx.relativePath());
        }
    }

    /**
     * Handles expressions of the form: 
     *    doc("fileName")/relativePath
     */
    @Override
    public LinkedList<Node> visitAbsoluteSlash(XPathParser.AbsoluteSlashContext ctx) {
        String fileName = ctx.fileName().getText().replace("\"", "");
        Document doc = XMLParser.parse(fileName);
        Node root = doc.getDocumentElement(); 

        this.currentContext.clear();
        this.currentContext.add(root);
        return visit(ctx.relativePath());
    }

    /**
     * Handles expressions of the form:
     *    doc("fileName")//relativePath
     */
    @Override
    public LinkedList<Node> visitAbsoluteDoubleSlash(XPathParser.AbsoluteDoubleSlashContext ctx) {
        String fileName = ctx.fileName().getText().replace("\"", "");
        Document doc = XMLParser.parse(fileName);
        Node root = doc.getDocumentElement();

        this.currentContext.clear();
        this.currentContext.add(root);
        this.currentContext.addAll(getDescendants(root));
        return visit(ctx.relativePath());
    }

    /**
     * Processes a tag name match (e.g., PERSONA).
     */
    @Override
    public LinkedList<Node> visitTagNameMatch(XPathParser.TagNameMatchContext ctx) {
        String tagName = ctx.tagName().getText();
        LinkedList<Node> result = new LinkedList<>();
        
        for (Node node : currentContext) {
            if (node.getNodeName().equals(tagName)) {
                result.add(node);
            }
        }
        
        if (result.isEmpty()) {
            for (Node node : currentContext) {
                LinkedList<Node> children = getChildren(node);
                for (Node child : children) {
                    if (child.getNodeName().equals(tagName)) {
                        result.add(child);
                    }
                }
            }
        }
        
        this.currentContext = result;
        return this.currentContext;
    }

    @Override
    public LinkedList<Node> visitAllChildren(XPathParser.AllChildrenContext ctx) {
        LinkedList<Node> result = new LinkedList<>();
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
        LinkedList<Node> result = new LinkedList<>();
        for (Node node : currentContext) {
            Node parent = node.getParentNode();
            if (parent != null && parent.getNodeType() == Node.ELEMENT_NODE) {
                result.add(parent);
            }
        }
        currentContext = getUnique(result);
        return currentContext;
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
        String attributeName = ctx.attributeName().getText();
        LinkedList<Node> result = new LinkedList<>();
        
        for (Node node : currentContext) {
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                if (element.hasAttribute(attributeName)) {
                    org.w3c.dom.Attr attr = element.getAttributeNode(attributeName);
                    result.add(attr);
                }
            }
        }
        
        currentContext = result;
        return currentContext;
    }

    @Override
    public LinkedList<Node> visitRpGrouping(XPathParser.RpGroupingContext ctx) {
        return visit(ctx.relativePath());
    }
    
    @Override
    public LinkedList<Node> visitRpSlash(XPathParser.RpSlashContext ctx) {
        visit(ctx.relativePath(0));
        visit(ctx.relativePath(1));
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
        visit(ctx.relativePath());
        
        LinkedList<Node> result = new LinkedList<>();
        for (Node node : currentContext) {
            LinkedList<Node> temp = new LinkedList<>();
            temp.add(node);
            currentContext = temp;
            
            if (!visit(ctx.filter()).isEmpty()) {
                result.add(node);
            }
        }
        
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
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        visit(ctx.relativePath());
        
        boolean result = !currentContext.isEmpty();
        
        currentContext = originalContext;
        
        return result ? currentContext : new LinkedList<>();
    }

    // f → rp1 = rp2 | rp1 eq rp2
    // Returns true if any node from rp1 is value-equal to any node from rp2
    @Override
    public LinkedList<Node> visitEqualityFilter(XPathParser.EqualityFilterContext ctx) {
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        visit(ctx.relativePath(0));
        LinkedList<Node> leftResults = new LinkedList<>(currentContext);
        
        currentContext = originalContext;
        visit(ctx.relativePath(1));
        LinkedList<Node> rightResults = currentContext;
        
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
        
        currentContext = originalContext;
        return isEqual ? currentContext : new LinkedList<>();
    }

    // f → rp1 == rp2 | rp1 is rp2
    // Returns true if any node from rp1 is identical to any node from rp2
    @Override
    public LinkedList<Node> visitIdentityFilter(XPathParser.IdentityFilterContext ctx) {
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        visit(ctx.relativePath(0));
        LinkedList<Node> leftResults = new LinkedList<>(currentContext);
        
        currentContext = originalContext;
        visit(ctx.relativePath(1));
        LinkedList<Node> rightResults = currentContext;
        
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
        
        currentContext = originalContext;
        return isIdentical ? currentContext : new LinkedList<>();
    }

    // f → rp = StringConstant
    // Returns true if any node's text content equals the string constant
    @Override
    public LinkedList<Node> visitStringFilter(XPathParser.StringFilterContext ctx) {
        LinkedList<Node> originalContext = new LinkedList<>(currentContext);
        
        visit(ctx.relativePath());
        
        String constant = ctx.StringConstant().getText().replaceAll("\"", "");
        
        boolean matches = false;
        for (Node node : currentContext) {
            if (node.getTextContent().equals(constant)) {
                matches = true;
                break;
            }
        }
        
        currentContext = originalContext;
        return matches ? currentContext : new LinkedList<>();
    }


    // f → (f)
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
            if (seenNodes.add(node)) {
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
        if (n1.getNodeType() != n2.getNodeType()) return false;
        
        switch (n1.getNodeType()) {
            case Node.TEXT_NODE:
                return n1.getNodeValue().equals(n2.getNodeValue());
            
            case Node.ELEMENT_NODE:
                if (!n1.getNodeName().equals(n2.getNodeName())) return false;
                
                NodeList children1 = n1.getChildNodes();
                NodeList children2 = n2.getChildNodes();
                if (children1.getLength() != children2.getLength()) return false;
                
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
