package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.partita = new Partita();
		this.io= io;
	}

	public void gioca() {
		String istruzione; 

		io.MostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando esegui = new Comando(istruzione);
/*altro NULLPOINT problem -> essendo comando gia dichiarato precedentemente; il problema getnome*/
		if(esegui.getNome()==null) {
			io.MostraMessaggio("devi digitare un comando");
			return false;
		}
		if (esegui.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (esegui.getNome().equals("vai"))
			this.vai(esegui.getParametro());
		else if (esegui.getNome().equals("aiuto"))
			this.aiuto();
		else if (esegui.getNome().equals("prendi"))
			this.prendi(esegui.getParametro());
		else if (esegui.getNome().equals("posa"))
			this.posa(esegui.getParametro());
		else
			io.MostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.MostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}  

	// implementazioni dei comandi dell'utente:
	private void prendi(String nomeAttrezzo) {
		if(!this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			io.MostraMessaggio("Attrezzo "+nomeAttrezzo+" non presente");
			return;
		}
			Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
			io.MostraMessaggio("Attrezzo "+nomeAttrezzo+" preso!");
		}
	
	private void posa(String nomeAttrezzo) { //attrezzo  gia nella borsa del giocatore
		if(!this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			io.MostraMessaggio("Attrezzo "+nomeAttrezzo+" non presente nella BORSA");
			return;
		}
			Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
			io.MostraMessaggio("Attrezzo "+nomeAttrezzo+" posato!");
	}
	

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.MostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.MostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.MostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.Giocatore.getCfu();
			this.partita.Giocatore.setCfu(cfu--);
		}
		io.MostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.MostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole ioConsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		gioco.gioca();
	}
}
