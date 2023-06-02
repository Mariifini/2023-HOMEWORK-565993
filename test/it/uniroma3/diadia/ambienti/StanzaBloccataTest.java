package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static it.uniroma3.diadia.direzioni.Direzione.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	public StanzaBloccata test= new StanzaBloccata("test","chiave", nord);
	public Stanza stanzaNord= new Stanza("sbloccata");
	public Attrezzo chiave= new Attrezzo("chiave", 1);
	
	@Before
	public void set() {
		test.impostaStanzaAdiacente(nord, stanzaNord);
	}
	@Test
	public void stanzaBloccatatest() {
		assertEquals(test.getNome(),test.getStanzaAdiacente(nord).getNome());
	}
	@Test
	public void stanzaSbloccataTest() {
		test.addAttrezzo(chiave);
		assertEquals(stanzaNord.getNome(), test.getStanzaAdiacente(nord).getNome());
	}
}
