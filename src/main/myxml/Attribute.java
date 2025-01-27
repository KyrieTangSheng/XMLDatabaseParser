package src.main.myxml;

/**
 * Represents an attribute of an XML element, e.g. name="value".
 */
public class Attribute {
    private String name;
    private String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + "=\"" + value + "\"";
    }
}