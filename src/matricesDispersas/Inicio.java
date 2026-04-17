package matricesDispersas;

import java.util.Scanner;

import enums.MainMenuOption;
import enums.MatrizOption;

public class Inicio {

	private static Tripleta mainTripleta;
//	private static Forma1 mainForma1;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu(scanner);

		MainMenuOption userInput;

		do {
			if (mainTripleta != null) {
				System.out.println("");
				System.out.println("Tripleta actual: " + mainTripleta.showSummary());
				System.out.println("");
			}
			userInput = menu.showMainMenu();

			switch (userInput) {
			case TRIPLETA: {

				caseTripleta(menu, scanner);

				break;
			}

			case FORMA_1: {

				System.out.println("FORMA 1");

				break;
			}

			case FORMA_2: {

				System.out.println("FORMA 2");

				break;
			}

			case EXIT: {
				System.out.println("ADIOS!");
				break;
			}
			default:
				System.out.println("Option Invalida");
			}

		} while (userInput != MainMenuOption.EXIT);

		scanner.close();

	}

	public static void caseTripleta(Menu menu, Scanner scanner) {
		MatrizOption userInput;

		do {
			System.out.println("Menu - Tripletas");
			if (mainTripleta != null) {
				System.out.println(mainTripleta.showSummary());
				userInput = menu.showMatrizMenu();
			} else {
				userInput = MatrizOption.NEW;
			}

			switch (userInput) {
			case NEW: {
				System.out.println("###");
				System.out.println("Datos de la nueva Matriz");
				System.out.println("Número de Filas:");
				int rows = scanner.nextInt();
				System.out.println("Número de Columnas:");
				int cols = scanner.nextInt();
				System.out.println("Cantidad de datos diferentes de 0:");
				int numDatos = scanner.nextInt();

				Tripleta newTripleta = new Tripleta(rows, cols, numDatos);
				newTripleta.fillTripleta();

				mainTripleta = newTripleta;

				break;
			}

			case SHOW_FORMA: {

				System.out.println(mainTripleta.showForma());
				break;
			}

			case SUMAR_FILAS: {
				System.out.println(mainTripleta.sumRows());
				break;
			}

			case SUMAR_COLUMNAS: {
				System.out.println(mainTripleta.sumCols());
				break;
			}

			case INSERT_DATO: {

				mainTripleta.insertDato(scanner);

				break;

			}

			case DELETE_DATO: {
				System.out.println("Eliminar por:");
				System.out.println("1 - Coordenada");
				System.out.println("2 - Dato");

				int decision = scanner.nextInt();

				if (decision == 1) {
					System.out.println("Fila:");
					int rowToDel = scanner.nextInt();
					System.out.println("Columna:");
					int colToDel = scanner.nextInt();

					mainTripleta.deleteByCord(rowToDel, colToDel);

				} else if (decision == 2) {
					System.out.println("Dato a eliminar:");
					int datoToDel = scanner.nextInt();
					mainTripleta.deleteByDato(datoToDel);

				} else {
					System.out.println("Option Invalidad");
				}

				break;
			}

			case SUMAR_MATRICES: {
				System.out.println("###");
				System.out.println("Datos de la Matriz a sumar");
				System.out.println("Número de Filas:");
				int rows = scanner.nextInt();
				System.out.println("Número de Columnas:");
				int cols = scanner.nextInt();
				System.out.println("Cantidad de datos diferentes de 0:");
				int numDatos = scanner.nextInt();

				Tripleta newTripleta = new Tripleta(rows, cols, numDatos);
				newTripleta.fillTripleta();

				System.out.println("Matris 1");
				System.out.println(mainTripleta.showForma());
				System.out.println("Matris 2");
				System.out.println(newTripleta.showForma());

				mainTripleta.sumMatrix(newTripleta);
				System.out.println("Resultado suma");
				System.out.println(mainTripleta.showForma());

				break;
			}

			case MULTIPLICAR_MATRICES: {
				System.out.println("###");
				System.out.println("Datos de la Matriz a multiplicar");
				System.out.println("Número de Filas:");
				int rows = scanner.nextInt();
				System.out.println("Número de Columnas:");
				int cols = scanner.nextInt();
				System.out.println("Cantidad de datos diferentes de 0:");
				int numDatos = scanner.nextInt();

				Tripleta newTripleta = new Tripleta(rows, cols, numDatos);
				newTripleta.fillTripleta();

				System.out.println("Matris 1");
				System.out.println(mainTripleta.showForma());
				System.out.println("Matris 2");
				System.out.println(newTripleta.showForma());

				mainTripleta.multiplyMatrix(newTripleta);
				System.out.println("Resultado multiplicación");
				System.out.println(mainTripleta.showForma());

				break;
			}

			case EXIT: {
				System.out.println("Menú Anteior!");
				break;
			}
			default:
				System.out.println("Option Invalida");
			}

		} while (userInput != MatrizOption.EXIT);

	}

}
