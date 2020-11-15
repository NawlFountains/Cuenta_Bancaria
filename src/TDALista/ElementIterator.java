package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Exceptions.*;


/**
 * Clase ElemntIterator. 
 * Implementa interfaz Iterator, con una Position.
 * @author Nahuel Ignacio Fuentes.
 * @param <E>: Tipo de dato de la lista.
 */

public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	public ElementIterator(PositionList<E> l) throws EmptyListException {
		lista= l;
		if (lista.isEmpty())		//Chequea si al lista esta vacia
			cursor=null;		//Si esta vacia entonces el cursor es nulo
		else
			cursor= lista.first();		//Sino el cursor se ubica al inicio de la lista
	}
	
	/**
	 * Chequea si existe una posicion siguiente
	 * no nula
	 * @return true si existe una posicion siguiente no nula, false si pasa lo contrario
	 */
	public boolean hasNext() {
		return cursor != null;		
	}
	
	/**
	 * Retorna el elemento siguiente de la posicion
	 * del cursor
	 * @return E elemento de la posicion siguiente
	 * @throws NoSuchElementException si no existe posicion siguiente no nula
	 */
	public E next() throws NoSuchElementException {
		if (cursor ==null)
			throw new NoSuchElementException("Error: No hay siguiente");
		E toReturn=null;
			toReturn = cursor.element();
		try {
			cursor=(cursor==lista.last())?null: lista.next(cursor);
		} catch (EmptyListException|InvalidPositionException|BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
			 
	}
}
