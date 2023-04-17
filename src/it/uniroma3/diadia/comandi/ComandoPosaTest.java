package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosaTest {
	private static final String ATTREZZO_DA_POSARE= "Attrezzodaposare";
	private Partita partita;
	private ComandoPosa comandoPosa;
	private Stanza stanza;
	private Borsa borsa;

	@Before
	public void setUp() throws Exception{
		this.partita = new Partita();
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setIO(new IOConsole());//quando c'e' il new non posso usarlo con l interfaccia
		this.stanza = new Stanza("stanza");
		borsa = partita.getGiocatore().getBorsa();
		partita.setStanzaCorrente(stanza);
		Attrezzo attrezzoNuovo= new Attrezzo(ATTREZZO_DA_POSARE,1);
		borsa.addAttrezzo(attrezzoNuovo);
	}

	@Test
	public void posaAttrezzoNonValido() {
		comandoPosa.setParametro("Libro");
		comandoPosa.esegui(partita);
		assertFalse(stanza.hasAttrezzo("Libro"));
	}

	@Test
	public void posaAttrezzoValido() {
		borsa.addAttrezzo(new Attrezzo("Piccone", 4));
		comandoPosa.setParametro("Piccone");
		comandoPosa.esegui(partita);
		assertTrue(stanza.hasAttrezzo("Piccone"));
	}

	@Test
	public void testEseguiAttrezzoPresente() {
		this.comandoPosa.setParametro(ATTREZZO_DA_POSARE);
		this.comandoPosa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(ATTREZZO_DA_POSARE));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(ATTREZZO_DA_POSARE));
	}


}

