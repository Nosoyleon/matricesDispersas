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
				System.out.println("Modo de insersión");
				System.out.println("1 - Por coordenada");
				System.out.println("2 - Por dato");

				int insertMode = scanner.nextInt();

				if (insertMode == 1) {
					System.out.println("Fila:");
					int insertRow = scanner.nextInt();
					System.out.println("Columna:");
					int insertCol = scanner.nextInt();
					System.out.println("Dato:");
					int insertDato = scanner.nextInt();

					boolean cordExist = false;

					for (int i = 1; i <= mainTripleta.getNumDatos(); i++) {
						int currentRow = mainTripleta.getDatos()[i][0];
						int currentCol = mainTripleta.getDatos()[i][1];
						int currentDato = mainTripleta.getDatos()[i][2];

						if (insertRow == currentRow && insertCol == currentCol) {
							// Existe la coordenada con dato
							// Sumar o reemplazar?
							System.out.println("Dato actual: " + currentDato + ", desea sumar (1) o Reemplazar (2)?");
							int decision = scanner.nextInt();

							if (decision == 1) {
								mainTripleta.setDato(i, currentRow, currentCol, currentDato + insertDato);
							} else if (decision == 2) {
								mainTripleta.setDato(i, currentRow, currentCol, insertDato);
							} else {
								System.out.println("Saliendo de insersion.");
							}

							cordExist = true;
						}
					}

					if (!cordExist) {
						// Si no existe, puede o no estar en el rango.
						// Redimensionar a mayor fila, mayor columna, incrementar numDatos
						int maxRows = mainTripleta.getRowsCount() - 1 > insertRow ? mainTripleta.getRowsCount() : insertRow + 1;
						int maxCols = mainTripleta.getColsCount() -1 > insertCol ? mainTripleta.getColsCount() : insertCol + 1;
						int newNumDatos = mainTripleta.getNumDatos() + 1;
						int[][] newDatos = new int[newNumDatos + 1][3];
						newDatos[0][0] = maxRows;
						newDatos[0][1] = maxCols;
						newDatos[0][2] = newNumDatos;

						for (int i = 1; i <= newNumDatos; i++) {
							if (i <= mainTripleta.getNumDatos()) {
								newDatos[i][0] = mainTripleta.getDatos()[i][0];
								newDatos[i][1] = mainTripleta.getDatos()[i][1];
								newDatos[i][2] = mainTripleta.getDatos()[i][2];
							} else {
								newDatos[i][0] = insertRow;
								newDatos[i][1] = insertCol;
								newDatos[i][2] = insertDato;
							}

						}
						mainTripleta.setDatos(newDatos);
						mainTripleta.orderMatriz();

					}

				} else if (insertMode == 2) {
//					Insert by Dato

				} else {
					System.out.println("Opción inválida");
				}

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
