package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			partita.getIO().mostraMessaggio("quale attrezzo vuoi donare?");
			return;
		}
		else {
			if(partita.getGiocatore().getBorsa().hasAttrezzo(parametro)) {
				partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().removeAttrezzo(parametro), partita);
				partita.getIO().mostraMessaggio("hai donato " +parametro);
			}
			else partita.getIO().mostraMessaggio(parametro + " non presente nella borsa");
		}
		
		
	}

}
