/**
 * 
 */
package javatris;

import java.applet.*;
import java.awt.*;

import javatris.piezas.*;
import javatris.tablero.*;

/**
 * Applet de la aplicación
 * 
 * @author Ramon
 * @version 0.1 2017/05/15
 *
 */
public class Javatris extends Applet {
	
	/**
	 * Imagen que utilizaremos para pintar temporalmente los elementos en pantalla
	 * para pasarlos todos al Applet de una tacada
	 */
	private Image imagenDb;
	
	/**
	 * Objeto de tipo Graphics que utilizaremos como doble buffer
	 */
	private Graphics dobleBuffer;
	
	/**
	 * Variables que contienen el ancho y el alto del applet
	 */
	private int ancho, alto;
	
	/**
	 * MediaTracker para la precarga de imágenes en el applet
	 */
	private MediaTracker mt;
	
	/**
	 * Objeto Image que almacenará la imagen del bloque
	 */
	private Image bloque;
	
	/**
	 * Objeto Image que se utilizará en la precarga de imágenes
	 */
	private Image imagenes;
	
	/**
	 * Contiene el nombre del fichero de imagen del bloque
	 */
	private String img = "img/bloque.gif";
	
	private angulo miAngulo;
	private tablero miTablero;
	private barra miBarra;

	/**
	 * Procedimiento init del Applet, inicializa los valores con los que vamos a trabajar
	 */
	public void init() {
		
		// Inicializamos el ancho y el alto
		ancho = (int) this.getBounds().getWidth();
		alto = (int) this.getBounds().getHeight();
		
		// Carga de las imágenes
		precargaImg();
		bloque = getImage(getCodeBase(),img);
		
		// Inicialización del doble buffer
		imagenDb = createImage(ancho, alto);
		dobleBuffer = imagenDb.getGraphics();
		
		// Inicializamos algunas piezas
		miAngulo = new angulo();
		miAngulo.ponPosicion(5,5);
		miBarra = new barra();
		miBarra.ponPosicion(10, 10);
		miTablero = new tablero(20,40,bloque,this);
		miTablero.dibujaPieza(miAngulo);
		miTablero.dibujaPieza(miBarra);
		
	}
	
	/**
	 * Procedimiento paint del Applet, redibuja su contenido
	 * @param g Recibe el objeto Graphics del applet
	 */
	public void paint(Graphics g) {
		
		// Vaciamos el doble buffer
		dobleBuffer.setColor(Color.white);
		dobleBuffer.fillRect(0, 0, ancho, alto);
		
		miTablero.dibujaTablero(dobleBuffer);
		
		g.drawImage(imagenDb, 0, 0, this);		
		
	}
	
	/**
	 * Sobrecarga del método update
	 * @param g Recibe el objeto Graphics del applet
	 */
	public void update(Graphics g) {
		
		paint(g);
		
	}
	
	/**
	 * Realiza la precarga de imágenes usando un MediaTracker
	 */
	private void precargaImg() {
		
		mt = new MediaTracker(this);
		imagenes = getImage(getCodeBase(), img);
		mt.addImage(imagenes, 0);
		try	{
			mt.waitForAll();
		} catch(InterruptedException e) {};
		
		if (mt.isErrorAny()) {
			this.showStatus("Error en la precarga de imágenes");
		} else {
			this.showStatus("Precarga de imágenes correcta");
		}
	}
}
