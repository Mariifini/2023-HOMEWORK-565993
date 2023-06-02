package it.uniroma3.diadia.ambienti;
import static it.uniroma3.diadia.direzioni.Direzione.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.direzioni.Direzione;
import it.uniroma3.diadia.personaggio.AbstractPersonaggio;


public class Labirinto {

	//	private Map<String, Stanza> stanze;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public Labirinto() {
		creaStanze();
	}

	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c =
				new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave",1);
		Attrezzo lucchetto = new Attrezzo("lucchetto",5);
		Attrezzo penna = new Attrezzo("penna",1);


		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		StanzaMagica laboratorioIA= new StanzaMagica("laboratorio IA");
		StanzaBloccata corridoio= new StanzaBloccata("corridoio", "chiave", nord);
		StanzaBuia sgabuzzino= new StanzaBuia("sgabuzzino", "lanterna");


		/* collega le stanze */
		atrio.impostaStanzaAdiacente(nord, corridoio);
		atrio.impostaStanzaAdiacente(est, aulaN11);
		atrio.impostaStanzaAdiacente(sud, aulaN10);
		atrio.impostaStanzaAdiacente(ovest, laboratorio);
		aulaN11.impostaStanzaAdiacente(est, laboratorio);
		aulaN11.impostaStanzaAdiacente(ovest, atrio);
		aulaN10.impostaStanzaAdiacente(nord, atrio);
		aulaN10.impostaStanzaAdiacente(est, aulaN11);
		aulaN10.impostaStanzaAdiacente(ovest, laboratorio);
		laboratorio.impostaStanzaAdiacente(est, atrio);
		laboratorio.impostaStanzaAdiacente(ovest, aulaN11);
		biblioteca.impostaStanzaAdiacente(sud, corridoio);
		corridoio.impostaStanzaAdiacente(nord, biblioteca);
		corridoio.impostaStanzaAdiacente(sud, atrio);
		laboratorio.impostaStanzaAdiacente(sud, laboratorioIA);
		laboratorioIA.impostaStanzaAdiacente(nord, laboratorio);
		laboratorioIA.impostaStanzaAdiacente(est, sgabuzzino);
		sgabuzzino.impostaStanzaAdiacente(ovest , laboratorioIA);


		/* pone gli attrezzi nelle stanze */
		corridoio.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(penna);
		atrio.addAttrezzo(lucchetto);
		sgabuzzino.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaVincente(Stanza vincente) {
		this.stanzaVincente=vincente;
	}

	public void setStanzaIniziale(Stanza iniziale) {
		this.stanzaIniziale=iniziale;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	public static class LabirintoBuilder extends Labirinto {

		static private Map<String, Stanza> stanze;
		private Stanza ultima;

		public LabirintoBuilder() {
			LabirintoBuilder.stanze= new HashMap<>();
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza iniziale= new Stanza(nome);
			super.setStanzaIniziale(iniziale);
			stanze.put(nome, iniziale);
//			if(stanze.containsKey(nome)) System.out.println("aggiunta iniziale");
			ultima=iniziale;
//			System.out.println("aggiunta iniziale" + nome);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza vincente= new Stanza(nome);
			super.setStanzaVincente(vincente);
			stanze.put(nome, vincente);
//			if(stanze.containsKey(nome)) System.out.println("aggiunta vincente");

			ultima=vincente;
//			System.out.println("aggiunta vincente" + nome);
			return this;
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza nuova= new Stanza(nome);
			stanze.put(nome, nuova);
//			if(stanze.containsKey(nome)) System.out.println("aggiunta stanza");

			ultima=nuova;
//			System.out.println("aggiunta" + nome);
			return this;
		}


		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo nuovo= new Attrezzo(nome, peso);
			ultima.addAttrezzo(nuovo);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso, String nomeStanza) {
			Attrezzo nuovo= new Attrezzo(nome, peso);
			if(LabirintoBuilder.stanze.containsKey(nomeStanza)) {
				LabirintoBuilder.stanze.get(nomeStanza).addAttrezzo(nuovo);
			}
			else System.out.println("e invece adesso non c'è" + nomeStanza);
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

		public LabirintoBuilder addStanzaBloccata(String nome, String attrezzo, Direzione direzione) {
			StanzaBloccata nuova= new StanzaBloccata(nome, attrezzo, direzione);
			stanze.put(nome, nuova);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaFrom, Direzione direzione,String stanzaTo) {
			stanze.get(stanzaFrom).impostaStanzaAdiacente(direzione, stanze.get(stanzaTo));
			stanze.get(stanzaTo).impostaStanzaAdiacente(direzione.opposta(), stanze.get(stanzaFrom));
			return this;
		}

		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio, String Stanza) {
			stanze.get(Stanza).addPersonaggio(personaggio);
			return this;
		}

		public LabirintoBuilder getLabirinto() {
			return this;
		}
		public Map<String, Stanza> getStanze(){
			return LabirintoBuilder.stanze;
		}
		public void stampaStanze() {
			System.out.println(LabirintoBuilder.stanze.toString());
		}
	}
}
