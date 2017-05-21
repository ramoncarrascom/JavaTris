/**
 * 
 */
package javatris.piezas;

import java.util.Random;

import javatris.tablero.Tablero;

/**
 * Catálogo de piezas disponibles para jugar
 * 
 * @author Ramon Carrasco Muñoz
 * @version 0.1 2017/05/15
 *
 */
public class Catalogo {
	
	private Pieza[] repositorio;
	
	/**
	 * Constructor
	 * @param miTablero tablero sobre el que se colocarán las piezas
	 */
	public Catalogo(Tablero miTablero) {
		
		int posX = miTablero.dameAncho()/2;
		int posY = 0;
		
		repositorio = new Pieza[]{new Barra(posX, posY)
				,new Angulo1(posX, posY)
				,new Angulo2(posX, posY)
				,new Ese1(posX,posY)
				,new Ese2(posX,posY)
				,new Cuadrado(posX, posY)
				,new Triangulo(posX, posY)};
		
	}
	
	/**
	 * Indica el número de piezas actualmente en el catálogo
	 * @return
	 */
	public int numeroPiezas() {
		
		return repositorio.length;
		
	}
	
	/**
	 * Devuelve la pieza que ocupa la posición pasada como parámetro. Si la pieza no existe,
	 * devuelve null
	 * @param posicion número de pieza que se solicita
	 * @return objeto de tipo Pieza correspondiente a la pieza solicitada
	 */
	public Pieza damePieza(int posicion) 
	{
		if ((posicion>0) & (posicion<=repositorio.length))
			return repositorio[posicion];
		else
			return null;					
	}
	
	/**
	 * Devuelve una pieza del catálogo al azar
	 * @return objeto de tipo Pieza correspondiente a la pieza obtenida
	 */
	public Pieza damePiezaRnd()
	{
		Random numero = new Random();
		int Posicion = numero.nextInt(repositorio.length);
		
		return repositorio[Posicion];
	}

}
