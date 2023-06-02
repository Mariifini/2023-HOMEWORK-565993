package it.uniroma3.diadia.personaggio;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	private String ciboPreferito;
	private boolean haMangiato;
	private Attrezzo attrezzo;
	
	private static final String CANE_AFFAMATO = "GRRR GRRR... sembra che il cane abbia fame... ha qualcosa nascosto nel collare";
	private static final String CANE_FELICE = "arf arf... sembra che il cane sia felice";
	private static final String CANE_MANGIA = "gnam gnam... il cane si getta sul cibo, sembra apprezzare... gli cade l'oggetto dal collare";
	private static final String CIBO_SBAGLIATO = "WHOF WHOF... il cane sputa quello che gli hai dato e ti morde.. hai perso 1 CFU";
	
	public Cane(String nome, String presentaz, String cibo, Attrezzo attrezzo) {
		super(nome, presentaz);
		this.ciboPreferito=cibo;
		this.haMangiato= false;
		this.attrezzo=attrezzo;
	}

	//@Override
	public String agisci(Partita partita) {
		StringBuilder risposta =
				new StringBuilder("...");
		if(haMangiato) risposta.append(CANE_FELICE);
		else {
			risposta.append(CANE_AFFAMATO);
		}
		return risposta.toString();
	}

	@Override
	public void riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(ciboPreferito)) {
			this.haMangiato=true;
			partita.getIO().mostraMessaggio(CANE_MANGIA);
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			partita.getIO().mostraMessaggio(CIBO_SBAGLIATO);
			partita.getGiocatore().setCfu(-1);
		}
	}
	
	@Override
	public String saluta() {
		StringBuilder risposta =
				new StringBuilder("...");
		if (!haSalutato()||!haMangiato)
			risposta.append(CANE_AFFAMATO);
		else if(haSalutato()&&!haMangiato) {
			risposta.append(CANE_AFFAMATO);
		}
		else 
			risposta.append(CANE_FELICE);
		SetSalutato();
		return risposta.toString();
	}
}
