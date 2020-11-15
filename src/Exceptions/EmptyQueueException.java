package Exceptions;
/**
 * Clase EmptyQueueException
 * @author Nahuel Fuentes
 */

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {
	
	public EmptyQueueException(String msg) {
		/**
		 * Excepcion ejectuada si se desea realizar la operacion
		 * dequeue o front de una pila vacia.
		 * @param s: mensaje que le queremos mandar al constructor de Exception.
		 */
		super(msg);
	}
}
