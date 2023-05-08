package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatorePerPeso implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo p1, Attrezzo p2) {
		if(p1.getPeso()==p2.getPeso())
			return p1.getNome().compareTo(p2.getNome());
		return (p1.getPeso()-p2.getPeso());
	}
}