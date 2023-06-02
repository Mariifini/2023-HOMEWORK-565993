package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("vai, aiuto, prendi, posa, guarda, interagisci, saluta, regala, fine");
	}
	
}

