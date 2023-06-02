package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;
import static it.uniroma3.diadia.direzioni.Direzione.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	Stanza StanzaDiTest= new Stanza("stanzaTest");
	Stanza stanzaAdiacente= new Stanza("stanzaAdiacente");
	Attrezzo attrezzoTest= new Attrezzo("AttrezzoTest", 1);
	Attrezzo attrezzoNo= new Attrezzo("AttrezzoNo",1);
	Stanza stanzaAdiacente1= new Stanza("stanzaAdiacente1");
	Stanza stanzaAdiacente2= new Stanza("stanzaAdiacente2");
	Stanza stanzaAdiacente3= new Stanza("stanzaAdiacente3");
	Stanza stanzaAdiacente4= new Stanza("stanzaAdiacente4");
	
	@Before
	public void ImpostaStanzaTest() {
		StanzaDiTest.impostaStanzaAdiacente(nord, stanzaAdiacente);
		StanzaDiTest.addAttrezzo(attrezzoTest);
	}


	@Test
	public void testGetStanzaAdiacente() {
		assertEquals(stanzaAdiacente, StanzaDiTest.getStanzaAdiacente(nord));
	}

	@Test
	public void testGetNome() {
		assertEquals("stanzaTest", StanzaDiTest.getNome());
	}

	@Test
	public void testHasAttrezzoTrue() {
		assertTrue(StanzaDiTest.hasAttrezzo("AttrezzoTest"));
	}
	
	@Test
	public void testHasAttrezzoFalse() {
		assertFalse(StanzaDiTest.hasAttrezzo("AttrezzoNo"));
	}
	
	@Test
	public void testGetAttrezzoEsistente() {
	assertEquals(attrezzoTest, StanzaDiTest.getAttrezzo("AttrezzoTest"));
	}

	@Test
	public void testGetAttrezzoNonEsistente() {
	assertNull(StanzaDiTest.getAttrezzo("altroNome"));
	}
	
	@Test
	public void testRemoveAttrezzoTrue() {
	assertTrue(StanzaDiTest.removeAttrezzo(attrezzoTest));
	}
	
	@Test
	public void testRemoveAttrezzoFalse() {
		assertFalse(StanzaDiTest.removeAttrezzo(attrezzoNo));
	}
	
//	@Test 
//	public void testNonPiuDi4Direzioni() {
//		StanzaDiTest.impostaStanzaAdiacente("test1", stanzaAdiacente1);
//		StanzaDiTest.impostaStanzaAdiacente("test2", stanzaAdiacente2);
//		StanzaDiTest.impostaStanzaAdiacente("test3", stanzaAdiacente3);
//		StanzaDiTest.impostaStanzaAdiacente("test4", stanzaAdiacente4);
//		assertNull(StanzaDiTest.getStanzaAdiacente("test4"));
//	}
//	
//	@Test 
//	public void testNonpiuDi10Attrezzi() {
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoTest);
//		StanzaDiTest.addAttrezzo(attrezzoNo);
//		assertFalse(StanzaDiTest.hasAttrezzo("AttrezzoNo"));
//	}
	
}
