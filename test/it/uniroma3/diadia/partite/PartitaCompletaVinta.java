package it.uniroma3.diadia.partite;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

public class PartitaCompletaVinta {

	IOSimulator io= new IOSimulator();
	DiaDia simulazione= new DiaDia(io);
	
	@Before
	public void SetTest() {
		io.setIstruzione("Player 1");
		io.setIstruzione("vai nord");
		io.setIstruzione("prendi lanterna");
		io.setIstruzione("vai sud");
		io.setIstruzione("vai sud");
		io.setIstruzione("vai est");
		io.setIstruzione("vai est");
		io.setIstruzione("vai sud");
		io.setIstruzione("vai est");
		io.setIstruzione("guarda");
		io.setIstruzione("posa lanterna");
		io.setIstruzione("guarda");
		io.setIstruzione("prendi chiave");
		io.setIstruzione("vai ovest");
		io.setIstruzione("vai nord");
		io.setIstruzione("vai ovest");
		io.setIstruzione("vai ovest");
		io.setIstruzione("vai nord");
		io.setIstruzione("posa chiave");
		io.setIstruzione("vai nord");
		simulazione.gioca();
	}

	@Test
	public void vintaTest() {
		assertTrue(simulazione.getPartita().getIsFinita());
	}
	
	@Test
	public void cfuTest() {
		assertEquals(7,simulazione.getPartita().getGiocatore().getCfu());
	}

}
