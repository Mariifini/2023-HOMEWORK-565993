package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {

	public String parametro;
	
	public abstract void esegui(Partita partita);
	
	public void setParametro(String parametro) {
		this.parametro= parametro;
	}
	
}
