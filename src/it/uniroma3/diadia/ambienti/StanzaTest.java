package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class StanzaTest {
	private static final String ATTREZZO ="AttrezzoDiTest";
	private static final String STANZA = "StanzaTest";

	
	private Stanza stanza;

	
	@Before
	public void setUp() {
		this.stanza= new Stanza(STANZA);
	}

	

	@Test
	public void testImpostaStanzaAdiacente() {
		//creo due stanze
		Stanza uno=new Stanza ("Uno");
		Stanza due=new Stanza ("Due");
		//impongo un legame di "ordinamento" basico
		uno.impostaStanzaAdiacente("nord", due);
		assertEquals(due, uno.getStanzaAdiacente("nord"));
		//verifico se viene rispettato
	}


	@Test
	public void testGetNome() {
		Stanza Newname= new Stanza("NEW");
		assertEquals("NEW", Newname.getNome());	}

	@Test
    public void testAddAttrezzo() {
        Stanza uno = new Stanza("Uno");
        Attrezzo pala = new Attrezzo("pala", 3);
        assertTrue((uno).addAttrezzo(pala));
        }
	

	 @Test
	    public void testHasAttrezzo() {
	        Stanza a = new Stanza("A");
	        Attrezzo pala = new Attrezzo("pala", 3);
	        Attrezzo martello = new Attrezzo("martello", 5);
	        a.addAttrezzo(pala);
	        a.addAttrezzo(martello);
	        assertTrue(a.hasAttrezzo("pala"));
	        assertTrue(a.hasAttrezzo("martello"));
	    }
	 @Test
		public void testHasAttrezzoInesistente() {
			Attrezzo attrezzo= new Attrezzo(ATTREZZO, 1);
			this.stanza.addAttrezzo(attrezzo);
			assertFalse(this.stanza.hasAttrezzo("inesistente"));
		}
	 

    @Test
    public void testRemoveAttrezzo() {
        Stanza s = new Stanza("Sala del Tesoro");
        Attrezzo a1 = new Attrezzo("martello", 2);
        Attrezzo a2 = new Attrezzo("chiave", 1);
        Attrezzo a3 = new Attrezzo("libro", 3);
        
        // Aggiunge gli attrezzi alla stanza
        s.addAttrezzo(a1);
        s.addAttrezzo(a2);
        s.addAttrezzo(a3);
        
        // Rimuove l'attrezzo "martello"
        assertTrue(s.removeAttrezzo(a1));
        
        // Verifica che l'attrezzo "martello" non sia più presente nella stanza
        assertFalse(s.hasAttrezzo("martello"));
        
        // Rimuove un attrezzo non presente nella stanza
        assertFalse(s.removeAttrezzo(new Attrezzo("spada", 4)));
    }
    
    @Test
    public void testRemoveAttrezzoWithMap() {
        Stanza s = new Stanza("Sala del Tesoro");
        Attrezzo a1 = new Attrezzo("martello", 2);
        Attrezzo a2 = new Attrezzo("chiave", 1);
        Attrezzo a3 = new Attrezzo("libro", 3);

        // Aggiunge gli attrezzi alla stanza
        s.addAttrezzo(a1);
        s.addAttrezzo(a2);
        s.addAttrezzo(a3);

        // Rimuove l'attrezzo "martello"
        assertTrue(s.removeAttrezzo(a1));

        // Verifica che l'attrezzo "martello" non sia più presente nella stanza
        assertFalse(s.hasAttrezzo("martello"));

        // Rimuove un attrezzo non presente nella stanza
        assertFalse(s.removeAttrezzo( new Attrezzo("spada", 4)));
    }
}

