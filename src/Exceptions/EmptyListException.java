package Exceptions;

@SuppressWarnings("serial")
public class EmptyListException extends Exception {
	public EmptyListException(String msg) {
		/**
		 * Ocurre cuando la lista que se 
		 * intenta acceder esta vacia
		 */
		super(msg);
	}
	
}
