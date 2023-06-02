package it.uniroma3.diadia.partite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

public class PartitaCompletaPersa {


	IOSimulator io= new IOSimulator();
	DiaDia simulazione= new DiaDia(io);

	@Before
	public void SetTest() throws Exception {
		io.setIstruzione("Player 1");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		simulazione.gioca();
	}
	@Test 
	public void finitaTest() {
		assertTrue(simulazione.getPartita().getIsFinita());
	}
	@Test
	public void giocatoreMortoTest() {
		assertEquals(0, simulazione.getPartita().getGiocatore().getCfu());
	}
	
}
