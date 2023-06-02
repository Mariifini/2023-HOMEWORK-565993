package it.uniroma3.diadia.personaggio;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	final private String RISATA_MALEFICA ="MUAHAAHAHAHAHAH";
	final private String SPOSTAMENTO_STANZA_CON_PIU_OGGETTI= "ma visto che mi hai salutato ti manderò nella stanza con più oggetti";
	final private String SPOSTAMENTO_STANZA_CON_MENO_OGGETTI=" e visto che non mi hai salutato ti manderò nella stanza con meno oggetti";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		StringBuilder risposta =
				new StringBuilder("Mi stai disturbando, vattene ");

		if(!haSalutato()) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzeOrdinatePerOggetti().get(0));
			risposta.append(SPOSTAMENTO_STANZA_CON_MENO_OGGETTI);
		}
		else {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzeOrdinatePerOggetti().get(partita.getStanzaCorrente().getStanzeOrdinatePerOggetti().size()-1));
			risposta.append(SPOSTAMENTO_STANZA_CON_PIU_OGGETTI);
		}
		return risposta.toString();
	}

	@Override
	public void riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getIO().mostraMessaggio(RISATA_MALEFICA);
	}

}
