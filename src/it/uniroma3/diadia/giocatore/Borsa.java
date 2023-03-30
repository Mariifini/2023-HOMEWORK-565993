package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
public final static int DEFAULT_PESO_MAX_BORSA = 10;
private Attrezzo[] attrezzi;
private int numeroAttrezzi;
private int pesoMax;
public Borsa() {
this(DEFAULT_PESO_MAX_BORSA);
}
public Borsa(int pesoMax) {
this.pesoMax = pesoMax;
this.attrezzi = new Attrezzo[10]; 
this.numeroAttrezzi = 0;
}
public boolean addAttrezzo(Attrezzo attrezzo) {
if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
return false;
if (this.numeroAttrezzi==10)
return false;
this.attrezzi[this.numeroAttrezzi] = attrezzo;
this.numeroAttrezzi++;
return true;
}
public int getPesoMax() {
return pesoMax;
}
public Attrezzo getAttrezzo(String nomeAttrezzo) {
Attrezzo a = null;
for (int i= 0; i<this.numeroAttrezzi; i++)
if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
a = attrezzi[i];

return a;
}
public int getPeso() {
int peso = 0;
for (int i= 0; i<this.numeroAttrezzi; i++)
peso += this.attrezzi[i].getPeso();

return peso;
}
public boolean isEmpty() {
return this.numeroAttrezzi == 0;
}
public boolean hasAttrezzo(String nomeAttrezzo) {
return this.getAttrezzo(nomeAttrezzo)!=null;
}
public Attrezzo removeAttrezzo(String nomeAttrezzo) {//nomeAttrezzo=nome he gli passa il metodo
Attrezzo a = null;
/*scorro tra gli attrezzi e quando trovo quello da togliere lo rimuovo*/
int i=0; //indice array;
while( i<this.attrezzi.length && a==null) {
	if (this.attrezzi[i]!= null && this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
	a=this.attrezzi[i];
	/*per evitare il nullpointer devo riordinare gli attrezzi scalandoli quando viene rimosso l'attrezzo*/
	for(int j=i; j<numeroAttrezzi; j++) {
		this.attrezzi[j]=this.attrezzi[j+1];}
	numeroAttrezzi--;}
	i++;//senno prende sempre e il primo
	}
return a;
}//remove attrezzo va messo anche dentro stanza
public String toString() {
StringBuilder s = new StringBuilder();

if (!this.isEmpty()) {
s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
for (int i= 0; i<this.numeroAttrezzi; i++)
s.append(attrezzi[i].toString()+" ");
}
else
s.append("Borsa vuota");
return s.toString();
}
}
