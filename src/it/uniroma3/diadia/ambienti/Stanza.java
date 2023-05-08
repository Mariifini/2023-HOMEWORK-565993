
package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
//	
//	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
//	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
//	
	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	
//    private Attrezzo[] attrezzi;
//    private int numeroAttrezzi;
//    private Stanza[] stanzeAdiacenti;
//    private int numeroStanzeAdiacenti;
//	private String[] direzioni;
//    
	
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.nome = nome;
		this.attrezzi= new HashMap<>();
		this.stanzeAdiacenti= new HashMap<>();
//        this.numeroStanzeAdiacenti = 0;
//        this.numeroAttrezzi = 0;
//        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
//        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
//        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
//        boolean aggiornato = false;
//    	for(int i=0; i<this.direzioni.length; i++)
//        	if (direzione.equals(this.direzioni[i])) {
//        		this.stanzeAdiacenti[i] = stanza;
//        		aggiornato = true;
//        	}
//    	if (!aggiornato)
//    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
//    			this.direzioni[numeroStanzeAdiacenti] = direzione;
//    			this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
//    		    this.numeroStanzeAdiacenti++;
//    		}
    	this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
//        Stanza stanza = null;
//		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
//        	if (this.direzioni[i].equals(direzione))
//        		stanza = this.stanzeAdiacenti[i];
//        return stanza;
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
//        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
//        	this.attrezzi[numeroAttrezzi] = attrezzo;
//        	this.numeroAttrezzi++;
//        	return true;
//        }
//        else {
//        	return false;
//        }
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
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	risultato.append(this.getDirezioni());
    	risultato.append("\nAttrezzi nella stanza: ");
//    	for (String direzione : this.direzioni)
//    		if (direzione!=null)
//    			risultato.append(" " + direzione);
    	
    	/*PROBLEMA NULLPOINTER
		 *si potrebbe mettere un ciclo di controlla con attrezzo!=null 
		 * MA ESSSENDO SCELTA COMPATTA-> ELIMINARE IL toString
		 *  prima c'era
		 *  for (Attrezzo attrezzo : this.attrezzi) {
			risultato.append(attrezzo.toString()+" ");}
		ora cambio con --- e ricambio per hw3*/
//		for (int i=0; i<this.numeroAttrezzi;i++) {
//		risultato.append(this.attrezzi[i]+" ");
//	}
//	return risultato.toString();
    	risultato.append(attrezzi.values().toString());
		
		return risultato.toString();
}

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
//	public boolean hasAttrezzo(String nomeAttrezzo) {
//		boolean trovato;
//		trovato = false;
//		for (Attrezzo attrezzo : this.attrezzi) {
//			if (attrezzo!=null && attrezzo.getNome().equals(nomeAttrezzo))
//				trovato = true;
//		}
//		return trovato;
//	}
    public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
//		Attrezzo attrezzoCercato;
//		attrezzoCercato = null;
//		for (Attrezzo attrezzo : this.attrezzi) {
//			//aggiunta la verifica dell'oggetto !=NULL per evitare null pointer
//			if (attrezzo!=null&&attrezzo.getNome().equals(nomeAttrezzo))
//				attrezzoCercato = attrezzo;
//		}
//		return attrezzoCercato;	
		return attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()) != null;
	}
	


	public Set<String> getDirezioni() {
//		String[] direzioni = new String[this.numeroStanzeAdiacenti];
//	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
//	    	direzioni[i] = this.direzioni[i];
//	    return direzioni;
		return this.stanzeAdiacenti.keySet();
    }
	
	
	@Override
	public boolean equals(Object o) {
		Stanza that= (Stanza) o;
		return that.getNome().equals(this.getNome());
	}

	

}