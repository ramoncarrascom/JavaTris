/**
 * 
 */
package javatris.tablero;

import java.applet.Applet;
import java.awt.Color;
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
	 * Dimensiones que se asignar�n a la rejilla
	 */
	private int vAncho, vAlto;
	
	/**
	 * Rejilla sobre la que se colocar�n las piezas. Sus valores son:
	 * 0 - No hay ning�n elemento
	 * 1 - Posici�n ocupada por una pieza
	 * 100 - Posici�n ocupada por un obst�culo
	 */
	private int[][] rejilla;
	
	/**
	 * Objeto que contiene la imagen correspondiente a un bloque
	 */
	private Image[] bloques;
	
	/**
	 * Dimensiones de la imagen del bloque
	 */
	private int bloqueAncho, bloqueAlto;
	
	/**
	 * Objeto que actuar� como observer
	 */
	private Applet vApplet;
	
	
	/**
	 * Constructor
	 * 
	 * @param Ancho establece el ancho de la rejilla del tablero
	 * @param Alto establece el alto de la rejilla del tablero
	 */
	public tablero(int Ancho, int Alto, Image[] imagenBloque, Applet miApplet) {
		
		// Inicializaci�n de ancho y alto
		vAncho = Ancho;
		vAlto = Alto;
		
		// Inicializaci�n de la rejilla
		rejilla = new int[vAncho][vAlto];
		
		// Inicializamos todos los valores a 0
		for (int i=0; i<vAncho; i++) {
			for (int n=0; n<vAlto; n++) {
				rejilla[i][n]=0;
			}
		}
		
		// Cargamos la imagen del bloque
		bloques = imagenBloque;
		bloqueAncho = bloques[0].getWidth(miApplet);
		bloqueAlto = bloques[0].getWidth(miApplet);
		
		// Inicializamos vApplet
		vApplet = miApplet;
		
		// Ponemos un obst�culo
		rejilla[10][25]=100;
		
	}
	
	/**
	 * Dibuja la pieza pasada como par�metro en la rejilla
	 * @param piezaDibujar Objeto de la pieza que se va a dibujar en la rejilla 
	 */
	public void dibujaPieza(pieza piezaDibujar)
	{
		
		// Variables que utilizaremos para calcular la posici�n a tratar
		int calculaX, calculaY;
		int[][] formaActual;
		
		formaActual = piezaDibujar.dameForma();
		
		// Borramos todos los restos de piezas de la rejilla
		for (int i=0; i<vAncho; i++)
			for (int m=0; m<vAlto; m++)
				if (rejilla[i][m]==1) rejilla[i][m]=0;
		
		// Recorremos todas las casillas de la pieza
		for (int i=0; i<5; i++) {
			for (int n=0; n<5; n++) {
				
				// Calculamos la posici�n actual
				calculaX = piezaDibujar.dameX() + i;
				calculaY = piezaDibujar.dameY() + n;
				
				// Si en la pieza, el bloque actual est� a 1, y podemos verlo, entonces dibujamos
				if (formaActual[n][i]==1 && calculaX>=0 && calculaY>=0 && calculaX<=vAncho && calculaY<=vAlto)
				{
					rejilla[calculaX][calculaY]=1;
				}
			}
		}
			
	}
	
	/**
	 * Dibuja el tablero en el objeto Graphics pasado como par�metro
	 * @param g objeto de tipo Graphics sobre el que se dibujar� el tablero
	 */
	public void dibujaTablero(Graphics g) {
		
		int imagenCorrespondiente;	// Indica el tipo de imagen dependiendo del tipo de celda
		
		g.setColor(Color.black);
		g.drawLine(0, 0, 0, vAlto*bloqueAlto);
		g.drawLine(0, 0, vAncho*bloqueAncho, 0);
		g.drawLine(vAncho*bloqueAncho, 0, vAncho*bloqueAncho, vAlto*bloqueAlto);
		g.drawLine(0, vAlto*bloqueAlto, vAncho*bloqueAncho, vAlto*bloqueAlto);
		
		for (int i=0; i<vAncho; i++) {
			for (int n=0; n<vAlto; n++) {

				switch(rejilla[i][n])
				{
					case 0: imagenCorrespondiente=-1; break;
					case 1: imagenCorrespondiente=0; break;
					case 100: imagenCorrespondiente=1; break;
					default: imagenCorrespondiente=-1;
				}
				
				if (imagenCorrespondiente>=0)
					g.drawImage(bloques[imagenCorrespondiente], i*bloqueAncho, n*bloqueAlto, vApplet);
				
			}
		}
	}
	
	/**
	 * Indica si la pieza pasada como par�metro tiene una colisi�n en su parte inferior
	 * @param miPieza pieza para la que queremos comprobar la colisi�n
	 * @return devuelve true si la pieza ha colisionado en su parte inferior
	 */
	public boolean colisionInferior(pieza miPieza) {
		
		// Variables de trabajo
		int filaActual=4;
		boolean hayColision=false;
		
		// Almacen de la forma actual de la pieza
		int[][] formaActual = miPieza.dameForma();
		
		while (!hayColision & filaActual>=0)
		{
			for (int i=0; i<5; i++){
				if (formaActual[filaActual][i]==1)
				{
					if (miPieza.dameY()+filaActual+1==vAlto | 
							rejilla[i+miPieza.dameX()][miPieza.dameY()+filaActual+1]>1)
						hayColision=true;
				}
			}
			filaActual--;
		}
		return hayColision;
	}
	
	public boolean colisionIzquierda(pieza miPieza) {
		
		// Variables de trabajo
		int columnaActual=0;
		boolean hayColision=false;
		
		// Almac�n de la forma actual de la pieza
		int[][] formaActual = miPieza.dameForma();
		
		while (!hayColision & columnaActual<=4)
		{
			for (int i=0; i<5; i++) {
				if (formaActual[i][columnaActual]==1)
				{
					if (miPieza.dameX()+columnaActual<=0 ||
							rejilla[miPieza.dameX()+columnaActual-1][i+miPieza.dameY()]>1)
						hayColision=true;
				}
			}
			columnaActual++;
		}
		return hayColision;
	}
	
public boolean colisionDerecha(pieza miPieza) {
		
		// Variables de trabajo
		int columnaActual=0;
		boolean hayColision=false;
		
		// Almac�n de la forma actual de la pieza
		int[][] formaActual = miPieza.dameForma();
		
		while (!hayColision & columnaActual<=4)
		{
			for (int i=0; i<5; i++) {
				if (formaActual[i][columnaActual]==1)
				{
					if (miPieza.dameX()+columnaActual+1>=vAncho ||
							rejilla[miPieza.dameX()+columnaActual+1][i+miPieza.dameY()]>1)
						hayColision=true;
				}
			}
			columnaActual++;
		}
		return hayColision;
	}
	
}
