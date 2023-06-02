package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Partita finita!");
		
	}

//	@Override
//	public void setParametro(String parametro) {}
	
}
