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
 * @author Ramon Carrasco Mu�oz
 * @version 0.1 2017/05/15
 *
 */
public class Javatris extends Applet implements Runnable, KeyListener {
	
	/**
	 * Constante indicando el ancho del tablero
	 */
	private final int anchoTablero=15;
	
	/**
	 * Constante indicando el alto del tablero
	 */
	private final int altoTablero=25;
	
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
	
	/**
	 * Tablero donde se desarrollar� el juego
	 */
	private Tablero miTablero;

	/**
	 * Pieza que est� actualmente en juego
	 */
	private Pieza piezaActual;
	
	/**
	 * Contiene la cola con las pr�ximas 5 piezas que se van a jugar
	 */
	private ColaPiezas miCola;
	
	/**
	 * Variable que almacena el estado en que se encuentra actualmente el juego. Valores:
	 * 0 - El juego a�n no ha empezado
	 * 1 - El juego est� en marcha
	 * 2 - El juego ha terminado
	 */
	private int estadoJuego;
	
	/**
	 * Nivel del juego
	 */
	private int nivel;
	
	/**
	 * Puntos del usuario
	 */
	private int puntos;
	
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
		
		// Inicializaci�n del intervalo de ca�da, nivel y estado del juego
		intervaloCaida = 600;
		nivel = 1;
		estadoJuego = 0;
		puntos = 0;
		
		// Inicializamos los componentes y obtenemos la primera pieza
		miTablero = new Tablero(anchoTablero,altoTablero,bloques,this);
		miCola = new ColaPiezas(miTablero);
		
		piezaActual=miCola.damePieza();
		
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
		miTablero.dibuja(dobleBuffer, 100, 50);
		switch (estadoJuego)
		{
		case 0:		dobleBuffer.drawString("Pulse Espacio para comenzar",10,10);
					break;
		case 1: 	dobleBuffer.drawString("Nivel: " + nivel, 10, 50);
					dobleBuffer.drawString("Puntos:" + puntos, 10, 80);
					miCola.dibuja(dobleBuffer, 270, 100);
					break;
		case 2:		dobleBuffer.drawString("Juego terminado. Pulse Espacio para comenzar", 10, 10);	
					break;
		}
		
		
		// dobleBuffer.drawString("X " + piezaActual.dameX() + " Y " + piezaActual.dameY(), 50, 50);
		
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
		comprobarSiSiguientePieza();
		piezaActual.cae();
		if (estadoJuego==1) miTablero.dibujaPieza(piezaActual);
	}
	
	/**
	 * Comprueba si se dan las condiciones para cambiar de pieza. Si es as�, fija la pieza
	 * y obtiene la siguiente
	 */
	private void comprobarSiSiguientePieza(){
		
		int filasQuitadas;
		
		if (miTablero.colisionInferior(piezaActual)) {

			miTablero.fijaPieza(piezaActual);
			filasQuitadas = miTablero.quitaFilas();
			puntos = puntos + (filasQuitadas*10);
			nivel = puntos / 200;
			piezaActual=miCola.damePieza();
			if (miTablero.haySuperposicion(piezaActual)) estadoJuego=2;
			repaint();
			
		}
	}
	
	/**
	 * Implementaci�n del m�todo run de la interfaz Runnable
	 */
	public void run() {
		while (true){
			if (estadoJuego==1) {
				ciclo();
				repaint();}
			try {Thread.sleep(intervaloCaida-(25*nivel));}
			catch (Exception e) {}
			
		}
	}
	
	/**
	 * Sobrecarga del m�todo start del applet
	 */
	public void start() {
		caida = new Thread(this);
		if (caida!=null) caida.start();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent evento) {
		
		char teclaChar=evento.getKeyChar();
		int teclaCode=evento.getKeyCode();
		if (estadoJuego==1)	
		{
			if (teclaChar=='a' | teclaCode==KeyEvent.VK_LEFT) 
				if (!miTablero.colisionIzquierda(piezaActual))
					piezaActual.ponPosicion(piezaActual.dameX()-1, piezaActual.dameY());
				
			if (teclaChar=='s' | teclaCode==KeyEvent.VK_DOWN) 
				if (!miTablero.colisionInferior(piezaActual)){
					comprobarSiSiguientePieza();
					piezaActual.ponPosicion(piezaActual.dameX(), piezaActual.dameY()+1);
				}				
			
			if (teclaChar=='d' | teclaCode==KeyEvent.VK_RIGHT) 
				if (!miTablero.colisionDerecha(piezaActual))
					piezaActual.ponPosicion(piezaActual.dameX()+1, piezaActual.dameY());
			
			if (teclaChar=='w' | teclaCode==KeyEvent.VK_UP | teclaCode==KeyEvent.VK_SPACE)
			{
				piezaActual.aumentaAngulo();
				if (miTablero.haySuperposicion(piezaActual))
					piezaActual.disminuyeAngulo();
			}
		}
		if (teclaCode==KeyEvent.VK_SPACE)
		{
			if (estadoJuego==0 || estadoJuego==2)
			{
				estadoJuego=1;
				miTablero.borra();
			}
			
		}
		
		miTablero.dibujaPieza(piezaActual);
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent evento) {
		
		
	}
}
