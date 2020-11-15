package TDADiccionario;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.*;


public class DiccionarioConLista<K,V> implements Dictionary<K,V> {
	//Atributos de instancia
	protected DoubleLinkedList<Entrada<K,V>> lista;
	
	//Constructor
	public DiccionarioConLista(){
		lista= new DoubleLinkedList<Entrada<K,V>>();
	}
	//Metodos
	public int size() {
		return lista.size();
	}
	public boolean isEmpty() {
		return lista.isEmpty();
	}
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		try {
			Position<Entrada<K,V>> p=lista.first();
			while (p!=lista.last()){
				if (p.element().getKey().equals(key))
					return p.element();
				p=lista.next(p);
			}
			if (p.element().getKey().equals(key))
				return p.element();
		} catch (EmptyListException|InvalidPositionException|BoundaryViolationException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		DoubleLinkedList<Entry<K,V>> aux= new DoubleLinkedList<Entry<K,V>>();
		for (Entry<K,V> e: entries()) {
			if (e.getKey().equals(key))
				aux.addLast(e);
		}
		return aux;
	}
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entrada<K,V> e= new Entrada<K,V>(key,value);
		lista.addFirst(e);
		return e;
	}
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		checkEntry(e);
		if (lista.isEmpty())
			throw new InvalidEntryException("Entrada no esta en el diccionario");
		try {
			Position<Entrada<K,V>> p = lista.first();
			while (p!=lista.last()) {
				if (p.element().equals(e)) {
					Entry<K,V> aux = p.element();
					lista.remove(p);
					return aux;
				}
				p=lista.next(p);
			}
			if (p.element().equals(e)) {
				Entry<K,V> aux = p.element();
				lista.remove(p);
				return aux;
			}
			throw new InvalidEntryException("Entrada no esta en el diccionario");
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	public Iterable<Entry<K, V>> entries() {
		DoubleLinkedList<Entry<K,V>> p = new DoubleLinkedList<Entry<K,V>>();
		if (!isEmpty()) {
			try {
			Position<Entrada<K,V>> pos = lista.first();
				while( pos != lista.last()) {
					p.addLast(pos.element());
					pos=lista.next(pos);
				}
				p.addLast(pos.element());
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return p;
	}
	//Metodos auxiliares
	private void checkKey(K key) throws InvalidKeyException {
		if (key==null) 
			throw new InvalidKeyException("Clave nula");
	}
	private void checkEntry(Entry<K,V> e) throws InvalidEntryException{
		if (e==null)
			throw new InvalidEntryException("Entry nula");
	}
}
