package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {
		
		static final private int NUMERO_MASSIMO_DIREZIONI = 4;
		static final public int NUMERO_MASSIMO_ATTREZZI = 10; //PRIMA ERA PRIVATE MA LO METTO PUBLIC PERCHE POTREBBE SERVIRE ANCHE DA ALTRE PARTI (ESEMPIO TEST);
		
		private String nome;
	    protected Attrezzo[] attrezzi;// solo la prima parte non nulla;
	    /* un array, unico strumento per modellare le collezioni*/
	    protected int numeroAttrezzi;//contatore di riempimento dell array sopra; per una rappresentazione compatta ( per levare il nullpoint;)
	    /*vantaggi e svantaggi rappresentazione compatta e/o non:
	     * compatta: scorrimento piu semplice ; finiti gli attrezzi ci fermiamo;
	     *  MA se eliminiamo un attrezzo -> riorganizzazione 
	     * non compatta: non devo riorganizzare MA svolgo sempre tutte le operazioni,
	     * ovvero la scansione e' sempre completa; 
	     * DALLA RIGA 110 CAPISCO CHE LA RAPP SCELTA QUI COMPATTA*/
	    
	    private StanzaProtected[] stanzeAdiacenti;
	    private int numeroStanzeAdiacenti;
		private String[] direzioni;
	    
	    /**
	     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	     * @param nome il nome della stanza
	     */
	    public StanzaProtected(String nome) {
	        this.nome = nome;
	        this.numeroStanzeAdiacenti = 0;
	        this.numeroAttrezzi = 0;
	        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
	        this.stanzeAdiacenti = new StanzaProtected[NUMERO_MASSIMO_DIREZIONI];
	        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	    }

	    /**
	     * Imposta una stanza adiacente.
	     *
	     * @param direzione direzione in cui sara' posta la stanza adiacente.
	     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	     */
	    public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
	        boolean aggiornato = false;
	    	for(int i=0; i<this.direzioni.length; i++)
	        	if (direzione.equals(this.direzioni[i])) {
	        		this.stanzeAdiacenti[i] = stanza;
	        		aggiornato = true;
	        	}
	    	if (!aggiornato)
	    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
	    			this.direzioni[numeroStanzeAdiacenti] = direzione;
	    			this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
	    		    this.numeroStanzeAdiacenti++;
	    		}
	    }

	    /**
	     * Restituisce la stanza adiacente nella direzione specificata
	     * @param direzione
	     */
		public StanzaProtected getStanzaAdiacente(String direzione) {
	        StanzaProtected stanza = null;
			for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	        	if (this.direzioni[i].equals(direzione))
	        		stanza = this.stanzeAdiacenti[i];
	        return stanza;
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
	    public Attrezzo[] getAttrezzi() {
	        return this.attrezzi;
	    }

	    /**
	     * Mette un attrezzo nella stanza.
	     * @param attrezzo l'attrezzo da mettere nella stanza.
	     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	     */
	    public boolean addAttrezzo(Attrezzo attrezzo) {
	        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
	        	this.attrezzi[numeroAttrezzi] = attrezzo;
	        	this.numeroAttrezzi++;
	        	return true;
	        }
	        else {
	        	return false;
	        }
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
	    	for (String direzione : this.direzioni)
	    		if (direzione!=null)
	    			risultato.append(" " + direzione);
	    	risultato.append("\nAttrezzi nella stanza: ");
	    		for (int i=0; i<this.numeroAttrezzi;i++) {
	    		risultato.append(this.attrezzi[i]+" ");
	    	}
	    	return risultato.toString();
	    }

	    /**
		* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
		* @return true se l'attrezzo esiste nella stanza, false altrimenti.
		*/
		public boolean hasAttrezzo(String nomeAttrezzo) {
			boolean trovato;
			trovato = false;
			for (Attrezzo attrezzo : this.attrezzi) {
				if (attrezzo!=null && attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
			return trovato;
		}

		/**
	     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
		 * @param nomeAttrezzo
		 * @return l'attrezzo presente nella stanza.
	     * 		   null se l'attrezzo non e' presente.
		 */
		public Attrezzo getAttrezzo(String nomeAttrezzo) {
			Attrezzo attrezzoCercato;
			attrezzoCercato = null;
			for (Attrezzo attrezzo : this.attrezzi) {
				if (attrezzo!=null && attrezzo.getNome().equals(nomeAttrezzo))
					attrezzoCercato = attrezzo;
			}
			return attrezzoCercato;	
		}

		/**
		 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
		 * @param nomeAttrezzo
		 * @return true se l'attrezzo e' stato rimosso, false altrimenti
		 */
		public boolean removeAttrezzo(Attrezzo attrezzo) {
			for (int i=0; i< this.attrezzi.length;i++) {
				if (this.attrezzi[i] !=null && this.attrezzi[i].getNome().equals(attrezzo.getNome())) {
				this.attrezzi[i]=null;
				return true; //non ho bisogno di andare avanti perche l ho trovato
			}
		}
			return false;
		}


		public String[] getDirezioni() {
			String[] direzioni = new String[this.numeroStanzeAdiacenti];
		    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
		    	direzioni[i] = this.direzioni[i];
		    return direzioni;
	    }

	}
