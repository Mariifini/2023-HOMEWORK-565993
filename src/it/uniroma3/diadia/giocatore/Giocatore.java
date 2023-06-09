package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ProprietÓ;

public class Giocatore {

	static final private ProprietÓ prop= new ProprietÓ();
	
	static final private int CFU_INIZIALI=Integer.parseInt(ProprietÓ.getProp("cfu_iniziali"));

	private String nome;
	private int cfu;
	private boolean vivo;
	private Borsa borsa;

	public Giocatore (){

		this.borsa=new Borsa();
		this.cfu=CFU_INIZIALI;
		this.vivo=true; 
	}

	public boolean isVivo() {
		return vivo; 
	}

	public void setVivo() {
		this.vivo=(this.cfu>0); 
	}

	public int getCfu() {
		return this.cfu; 
	}

	public void setCfu(int dcfu) {
		this.cfu = this.cfu + dcfu; 
	}	

	public void setNome(String nome) {
		this.nome=nome;
	}
	public String GetNome() {
		return this.nome;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}
}
