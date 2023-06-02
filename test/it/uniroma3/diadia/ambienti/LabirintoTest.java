package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {
	
	Labirinto labirintoTest= new Labirinto();

	@Test
	public void testGetStanzaVincente() {
		assertNotNull(labirintoTest.getStanzaVincente());
	}

	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(labirintoTest.getStanzaIniziale());
	}

}
