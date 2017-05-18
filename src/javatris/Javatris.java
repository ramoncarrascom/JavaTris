/**
 * 
 */
package javatris;

import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javatris.piezas.*;
import javatris.tablero.*;

/**
 * Applet de la aplicaci�n
 * 
 * @author Ramon
 * @version 0.1 2017/05/15
 *
 */
public class Javatris extends Applet implements Runnable, KeyListener {
	
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
	 * Objeto Image que almacenar� el repositorio de im�genes de bloque
	 */
	private Image[] bloques;	
	private String[] ficheros = {
			 "img/bloque.gif"
			,"img/obstaculo.gif"};
	
	/**
	 * Indica los milisegundos de espera para el siguiente paso en la ca�da
	 */
	private int intervaloCaida;
	
	/**
	 * Hilo que controla la ca�da
	 */
	private Thread caida;	
	
	private angulo miAngulo;
	private tablero miTablero;
	private barra miBarra;
	private pieza piezaActual;

	/**
	 * Procedimiento init del Applet, inicializa los valores con los que vamos a trabajar
	 */
	public void init() {
		
		// Inicializamos el ancho y el alto
		ancho = (int) this.getBounds().getWidth();
		alto = (int) this.getBounds().getHeight();
		
		// Carga de las im�genes
		bloques=new Image[ficheros.length];
		cargaImg(bloques, ficheros);
		
		// Inicializaci�n del doble buffer
		imagenDb = createImage(ancho, alto);
		dobleBuffer = imagenDb.getGraphics();
		
		// Inicializaci�n del intervalo de ca�da
		intervaloCaida = 500;
		
		// Inicializamos algunas piezas
		miAngulo = new angulo();
		miAngulo.ponPosicion(10,20);
		miBarra = new barra();
		miBarra.ponPosicion(15, 10);
		miTablero = new tablero(20,30,bloques,this);
		miTablero.dibujaPieza(miAngulo);
		
		piezaActual=miAngulo;
		
		// Manejadores de eventos
		addKeyListener(this);
		
	}
	
	/**
	 * Procedimiento paint del Applet, redibuja su contenido
	 * @param g Recibe el objeto Graphics del applet
	 */
	public void paint(Graphics g) {
		
		// Vaciamos el doble buffer
		dobleBuffer.setColor(Color.white);
		dobleBuffer.fillRect(0, 0, ancho, alto);
		
		dobleBuffer.setColor(Color.black);
		miTablero.dibujaTablero(dobleBuffer);
		
		dobleBuffer.drawString("X " + piezaActual.dameX() + " Y " + piezaActual.dameY(), 50, 50);
		
		g.drawImage(imagenDb, 0, 0, this);		
		
	}
	
	/**
	 * Sobrecarga del m�todo update
	 * @param g Recibe el objeto Graphics del applet
	 */
	public void update(Graphics g) {
		
		paint(g);
		
	}
	
	/**
	 * Realiza la precarga de im�genes usando un MediaTracker
	 */
	private void cargaImg(Image[] repositorioImg, String[] ficheros) {

		Image[] imagenesPrecarga;
		MediaTracker mt;
		
		imagenesPrecarga = new Image[ficheros.length];
		mt = new MediaTracker(this);
		
		for (int i=0; i<ficheros.length; i++)
		{
			imagenesPrecarga[i] = getImage(getCodeBase(), ficheros[i]);
			mt.addImage(imagenesPrecarga[i], i);			
		}

		try	{
			mt.waitForAll();
		} catch(InterruptedException e) {};
		
		if (mt.isErrorAny()) {
			this.showStatus("Error en la precarga de im�genes");
		} else {
			this.showStatus("Precarga de im�genes correcta");
			
			for (int i=0; i<ficheros.length; i++)
				repositorioImg[i] = getImage(getCodeBase(), ficheros[i]);
		}
	}
	
	/**
	 * Ciclo de cada paso del juego
	 */
	private void ciclo() {
		// miAngulo.cae();
		// miTablero.dibujaPieza(miAngulo);
	}
	
	/**
	 * Implementaci�n del m�todo run de la interfaz Runnable
	 */
	public void run() {
		/*while (!miTablero.colisionInferior(miAngulo)){
			ciclo();
			repaint();
			try {Thread.sleep(intervaloCaida);}
			catch (Exception e) {}
		}*/
	}
	
	/**
	 * Sobrecarga del m�todo start del applet
	 */
	public void start() {
		caida = new Thread(this);
		if (caida!=null) caida.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent evento) {
		char tecla=evento.getKeyChar();
		if (tecla=='w') 
				piezaActual.ponPosicion(piezaActual.dameX(), piezaActual.dameY()-1);
			
		if (tecla=='a') 
			if (!miTablero.colisionIzquierda(piezaActual))
				piezaActual.ponPosicion(piezaActual.dameX()-1, piezaActual.dameY());
			
		if (tecla=='s') 
			if (!miTablero.colisionInferior(piezaActual))
				piezaActual.ponPosicion(piezaActual.dameX(), piezaActual.dameY()+1);
		
		if (tecla=='d') 
			if (!miTablero.colisionDerecha(piezaActual))
				piezaActual.ponPosicion(piezaActual.dameX()+1, piezaActual.dameY());
		
		if (tecla=='z')
			piezaActual.cambiaAngulo();
		
		miTablero.dibujaPieza(piezaActual);
		repaint();
	}
}
