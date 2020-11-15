package Exceptions;
/**
 * Clase EmptyStackException
 * @author Nahuel Fuentes
 */
@SuppressWarnings("serial")
public class EmptyStackException extends Exception{
	public EmptyStackException(String msg) {
		/**
		 * Excepcion ejectuada si se desea realizar la operacion
		 * pop o top de una pila vacia.
		 * @param s: mensaje que le queremos mandar al constructor de Exception.
		 */
		super(msg);
	}
}
