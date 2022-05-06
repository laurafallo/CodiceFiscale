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
import java.util.ArrayList;

public class ReadPersoneXML {
	private static final String FILENAME = "inputPersone.xml";
	private ArrayList<Persona> persone = new ArrayList<>();



	public ArrayList<Persona> readPersone(){
	
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

			System.out.println("Scanning Persone");

			NodeList list = doc.getElementsByTagName("persona");

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					// get staff's attribute
					// String id = element.getAttribute("id");

					// get text
					String nome = element.getElementsByTagName("nome").item(0).getTextContent();
					String cognome = element.getElementsByTagName("cognome").item(0).getTextContent();
					String sesso = element.getElementsByTagName("sesso").item(0).getTextContent();
					String comune = element.getElementsByTagName("comune_nascita").item(0).getTextContent();
					String data = element.getElementsByTagName("data_nascita").item(0).getTextContent();

					persone.add(new Persona(nome, cognome, data, sesso, comune));
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return persone;
	}
}