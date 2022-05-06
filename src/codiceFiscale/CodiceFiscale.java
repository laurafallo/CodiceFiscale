package codiceFiscale;

import java.util.HashMap;

public class CodiceFiscale {
	private HashMap<String,String> comuni;
	private String CF;

	public CodiceFiscale(HashMap<String,String> comuni){
		this.comuni = comuni;
	}

	//metodo che prende le consonanti in nome 
	private void consonantsName(String[] nome) {
		int j = 0;
		String codice = "";
		int numeroConsonanti = 0;
		for(int i = 0; i< nome.length; i++) {
			if (!(nome[i].equals("A") || nome[i].equals("E") || nome[i].equals("I") || nome[i].equals("O") || nome[i].equals("U"))){
				numeroConsonanti ++;
			}
		}

		for(int i = 0; i< nome.length; i++) {
			if (!(nome[i].equals("A") || nome[i].equals("E") || nome[i].equals("I") || nome[i].equals("O") || nome[i].equals("U"))){
				if(!(numeroConsonanti>3 && j==1)){
					codice += nome[i];
					if(j == 3 || j==4) break;
				}
				j++;
			}
		}
				
		while (j < 3) {
			for(int i = 0; i< nome.length; i++) {
				if (nome[i].equals("A") || nome[i].equals("E") || nome[i].equals("I") || nome[i].equals("O") || nome[i].equals("U")){
					codice += nome[i];
					j++;
					if(j == 3) break;
				}
			}
		}  



		// System.out.println("Name: " + codice);
		CF += codice;
	}
	
	
	//metodo che prende le consonanti in cognome
	private void consonantsSurname(String[] cognome) {
		int j = 0;
		String codice = "";
		
		for(int i = 0; i< cognome.length; i++) {
			if (!(cognome[i].equals("A") || cognome[i].equals("E") || cognome[i].equals("I") || cognome[i].equals("O") || cognome[i].equals("U"))){
				codice += cognome[i];
				j++;
				if(j == 3) break;
			}
		}
				
		while (j < 3) {
			for(int i = 0; i< cognome.length; i++) {
				if (cognome[i].equals("A") || cognome[i].equals("E") || cognome[i].equals("I") || cognome[i].equals("O") || cognome[i].equals("U")){
					codice += cognome[i];
					j++;
					if(j == 3) break;
				}
			}
		} 

		// System.out.println("Surname: " + codice);
		CF += codice;
	}
	
	//metodo che prende le ultime due cifre dell'anno
	private void year(String anno) {
		String annoVero = anno.substring(2, 4);
		CF += annoVero;
	}
	
	private void month (String mese) {
		char letteraMese = '0';
		String meseVero = mese.substring(5, 7);
		if(meseVero.equals("01"))
			letteraMese = 'A';
		if(meseVero.equals("02"))
			letteraMese = 'B';
		if(meseVero.equals("03"))
			letteraMese = 'C';
		if(meseVero.equals("04"))
			letteraMese = 'D';
		if(meseVero.equals("05"))
			letteraMese = 'E';
		if(meseVero.equals("06"))
			letteraMese = 'H';
		if(meseVero.equals("07"))
			letteraMese = 'L';
		if(meseVero.equals("08"))
			letteraMese = 'M';
		if(meseVero.equals("09"))
			letteraMese = 'P';
		if(meseVero.equals("10"))
			letteraMese = 'R';
		if(meseVero.equals("11"))
			letteraMese = 'S';
		if(meseVero.equals("12"))
			letteraMese = 'T';
		
		CF += letteraMese;
	}
	
	public void day(String giorno, String genere) {
		String giornoVero;
		if(genere.equals("M")) giornoVero = giorno.substring(8, 10);
		else giornoVero = Integer.toString((Integer.parseInt(giorno.substring(8, 10))+40));
		CF += giornoVero;
	}
	
	private void comune(String comune) {
		CF += comuni.get(comune);
	}

	private void letteraControllo(){
		/*Carattere di controllo*/
		int sommaPari=0;
		for (int i=1;i<=13;i+=2) {
			switch (CF.charAt(i)) {
				case '0': {sommaPari+=0;break;}
				case '1': {sommaPari+=1;break;}
				case '2': {sommaPari+=2;break;}
				case '3': {sommaPari+=3;break;}
				case '4': {sommaPari+=4;break;}
				case '5': {sommaPari+=5;break;}
				case '6': {sommaPari+=6;break;}
				case '7': {sommaPari+=7;break;}
				case '8': {sommaPari+=8;break;}
				case '9': {sommaPari+=9;break;}
				case 'A': {sommaPari+=0;break;}
				case 'B': {sommaPari+=1;break;}
				case 'C': {sommaPari+=2;break;}
				case 'D': {sommaPari+=3;break;}
				case 'E': {sommaPari+=4;break;}
				case 'F': {sommaPari+=5;break;}
				case 'G': {sommaPari+=6;break;}
				case 'H': {sommaPari+=7;break;}
				case 'I': {sommaPari+=8;break;}
				case 'J': {sommaPari+=9;break;}
				case 'K': {sommaPari+=10;break;}
				case 'L': {sommaPari+=11;break;}
				case 'M': {sommaPari+=12;break;}
				case 'N': {sommaPari+=13;break;}
				case 'O': {sommaPari+=14;break;}
				case 'P': {sommaPari+=15;break;}
				case 'Q': {sommaPari+=16;break;}
				case 'R': {sommaPari+=17;break;}
				case 'S': {sommaPari+=18;break;}
				case 'T': {sommaPari+=19;break;}
				case 'U': {sommaPari+=20;break;}
				case 'V': {sommaPari+=21;break;}
				case 'W': {sommaPari+=22;break;}
				case 'X': {sommaPari+=23;break;}
				case 'Y': {sommaPari+=24;break;}
				case 'Z': {sommaPari+=25;break;}
			}
		}
		int sommaDispari=0;
		for (int i=0;i<=14;i+=2) {
			switch (CF.charAt(i)) {
				case '0': {sommaDispari+=1;break;}
				case '1': {sommaDispari+=0;break;}
				case '2': {sommaDispari+=5;break;}
				case '3': {sommaDispari+=7;break;}
				case '4': {sommaDispari+=9;break;}
				case '5': {sommaDispari+=13;break;}
				case '6': {sommaDispari+=15;break;}
				case '7': {sommaDispari+=17;break;}
				case '8': {sommaDispari+=19;break;}
				case '9': {sommaDispari+=21;break;}
				case 'A': {sommaDispari+=1;break;}
				case 'B': {sommaDispari+=0;break;}
				case 'C': {sommaDispari+=5;break;}
				case 'D': {sommaDispari+=7;break;}
				case 'E': {sommaDispari+=9;break;}
				case 'F': {sommaDispari+=13;break;}
				case 'G': {sommaDispari+=15;break;}
				case 'H': {sommaDispari+=17;break;}
				case 'I': {sommaDispari+=19;break;}
				case 'J': {sommaDispari+=21;break;}
				case 'K': {sommaDispari+=2;break;}
				case 'L': {sommaDispari+=4;break;}
				case 'M': {sommaDispari+=18;break;}
				case 'N': {sommaDispari+=20;break;}
				case 'O': {sommaDispari+=11;break;}
				case 'P': {sommaDispari+=3;break;}
				case 'Q': {sommaDispari+=6;break;}
				case 'R': {sommaDispari+=8;break;}
				case 'S': {sommaDispari+=12;break;}
				case 'T': {sommaDispari+=14;break;}
				case 'U': {sommaDispari+=16;break;}
				case 'V': {sommaDispari+=10;break;}
				case 'W': {sommaDispari+=22;break;}
				case 'X': {sommaDispari+=25;break;}
				case 'Y': {sommaDispari+=24;break;}
				case 'Z': {sommaDispari+=23;break;}
			}
		}
		int interoControllo = (sommaPari+sommaDispari)%26;
		String carattereControllo="";
		switch (interoControllo) {
			case 0:{carattereControllo="A";break;}
			case 1:{carattereControllo="B";break;}
			case 2:{carattereControllo="C";break;}
			case 3:{carattereControllo="D";break;}
			case 4:{carattereControllo="E";break;}
			case 5:{carattereControllo="F";break;}
			case 6:{carattereControllo="G";break;}
			case 7:{carattereControllo="H";break;}
			case 8:{carattereControllo="I";break;}
			case 9:{carattereControllo="J";break;}
			case 10:{carattereControllo="K";break;}
			case 11:{carattereControllo="L";break;}
			case 12:{carattereControllo="M";break;}
			case 13:{carattereControllo="N";break;}
			case 14:{carattereControllo="O";break;}
			case 15:{carattereControllo="P";break;}
			case 16:{carattereControllo="Q";break;}
			case 17:{carattereControllo="R";break;}
			case 18:{carattereControllo="S";break;}
			case 19:{carattereControllo="T";break;}
			case 20:{carattereControllo="U";break;}
			case 21:{carattereControllo="V";break;}
			case 22:{carattereControllo="W";break;}
			case 23:{carattereControllo="X";break;}
			case 24:{carattereControllo="Y";break;}
			case 25:{carattereControllo="Z";break;}
		}
		CF += carattereControllo;
	}

	public String codice(Persona persona){
		CF = "";
		consonantsSurname(persona.getCognome().split("", 0));
		consonantsName(persona.getNome().split("", 0));
		year(persona.getData());
		month(persona.getData());
		day(persona.getData(), persona.getSesso());
		comune(persona.getComune());
		letteraControllo();
		
		// System.out.println(CF);
		return CF;
	}
}
