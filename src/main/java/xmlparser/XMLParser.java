package xmlparser;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XMLParser {
	
	/**
	 * 
	 * Read XML file and parse it into a parseTree using DOM API
	 * @return XML Document Object
	 */
	public static Document parse(String fileName) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			// Disable DTD validation
			factory.setValidating(false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(fileName);
		} catch (Exception e) {
			System.err.println("Error parsing XML file: " + fileName);
			e.printStackTrace();
			return null;
		}
	}
}