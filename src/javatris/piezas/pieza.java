/**
 * 
 */
package javatris.piezas;

import java.awt.Image;

/**
 * Clase que contiene las propiedades y métodos comunes para todas las piezas. Todas deberán
 * heredar de esta clase
 * 
 * @author Ramón Carrasco Muñoz
 * @version 0.1 2017/05/14
 *
 */
public class pieza {
	
	/** 
	 * Contiene la definición de la forma de la figura
	 */
	protected int forma[][][];
	
	/**
	 * Indica el ángulo de rotación actual de la pieza, en un número del 0 al 3:
	 * 	0 - 0º
	 *  1 - 90º
	 *  2 - 180º
	 *  3 - 270º
	 */
	private int angulo;
	
	/**
	 * Posición de la pieza dentro de la rejilla
	 */
	private int posicionX, posicionY;
	
	/**
	 * Constructor
	 */
	protected pieza(){
		
		// Inicialización de elementos
		forma = new int[4][5][];
		angulo = 0;		
	}
	
	/**
	 * Cambia el ángulo al siguiente disponible en la pieza
	 */
	public void cambiaAngulo(){
		
		angulo+=1;
		if (angulo==4) angulo=0;
		
	}
	
	/**
	 * Devuelve la posición X de la pieza
	 */
	public int dameX() { return posicionX; }
	
	/**
	 * Devuelve la posición Y de la pieza
	 */
	public int dameY() { return posicionY; }
	
	/**
	 * Establece la posición de la pieza
	 * @param posX es la posición de la pieza en el eje X
	 * @param posY es la posición de la pieza en el eje Y
	 */
	public void ponPosicion(int posX, int posY)
	{
		posicionX=posX;
		posicionY=posY;
	}
	
	/**
	 * Devuelve la forma correspondiente al ángulo actual
	 * 
	 * @return array conteniendo la forma
	 */
	public int[][] dameForma() { return forma[angulo]; }
	
}

