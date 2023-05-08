package it.uniroma3.diadia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {

	private Map<Integer , String> istruzioni;
	private Map<Integer, List<String>> messaggi;
	private int numeroIstruzioneCorrente;
	private int numeroIstruzioneDaLeggere;


	public IOSimulator() {
		this.istruzioni=new HashMap<>();
		this.messaggi= new HashMap<>();
		this.numeroIstruzioneCorrente=0;
		this.numeroIstruzioneDaLeggere=0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		List<String> l;
		if(messaggi.containsKey(this.numeroIstruzioneCorrente)) {
		l=messaggi.get(this.numeroIstruzioneCorrente);
		l.add(messaggio);
		}
		else {
		l=new LinkedList<String>();
		l.add(messaggio);
		}
				System.out.println(messaggio);
				return;
	
	}
	
	@Override
	public String leggiRiga() {
		numeroIstruzioneDaLeggere+=1;
		
		return istruzioni.get((this.numeroIstruzioneDaLeggere)-1);
		
	
	}
	
	public int getIstruzioneCorrente() {
		return this.numeroIstruzioneCorrente;
	}

	
	public void setIstruzione(String Istruzione) {
		istruzioni.put(this.numeroIstruzioneCorrente, Istruzione);
		numeroIstruzioneCorrente+=1;
		}

}

