package it.uniroma3.diadia;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;



public class PartitaTest {
	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		this.partita= new Partita(io);
	}//in questo modo prima di ogni test inizializzo
	@Test
	public void testGetStanzaVincente1() {
		assertNotNull( partita.getStanzaVincente());
	}
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", partita.getStanzaVincente().getNome());
	}

	@Test
	public void testGetStanzaCorrente() {
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
	}
	@Test
	public void testNonVintaSeStanzaCorrenteNonVincente() {
		this.partita.setStanzaCorrente(new Stanza("NonVincente"));
		assertFalse(this.partita.vinta());
		}

	@Test
	public void testVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue (partita.vinta());
	}

	@Test
	public void testIsFinita() {
		this.partita.setCfu(0);
		assertTrue("0", partita.isFinita());
	}


	@Test
	public void testSetCfu() {
		partita.setCfu(12);
		assertEquals(12,partita.getCfu());
	}

}
