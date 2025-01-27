package src.main.myxml;

/**
 * Represents a text node within an element's content.
 */
public class TextNode implements Node {
    private Node parent;
    private String content;

    public TextNode(String content) {
        this.content = content;
        this.parent = null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextNode{" +
                "content='" + content + '\'' +
                '}';
    }

    public void setParent(Node n){
        this.parent = n;
    }

    public String getNodeType(){
        return "textNode";
    }

    public Node getParent(){
        return parent;
    }
}
