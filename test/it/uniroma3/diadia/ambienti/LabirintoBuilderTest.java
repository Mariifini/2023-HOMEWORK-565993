package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;


import org.junit.Test;
import static it.uniroma3.diadia.direzioni.Direzione.*;
public class LabirintoBuilderTest {
	
	public Labirinto lab;
	

	@Test
	public void testMonolocale() {
		this.lab= Labirinto.newBuilder()
				.addStanzaIniziale("Salotto")
				.addStanzaVincente("Salotto")
				.getLabirinto();
		assertEquals(lab.getStanzaIniziale(),lab.getStanzaVincente());
	}

	@Test
	public void testBilocale() {
		this.lab= Labirinto.newBuilder()
				.addStanzaIniziale("Salotto")
				.addStanzaVincente("cucina")
				.addAttrezzo("mestolo", 1)
				.addAdiacenza("Salotto", nord, "cucina")
				.addAttrezzo("coltello", 2)
				.getLabirinto();
		assertSame(lab.getStanzaIniziale().getStanzaAdiacente(nord), lab.getStanzaVincente());
		assertTrue(lab.getStanzaVincente().hasAttrezzo("coltello"));
		assertSame(lab.getStanzaIniziale().getStanzaAdiacente(nord).getStanzaAdiacente(sud), lab.getStanzaIniziale());
	}
}
