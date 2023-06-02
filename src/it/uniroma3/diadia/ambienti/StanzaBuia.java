package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoNecessario;
	
	public StanzaBuia(String Nome, String Attrezzo) {
		super(Nome);
		this.attrezzoNecessario=Attrezzo;
	}
	
@Override
public String getDescrizione() {
	if(!this.hasAttrezzo(attrezzoNecessario)) 
		return this.toString();
	else
		return super.toString();
}

@Override
public String toString() {
	StringBuilder risultato = new StringBuilder();
	risultato.append("Qui c'è un buio pesto...");
	return risultato.toString();
}

}
