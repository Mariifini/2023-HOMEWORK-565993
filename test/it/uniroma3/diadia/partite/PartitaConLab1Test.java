package it.uniroma3.diadia.partite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static it.uniroma3.diadia.direzioni.Direzione.*;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;

public class PartitaConLab1Test {

	
	IOSimulator io= new IOSimulator();
	Labirinto simulato= Labirinto.newBuilder()
			.addStanzaIniziale("iniziale")
			.addStanzaVincente("vincente")
			.addAdiacenza("iniziale", nord, "vincente")
			.getLabirinto();
	DiaDia simulazione= new DiaDia(io, simulato);
	
	@Before
	public void Set() {
		io.setIstruzione("player 1");
		io.setIstruzione("vai nord");
		simulazione.gioca();
	}
	
	@Test
	public void testVinta() {
		assertTrue(simulazione.getPartita().getIsFinita());
	}

}
