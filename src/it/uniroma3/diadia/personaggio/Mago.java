package it.uniroma3.diadia.personaggio;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_RIFIUTO = "Mi spiace, non accetto doni dagli sconosciuti.";
	private static final String MESSAGGIO_GRAZIE = "questo attrezzo non mi serve, ma gli posso fare un incantesimo per renderlo più leggero";
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public void riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(!this.haSalutato()) {
		partita.getIO().mostraMessaggio(MESSAGGIO_RIFIUTO);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		}
		else {
			partita.getIO().mostraMessaggio(MESSAGGIO_GRAZIE);
			partita.getGiocatore().getBorsa().addAttrezzo(alleggerisciAttrezzo(attrezzo));
			
		}
	}
	
	private Attrezzo alleggerisciAttrezzo(Attrezzo attrezzo) {
		int pesoDimezzato= attrezzo.getPeso()/2;
		Attrezzo attrezzoLeggero= new Attrezzo(attrezzo.getNome(), pesoDimezzato);
		return attrezzoLeggero;
	}
}