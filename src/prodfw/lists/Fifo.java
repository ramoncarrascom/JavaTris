/**
 * 
 */
package prodfw.lists;

import java.util.LinkedList;

/**
 * Implementación de clase para el manejo de una cola de tipo FIFO (First-In-First-Out)
 * Permite pasar un número límite de elementos, de forma que la adición de nuevos elementos
 * elimina los elementos más antiguos
 * 
 * @author Ramon Carrasco Muñoz
 * @version 0.1 2017/05/15
 *
 */
public class Fifo<T> {

	/**
	 * Lista interna que almacenará la cola
	 */
	private LinkedList<T> miLista;
	
	/**
	 * Indica si la cola tiene un número limitado de elementos
	 */
	private boolean esLimitada=false;
	
	/**
	 * Cantidad máxima de elementos a almacenar en la lista
	 */
	private int limite=0;
	
	/**
	 * Constructor de la clase sin parámetros
	 */
	public Fifo() {
		
		miLista = new LinkedList<T>();
		
	}
	
	/**
	 * Constructor de la clase indicando el máximo número de elementos de la cola
	 * @param maximoElementos int indicando el máximo de elementos de la lista
	 * @throws Exception 
	 */
	public Fifo(int maximoElementos) throws Exception {
		this();
		
		if (maximoElementos<1) {
			throw new Exception("El número de elementos de la lista no puede ser negativo o 0");
		}
		
		esLimitada=true;
		limite=maximoElementos;
		
	}
	
	/**
	 * Añade un elemento a la cola
	 * @param objeto objeto a añadir a la cola
	 */
	public void queue(T objeto) {
		
		if (esLimitada & (miLista.size()==limite)) {
			miLista.removeFirst();
		}
		miLista.add(objeto);
		
	}
	
	/**
	 * Obtiene el primer elemento de la cola, sin eliminarlo
	 * @return primer elemento de la cola
	 */
	public T get() {
		
		if (!miLista.isEmpty()) 
			return miLista.getFirst();
		else
			return null;
		
	}
	
	/**
	 * Obtiene el primer elemento de la cola, y lo elimina
	 * @return primer elemento de la cola
	 */
	public T peek() {
		
		if (!miLista.isEmpty()) 
			return miLista.peekFirst();
		else
			return null;
		
	}
	
	/**
	 * Devuelve un boolean que indica si la cola está vacía
	 * @return devuelve true si la cola está vacía
	 */
	public boolean isEmpty() {
		
		return miLista.isEmpty();
		
	}
	
	/**
	 * Devuelve un array conteniendo los elementos de la cola
	 * @return array conteniendo todos los elementos de la cola
	 */
	public Object[] toArray() {
		
		return miLista.toArray();
		
	}
	
	/**
	 * Devuelve el tamaño de la cola
	 * @return tamaño de la cola
	 */
	public int size() {
		
		return miLista.size();
		
	}
	
	
}
