package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comando.AbstractComando;
import it.uniroma3.diadia.comando.FabbricaDiComandi;
import it.uniroma3.diadia.comando.FabbricaDiComandiRiflessiva;

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
	
//	static final private Proprietà prop= new Proprietà();
	
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String MESSAGGIO_ARRIVEDERCI = ""+
			"\nQuesta partita è finita...\n" +
			"Però ci sono ancora tante cose da scoprire!\n"+
			"torna a giocare spesso, che piano piano molte cose cambieranno\n" +
			"spero tu ti sia divertito a giocare\n"+
			"torna presto!\n";


	private Partita partita;
	public IO Io;

	public DiaDia(IO that) {
		this.Io= that;
		this.partita = new Partita(Io);
	}

	public DiaDia(IO that, Labirinto labirinto) {
		this.Io=that;
		this.partita= new Partita(Io, labirinto);
	}

	public void gioca() {
		String istruzione; 
		Io.mostraMessaggio("Benvenuto giocatore, come ti chiami?");
		this.partita.getGiocatore().setNome(Io.leggiRiga());
		Io.mostraMessaggio("Bene "+ this.partita.getGiocatore().GetNome());
		Io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do				
			istruzione = Io.leggiRiga();
		while (!processaIstruzione(istruzione));
		Io.mostraMessaggio(MESSAGGIO_ARRIVEDERCI);
	}
	public Partita getPartita() {
		return this.partita;
	}
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(this.Io.getScanner());
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		partita.setIsFinita();
		if(istruzione.equals("fine")) return true;
		return this.partita.getIsFinita();
	}

	public static void main(String[] argc) throws Exception {
		Scanner scannerDiLinee = new Scanner(System.in);
		IO Io= new IOConsole(scannerDiLinee);
		Labirinto labirinto = new CaricatoreLabirinto("labirinto.txt").carica();
		DiaDia gioco = new DiaDia(Io, labirinto);
		gioco.gioca();
		scannerDiLinee.close();
	}
}