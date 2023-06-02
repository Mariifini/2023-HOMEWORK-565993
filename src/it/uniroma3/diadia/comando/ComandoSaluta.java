package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggio.*;
public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
		
		if(partita.getStanzaCorrente().getPersonaggio().getClass().equals(Cane.class))
			partita.getIO().mostraMessaggio("ciao bel cagnolone!");

		else 
			partita.getIO().mostraMessaggio("Salve, mi chiamo " + partita.getGiocatore().GetNome() + " e mi sono perso.");

	partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		}
		else
			partita.getIO().mostraMessaggio("questa stanza è vuota...");
	}

}
