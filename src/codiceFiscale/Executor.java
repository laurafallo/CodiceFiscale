package codiceFiscale;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLStreamException;

public class Executor {
    public static void main(String[] args) throws XMLStreamException {
        //Parse the XML
		ReadPersoneXML readPersoneXml = new ReadPersoneXML();
		ReadComuniXML readComuniXml = new ReadComuniXML();
        ReadCFXML readCFXml = new ReadCFXML();
        GenerateXML generateXML = new GenerateXML();

		ArrayList<Persona> persone = readPersoneXml.readPersone();
        HashMap<String,String> comuni = readComuniXml.readComuni();
        ArrayList<String> CFs = readCFXml.readCFs();

        CodiceFiscale cf = new CodiceFiscale(comuni);

        ArrayList<String> spaiati = new ArrayList<>();
        ArrayList<String> invalidi = new ArrayList<>();

        for(int i = 0; i<persone.size(); i++){
            persone.get(i).setCF(cf.codice(persone.get(i)));
            if(CFs.contains(persone.get(i).getCF())){
                persone.get(i).setValid(true);
            } else{
                persone.get(i).setValid(false);
                invalidi.add(persone.get(i).getCF());
            }
        }

        Boolean check = true;
        for(int i=0; i<CFs.size(); i++){
            for(int j=0; j<persone.size(); j++){
                if(CFs.get(i).equals(persone.get(j).getCF())){
                    check = false;
                    break;
                }
            }
            if(check){
                spaiati.add(CFs.get(i));
            }
            check = true;
        }

        generateXML.genXML(persone, invalidi, spaiati);
	}
}
