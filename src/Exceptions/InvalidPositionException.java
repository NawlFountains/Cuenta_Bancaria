package Exceptions;

@SuppressWarnings("serial")
public class InvalidPositionException extends Exception{
	public InvalidPositionException(String msg) {
		/**
		 * Lanza error si la posicion de una lista
		 * se intenta referenciar y esta no existe o fue eliminada
		 */
		super(msg);
	}
}
