package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("comando non valido");
	}

	
	
}
