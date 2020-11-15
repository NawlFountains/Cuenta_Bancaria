package TDALista;
import java.util.Iterator;

/**
 * Clase DoubleLinkedList. 
 * Implementa interfaz PositionList, con una lista doblemente enlazada.
 * @author Nahuel Ignacio Fuentes.
 * @param <E>: Tipo de dato a almacenar en la lista.
 */

import Exceptions.*;

/**
 * Clase DoubleLinkedList
 * Implementa la interfaz PositionList, con nodos centinelas head y tail.
 * @author Nahuel Ignacio Fuentes.
 * @param <E>: Tipo de dato a almacenar en la lista.
 */
public class DoubleLinkedList<E> implements PositionList<E> {
	//Atributos de instancia
	protected DNode<E> head,tail;		//Nodos centinelas cabeza y fin respectivamente
	protected int size;			//Referencia al tamano de la lista
		
	/**
	 * Se crea la clase nodo dentro de la implementacion
	 * de la lista para que no sea accesible para 
	 * el exterior del programa
	 */
	@SuppressWarnings("hiding")
	private class DNode<E> implements Position<E> {
		//Atributos de instancia
		private DNode<E> next,prev;
		private E element;
		
		//Constructor
		public DNode(DNode<E> newPrev,DNode<E> newNext, E e) {
			prev=newPrev;
			next=newNext;
			element=e;
		}
		
		public E element() {
			return element;
		}
		//getters
		public DNode<E> getNext(){
			return next;
		}
		public DNode<E> getPrev(){
			return prev;
		}
		//setters
		public void setNext(DNode<E> newNext) {
			next=newNext;
		}
		public void setPrev(DNode<E> newPrev) {
			prev=newPrev;
		}
		public void setElement(E newElement) {
			element=newElement;
		}
	}

	
	//Constructor
	public DoubleLinkedList() {
		head=new DNode<E>(null,null,null);
		tail=new DNode<E>(head,null,null);
		size=0;
		head.setNext(tail);
	}
	//Metodos
	
	/**
	 * Chequea si la lista esta vacia
	 * @return True si la lista esta vacia, false si lo contrario
	 */
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * Consulta el tamano de la lista
	 * @return tamano de la lista
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Retorna la posicion del primer elemento de la lista
	 * @return Posicion del primer elemento de la lista
	 * @throws EmptyListException si la lista esta vacia
	 */
	public Position<E> first() throws EmptyListException{
		if (isEmpty())
			throw new EmptyListException("First:: Lista Vacia");
		return head.getNext();
	}
	
	/**
	 * Retorna la posicion del ultimo elemento de la lista
	 * @return Posicion del ultimo elemento de la lista
	 * @throws EmptyListException si la lista esta vacia
	 */
	public Position<E> last() throws EmptyListException{
		if (isEmpty()) 
			throw new EmptyListException("Last:: Lista vacia");
		return tail.getPrev();
	}
	
	/**
	 * Devuelve la posicion siguiente a la
	 * posicion ingresada por el usuario
	 * @param posicion
	 * @return posicion siguiente
	 * @throws BoundaryViolationException si la posicion ingresada es la ultima de la lista
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia
	 */
	public Position<E> next(Position<E> p) throws BoundaryViolationException, InvalidPositionException{
		DNode<E> v= checkPosition(p);
		DNode<E> next= v.getNext();
		if (next==tail)
			throw new BoundaryViolationException("No se puede ir despues del ultimo elemento de la lista");
		return next;
	}
	
	/**
	 * Devuelve la posicion previa a la
	 * posicion ingresada por el usuario
	 * @param posicion
	 * @return posicion previa
	 * @throws BoundaryViolationException si la posicion ingresada es la primera de la lista
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		DNode<E> v= checkPosition(p);
		DNode<E> prev= v.getPrev();
		if (prev==head)
			throw new BoundaryViolationException("No se puede ir antes del primer elemento de la lista");
		return prev;
	}
	//Setters
	
	/*
	 * Agrega un elemento al inicio de la lista
	 * @param E elemento a ingresar
	 */
	public void addFirst(E elem) {
		size++;
		DNode<E> newNode= new DNode<E>(head,head.getNext(),elem);
		head.getNext().setPrev(newNode);
		head.setNext(newNode);
	}
	
	/*
	 * Agrega un elemento al inicio de la lista
	 * @param E elemento a ingresar
	 */
	public void addLast(E elem) {
		size++;
		DNode<E> newNode= new DNode<E>(tail.getPrev(),tail,elem);
		tail.getPrev().setNext(newNode);
		tail.setPrev(newNode);
	}
	
	/*
	 * Agrega un elemento en la posicion previa
	 * a la posicion ingresada
	 * @param Posicion
	 * @param E elemento
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia
	 */
	public void addBefore(Position<E> p, E elem) throws InvalidPositionException {
		DNode<E> v = checkPosition(p);
		size++;
		DNode<E> newNode = new DNode <E>(v.getPrev(), v, elem);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}
	
	/*
	 * Agrega un elemento en la posicion siguiente
	 * a la posicion ingresada
	 * @param Posicion
	 * @param E elemento
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia
	 */
	public void addAfter(Position<E> p, E elem) throws InvalidPositionException {
		DNode<E> v= checkPosition(p);
		size++;
		DNode<E> newNode = new DNode<E>(v,v.getNext(),elem);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}
	
	/*
	 * Remueve una posicion de la lista y
	 * retorna el valor de su elemento
	 * @param posicion a eliminar
	 * @return elemento de la posicion eliminada
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia
	 */
	public E remove(Position<E> p) throws InvalidPositionException{
		DNode<E> v = checkPosition(p);
		size--;
		DNode<E> vPrev= v.getPrev();
		DNode<E> vNext= v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		E vElem= v.element();
		v.setNext(null);
		v.setPrev(null);
		return vElem;
	}
	
	/**
	 * Remplaza el valor de la posicion pasa por parametro
	 * con el valor pasado por parametro
	 * @param Posicion a remplazar valor
	 * @param Valor nuevo
	 * @return Valor remplazado
	 * @throws InvalidPositionException si la posicion es invalida o la lista esta vacia
	 */
	public E set(Position<E> p, E elem) throws InvalidPositionException{
		DNode<E> v= checkPosition(p);
		E old = v.element();
		v.setElement(elem);
		return old;
	}
	
	/**
	 * Retorna un iterador de los elementos de la lista
	 * @return iterador de los elementos de la lsita
	 */
	public Iterator<E> iterator(){
		ElementIterator<E> aux=null;
		try {
			 aux =new ElementIterator<E>(this);
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return aux;
	}
	
	/**
	 * Retorna una coleccion iterable de las posiciones de la lista
	 * @return coleccion iterable de posiciones
	 */
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> p= new DoubleLinkedList<Position<E>>();
		if (!isEmpty()) {
			Position<E> pos = head.getNext();
			try {
				while( pos != last()) {
					p.addLast(pos);
					pos=next(pos);
				}
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
			p.addLast(pos);
		}
		return p;
		
	}
	
	//Metodo auxiliar
	
	/**
	 * Chequea que si una posicion es valida y si lo es
	 * la retorna como un nodo
	 * @param Posicion de una lista
	 * @return la posicion ingresada convertida en nodo
	 * @throws InvalidPositionException si la posicion es nula o no pertenece a la lista
	 */
	protected DNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p==null)
			throw new InvalidPositionException("Posicion nula");
		if (p==head)
			throw new InvalidPositionException("La cabeza de la lista no es una posicion valida");
		if (p==tail)
			throw new InvalidPositionException("LA cola de la lista no es una posicion vlaida");
		try {
			DNode<E> temp= (DNode<E>)p;
			if ((temp.getPrev()==null) || (temp.getNext()==null))
				throw new InvalidPositionException ("Posicion no poertenece a una lista valida");
			return temp;}
		catch(ClassCastException e) {
			throw new InvalidPositionException("La posicion es de un tipo distinto al de la lista");
		}
		}
			
	}
