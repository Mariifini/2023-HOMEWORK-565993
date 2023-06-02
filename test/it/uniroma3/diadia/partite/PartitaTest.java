package it.uniroma3.diadia.partite;

import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class PartitaTest {
	private IOConsole Ioconsole=new IOConsole();
	private Partita partitaTest= new Partita(Ioconsole);

	@Test
	public void testGetIsFinitaFalse() {
		assertFalse(partitaTest.getIsFinita());
	}

	@Test
	public void testGetIsFinitaTRUE() {
		partitaTest.getGiocatore().setCfu(-20);
		partitaTest.getGiocatore().setVivo();
		partitaTest.setIsFinita();
		assertTrue(partitaTest.getIsFinita());
	}


}
