package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.direzioni.Direzione;
import it.uniroma3.diadia.personaggio.AbstractPersonaggio;
import it.uniroma3.diadia.personaggio.Cane;
import it.uniroma3.diadia.personaggio.Mago;
import it.uniroma3.diadia.personaggio.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche:";

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie */
	private static final String STANZE_BUIE_MARKER = "Stanze Buie:";

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate */
	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/* prefisso della riga contenente le specifiche dei maghi*/
	private static final String MAGHI_MARKER = "Maghi:";

	/* prefisso della riga contenente le specifiche delle streghe*/
	private static final String STREGHE_MARKER = "Streghe:";

	/* prefisso della riga contenente le specifiche dei cani*/
	private static final String CANI_MARKER = "Cani:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11
		Maghi: Merlino bacchetta 1 N10, Voldemort serpente 3 N11
		Descrizione Merlino: sono un mago gentile
		Descrizione Voldemort: io non ho il naso
		Streghe: Hermione biblioteca
		Descrizione Hermione: sono la strega più brillante della mia età
		Cani: fuffi osso chiave 3 n10, snoopy ciotola libro 1 n11
		Descrizione fuffi: bau bau bau
		Descrizione snoopy: era una notte buia e tempestosa
	 */
	private LineNumberReader reader;

	//	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private LabirintoBuilder builder= Labirinto.newBuilder();

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		//		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	//	public CaricatoreLabirinto(Reader reader) throws FileNotFoundException {
	//		this.nome2stanza = new HashMap<String,Stanza>();
	//		this.reader = new LineNumberReader(reader);
	//		this.builder= Labirinto.newBuilder();
	//	}

	public LabirintoBuilder carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiInizialeEvincente();
			//builder.stampaStanze();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaStreghe();
			this.leggiECollocaMaghi();
			this.leggiECollocaCani();
			this.leggiEImpostaUscite();
			return builder.getLabirinto();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			//Stanza stanza = new Stanza(nomeStanza);
//			System.out.println(nomeStanza);
			this.builder.addStanza(nomeStanza);
			//this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			//			Stanza stanza = new StanzaMagica(nomeStanza);
			this.builder.addStanzaMagica(nomeStanza, 3);
			//			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String specificheStanzaBloccata= this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificaStanzaBloccata: separaStringheAlleVirgole(specificheStanzaBloccata)) {
			String nomeStanza = null;
			String direzione= null;
			Direzione direzioneBloccata = null;
			String attrezzoNecessario = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)){
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata."));
				direzione=scannerLinea.next();
				for (Direzione l : Direzione.values()) {
					if(l.name().equals(direzione)) direzioneBloccata=l;
				}
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo che sblocca la direzione."));
				attrezzoNecessario = scannerLinea.next();
			}
			this.builder.addStanzaBloccata(nomeStanza, attrezzoNecessario, direzioneBloccata);
			//			Stanza stanzaBloccata= new StanzaBloccata(nomeStanza, attrezzoNecessario, direzioneBloccata);
			//			this.nome2stanza.put(nomeStanza, stanzaBloccata);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanzaBuie= this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanzaBuia: separaStringheAlleVirgole(specificheStanzaBuie)) {
			String nomeStanza = null;
			String attrezzoNecessario = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)){
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo necessario."));
				attrezzoNecessario = scannerLinea.next();
			}
			this.builder.addStanzaBuia(nomeStanza, attrezzoNecessario);
			//			Stanza stanzaBuia= new StanzaBuia(nomeStanza, attrezzoNecessario);
			//			this.nome2stanza.put(nomeStanza, stanzaBuia);
		}
	}

	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi= this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for( String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago= null;
			String nomeAttrezzo = null;
			String pesoAttrezzo= null;
			String nomeStanza= null;
			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo tenuto dal mago."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove si trova il mago"));
				nomeStanza = scannerLinea.next();
			}	
			int peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo delMago= new Attrezzo(nomeAttrezzo, peso);
			String descrizione= this.leggiRigaCheCominciaPer("Descrizione "+ nomeMago + ":");
			AbstractPersonaggio mago= new Mago(nomeMago, descrizione, delMago);
//			System.out.println(nomeStanza);
			builder.getStanze().get(nomeStanza).addPersonaggio(mago);
			//			this.nome2stanza.get(nomeStanza).addPersonaggio(mago);
		}
	}

	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheCani= this.leggiRigaCheCominciaPer(CANI_MARKER);

		for( String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String cibo = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il cibo preferito dal cane."));
				cibo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo tenuto dal cane."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove si trova il cane"));
				nomeStanza = scannerLinea.next();
			}	
			int peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo delCane= new Attrezzo(nomeAttrezzo, peso);
			String descrizione= this.leggiRigaCheCominciaPer("Descrizione "+ nomeCane + ":");
			AbstractPersonaggio cane= new Cane(nomeCane, descrizione, cibo, delCane);
			builder.getStanze().get(nomeStanza).addPersonaggio(cane);
			//			this.nome2stanza.get(nomeStanza).addPersonaggio(cane);
		}
	}

	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe= this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for( String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega= null;
			String nomeStanza= null;
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove si trova la strega"));
				nomeStanza = scannerLinea.next();
			}	
			String descrizione= this.leggiRigaCheCominciaPer("Descrizione "+ nomeStrega + ":");
			AbstractPersonaggio strega= new Strega(nomeStrega, descrizione);
//			System.out.println(nomeStanza);
			builder.getStanze().get(nomeStanza).addPersonaggio(strega);
			//			this.nome2stanza.get(nomeStanza).addPersonaggio(strega);
		}
	}


	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			int peso= 0;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				peso = Integer.parseInt(pesoAttrezzo);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
			//			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}


	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext())
			result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		//		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		//		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		this.builder.addStanzaVincente(nomeStanzaVincente);
		//		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		//		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}



	//	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
	//		int peso;
	//		try {
	//			peso = Integer.parseInt(pesoAttrezzo);
	//			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
	//			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
	//			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
	//		}
	//		catch (NumberFormatException e) {
	//			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
	//		}
	//	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscite : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza= null;
			String dir=null;
			String stanzaDestinazione= null; 
			try (Scanner scannerDiLinea = new Scanner(specificaUscite)) {		
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					dir = scannerDiLinea.next();
					Direzione that=null;
					for (Direzione l : Direzione.values()) {
						if(l.name().equals(dir)) 
							that=l;
					}
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					stanzaDestinazione = scannerDiLinea.next();
					builder.addAdiacenza(stanzaPartenza, that, stanzaDestinazione);
//					System.out.println(stanzaPartenza);
					impostaUscita(stanzaPartenza, that, stanzaDestinazione);
				}
			}
		}
	}

		private String msgTerminazionePrecoce(String msg) {
			return "Terminazione precoce del file prima di leggere "+msg;
		}

		private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
			check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
//			System.out.println(nomeA);
			check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
			Stanza partenzaDa = this.builder.getStanze().get(stanzaDa);
			Stanza arrivoA = this.builder.getStanze().get(nomeA);
			partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		}


		final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
			if (!condizioneCheDeveEsseraVera)
				throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
		}

		public Stanza getStanzaIniziale() {
			return this.stanzaIniziale;
		}

		public Stanza getStanzaVincente() {
			return this.stanzaVincente;
		}
	}
