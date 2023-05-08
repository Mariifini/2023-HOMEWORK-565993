package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaIniziale;//stanza iniziale
	private Stanza stanzaVincente;//stanza finale
	/*La classe Labirinto ha un metodo privato 
	init() che inizializza il Labirinto
	quindi il metodo init() deve andare a sostituire crea stanze*/

	
public Labirinto () {
	this.init();
}
/**
 * Crea tutte le stanze e le porte di collegamento
 */
private void init() {

	/* crea gli attrezzi */
	Attrezzo lanterna = new Attrezzo("lanterna",3);
	Attrezzo osso = new Attrezzo("osso",1);
	Attrezzo spada = new Attrezzo("spada",4);
	Attrezzo piedediporco =new Attrezzo("piedediporco",2);
	
	/* crea stanze del labirinto */
	Stanza atrio = new Stanza("Atrio");
	Stanza aulaN11 = new StanzaMagica("Aula N11");
	Stanza aulaN10 = new StanzaBloccata("Aula N10","piedediporco","est");
	Stanza laboratorio = new Stanza("Laboratorio Campus");
	Stanza biblioteca = new Stanza("Biblioteca");
	
	/* collega le stanze */
	atrio.impostaStanzaAdiacente("nord", biblioteca);
	atrio.impostaStanzaAdiacente("est", aulaN11);
	atrio.impostaStanzaAdiacente("sud", aulaN10);
	atrio.impostaStanzaAdiacente("ovest", laboratorio);
	aulaN11.impostaStanzaAdiacente("est", laboratorio);
	aulaN11.impostaStanzaAdiacente("ovest", atrio);
	aulaN10.impostaStanzaAdiacente("nord", atrio);
	aulaN10.impostaStanzaAdiacente("est", aulaN11);
	aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
	laboratorio.impostaStanzaAdiacente("est", atrio);
	laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
	biblioteca.impostaStanzaAdiacente("sud", atrio);

    /* pone gli attrezzi nelle stanze */
	aulaN10.addAttrezzo(lanterna);
	atrio.addAttrezzo(osso);
	laboratorio.addAttrezzo(spada);
	aulaN11.addAttrezzo(piedediporco);

	// il gioco comincia nell'atrio
    stanzaIniziale= atrio;  
	stanzaVincente = biblioteca;
}

public Stanza getStanzaVincente() {
	return stanzaVincente;
}
public Stanza getStanzaIniziale() {
	return this.stanzaIniziale;
}
public void setStanzaIniziale( Stanza iniziale) {
	 this.stanzaIniziale=iniziale ;
}
public void setStanzaVincente(Stanza vincente) {
	this.stanzaVincente=vincente;
}
}

