package it.uniroma3.diadia.comandi
;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa  implements Comando {
	private String nomeAttrezzo;
	private IO io;
	private final String NOME= "posa";

//	public ComandoPosa(String nomeAttrezzo) {
//		this.nomeAttrezzo = nomeAttrezzo;
//	}

	public void esegui(Partita partita) { 
		Stanza stanzaCorrente=partita.getStanzaCorrente();	
		Borsa borsa=partita.getGiocatore().getBorsa();
		if(!borsa.hasAttrezzo(nomeAttrezzo)) {
			this.io.mostraMessaggio(("La borsa e' vuota!"));
			return;
		}
		else {
			Attrezzo attrezzoDaPosare= borsa.getAttrezzo(getParametro());
			if(attrezzoDaPosare==null) {
				this.io.mostraMessaggio("Attrezzo "+ attrezzoDaPosare +" non presente nella BORSA");
				return;
			}
			boolean attrezzoPosato= (stanzaCorrente.addAttrezzo(attrezzoDaPosare));
			if(!attrezzoPosato) {
				this.io.mostraMessaggio("non c'e' piu spazio per nuovi attrezzi nella stanza");
				return;
			}
			borsa.removeAttrezzo(this.nomeAttrezzo);
			this.io.mostraMessaggio("Attrezzo "+this.nomeAttrezzo+" posato!");
		}
	}
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	@Override
	public void setIO(IO io) {
		this.io=io;
		
	}
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	@Override
	public String getNome() {
		return NOME;
	}
}


