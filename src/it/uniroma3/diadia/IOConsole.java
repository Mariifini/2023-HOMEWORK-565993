package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO{
	
	private Scanner scannerDiLinee;
	
	public IOConsole(Scanner scannerDiLinee) {
		this.scannerDiLinee=scannerDiLinee;
	}
	public IOConsole() {
		super();
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	@Override
	public String leggiRiga() {
		String riga = scannerDiLinee.nextLine();
//		scannerDiLinee.close();
		return riga;
	}
	
	@Override
	public Scanner getScanner() {
		return this.scannerDiLinee;
	}
}
