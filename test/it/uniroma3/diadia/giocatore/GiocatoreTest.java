package it.uniroma3.diadia.giocatore;
import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {

	Giocatore giocatoreTest= new Giocatore();
	

	@Test
	public void testIsVivoTrue() {
		assertTrue(giocatoreTest.isVivo());}

	@Test
	public void testIsVivoFalse() {
		giocatoreTest.setCfu(-20);
		giocatoreTest.setVivo();
		assertFalse(giocatoreTest.isVivo());}

	@Test
	public void testGetCfu() {
		assertEquals(20,giocatoreTest.getCfu());
	}

	@Test
	public void testSetCfu() {
		giocatoreTest.setCfu(-10);
		assertEquals(10,giocatoreTest.getCfu());
	}
	
	@Test
	public void testGetNome() {
		giocatoreTest.setNome("giocatoreTest");
		assertEquals("giocatoreTest",giocatoreTest.GetNome());
	}

}
