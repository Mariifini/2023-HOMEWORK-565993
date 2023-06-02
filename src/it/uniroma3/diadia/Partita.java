package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;
/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private IO io;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Boolean isFinita;
	private Stanza corrente;

	public Partita(IO io) {
		this.labirinto= new Labirinto();
		this.giocatore= new Giocatore();
		this.corrente=labirinto.getStanzaIniziale();
		this.io= io;
		this.isFinita= false;
	}

	public Partita(IO io, Labirinto labirinto) {
		this.labirinto= labirinto;
		this.giocatore= new Giocatore();
		this.corrente= labirinto.getStanzaIniziale();
		this.io= io;
		this.isFinita= false;
	}
	
	public Stanza getStanzaCorrente() {
		return this.corrente;
	}
	public void setStanzaCorrente(Stanza newCorrente) {
		this.corrente=newCorrente;
	}
	
	public void setIsFinita() {
		if(!giocatore.isVivo()) this.isFinita=true;
		if(getStanzaCorrente()==labirinto.getStanzaVincente()) this.isFinita=true;
	}

	public Boolean getIsFinita() {
		if(!giocatore.isVivo()) 
			io.mostraMessaggio("mi spiace "+giocatore.GetNome()+ ", sei morto MALISSIMO\n");
		else if(getStanzaCorrente()==labirinto.getStanzaVincente()) 
			io.mostraMessaggio("bravissimo "+giocatore.GetNome()+ ", hai VINTO!!\n");
		return this.isFinita;
	}
	
	public IO getIO() {
		return this.io;	
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}