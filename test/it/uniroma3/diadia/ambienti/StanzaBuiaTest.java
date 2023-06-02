package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {


	private StanzaBuia stanzaBuia;
	private Stanza stanzaNonBuia;
		
	@Before
	public void setUp() {
		this.stanzaBuia = new StanzaBuia("StanzaBuia","attrezzoLuce");
		this.stanzaNonBuia = new Stanza("StanzaBuia");
	}

	@Test
	public void testDescrizioneLuceAssente() {
		assertEquals("Qui c'è un buio pesto...",this.stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testDescrizioneLucePresente() {
		Attrezzo attrezzoSblocco = new Attrezzo("attrezzoLuce",1);
		this.stanzaNonBuia.addAttrezzo(attrezzoSblocco);
		this.stanzaBuia.addAttrezzo(attrezzoSblocco);
		assertEquals(this.stanzaNonBuia.getDescrizione(),this.stanzaBuia.getDescrizione());
}
}
