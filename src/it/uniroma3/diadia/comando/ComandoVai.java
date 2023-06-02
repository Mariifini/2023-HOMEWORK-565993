package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.direzioni.Direzione;

public class ComandoVai extends AbstractComando {
	
	public ComandoVai() {
	}
	
//	@Override
//	public void setParametro(String parametro) {
//		this.direzione= parametro;
//	}
	
	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();
		Stanza corrente= partita.getStanzaCorrente();

		if (this.parametro==null) {
			io.mostraMessaggio("Dove vuoi andare?");
			io.mostraMessaggio(partita.getStanzaCorrente().stampaDirezioni());
			return;
		}

		else { 
			Stanza prossima = null;
			Direzione that=null;
			for (Direzione l : Direzione.values()) {
				if(l.name().equals(parametro)) that=l;
			}
			prossima = corrente.getStanzaAdiacente(that);
			if (prossima == null) {
				io.mostraMessaggio(corrente.stampaDirezioni());
				return;
			}
			else {
				partita.setStanzaCorrente(prossima);
				partita.getGiocatore().setCfu(-1);
				partita.getGiocatore().setVivo();
				partita.setIsFinita();
			}
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

}
