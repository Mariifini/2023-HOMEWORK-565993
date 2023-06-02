package it.uniroma3.diadia.giocatore;
import java.util.*;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.*;


public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = Integer.parseInt(Proprietà.getProp("peso_max_borsa"));
	private Map<String, Attrezzo> attrezzi; 
	private int pesoMax;
	private int pesoAtt;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.pesoAtt = 0;
		this.attrezzi= new HashMap<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		boolean a= false;
		if(this.getPesoAtt()+attrezzo.getPeso()<=this.pesoMax) {
			attrezzi.put(attrezzo.getNome(), attrezzo);
			pesoAtt=pesoAtt+attrezzo.getPeso();
			a=true;
		}

		return a;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public int getPesoAtt() {
		return this.pesoAtt;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a=getAttrezzo(nomeAttrezzo);
		this.pesoAtt=pesoAtt-a.getPeso();
		attrezzi.remove(nomeAttrezzo);
		return a;
	}

	public Set<String> getAttrezzi(){
		return this.attrezzi.keySet();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.pesoAtt+"kg/"+this.getPesoMax()+"kg): ");
			s.append(getContenutoOrdinatoPerPeso());
		}
		else
			s.append("Borsa vuota");
		
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> elenco= new ArrayList<>();
		elenco.addAll(attrezzi.values());
		ComparatorePerPeso comp= new ComparatorePerPeso();
		Collections.sort(elenco, comp);

		return elenco;
	}


	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatorePerNome comp= new ComparatorePerNome();
		SortedSet<Attrezzo> set= new TreeSet<>(comp);
		set.addAll(attrezzi.values());

		return set;
	}


	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPeso comp= new ComparatorePerPeso();
		SortedSet<Attrezzo> set= new TreeSet<>(comp);
		set.addAll(attrezzi.values());

		return set;
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		ComparatorePerNome comp= new ComparatorePerNome();
		Set<Attrezzo> temp;
		Map<Integer,Set<Attrezzo>> map= new HashMap<Integer, Set<Attrezzo>>();
		List<Attrezzo> l= new ArrayList<>(attrezzi.values());
		for(Attrezzo attrezzo: l) {
			if(map.containsKey(attrezzo.getPeso())) {
				temp= map.get(attrezzo.getPeso());
				temp.add(attrezzo);
			}
			else {
				temp= new TreeSet<Attrezzo>(comp);
				temp.add(attrezzo);
				map.put(attrezzo.getPeso(), temp);
			}
		}
		
		return map;
	}
	
}
