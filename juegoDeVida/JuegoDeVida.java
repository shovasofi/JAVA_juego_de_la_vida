package juegoDeVida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class JuegoDeVida {

	private static final int COLUMNA = 10;
	private static final int FILA = 10;
	static int[][] matriz = new int[FILA][COLUMNA];
	static int[][] futuraGeneracion = new int[FILA][COLUMNA];

	/**
	 * @author Shova Shrestha
	 */

	public JuegoDeVida() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void leerMatriz() {
		for (int[] elem : matriz) {
			for (int u : elem) {

				System.out.print(u);
				System.out.print(", ");
			}
			System.out.println();
		}

	}

	public static int[][] matrizAnterior() {

		// Random r = new Random();

		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[i].length; j++) {
				// int random = r.nextInt(2); matriz[i][j]=random;
				matriz[0][0] = 1;
				matriz[0][1] = 1;
				matriz[1][0] = 1;
				matriz[2][1] = 1;
				matriz[3][1] = 1;
				matriz[3][2] = 1;

			}
			leerMatriz();

		}
		return matriz;
	}

	/**
	 * copiado de
	 * https://github.com/egibide-ciberseguridad/juego-de-la-vida/blob/main/Conway/src/main/java/com/jaureguialzo/Main.java
	 * 
	 * @return
	 */
	// inicio
	public static int numCiclos() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Número de ciclos: ");

		int numero = 5;
		try {
			numero = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return numero;
	}
	// fin

	/**
	 * 
	 */
	public static void futuraGeneracion() {
		

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; i < matriz[i].length; j++) {
				
				int cont = 0;
				/**
				 * • Cualquier célula con menos de 2 vecinos muere en la siguiente generación
				 * por soledad. • Cualquier célula que tenga 2 ó 3 vecinos sobrevive en la
				 * siguiente generación. • Cualquier célula con más de 3 vecinos muere en la
				 * siguiente generación por sobrepoblación. • En cualquier celda vacía que esté
				 * rodeada exactamente de 3 células, nace por “generación espontánea” una nueva
				 * célula en la generación siguiente
				 */
				// matrizAnterior();
				try {
					if (i > 0 && j > 0 && j < COLUMNA && i < FILA) {
						if (matriz[i][j + 1] == 1) { // 1,2 derecha
							cont++;
						}
						if (matriz[i][j - 1] == 1) {// 1,0 izquierda
							cont++;
						}
						if (matriz[i + 1][j] == 1) {// 0,1 arriba
							cont++;
						}
						if (matriz[i - 1][j] == 1) {// 2,1 abajo
							cont++;
						}
						if (matriz[i - 1][j - 1] == 1) {// 0,0 superior izquierda
							cont++;
						}

						if (matriz[i - 1][j + 1] == 1) {// 0,0 Superior derecha
							cont++;
						}
						if (matriz[i + 1][j - 1] == 1) {// 2,0 Inferior izquierda
							cont++;
						}
						if (matriz[i + 1][j + 1] == 1) { // 2,2 Inferior Derecho
							cont++;
						}

					}
					// reglas del juego

					if (matriz[i][j] == 1) {
						if (cont == 2 || cont == 3) {
							futuraGeneracion[i][j] = 1;
						} else {
							futuraGeneracion[i][j] = 0;
						}
					} else if (matriz[i][j] == 0) {
						if (cont == 3) {
							futuraGeneracion[i][j] = 1;
						} else {
							futuraGeneracion[i][j] = 0;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					throw e;
				}

			} // fin for
		} // fin for
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {

				matriz[i][j] = futuraGeneracion[i][j];
			}

		}
		// leerMatriz();

	}

	public static void main(String[] args) {

		// Mostrar LA MATRIZ
		System.out.println("Estado inicial: ");
		matrizAnterior();

		// Pedir al usuario el número de ciclos a repetir
		int ciclos = numCiclos();
		System.out.println();

		// Actualizar LA MATRIZ
		try {
			for (int i = 0; i < ciclos; i++) {
				System.out.format("Ciclo: %d%n", i + 1);
				futuraGeneracion();

				matrizAnterior();
				
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw e;
		}

		/*
		 * try { matrizAnterior();
		 * System.out.println("----------------------------------------------------");
		 * futuraGeneracion(); }catch( java.lang.ArrayIndexOutOfBoundsException e) {
		 * 
		 * }
		 */

	}

	/*
	 * System.out.println("Tecle 0 si quiere jugar"); opc=sc.nextInt();
	 * while(opc==0) {
	 * 
	 * //System.out.println(matriz); System.out.println(" "); sc.close(); }
	 * 
	 * 
	 * if (celulaViva) { contador--; }
	 * 
	 * if (contador == 2 && celulaViva || contador == 3) { return true; } else {
	 * return false; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 

 * Clase con métodos estáticos para generar y leer ficheros en formato CSV
 * @author Alumno
 *
 *
public class TratarFicheroCSV {

	// separador que se va a utilizar en el fichero CSV
	static final String SEPARADOR = ";";

	/**
	 * Método para escribir una tabla - array 2D- de valores enteros en un fichero
	 * CSV
	 * 
	 * @param valores array de 2D con los valores enteros que queremos escribir
	 * @param ruta    nombre y ruta completa del fichero al que escribimos
	 * @return Verdadero si se puede escribir sin errores, Falso si hay algún error
	 
	static boolean escribirAFichero(int[][] valores, String ruta) {

		String linea;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
			// abrimos el fichero en modo "destructivo"
			// recorrido del array por filas
			for (int i = 0; i < valores.length; i++) {
				linea = "";
				for (int k = 0; k < valores[i].length; k++) {
					linea = linea + Integer.toString(valores[i][k]) + SEPARADOR;
					// linea=linea+valores[i][k]+SEPARADOR; //más conciso
				}
				// eliminamos de la cadena el último separador
				linea = linea.substring(0, linea.length() - 1);
				// y la agregamos al fichero, añadiendo un salto de línea
				bw.write(linea);
				bw.newLine();

			} // fin de recorrido de líneas
			return true;

		} catch (IOException e) {
			System.out.println("Se produjo el siguiente error al acceder al fichero \n " + e.getMessage());
			return false;

		}
		// no necesitamos finally al haber usado la estructura try-resources
	} // fin del método de escritura
	 */

	// https://github.com/egibide-ciberseguridad/juego-de-la-vida/blob/main/Conway/src/main/java/com/jaureguialzo/Main.java
//https://github.com/PascoDominguez/IPC1_E_Practica2_201403504/blob/master/Documents/NetBeansProjects/Jvida/src/jvida/Jugar.java
}