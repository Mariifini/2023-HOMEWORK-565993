package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Qui è dove ti trovi");
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio("Questa è la tua situazione attuale:");
		partita.getIO().mostraMessaggio("Hai "+ partita.getGiocatore().getCfu()+ " Cfu");
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().toString());

	}
	
}
