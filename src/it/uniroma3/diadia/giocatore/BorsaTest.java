package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private static final String ATTREZZO = "attrezzoSemplice";
	public final static int PESO_MAX_BORSA = 10;
	private Borsa borsa;
	Attrezzo attrezzoTest= new Attrezzo("attrezzoTest", 1);
	Attrezzo attrezzoTest2= new Attrezzo("attrezzoTest2",1);
	Attrezzo attrezzoTestPesante= new Attrezzo("attrezzoTestPesante", 12);
	Attrezzo attrezzoA1= new Attrezzo("a1", 1);
	Attrezzo attrezzoA2= new Attrezzo("a2", 2);
	Attrezzo attrezzoB1= new Attrezzo("b1",1);
	Attrezzo attrezzoB2= new Attrezzo("b2", 2);
	@Before
	public void setUp() {
		this.borsa= new Borsa(PESO_MAX_BORSA);
		
	}

	@Test
	public void testAddAttrezzoSingolo() { //creo un metodo di utulita'
		Attrezzo attrezzo =creaAttrezzoEAggiungiBorsa(this.borsa,ATTREZZO,1);
		assertEquals (attrezzo, this.borsa.getAttrezzo(ATTREZZO));
	}
	


	@Test
	public void testAddAttrezzoTroppoPesante() {
		Attrezzo attrezzoPesante=new Attrezzo("attrezzoPesante", PESO_MAX_BORSA+1);
		assertFalse(this.borsa.addAttrezzo(attrezzoPesante));
	}
	@Test
	public void testGetattrezzoBorsaVuota() {
		assertNull(this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testAttrezzoInesistente() {
		creaAttrezzoEAggiungiBorsa(this.borsa, ATTREZZO, 1);
		assertNull(this.borsa.getAttrezzo("inesistente"));
	}
	@Test
	public void testGetPesoMax() {
		assertEquals(PESO_MAX_BORSA,this.borsa.getPesoMax());
	}
	@Test
	public void testGetPesoIniziale() {
		assertEquals(0,this.borsa.getPesoAtt());
	}
	@Test
	public void testGetPesoDopoAggiuntaAttrezzo() {
		creaAttrezzoEAggiungiBorsa(this.borsa, ATTREZZO, 1);
		assertEquals(1,this.borsa.getPesoAtt());
	}
	@Test
	public void testEmptyIniziale() {
		assertTrue(this.borsa.isEmpty());
	}
	@Test
	public void testEmptyDopoAggiunta() {
		creaAttrezzoEAggiungiBorsa(this.borsa, ATTREZZO, 1);
		assertFalse(this.borsa.isEmpty());
	}
	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo(ATTREZZO));
	}
	@Test
	public void testRemoveAttrezzo() {
		creaAttrezzoEAggiungiBorsa(this.borsa, ATTREZZO, 1);
		this.borsa.removeAttrezzo(ATTREZZO);
		assertFalse(this.borsa.hasAttrezzo(ATTREZZO));
	}
	@Test
	public void testRemoveAttrezzoPesoZero() {
		creaAttrezzoEAggiungiBorsa(this.borsa, ATTREZZO, 1);
		this.borsa.removeAttrezzo(ATTREZZO);
		assertEquals(0,this.borsa.getPesoAtt());
	}
	/*metodo di utilita'*/
private Attrezzo creaAttrezzoEAggiungiBorsa(Borsa borsa, String nomeAttrezzo, int peso) {
	Attrezzo attrezzo =new Attrezzo(nomeAttrezzo,peso);
	borsa.addAttrezzo(attrezzo);
	return attrezzo;}
	
	@Test
	public void testAddAttrezzoFalse11Oggetti() {
		borsa.addAttrezzo(attrezzoTest);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.addAttrezzo(attrezzoTest2);
		assertFalse(borsa.addAttrezzo(attrezzoTest2));
	}
	@Test
	public void testAddAttrezzoFalseTroppoPesante() {
		borsa.addAttrezzo(attrezzoTest);
		assertFalse(borsa.addAttrezzo(attrezzoTestPesante));
		assertFalse(borsa.hasAttrezzo("AttrezzoTestPesante"));
	}

	@Test
	public void testOrdineAlfabetico() {
		borsa.addAttrezzo(attrezzoA1);
		borsa.addAttrezzo(attrezzoB1);
		SortedSet<Attrezzo> set= borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it= set.iterator();
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoB1, it.next());
	}
	@Test
	public void testOrdineAlfabeticoInv() {
		borsa.addAttrezzo(attrezzoB1);
		borsa.addAttrezzo(attrezzoA1);
		SortedSet<Attrezzo> set= borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it= set.iterator();
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoB1, it.next());
	}

	@Test
	public void testOrdineAlfabeticoSame() {
		borsa.addAttrezzo(attrezzoA2);
		borsa.addAttrezzo(attrezzoA1);
		SortedSet<Attrezzo> set= borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it= set.iterator();
		assertEquals(2, set.size());
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoA2, it.next());
	}
	
	@Test
	public void testOrdineDiPeso() {
		borsa.addAttrezzo(attrezzoA2);
		borsa.addAttrezzo(attrezzoA1);
		List<Attrezzo> l= borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it= l.iterator();
		assertEquals(2, l.size());
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoA2, it.next());
	}
	@Test
	public void testOrdineDiPesoUguale() {
		borsa.addAttrezzo(attrezzoB1);
		borsa.addAttrezzo(attrezzoA1);
		List<Attrezzo> l= borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it= l.iterator();
		assertEquals(2, l.size());
		assertSame(attrezzoA1, it.next());
		assertSame(attrezzoB1, it.next());
		
	}
	
	@Test
	public void testMappaDiSet() {
		borsa.addAttrezzo(attrezzoA1);
		borsa.addAttrezzo(attrezzoA2);
		borsa.addAttrezzo(attrezzoB1);
		borsa.addAttrezzo(attrezzoB2);
		borsa.addAttrezzo(attrezzoTest);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.getContenutoRaggruppatoPerPeso();
	}
	
	@Test
	public void testSortedPerPeso() {
		borsa.addAttrezzo(attrezzoA1);
		borsa.addAttrezzo(attrezzoB1);
		SortedSet<Attrezzo> set= borsa.getSortedSetOrdinatoPerPeso();
		assertEquals(2,set.size());
	}
}
