/**
 * 
 */
package javatris.piezas;

/**
 * Clase que contiene las propiedades y métodos comunes para todas las piezas. Todas deberán
 * heredar de esta clase
 * 
 * @author Ramón Carrasco Muñoz
 * @version 0.1 2017/05/14
 *
 */
public class Pieza {
	
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
	 * Constructor sin parámetros
	 */
	protected Pieza(){
		
		// Inicialización de elementos
		forma = new int[4][5][];
		angulo = 0;	
	}
	
	/**
	 * Constructor indicando la posición inicial de la pieza
	 * @param posX posición inicial X de la pieza
	 * @param posY posición inicial Y de la pieza
	 */
	protected Pieza(int posX, int posY)
	{
		this();
		
		posicionX = posX;
		posicionY = posY;
	}
	
	/**
	 * Cambia el ángulo al siguiente disponible en la pieza
	 */
	public void aumentaAngulo(){		
		angulo+=1;
		if (angulo==4) angulo=0;		
	}
	
	/**
	 * Disminuye el ángulo al anterior disponible en la pieza
	 */
	public void disminuyeAngulo(){
		angulo-=1;
		if (angulo<0) angulo=3;
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
	
	/**
	 * Hace que la pieza caiga 1 posición
	 */
	public void cae()
	{
		posicionY+=1;
	}
	
	/**
	 * Devuelve el alto real de la pieza en su ángulo actual
	 * @return número de casillas que ocupa en vertical
	 */
	public int dameAlto()
	{
		int[][] formaActual; 	// Array para almacenar la forma actual
		int filaActual;			// contador para recorrer todas las filas de la forma
		boolean encontrado;		// tomará valor true cuando se encuentre una fila con casilla
		
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

