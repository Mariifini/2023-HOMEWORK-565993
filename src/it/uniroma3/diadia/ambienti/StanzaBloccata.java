package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String attrezzoSbloccante;
	private String direzioneBloccata;


	public StanzaBloccata(String nome,String attrezzoSbloccante, String direzioneBloccata ) {
		super(nome);
		this.attrezzoSbloccante=attrezzoSbloccante;
		this.direzioneBloccata=direzioneBloccata;
	}
	@Override
	public Stanza getStanzaAdiacente(String direzione) {//posso andare in adiacente solo se la direzione non  bloccata e se ho l attrezzo sbloccante
		if(direzione.equals(this.direzioneBloccata)&&!super.hasAttrezzo(attrezzoSbloccante)) //has attrezzo della classe superiore
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		String bloccata = "Stanza bloccata nella direzione: " +direzioneBloccata+" \n Prendi il " + attrezzoSbloccante + " e posalo nella stanza";

		if(!this.hasAttrezzo(attrezzoSbloccante))
			return bloccata;
		return super.getDescrizione();
	}
}

