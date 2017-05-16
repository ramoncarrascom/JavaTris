/**
 * 
 */
package javatris.tablero;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

import javatris.piezas.pieza;

/**
 * Clase para el tablero
 * 
 * @author Ramon
 * @version 0.1 2017/05/15
 *
 */
public class tablero {

	/**
	 * Dimensiones que se asignarán a la rejilla
	 */
	private int vAncho, vAlto;
	
	/**
	 * Rejilla sobre la que se colocarán las piezas
	 */
	private int[][] rejilla;
	
	/**
	 * Objeto que contiene la imagen correspondiente a un bloque
	 */
	private Image bloque;
	
	/**
	 * Dimensiones de la imagen del bloque
	 */
	private int bloqueAncho, bloqueAlto;
	
	/**
	 * Objeto que actuará como observer
	 */
	private Applet vApplet;
	
	
	/**
	 * Constructor
	 * 
	 * @param Ancho establece el ancho de la rejilla del tablero
	 * @param Alto establece el alto de la rejilla del tablero
	 */
	public tablero(int Ancho, int Alto, Image imagenBloque, Applet miApplet) {
		
		// Inicialización de ancho y alto
		vAncho = Ancho;
		vAlto = Alto;
		
		// Inicialización de la rejilla
		rejilla = new int[vAncho][vAlto];
		
		// Inicializamos todos los valores a 0
		for (int i=0; i<vAncho; i++) {
			for (int n=0; n<vAlto; n++) {
				rejilla[i][n]=0;
			}
		}
		
		// Cargamos la imagen del bloque
		bloque = imagenBloque;
		bloqueAncho = imagenBloque.getWidth(miApplet);
		bloqueAlto = imagenBloque.getWidth(miApplet);
		
		// Inicializamos vApplet
		vApplet = miApplet;
	}
	
	/**
	 * Dibuja la pieza pasada como parámetro en la rejilla
	 * @param piezaDibujar Objeto de la pieza que se va a dibujar en la rejilla 
	 */
	public void dibujaPieza(pieza piezaDibujar)
	{
		
		// Variables que utilizaremos para calcular la posición a tratar
		int calculaX, calculaY;
		int[][] formaActual;
		
		formaActual = piezaDibujar.dameForma();
		
		// Recorremos todas las casillas de la pieza
		for (int i=0; i<5; i++) {
			for (int n=0; n<5; n++) {
				
				calculaX = piezaDibujar.dameX() + i;
				calculaY = piezaDibujar.dameY() + n;
				
				if (formaActual[n][i]==1 && calculaX>=0 && calculaY>=0)
				{
					rejilla[calculaX][calculaY]=1;
				}
			}
		}
			
	}
	
	/**
	 * Dibuja el tablero en el objeto Graphics pasado como parámetro
	 * @param g objeto de tipo Graphics sobre el que se dibujará el tablero
	 */
	public void dibujaTablero(Graphics g) {
		
		for (int i=0; i<vAncho; i++) {
			for (int n=0; n<vAlto; n++) {
				if (rejilla[i][n]==1) {
					g.drawImage(bloque, i*bloqueAncho, n*bloqueAlto, vApplet);
				}
			}
		}
	}
	
}
