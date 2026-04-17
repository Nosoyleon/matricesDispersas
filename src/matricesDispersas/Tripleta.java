package matricesDispersas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tripleta {
	private int datos[][];

	public Tripleta(int rows, int cols, int numDatos) {
		datos = new int[numDatos + 1][3];
		datos[0][0] = rows;
		datos[0][1] = cols;
		datos[0][2] = numDatos;
	}

	public int[][] getDatos() {
		return datos;
	}

	public int getRowsCount() {
		return datos[0][0];
	}

	public int getColsCount() {
		return datos[0][1];
	}

	public int getNumDatos() {
		return datos[0][2];
	}

	public void setDato(int i, int row, int col, int dato) {
		datos[i][0] = row;
		datos[i][1] = col;
		datos[i][2] = dato;
	}

	public void setDatos(int[][] newDatos) {
		datos = newDatos;
	}

	public void fillTripleta() {
		int rows = datos[0][0];
		int cols = datos[0][1];
		int numDatos = datos[0][2];
		int datosRow = 1;

		while (datosRow <= numDatos) {
			int randomRow = (int) (Math.random() * ((rows - 1) - 0 + 1)) + 0;
			int randomCol = (int) (Math.random() * ((cols - 1) - 0 + 1)) + 0;
			int randomDato;

			do {
				randomDato = (int) (Math.random() * (20 - (-20) + 1)) + (-20);
			} while (randomDato == 0);

			boolean existCord = false;

			// Check if dato in cord was added

			for (int j = 1; j <= numDatos; j++) {
				if (datos[j][0] == randomRow && datos[j][1] == randomCol) {
					existCord = true;
				}
			}

			if (!existCord) {

				datos[datosRow][0] = randomRow;
				datos[datosRow][1] = randomCol;
				datos[datosRow][2] = randomDato;
				datosRow++;
			}

		}

		orderMatriz();

	}

	public String showSummary() {
		return "Info: filas(" + datos[0][0] + ") | Columnas(" + datos[0][1] + ") | Datos(" + datos[0][2] + ")";
	}

	public String showForma() {
		String output = "";

		output += "## Tripleta ##\n";
		output += "----------\n";

		for (int row = 0; row <= datos[0][2]; row++) {
			for (int col = 0; col <= 2; col++) {
				output += "| " + datos[row][col] + " |";
			}
			output += "\n";
		}

		return output;
	}

	public String sumRows() {
		int numDatos = datos[0][2];
		String output = "";
		int sumFila = 0;

		for (int i = 1; i <= numDatos; i++) {
			if (i == 1) {
				sumFila += datos[i][2];
			} else {
				if (datos[i][0] == datos[i - 1][0]) {
					sumFila += datos[i][2];
				} else {
					output += "Suma Fila " + datos[i - 1][0] + ": " + sumFila + "\n";
					sumFila = datos[i][2];
				}

				if (i == numDatos) {
					output += "Suma Fila " + datos[i][0] + ": " + sumFila + "\n";
				}

			}

		}

		return output;
	}

	public String sumCols() {
		int numDatos = datos[0][2];
		String output = "";
		int sumCol = 0;
		ArrayList<Integer> colsSumed = new ArrayList<>();
		String[] orderedOutput = new String[datos[0][1]];

		for (int i = 1; i <= numDatos; i++) {

			if (!colsSumed.contains(datos[i][1])) {
				sumCol += datos[i][2];
				for (int j = i + 1; j <= numDatos; j++) {
					if (datos[i][1] == datos[j][1]) {
						sumCol += datos[j][2];
					}
				}

				orderedOutput[datos[i][1]] = "Suma Columna " + datos[i][1] + ": " + sumCol;
				sumCol = 0;
				colsSumed.add(datos[i][1]);
			}

		}

		for (int i = 0; i < orderedOutput.length; i++) {
			if (orderedOutput[i] != null) {
				output += orderedOutput[i] + "\n";
			}
		}

		return output;
	}

	public void insertDato(Scanner scanner) {
		System.out.println("Fila:");
		int insertRow = scanner.nextInt();
		System.out.println("Columna:");
		int insertCol = scanner.nextInt();
		System.out.println("Dato:");
		int insertDato = scanner.nextInt();

		boolean cordExist = false;

		for (int i = 1; i <= getNumDatos(); i++) {
			int currentRow = getDatos()[i][0];
			int currentCol = getDatos()[i][1];
			int currentDato = getDatos()[i][2];

			if (insertRow == currentRow && insertCol == currentCol) {
				// Existe la coordenada con dato
				// Sumar o reemplazar?
				System.out.println("Dato actual: " + currentDato + ", desea sumar (1) o Reemplazar (2)?");
				int decision = scanner.nextInt();

				if (decision == 1) {
					setDato(i, currentRow, currentCol, currentDato + insertDato);
				} else if (decision == 2) {
					setDato(i, currentRow, currentCol, insertDato);
				} else {
					System.out.println("Saliendo de insersion.");
				}

				cordExist = true;
			}
		}

		if (!cordExist) {
			// Si no existe, puede o no estar en el rango.
			// Redimensionar a mayor fila, mayor columna, incrementar numDatos
			int maxRows = getRowsCount() - 1 > insertRow ? getRowsCount() : insertRow + 1;
			int maxCols = getColsCount() - 1 > insertCol ? getColsCount() : insertCol + 1;
			int newNumDatos = getNumDatos() + 1;
			int[][] newDatos = new int[newNumDatos + 1][3];
			newDatos[0][0] = maxRows;
			newDatos[0][1] = maxCols;
			newDatos[0][2] = newNumDatos;

			for (int i = 1; i <= newNumDatos; i++) {
				if (i <= getNumDatos()) {
					newDatos[i][0] = getDatos()[i][0];
					newDatos[i][1] = getDatos()[i][1];
					newDatos[i][2] = getDatos()[i][2];
				} else {
					newDatos[i][0] = insertRow;
					newDatos[i][1] = insertCol;
					newDatos[i][2] = insertDato;
				}

			}
			setDatos(newDatos);
			orderMatriz();

		}
	}

	public void deleteByDato(int dato) {
		int foundRow;
		int foundCol;
		boolean found = false;

		for (int i = 1; i <= getNumDatos(); i++) {
			if (datos[i][2] == dato) {
				foundRow = datos[i][0];
				foundCol = datos[i][1];
				deleteByCord(foundRow, foundCol);
				found = true;
			}

		}

		if (!found) {
			System.out.println("No se encontró la coordenada");
		}
	}

	public void deleteByCord(int row, int col) {
		boolean found = false;
		int[][] newDatos = new int[getNumDatos()][3];
		int pos = 1;

		for (int i = 1; i <= getNumDatos(); i++) {
			if (datos[i][0] == row && datos[i][1] == col) {
				found = true;
			} else {
				newDatos[pos][0] = datos[i][0];
				newDatos[pos][1] = datos[i][1];
				newDatos[pos][2] = datos[i][2];
				pos++;
			}

		}

		if (!found) {
			System.out.println("No se encontró la coordenada");
		} else {
			newDatos[0][0] = getRowsCount();
			newDatos[0][1] = getColsCount();
			newDatos[0][2] = getNumDatos() - 1;
			datos = newDatos;

		}

	}

	public void sumMatrix(Tripleta matrizToSum) {
		if (matrizToSum == null) {
			return;
		}

		int rows = Math.max(getRowsCount(), matrizToSum.getRowsCount());
		int cols = Math.max(getColsCount(), matrizToSum.getColsCount());

		int maxDatos = getNumDatos() + matrizToSum.getNumDatos();
		int[][] acumulado = new int[maxDatos + 1][3];
		acumulado[0][0] = rows;
		acumulado[0][1] = cols;
		int acumuladoCount = 0;

		// Current Matrix
		for (int i = 1; i <= datos[0][2]; i++) {
			int row = datos[i][0];
			int col = datos[i][1];
			int value = datos[i][2];
			boolean found = false;

			for (int j = 1; j <= acumuladoCount && !found; j++) {
				if (acumulado[j][0] == row && acumulado[j][1] == col) {
					acumulado[j][2] += value;
					found = true;

					if (acumulado[j][2] == 0) {
						for (int k = j; k < acumuladoCount; k++) {
							acumulado[k][0] = acumulado[k + 1][0];
							acumulado[k][1] = acumulado[k + 1][1];
							acumulado[k][2] = acumulado[k + 1][2];
						}
						acumuladoCount--;
					}
				}
			}

			if (!found) {
				acumuladoCount++;
				acumulado[acumuladoCount][0] = row;
				acumulado[acumuladoCount][1] = col;
				acumulado[acumuladoCount][2] = value;
			}
		}

		// Matrix to sum
		int[][] datosToSum = matrizToSum.getDatos();
		for (int i = 1; i <= datosToSum[0][2]; i++) {
			int row = datosToSum[i][0];
			int col = datosToSum[i][1];
			int value = datosToSum[i][2];
			boolean found = false;

			for (int j = 1; j <= acumuladoCount && !found; j++) {
				if (acumulado[j][0] == row && acumulado[j][1] == col) {
					acumulado[j][2] += value;
					found = true;

					if (acumulado[j][2] == 0) {
						for (int k = j; k < acumuladoCount; k++) {
							acumulado[k][0] = acumulado[k + 1][0];
							acumulado[k][1] = acumulado[k + 1][1];
							acumulado[k][2] = acumulado[k + 1][2];
						}
						acumuladoCount--;
					}
				}
			}

			if (!found) {
				acumuladoCount++;
				acumulado[acumuladoCount][0] = row;
				acumulado[acumuladoCount][1] = col;
				acumulado[acumuladoCount][2] = value;
			}
		}

		acumulado[0][2] = acumuladoCount;
		datos = removeZeroValues(acumulado);
		orderMatriz();
	}

	public void orderMatriz() {
		Arrays.sort(datos, 1, datos.length, (a, b) -> {
			if (a[0] != b[0]) {
				return Integer.compare(a[0], b[0]); // sort by column 0 first
			} else {
				return Integer.compare(a[1], b[1]); // if equal, sort by column 1
			}
		});

	}

	public int[][] removeZeroValues(int[][] createdMatrix) {
		int validValues = 0;

		for (int i = 1; i <= createdMatrix[0][2]; i++) {
			if (createdMatrix[i][2] != 0) {
				validValues++;
			}
		}

		int[][] cleanedMatrix = new int[validValues + 1][3];
		cleanedMatrix[0][0] = createdMatrix[0][0];
		cleanedMatrix[0][1] = createdMatrix[0][1];
		cleanedMatrix[0][2] = validValues;

		int pos = 1;
		for (int i = 1; i <= createdMatrix[0][2]; i++) {
			if (createdMatrix[i][2] != 0) {
				cleanedMatrix[pos][0] = createdMatrix[i][0];
				cleanedMatrix[pos][1] = createdMatrix[i][1];
				cleanedMatrix[pos][2] = createdMatrix[i][2];
				pos++;
			}
		}

		return cleanedMatrix;
	}

}
