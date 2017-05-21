/**
 * 
 */
package javatris.tablero;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

import javatris.piezas.Catalogo;
import javatris.piezas.Pieza;
import prodfw.lists.Fifo;

/**
 * Clase para la cola de piezas a ir añadiendo al juego
 * 
 * @author Ramon
 * @version 0.1 2017/05/15
 *
 */
public class ColaPiezas {

	/**
	 * Cola para las piezas
	 */
	private Fifo<Pieza> Cola;
	
	/**
	 * Tablero de trabajo
	 */
	private Tablero miTablero;
	
	/**
	 * Array que contiene las imágenes de los bloques
	 */
	private Image[] bloques;
	
	/**
	 * Dimensiones del bloque
	 */
	private int bloqueAncho, bloqueAlto;
	
	/**
	 * Applet de trabajo
	 */
	private Applet vApplet;
	
	/**
	 * Constructor
	 * @param tableroTrabajo indica el tablero con el que se va a trabajar
	 */
	public ColaPiezas(Tablero tableroTrabajo) {
		
		// Instanciado de la cola
		try {
			Cola = new Fifo<Pieza>(5); 
		} catch (Exception e) {};
		
		// Inicialización de propiedades internas
		miTablero = tableroTrabajo;
		
		// Inicialización de la cola
		for (int i=0; i<5; i++)
		{
			Cola.queue(new Catalogo(miTablero).damePiezaRnd());
		}
		
		// Inicialización de las imágenes del bloque
		bloques=miTablero.dameBloques();
		vApplet=miTablero.dameApplet();
		bloqueAncho=bloques[0].getWidth(vApplet);
		bloqueAlto=bloques[0].getHeight(vApplet);
	}
	
	/**
	 * Devuelve la siguiente pieza de la cola, y añade una pieza nueva
	 * @return siguiente pieza
	 */
	public Pieza damePieza()
	{
		Pieza nuevaPieza;
		
		// Cogemos la primera pieza, y añadimos una nueva
		nuevaPieza=Cola.peek();
		Cola.queue(new Catalogo(miTablero).damePiezaRnd());
		
		return nuevaPieza;
	}
	
	/**
	 * Dibuja la cola de piezas en el elemento Graphics pasado como parámetro
	 * @param g
	 */
	public void dibuja(Graphics g)
	{
		dibuja(g,0,0);
	}
	
	/**
	 * Dibuja la cola de piezas en la posición indicada del elemento Graphics pasado como parámetro
	 * @param g elementos Graphics sobre el que dibujar la cola
	 * @param posX posición X donde situar la imagen de la cola
	 * @param posY posición Y donde situar la imagen de la cola
	 */
	public void dibuja(Graphics g, int posX, int posY)
	{
		Object[] arrayCola=Cola.toArray();
		int[][] forma;
		int filaActual;
		
		filaActual=0;
		
		for (int i=0; i<arrayCola.length; i++)
		{
			forma=((Pieza) arrayCola[i]).dameForma();
			for (int m=0; m<5; m++)
				for (int n=0; n<5; n++)
					if (forma[n][m]==1) g.drawImage(bloques[0], posX+(m*bloqueAncho), posY+(((filaActual*bloqueAlto) + (n*bloqueAlto))), vApplet);
			
			filaActual = filaActual + ((Pieza) arrayCola[i]).dameAlto() + 1;			
		}
	}
	
}
