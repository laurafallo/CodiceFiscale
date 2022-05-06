package codiceFiscale;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ReadComuniXML {
	private static final String FILENAME = "comuni.xml";
	private HashMap<String,String> comuni = new HashMap<String,String>();




	public HashMap<String,String> readComuni(){
	
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));

			doc.getDocumentElement().normalize();

			System.out.println("Scanning Comuni");

			NodeList list = doc.getElementsByTagName("comune");

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					// get staff's attribute
					// String id = element.getAttribute("id");

					// get text
					String nome = element.getElementsByTagName("nome").item(0).getTextContent();
					String codice = element.getElementsByTagName("codice").item(0).getTextContent();

					comuni.put(nome, codice);
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return comuni;
	}
}