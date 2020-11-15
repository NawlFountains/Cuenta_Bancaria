package Programa;
import java.util.Deque;

/*
 * Clase Cuenta 
 * Crea una cuenta con un apellido
 * e inicia su saldo y historial en
 * 0 y vacio, respectivamente
 */

import TDADeque.*;

/**
 * Clase Cuenta
 * Crea y provee metodos para manejar una cuenta bancaria.
 * @author Nahuel Ignacio Fuentes.
 */
public class Cuenta {
	//Atributos de instancia
	protected String apellido;
	protected float saldo;
	protected LinkedDeque<Float> historial;
	
	//Constructor
	public Cuenta(String apellido){
		this.apellido=apellido;
		saldo=0;
		historial= new LinkedDeque<Float>();
	}
	
	//metodos
	
	/*
	 * Aumenta la cantidad de fondos del saldo
	 * con el monto pasado por parametro
	 * y lo agrega al historial
	 * @param float monto a depositar
	 */
	public void deposito(float monto) {
		historial.addFirst(monto);
		saldo=saldo+monto;
	}
	
	/*
	 * Reduce la cantidad de fondos del saldo
	 * con el monto pasado por parametro
	 * y lo agrega al historial
	 * @param float monto a extraer
	 */
	public void extraccion(float monto) {
		historial.addFirst(monto);
		saldo=saldo-Math.abs(monto);
	}
	
	/*
	 * Chequea si existe una operacion reciente
	 * , si existe la retorna
	 * @return float operacion reciente
	 */
	public float operacionReciente() {
		return historial.getFirst();
	}
	
	/*
	 * Chequea si existe una operacion antigua
	 * , si existe la retorna
	 * @return float operacion mas antigua
	 */
	public float operacionAntigua() {
		return historial.getLast();
	}
	
	/*
	 * Retorna el apellido de la 
	 * cuenta creada
	 * @return String apellido
	 */
	public String getApellido() {
		return apellido;
	}
	
	/*
	 * Retorna el saldo actual de
	 * la cuenta receptora del mensaje
	 * @return float saldo actual.
	 */
	public float getSaldo() {
		return saldo;
	}
	
	/*
	 * Devuelve una estructura auxiliar para
	 * poder ver el historial
	 */
	public Deque<Float> getHistorial(){
		return historial;
	}
}
