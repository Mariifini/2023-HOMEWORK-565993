package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class GiocatoreTest {
	private Giocatore Giocatore;
	@Before
	public void setUp() {
		this.Giocatore= new Giocatore();	
	}

	@Test
	public void testCfuNonFinitiInizioPartita() {
		assertNotEquals(0,this.Giocatore.getCfu());
	}
	@Test
	public void testCfuIniziali() {
		assertEquals(it.uniroma3.diadia.giocatore.Giocatore.CFU_INIZIALI, this.Giocatore.getCfu());
	}

	@Test
	public void testGetBorsa() {
		assertNotNull(this.Giocatore.getBorsa());
		
	}
}
