package TDADiccionario;

import TDALista.*;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;

public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V> {
	//Atributos de instancia
	protected int n;
	protected int N=13;
	protected Dictionary<K,V> [] A;
	protected static final float FACTOR=0.5F;
	
	//Constructor
	@SuppressWarnings("unchecked")
	public DiccionarioConHashAbierto() {
		A = (Dictionary<K,V> []) new DiccionarioConLista[N];
		n=0;
		for (int i=0; i<N; i++)
			A[i]= new DiccionarioConLista<K,V>();
	}
	
	//Metodos
	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n==0;
	}

	public Entry<K, V> find(K key) throws InvalidKeyException {
		return A[h(key)].find(key);
	}

	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		return A[h(key)].findAll(key);
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if ((n+1)/N>FACTOR)
			reHash();
		Entry<K,V> e= A[h(key)].insert(key, value);
		n++;
		return e;
	}

	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		checkEntry(e);
			try {
				Entry<K, V> t = A[h(e.getKey())].remove(e);
				if (t!=null)
					n--;
				return t;
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return null;
	}
	
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> p = new DoubleLinkedList<Entry<K,V>>();
		for (int i=0; i<N; i++) {
			for (Entry<K,V> e: A[i].entries()) {
				p.addLast(e);
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
		A= new Dictionary[N];
		for (int i=0; i<N;i++) {
			A[i]= new DiccionarioConLista<K,V>();
		}
		for (Entry<K,V> e: entradas) {
			A[h(e.getKey())].insert(e.getKey(), e.getValue());
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
