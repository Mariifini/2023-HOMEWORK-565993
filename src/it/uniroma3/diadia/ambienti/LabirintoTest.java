package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	public Labirinto Labirinto;


	@Before
	public void setUp() {
		this.Labirinto = new Labirinto();
	}
	
	
	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(this.Labirinto.getStanzaIniziale());
		
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertNotNull(this.Labirinto.getStanzaVincente());
	}
	
}

