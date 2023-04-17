package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando  {
	
	 private final String[] elencoComandi={"vai", "aiuto", "fine","prendi", "posa", "guarda"};
	 public IO io;
	 private final static String NOME="aiuto";
	 
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
	}
public void setParametro(String parametro) {}//non vuole nessun parametro

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
