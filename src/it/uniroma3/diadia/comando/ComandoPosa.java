package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando {
	public ComandoPosa() {
	}

	
	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {partita.getIO().mostraMessaggio("quale attrezzo vuoi lasciare?");
		return;
		}

		else {
			if (partita.getGiocatore().getBorsa().hasAttrezzo(parametro)) {
				partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(parametro));
				partita.getIO().mostraMessaggio(parametro+ " rimosso");}
			else {partita.getIO().mostraMessaggio(parametro + " non presente nella borsa");
			}
		}
	}

}
