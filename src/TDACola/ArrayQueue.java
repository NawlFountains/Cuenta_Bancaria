package TDACola;
import Exceptions.*;
/**
 * Clase ArrayQueue. 
 * Implementa interfaz Queue, con una cola circular.
 * @author Ezequiel Ramírez Beltrán.
 * @author Dmytro Shkolyar.
 * @param <E>: Tipo de dato a almacenar en la cola.
 */
public class ArrayQueue<E> implements Queue<E> {

	protected E[] q;
	protected int f,r;
	
	/**
	 * Constructor de ArrayQueue.
	 * 
	 * Inicializa :
	 *  el arreglo de elementos con 10 espacios disponibles.
	 *  f y r en 0.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		q = (E[]) new Object[10];
		f = 0;
		r = 0;
	}
	
	/**
	 * Retorna la cantidad de elementos en la cola
	 * @return entero cantidad de elementos de la cola.
	 */
	public int size() {
		return((q.length - f + r) % q.length) ;
	}

	/**
	 * Chequea si al cola esta vacia
	 * @return true si la cola esta vacia, false si no.
	 */
	public boolean isEmpty() {
		return f == r;
	}
	
	/**
	 * Chequea y retorna, sin eliminar, el elemento al frente de la cola
	 * @return elemento al frente de la cola
	 * @throws EmptyQueueException si la cola esta vacia
	 */
	public E front() throws EmptyQueueException {
		if(isEmpty()) {
			throw new EmptyQueueException("Cola vacía.");
		}
		else {
			return q[f];
		}
	}

	/**
	 * Encola el elemento pasa por parametro al final de la cola
	 * @param elemento ingresado al final de la cola
	 */
	public void enqueue(E element) {
		if(size() == q.length-1) {
			E[] aux = copiar(f);
			r = size();
			f = 0;
			q = aux;
		}
			q[r] = element;
			r = (r + 1) % q.length;
	}
	
	/**
	 * Duplica el tamaño del arreglo de la cola circular, e inserta en el los elementos que contenía 
	 * anteriormente.
	 * @param start posición en el arreglo, a partir del cual se realizará la copia de los elementos.
	 * @return arreglo de elementos con un el doble de tamaño que el que se tenía anteriormente. 
	 */
	private E[] copiar(int start) {
		int j = 0;
		@SuppressWarnings("unchecked")
		E[] aux = (E[]) new Object[q.length*2];
		for(int i = f;!(start == r);i++) {
			start = i % q.length;
			aux[j++] = q[start];
		}
		return aux;
	} 

	
	/**
	 * Eliminar y retorna el primer elemento de la cola
	 * @return primer elemento de la cola
	 * @throws EmptyQueueException si al cola esta vacia
	 */
	public E dequeue() throws EmptyQueueException {
		if( isEmpty()) {
			throw new EmptyQueueException("Cola vacía.");
		}
		else {
			E temp = q[f];
			q[f] = null;
			f = (f + 1) % (q.length);
			return temp;
		}
	}
}