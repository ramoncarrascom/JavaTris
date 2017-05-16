package javatris.piezas;

/**
 * Clase para el ángulo recto
 * 
 * @author Ramon
 * @version 0.1 2017/05/14
 *
 */
public class angulo extends pieza {

	/**
	 * Constructor
	 */
	public angulo() {
		super();
		
		// forma con 0º
		forma[0][0]=new int[]{0,0,0,0,0};
		forma[0][1]=new int[]{1,0,0,0,0};
		forma[0][2]=new int[]{1,1,1,0,0};
		forma[0][3]=new int[]{0,0,0,0,0};
		forma[0][4]=new int[]{0,0,0,0,0};
		
		// forma con 90º
		forma[1][0]=new int[]{0,0,1,0,0};
		forma[1][1]=new int[]{0,0,1,0,0};
		forma[1][2]=new int[]{0,1,1,0,0};
		forma[1][3]=new int[]{0,0,0,0,0};
		forma[1][4]=new int[]{0,0,0,0,0};
		
		// forma con 180º
		forma[2][0]=new int[]{1,1,1,0,0};
		forma[2][1]=new int[]{0,0,1,0,0};
		forma[2][2]=new int[]{0,0,0,0,0};
		forma[2][3]=new int[]{0,0,0,0,0};
		forma[2][4]=new int[]{0,0,0,0,0};
		
		// forma con 270º
		forma[3][0]=new int[]{1,1,0,0,0};
		forma[3][1]=new int[]{1,0,0,0,0};
		forma[3][2]=new int[]{1,0,0,0,0};
		forma[3][3]=new int[]{0,0,0,0,0};
		forma[3][4]=new int[]{0,0,0,0,0};
	}
}
