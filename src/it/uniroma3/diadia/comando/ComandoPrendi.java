package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends AbstractComando {
	

	public ComandoPrendi() {

	}

	
	@Override
	public void setParametro(String parametro) {
		this.parametro= parametro;
	}
	
	public void esegui(Partita partita) {
		final IO io= partita.getIO();
		if(this.parametro==null) {
			io.mostraMessaggio("quale attrezzo vuoi raccogliere?");
			return;
		}


		else {
			Stanza corrente= partita.getStanzaCorrente();
			Borsa borsa=partita.getGiocatore().getBorsa();

			if (corrente.hasAttrezzo(parametro)) {

				if (borsa.addAttrezzo(corrente.getAttrezzo(parametro))) {
					corrente.removeAttrezzo(corrente.getAttrezzo(parametro));
					io.mostraMessaggio("preso "+ parametro);}

				else { 
					io.mostraMessaggio("non entra nello zaino...");
				}
			}

			else {
				io.mostraMessaggio("attrezzo non trovato\n");
			}
		}
	}
}

