package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	StanzaMagica test= new StanzaMagica("test",1);
	Attrezzo attrezzoTest= new Attrezzo("prova",1);
	
	@Before
	public void Set() {
		test.addAttrezzo(attrezzoTest);
		test.addAttrezzo(attrezzoTest);
		test.addAttrezzo(attrezzoTest);
	}
	
	
	@Test
	public void addAttrezzoNormale() {
		assertTrue(test.hasAttrezzo("prova"));
	}
	
	@Test
	public void addAttrezzoMagico() {
		assertTrue(test.hasAttrezzo("avorp"));
	}

	@Test
	public void pesoRaddoppiato() {
		assertEquals(2, test.getAttrezzo("avorp").getPeso());
	}
}
