package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import org.junit.Before;

	public class ComandoPrendiTest {
		private Partita partitaTest;
		private Comando comandoPrendi;
		private Stanza stanzaTest;
		private Borsa borsaTest;
		private IO io;

		@Before
		public void setUp() {
			partitaTest = new Partita(io);
			comandoPrendi = new ComandoPrendi();
			stanzaTest = new Stanza("Camera");
			stanzaTest.addAttrezzo(new Attrezzo("Libro", 1));
			stanzaTest.addAttrezzo(new Attrezzo("Comodino", 15));
			borsaTest = partitaTest.getGiocatore().getBorsa();
			partitaTest.setStanzaCorrente(stanzaTest);
			this.comandoPrendi.setIO(new IOConsole());
		}

		@Test
		public void prendiAttrezzoNonValido() {
			comandoPrendi.setParametro("Penna");
			comandoPrendi.esegui(partitaTest);
			assertTrue(borsaTest.isEmpty());
		}

		@Test
		public void prendiAttrezzoValido() {
			comandoPrendi.setParametro("Libro");
			comandoPrendi.esegui(partitaTest);
			assertFalse(borsaTest.isEmpty());
		}

		@Test
		public void prendiAttrezzoTroppoPesante() {
			comandoPrendi.setParametro("Comodino");
			comandoPrendi.esegui(partitaTest);
			assertTrue(borsaTest.isEmpty());
		}

		@Test
		public void prendiAttrezzoNull() {
			comandoPrendi.setParametro(null);
			comandoPrendi.esegui(partitaTest);
			assertTrue(borsaTest.isEmpty());
		}

	}