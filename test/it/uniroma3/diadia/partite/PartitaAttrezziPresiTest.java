package it.uniroma3.diadia.partite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

public class PartitaAttrezziPresiTest {

	IOSimulator io= new IOSimulator();
	DiaDia simulazione= new DiaDia(io);

	@Before
	public void SetTest() {
		io.setIstruzione("Player 1");
		io.setIstruzione("prendi osso");
		io.setIstruzione("vai nord");
		io.setIstruzione("prendi lanterna");
		io.setIstruzione("fine");
		simulazione.gioca();
	}
	
	@Test
	public void HasOssoTest() {
		assertTrue(simulazione.getPartita().getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
	@Test
	public void HasLanternaTest() {
		assertTrue(simulazione.getPartita().getGiocatore().getBorsa().hasAttrezzo("lanterna"));
	}
	@Test
	public void StanzaVuotaTest() {
		assertFalse(simulazione.getPartita().getStanzaCorrente().hasAttrezzo("lanterna"));
	}
}
