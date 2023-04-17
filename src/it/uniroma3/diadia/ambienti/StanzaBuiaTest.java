package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private static final String ATTREZZO_LUCE_TEST= "attrezzoLuceTest";
	private StanzaBuia stanzaBuia;
	private Attrezzo altroAttrezzo;
	
	private static final String DESCRIZIONE_STANZA="StanzaBuia\n"+
			"Uscite: \n"+
			"Attrezzi nella stanza: attrezzoLuceTest (1kg) ";
	public static Attrezzo creaAttrezzoEAggiungiAStanza (Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo= new Attrezzo(nomeAttrezzo,peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}
	

	@Before
	public void setUp() {
		altroAttrezzo = new Attrezzo("altroAttrezzo",1);
		this.stanzaBuia= new StanzaBuia("StanzaBuia",ATTREZZO_LUCE_TEST);
	}
@Test
public void testGetDescrizioneLuceNonPresente() {
	assertEquals(StanzaBuia.DESCRIZIONE_STANZA_BUIA,this.stanzaBuia.getDescrizione());
}
@Test
public void testGetDescrizioneConLuce() {
	creaAttrezzoEAggiungiAStanza(this.stanzaBuia, ATTREZZO_LUCE_TEST,1);
	assertEquals(DESCRIZIONE_STANZA,this.stanzaBuia.getDescrizione());
}
@Test
public void testGetDescrizioneConAltroAttrezzo() {
	stanzaBuia.addAttrezzo(altroAttrezzo);
	assertEquals("Qui c'e' buio pesto!",stanzaBuia.getDescrizione());
}
}

