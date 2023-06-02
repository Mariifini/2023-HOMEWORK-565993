package it.uniroma3.diadia.ambienti;
import java.util.*;


import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.direzioni.Direzione;
import it.uniroma3.diadia.personaggio.AbstractPersonaggio;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base */

public class Stanza {

	//	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	//	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome, AbstractPersonaggio personaggio) {
		this.nome = nome;
		this.attrezzi= new HashMap<>();
		this.stanzeAdiacenti= new HashMap<>();
		this.personaggio= personaggio;
	}
	
	public Stanza(String nome) {
		this(nome, null);
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */

	public String getDescrizione() {
		return this.toString();
	}


	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */

	public Set<String> getAttrezzi() {
		return this.attrezzi.keySet();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {

		if(!attrezzi.containsValue(attrezzo)) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}


	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.getDirezioni());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(attrezzi.values().toString());
		if (this.personaggio==null) risultato.append("\nqui non c'è nessuno a parte te!");
		else risultato.append("\nnon sei solo qui, insieme a te c'è "+ personaggio.toString());
		return risultato.toString();

	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome(), attrezzo);
	}


	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}

	public String stampaDirezioni() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Direzioni disponibili:");
		risultato.append(this.getDirezioni());
		return risultato.toString();
	}
	
	public void addPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio=personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	@Override
	public boolean equals(Object o) {
		Stanza that= (Stanza) o;
		return that.getNome().equals(this.getNome());
	}
	
	public List<Stanza> getStanzeOrdinatePerOggetti(){
		List<Stanza> listaOrdinataPerNumeroOggetti = new ArrayList<>();
		listaOrdinataPerNumeroOggetti.addAll(stanzeAdiacenti.values());
		Collections.sort(listaOrdinataPerNumeroOggetti, new ComparatorePerNumeroOggetti());
		return listaOrdinataPerNumeroOggetti;
				}
}