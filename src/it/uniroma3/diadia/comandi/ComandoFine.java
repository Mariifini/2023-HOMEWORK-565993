package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private IO io;
	private static final String NOME= "fine";
	public static final String MESSAGGIO_FINE= "grazie per aver giocato!";
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.io.mostraMessaggio(MESSAGGIO_FINE);
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
