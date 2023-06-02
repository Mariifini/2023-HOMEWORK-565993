package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class BorsaTest {

	Borsa borsaTest=new Borsa();
	Attrezzo attrezzoTest= new Attrezzo("attrezzoTest", 1);
	Attrezzo attrezzoTest2= new Attrezzo("attrezzoTest2",1);
	Attrezzo attrezzoTestPesante= new Attrezzo("attrezzoTestPesante", 12);
	Attrezzo attrezzoA1= new Attrezzo("a1", 1);
	Attrezzo attrezzoA2= new Attrezzo("a2", 2);
	Attrezzo attrezzoB1= new Attrezzo("b1",1);
	Attrezzo attrezzoB2= new Attrezzo("b2", 2);

	@Test
	public void testGetAttrezzo() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertSame(attrezzoTest,borsaTest.getAttrezzo("attrezzoTest"));
	}

	@Test
	public void testGetPeso() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertEquals(1,borsaTest.getPesoAtt());
	}

	@Test
	public void testIsEmpty() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertFalse(borsaTest.isEmpty());
	}

	@Test
	public void testHasAttrezzoTrue() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertTrue(borsaTest.hasAttrezzo("attrezzoTest"));
	}

	@Test
	public void testHasAttrezzoFasle() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertFalse(borsaTest.hasAttrezzo("attrezzoInesistente"));
	}

	@Test
	public void testRemoveAttrezzo() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertEquals(attrezzoTest, borsaTest.removeAttrezzo("attrezzoTest"));
		assertFalse(borsaTest.hasAttrezzo("attrezzoTest"));
	}

	@Test
	public void testAddAttrezzoTrue() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertTrue(borsaTest.addAttrezzo(attrezzoTest2));
		assertTrue(borsaTest.hasAttrezzo("attrezzoTest2"));
	}
	@Test
	public void testAddAttrezzoFalse11Oggetti() {
		borsaTest.addAttrezzo(attrezzoTest);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.addAttrezzo(attrezzoTest2);
		assertFalse(borsaTest.addAttrezzo(attrezzoTest2));
	}
	@Test
	public void testAddAttrezzoFalseTroppoPesante() {
		borsaTest.addAttrezzo(attrezzoTest);
		assertFalse(borsaTest.addAttrezzo(attrezzoTestPesante));
		assertFalse(borsaTest.hasAttrezzo("AttrezzoTestPesante"));
	}

	@Test
	public void testOrdineAlfabetico() {
		borsaTest.addAttrezzo(attrezzoA1);
		borsaTest.addAttrezzo(attrezzoB1);
		SortedSet<Attrezzo> set= borsaTest.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it= set.iterator();
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoB1, it.next());
	}
	@Test
	public void testOrdineAlfabeticoInv() {
		borsaTest.addAttrezzo(attrezzoB1);
		borsaTest.addAttrezzo(attrezzoA1);
		SortedSet<Attrezzo> set= borsaTest.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it= set.iterator();
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoB1, it.next());
	}

	@Test
	public void testOrdineAlfabeticoSame() {
		borsaTest.addAttrezzo(attrezzoA2);
		borsaTest.addAttrezzo(attrezzoA1);
		SortedSet<Attrezzo> set= borsaTest.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it= set.iterator();
		assertEquals(2, set.size());
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoA2, it.next());
	}
	
	@Test
	public void testOrdineDiPeso() {
		borsaTest.addAttrezzo(attrezzoA2);
		borsaTest.addAttrezzo(attrezzoA1);
		List<Attrezzo> l= borsaTest.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it= l.iterator();
		assertEquals(2, l.size());
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoA2, it.next());
	}
	@Test
	public void testOrdineDiPesoUguale() {
		borsaTest.addAttrezzo(attrezzoB1);
		borsaTest.addAttrezzo(attrezzoA1);
		List<Attrezzo> l= borsaTest.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it= l.iterator();
		assertEquals(2, l.size());
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoB1, it.next());
		
	}
	
	@Test
	public void testMappaDiSet() {
		borsaTest.addAttrezzo(attrezzoA1);
		borsaTest.addAttrezzo(attrezzoA2);
		borsaTest.addAttrezzo(attrezzoB1);
		borsaTest.addAttrezzo(attrezzoB2);
		borsaTest.addAttrezzo(attrezzoTest);
		borsaTest.addAttrezzo(attrezzoTest2);
		borsaTest.getContenutoRaggruppatoPerPeso();
	}
	
	@Test
	public void testSortedPerPeso() {
		borsaTest.addAttrezzo(attrezzoA1);
		borsaTest.addAttrezzo(attrezzoB1);
		SortedSet<Attrezzo> set= borsaTest.getSortedSetOrdinatoPerPeso();
		assertEquals(2,set.size());
	}
}
