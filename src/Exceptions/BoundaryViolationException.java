package Exceptions;

@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception {
	public BoundaryViolationException(String msg) {
		/**
		 * Cuando se intenta acceder a la posicion para 
		 * settear un elemento o remover esa posicion
		 * y esa posicion ya fue removida
		 */
		super(msg);
	}
}
