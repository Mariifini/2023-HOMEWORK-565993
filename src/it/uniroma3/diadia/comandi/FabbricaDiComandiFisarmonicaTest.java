package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	@Before
	public void setUp() {
		fabbrica = new FabbricaDiComandiFisarmonica(this.io);
	}

	@Test
	public void testNome() {
		assertEquals("vai",fabbrica.costruisciComando("vai").getNome());
	}
	@Test
	public void testParametro() {
		assertEquals("nord",fabbrica.costruisciComando("vai nord").getParametro());
	}

}
