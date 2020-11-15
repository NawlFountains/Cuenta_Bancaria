package TDAPila;
import Exceptions.*;

/**
 * Clase LinkedStack. 
 * Implementa interfaz Stack, con una lista enlazada.
 * @author Nahuel Ignacio Fuentes.
 * @param <E>: Tipo de dato a almacenar en la pila.
 */

public class LinkedStack<E> implements Stack<E> {
	//Atributos de instancia
	protected Node<E> head;		//Referencia al nodo en el tope
	protected int tamano;		//Cantidad de elementos en la pila
	
	/**
	 * Se implementa la clase nodo dentro
	 * de la implementacion para que quede fuera del
	 * alcance de otros programas
	 */
	@SuppressWarnings("hiding")
	private class Node<E> {
		private E element;
		private Node<E> siguiente;
		
		//Constructor
		public Node(E item, Node<E> sig) {
			element=item;
			siguiente=sig;
		}
		
		public Node(E item) {	//Invoca al otro construtor e declara el siguiente nulo, osea nada.
			this(item,null);
		}
		
		//Setter
		
		/**
		 * Setea un nuevo elemento para el nodo
		 * @param elemento para el nodo
		 */
		public void setElemento(E elem) {
			this.element=elem;
		}
		
		/**
		 * Setea un nuevo nodo siguiente para el nodo receptor
		 * @param Nodo siguiente
		 */
		public void setSiguiente(Node <E> siguiente) {
			this.siguiente=siguiente;
		}
		
		//Getters
		
		/**
		 * Retorna el elemento del nodo
		 * @return elemento del nodo
		 */
		public E getElemento() {
			return element;
		}
		
		/**
		 * Retorna una referencia al nodo siguiente
		 * @return nodo siguiente
		 */
		public Node<E> getSiguiente() {
			return siguiente;
		}
	}
	
	//Constructor
	/**
	 * Crea una pila vacia
	 */
	public LinkedStack() {		//inicializo la pila con head == null y tamano 0;
		head=null;
		tamano=0;
	}
	
	//Metodos
	
	/**
	 * Introduce en el tope de la pila
	 * el elemento pasado por parametro
	 * @param Elemento a ingresar
	 */
	public void push(E item) {
		Node<E> aux= new Node<E>(item);
		aux.setElemento(item);
		aux.setSiguiente(head);
		head=aux;
		tamano++;
	}
	/**
	 * Retorna y quita el elemento en el 
	 * tope de la pila, dejando al proximo elemento
	 * en el tope
	 * @return elemento en el tope de la pila
	 * @throws EmptyStackException si la pila esta vacia
	 */
	public E pop() throws EmptyStackException {
		if (isEmpty()) 
			throw new EmptyStackException("Pila vacia");
		else {
			E aux= head.getElemento();
			head= head.getSiguiente();
			tamano--;
			return aux;
		}
	}
	/**
	 * Chequea si la pila esta vacia
	 * @return true si la pila esta vacia, false si pasa lo contrario
	 */
	public boolean isEmpty() {
		return head==null;
	}
	/**
	 * Chequea el tamano de la pila
	 * @return cantidad de elementos en la pila
	 */
	public int size() {
		return tamano;
	}
	/**
	 * Retorna el elemento en el tope de la pila
	 * sin afectar su estado en la pila
	 * @return elemento en el tope de la pila
	 * @throws EmptyStackException si la pila esta vacia
	 */
	public E top() throws EmptyStackException{
		if (isEmpty())
			throw new EmptyStackException("Pila vacia");
		else
			return head.getElemento();
	}
}
