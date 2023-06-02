package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.direzioni.Direzione;

public class StanzaBloccata extends Stanza {

	private String attrezzoNecessario;
	private Direzione direzioneBloccata;

	public StanzaBloccata(String nome, String attrezzo, Direzione direzione) {
		super(nome);
		this.attrezzoNecessario=attrezzo;
		this.direzioneBloccata=direzione;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(direzione.equals(direzioneBloccata)&& !this.hasAttrezzo(attrezzoNecessario))
			return this;
		else 
			return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		return this.toString();
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.toString());
		if(!this.hasAttrezzo(attrezzoNecessario)) {
			risultato.append("\nSembra che la direzione ");
			risultato.append(this.direzioneBloccata);
			risultato.append(" abbia qualcosa di strano...");
		}
		else {
			risultato.append("tutte le direzioni sono state sbloccate!");
		}
		return risultato.toString();
	}
}
