/**
 * 
 */
package javatris.piezas;

/**
 * Clase que contiene las propiedades y m�todos comunes para todas las piezas. Todas deber�n
 * heredar de esta clase
 * 
 * @author Ram�n Carrasco Mu�oz
 * @version 0.1 2017/05/14
 *
 */
public class Pieza {
	
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
	 * Constructor sin par�metros
	 */
	protected Pieza(){
		
		// Inicializaci�n de elementos
		forma = new int[4][5][];
		angulo = 0;	
	}
	
	/**
	 * Constructor indicando la posici�n inicial de la pieza
	 * @param posX posici�n inicial X de la pieza
	 * @param posY posici�n inicial Y de la pieza
	 */
	protected Pieza(int posX, int posY)
	{
		this();
		
		posicionX = posX;
		posicionY = posY;
	}
	
	/**
	 * Cambia el �ngulo al siguiente disponible en la pieza
	 */
	public void aumentaAngulo(){		
		angulo+=1;
		if (angulo==4) angulo=0;		
	}
	
	/**
	 * Disminuye el �ngulo al anterior disponible en la pieza
	 */
	public void disminuyeAngulo(){
		angulo-=1;
		if (angulo<0) angulo=3;
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
	
	/**
	 * Hace que la pieza caiga 1 posici�n
	 */
	public void cae()
	{
		posicionY+=1;
	}
	
	/**
	 * Devuelve el alto real de la pieza en su �ngulo actual
	 * @return n�mero de casillas que ocupa en vertical
	 */
	public int dameAlto()
	{
		int[][] formaActual; 	// Array para almacenar la forma actual
		int filaActual;			// contador para recorrer todas las filas de la forma
		boolean encontrado;		// tomar� valor true cuando se encuentre una fila con casilla
		
		formaActual=forma[angulo];
		filaActual=5;
		encontrado=false;
		
		while (!encontrado && filaActual>=1)
		{
			for (int columnaActual=1; columnaActual<=5; columnaActual++)
				if (formaActual[filaActual-1][columnaActual-1]==1)
					encontrado=true;
			
			if (!encontrado) filaActual--;
		}
		
		return filaActual;
	}
	
}

