package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {

	private Map<String, Stanza> stanze;
	private Stanza ultima;

	public LabirintoBuilder() {
		stanze= new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza iniziale= new Stanza(nome);
		super.setStanzaIniziale(iniziale);
		stanze.put(nome, iniziale);
		ultima=iniziale;
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza vincente= new Stanza(nome);
		super.setStanzaVincente(vincente);
		stanze.put(nome, vincente);
		ultima=vincente;
		return this;
	}

	public LabirintoBuilder addStanza(String nome) {
		Stanza nuova= new Stanza(nome);
		stanze.put(nome, nuova);
		ultima=nuova;
		return this;
	}

	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo nuovo= new Attrezzo(nome, peso);
		ultima.addAttrezzo(nuovo);
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nome, int valore) {
		StanzaMagica nuova= new StanzaMagica(nome, valore);
		stanze.put(nome, nuova);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
		StanzaBuia nuova= new StanzaBuia(nome, attrezzo);
		stanze.put(nome, nuova);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nome, String attrezzo, String direzione) {
		StanzaBloccata nuova= new StanzaBloccata(nome, attrezzo, direzione);
		stanze.put(nome, nuova);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanzaIniziale, String stanzaFinale, String Direzione) {
		stanze.get(stanzaIniziale).impostaStanzaAdiacente(Direzione, stanze.get(stanzaFinale));
		return this;
	}

	public LabirintoBuilder getLabirinto() {
		return this;
	}

}