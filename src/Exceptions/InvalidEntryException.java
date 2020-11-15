package Exceptions;

@SuppressWarnings("serial")
public class InvalidEntryException extends Exception{
	public InvalidEntryException(String msg) {
		/**
		 * Error lanzado al querer remover una
		 * entrada inexistente o invalida
		 */
		super(msg);
	}
}
