package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {
	
	public Labirinto lab;
	

	@Test
	public void testMonolocale() {
		this.lab= new LabirintoBuilder()
				.addStanzaIniziale("Salotto")
				.addStanzaVincente("Salotto")
				.getLabirinto();
		assertEquals(lab.getStanzaIniziale(),lab.getStanzaVincente());
	}

	@Test
	public void testBilocale() {
		this.lab= new LabirintoBuilder()
				.addStanzaIniziale("Salotto")
				.addStanzaVincente("cucina")
				.addAttrezzo("mestolo", 1)
				.addAdiacenza("Salotto", "cucina", "nord")
				.addAttrezzo("coltello", 2)
				.getLabirinto();
		assertSame(lab.getStanzaIniziale().getStanzaAdiacente("nord"), lab.getStanzaVincente());
		assertTrue(lab.getStanzaVincente().hasAttrezzo("coltello"));
	}
}
