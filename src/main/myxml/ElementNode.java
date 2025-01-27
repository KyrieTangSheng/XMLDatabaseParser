package src.main.myxml;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents an XML element, like <tagname ...attributes...> ... </tagname>.
 */
public class ElementNode implements Node{
    private String name;
    private String namespaceURI;               // e.g. "http://www.tei-c.org/ns/1.0" if applicable
    private List<Attribute> attributes;
    private List<Node> children;               // children can be ElementNode, TextNode, etc.

    // Optional: keep a reference to the parent node if needed
    private ElementNode parent;

    public ElementNode(String name) {
        this.name = name;
        this.namespaceURI = "";
        this.attributes = new ArrayList<>();
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);

        // If it's another ElementNode or something that can have a parent, set parent
        if (child instanceof ElementNode) {
            ((ElementNode) child).setParent(this);
        }
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(ElementNode parent) {
        this.parent = parent;
    }
    
    public String getNodeType(){
        return "elementNode";
    }

    @Override
    public String toString() {
        return "ElementNode{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", children=" + children +
                '}';
    }
}