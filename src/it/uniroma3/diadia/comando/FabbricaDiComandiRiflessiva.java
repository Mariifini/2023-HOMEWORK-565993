package it.uniroma3.diadia.comando;
import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	public Scanner scannerDiParole;
	
	public FabbricaDiComandiRiflessiva(Scanner scannerDiLinee) {
		this.scannerDiParole=scannerDiLinee;
	}
	
	@Override
	public AbstractComando costruisciComando(String istruzione) {
		scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		try {
			String nomeClasse = "it.uniroma3.diadia.comando.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (AbstractComando)Class.forName(nomeClasse).newInstance();
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
		}
		return comando;
	}
}