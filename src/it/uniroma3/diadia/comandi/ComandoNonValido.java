package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private IO io;
	private static final String NOME="Comando non valido";
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando non valido");	
	}
	
	public void setParametro(String parametro) {	
	}

	@Override
	public void setIO(IO io) {
		this.io=io;
		
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return NOME;
	}
}


