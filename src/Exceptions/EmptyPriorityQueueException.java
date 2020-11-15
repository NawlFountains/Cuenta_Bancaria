package Exceptions;

@SuppressWarnings("serial")
public class EmptyPriorityQueueException extends Exception {
	public EmptyPriorityQueueException(String msg) {
		/**
		 * Esta excepcion se lanza cuando se intenta alterar
		 * una PriorityQueue pero esta se encuentra vacia
		 */
		super(msg);
	}
}
