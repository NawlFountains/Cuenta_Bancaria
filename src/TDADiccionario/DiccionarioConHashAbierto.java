package TDADiccionario;

import TDALista.*;

import java.util.Iterator;

import Exceptions.EmptyListException;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;

/*
 * @author Nahuel Ignacio Fuentes
 * 
 * @param <K> tipo que define a las claves de las entradas del diccinoario
 * @param <V> tipo que define a los valores de las entradas del diccinoario
 * 
 */
public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V> {
	//Atributos de instancia
	protected int n;
	protected int N=13;
	protected PositionList<Entry<K,V>> [] A;
	protected static final float FACTOR=0.5F;
	
	//Constructor
	@SuppressWarnings("unchecked")
	public DiccionarioConHashAbierto() {
		A = (PositionList<Entry<K,V>> []) new DoubleLinkedList[N];
		n=0;
		for (int i=0; i<N; i++)
			A[i]= new DoubleLinkedList<Entry<K,V>>();
	}
	
	//Metodos
	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n==0;
	}

	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> toReturn = null;
		if (!A[h(key)].isEmpty())
			try {
				toReturn=A[h(key)].first().element();
			} catch (EmptyListException|InvalidKeyException e) {
				e.printStackTrace();
			}
		return toReturn;
		
	}

	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K,V>> toReturn= new DoubleLinkedList<Entry<K,V>>();
		for (Position<Entry<K,V>> e : A[h(key)].positions()) {
			toReturn.addFirst(e.element());
		}
		return toReturn;
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		if ((n+1)/N>FACTOR)
			reHash();
		Entry<K,V> nuevo= new Entrada<K,V>(key,value);
		A[h(key)].addFirst(nuevo);
		n++;
		return nuevo;
	}

	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		checkEntry(e);
		K key=e.getKey();
		boolean encontrado=false;
		Entry<K,V> toReturn=null;
		try {
			if (A[h(key)].isEmpty())
				throw new InvalidEntryException("La entrada no pertence a este diccionario");
			Iterator<Position<Entry<K,V>>> it= A[h(key)].positions().iterator();
			while (!encontrado && it.hasNext()) {
				Position<Entry<K,V>> p= it.next();
				if (p.element().equals(e)) {
					encontrado=true;
					
				}
			}
		} catch (InvalidKeyException | InvalidEntryException e1) {
			throw new InvalidEntryException("La entrada no pertence a este diccionario");
		}
		if (!encontrado)
			throw new InvalidEntryException("No se encuentra esa entrada en este diccionario");
		n--;
		return toReturn;
	}
	
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> p = new DoubleLinkedList<Entry<K,V>>();
		for (int i=0; i<N; i++) {
			for (Position<Entry<K,V>> e: A[i].positions()) {
				p.addLast(e.element());
			}
		}
		return p;
	}
	
	//Metodos auxiliares
	private int h(K key) throws InvalidKeyException {
		checkKey(key);
		return Math.abs(key.hashCode()%N);
	}
	private void checkEntry(Entry<K,V> e) throws InvalidEntryException{
		if (e==null)
			throw new InvalidEntryException("Entrada nula");
	}
	private void checkKey(K key) throws InvalidKeyException{
		if (key==null)
			throw new InvalidKeyException("Clave nula");
	}
	@SuppressWarnings("unchecked")
	private void reHash() throws InvalidKeyException {
		Iterable<Entry<K,V>> entradas= entries();
		N=nextPrimo(N*2);
		A= new PositionList[N];
		for (int i=0; i<N;i++) {
			A[i]= new DoubleLinkedList<Entry<K,V>>();
		}
		for (Entry<K,V> e: entradas) {
			Entry<K,V> aux= new Entrada<K,V>(e.getKey(),e.getValue());
			A[h(e.getKey())].addFirst(aux);
		}
			
	}
	/**
	 * Busca el proximo numero primo al entero pasado por parametro
	 * @param num entero 
	 * @return numero primo
	 */
	private int nextPrimo(int num) {
		boolean encontre=false;
		int primo=num;
		while(!encontre) {
			if(esPrimo(primo))
				encontre=true;
			else
				primo++;
		}
		return primo;
	}
	
	/**
	 * Verifica si el numero pasado por parametro es un numero primo
	 * @param p numero entero
	 * @return verdadero si p es primo, falso en caso contrario
	 */
	private boolean esPrimo(int p) {
		boolean encontre=true;
		for(int i=2;i<p && encontre;i++)
			if(p%i==0)
				encontre=false;
		return encontre;
	}
	
}
