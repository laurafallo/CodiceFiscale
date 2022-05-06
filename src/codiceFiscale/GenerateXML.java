package codiceFiscale;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerateXML {
    private static final String xmlFilePath = "codiciPersone.xml";
 
    public void genXML(ArrayList<Persona> listPersone, ArrayList<String> listInvalidi, ArrayList<String> listSpaiati){
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
    
            Document document = documentBuilder.newDocument();
    
            // root element
            Element root = document.createElement("output");
            document.appendChild(root);
    
            Element persone = document.createElement("persone");
            root.appendChild(persone);
            persone.setAttribute("numero", Integer.toString(listPersone.size()));
    
            //you can also use staff.setAttribute("id", "1") for this
            for(int i=0; i<listPersone.size(); i++){
                Element persona = document.createElement("persona");
                persone.appendChild(persona);
                persona.setAttribute("id", Integer.toString(i));
                
                Element nome = document.createElement("nome");
                nome.appendChild(document.createTextNode(listPersone.get(i).getNome()));
                persona.appendChild(nome);

                Element cognome = document.createElement("cognome");
                cognome.appendChild(document.createTextNode(listPersone.get(i).getCognome()));
                persona.appendChild(cognome);

                Element sesso = document.createElement("sesso");
                sesso.appendChild(document.createTextNode(listPersone.get(i).getSesso()));
                persona.appendChild(sesso);

                Element comune_nascita = document.createElement("comune_nascita");
                comune_nascita.appendChild(document.createTextNode(listPersone.get(i).getComune()));
                persona.appendChild(comune_nascita);

                Element data_nascita = document.createElement("data_nascita");
                data_nascita.appendChild(document.createTextNode(listPersone.get(i).getData()));
                persona.appendChild(data_nascita);
                
                if(listPersone.get(i).isValid()){
                    Element codice_fiscale = document.createElement("codice_fiscale");
                    codice_fiscale.appendChild(document.createTextNode(listPersone.get(i).getCF()));
                    persona.appendChild(codice_fiscale);
                }else{
                    Element codice_fiscale = document.createElement("codice_fiscale");
                    codice_fiscale.appendChild(document.createTextNode("ASSENTE"));
                    persona.appendChild(codice_fiscale);
                }
            }
            
            Element codici = document.createElement("codici");
            root.appendChild(codici);

            Element invalidi = document.createElement("invalidi");
            codici.appendChild(invalidi);
            invalidi.setAttribute("numero", Integer.toString(listInvalidi.size()));

            for(int i=0; i<listInvalidi.size(); i++){
                Element codice = document.createElement("codice");
                codice.appendChild(document.createTextNode(listInvalidi.get(i)));
                invalidi.appendChild(codice);
            }

            Element spaiati = document.createElement("spaiati");
            codici.appendChild(spaiati);
            spaiati.setAttribute("numero", Integer.toString(listSpaiati.size()));

            for(int i=0; i<listSpaiati.size(); i++){
                Element codice = document.createElement("codice");
                codice.appendChild(document.createTextNode(listSpaiati.get(i)));
                spaiati.appendChild(codice);
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
    
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
    
            transformer.transform(domSource, streamResult);
    
            System.out.println("Done creating XML File");
    
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
