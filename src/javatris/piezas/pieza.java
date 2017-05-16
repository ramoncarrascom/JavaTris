/**
 * 
 */
package javatris.piezas;

import java.awt.Image;

/**
 * Clase que contiene las propiedades y m�todos comunes para todas las piezas. Todas deber�n
 * heredar de esta clase
 * 
 * @author Ram�n Carrasco Mu�oz
 * @version 0.1 2017/05/14
 *
 */
public class pieza {
	
	/** 
	 * Contiene la definici�n de la forma de la figura
	 */
	protected int forma[][][];
	
	/**
	 * Indica el �ngulo de rotaci�n actual de la pieza, en un n�mero del 0 al 3:
	 * 	0 - 0�
	 *  1 - 90�
	 *  2 - 180�
	 *  3 - 270�
	 */
	private int angulo;
	
	/**
	 * Posici�n de la pieza dentro de la rejilla
	 */
	private int posicionX, posicionY;
	
	/**
	 * Constructor
	 */
	protected pieza(){
		
		// Inicializaci�n de elementos
		forma = new int[4][5][];
		angulo = 0;		
	}
	
	/**
	 * Cambia el �ngulo al siguiente disponible en la pieza
	 */
	public void cambiaAngulo(){
		
		angulo+=1;
		if (angulo==4) angulo=0;
		
	}
	
	/**
	 * Devuelve la posici�n X de la pieza
	 */
	public int dameX() { return posicionX; }
	
	/**
	 * Devuelve la posici�n Y de la pieza
	 */
	public int dameY() { return posicionY; }
	
	/**
	 * Establece la posici�n de la pieza
	 * @param posX es la posici�n de la pieza en el eje X
	 * @param posY es la posici�n de la pieza en el eje Y
	 */
	public void ponPosicion(int posX, int posY)
	{
		posicionX=posX;
		posicionY=posY;
	}
	
	/**
	 * Devuelve la forma correspondiente al �ngulo actual
	 * 
	 * @return array conteniendo la forma
	 */
	public int[][] dameForma() { return forma[angulo]; }
	
}

