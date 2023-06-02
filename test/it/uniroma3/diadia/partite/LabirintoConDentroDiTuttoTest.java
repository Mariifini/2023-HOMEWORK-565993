package it.uniroma3.diadia.partite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggio.AbstractPersonaggio;
import it.uniroma3.diadia.personaggio.Cane;
import it.uniroma3.diadia.personaggio.Mago;
import it.uniroma3.diadia.personaggio.Strega;
import static it.uniroma3.diadia.direzioni.Direzione.*;

public class LabirintoConDentroDiTuttoTest {

	public Labirinto lab;
	
	@Before
	public void Setup() {
		Attrezzo chiave= new Attrezzo("chiave", 1);
		Attrezzo torcia= new Attrezzo("torcia", 1);
		AbstractPersonaggio mago= new Mago("Merlino", "guarda che bel cappello", chiave);
		AbstractPersonaggio strega= new Strega("Bellatrix", "sono pericolosaaaaa");
		AbstractPersonaggio cane= new Cane("fuffi", "bau bau", "osso", torcia);
		this.lab= Labirinto.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("uscita")
				.addStanzaBloccata("bloccata", "chiave", nord)
				.addStanzaMagica("magica", 3)
				.addStanzaBuia("buia", "torcia")
				.addStanza("intermedia1")
				.addStanza("intermedia2")
				.addAdiacenza("Ingresso", nord, "bloccata")
				.addAdiacenza("bloccata", nord, "intermedia1")
				.addAdiacenza("intermedia1", nord, "magica")
				.addAdiacenza("magica", nord, "intermedia2")
				.addAdiacenza("intermedia2", nord, "buia")
				.addAdiacenza("buia", nord, "uscita")
				.addAttrezzo("osso", 1, "intermedia1")
				.addPersonaggio(mago, "bloccata")
				.addPersonaggio(cane, "intermedia2")
				.addPersonaggio(strega, "bloccata")
				.getLabirinto();
	}
	
	@Test
	public void test() {
	assertTrue(true);
	}

}
