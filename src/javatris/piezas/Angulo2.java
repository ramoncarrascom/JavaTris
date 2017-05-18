package javatris.piezas;

/**
 * Clase para el ángulo recto
 * 
 * @author Ramon
 * @version 0.1 2017/05/14
 *
 */
public class Angulo2 extends Pieza {

	/**
	 * Constructor sin parámetros
	 */
	public Angulo2() {
		this(0,0);
	}
	
	/**
	 * Constructor indicando posición inicial
	 * @param posX posición X inicial
	 * @param posY posición Y inicial
	 */
	public Angulo2(int posX, int posY) {
		super(posX, posY);
		
		// forma con 0º
		forma[0][0]=new int[]{0,0,0,0,0};
		forma[0][1]=new int[]{0,0,1,0,0};
		forma[0][2]=new int[]{1,1,1,0,0};
		forma[0][3]=new int[]{0,0,0,0,0};
		forma[0][4]=new int[]{0,0,0,0,0};
		
		// forma con 90º
		forma[1][0]=new int[]{0,1,1,0,0};
		forma[1][1]=new int[]{0,0,1,0,0};
		forma[1][2]=new int[]{0,0,1,0,0};
		forma[1][3]=new int[]{0,0,0,0,0};
		forma[1][4]=new int[]{0,0,0,0,0};
		
		// forma con 180º
		forma[2][0]=new int[]{1,1,1,0,0};
		forma[2][1]=new int[]{1,0,0,0,0};
		forma[2][2]=new int[]{0,0,0,0,0};
		forma[2][3]=new int[]{0,0,0,0,0};
		forma[2][4]=new int[]{0,0,0,0,0};
		
		// forma con 270º
		forma[3][0]=new int[]{1,0,0,0,0};
		forma[3][1]=new int[]{1,0,0,0,0};
		forma[3][2]=new int[]{1,1,0,0,0};
		forma[3][3]=new int[]{0,0,0,0,0};
		forma[3][4]=new int[]{0,0,0,0,0};
	}
}