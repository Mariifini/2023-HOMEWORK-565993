package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoInteragisci extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().agisci(partita));
		}
		else 
			partita.getIO().mostraMessaggio("questa stanza non ha nessuno con cui interagire!");		
	}

}
