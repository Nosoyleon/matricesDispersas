package matricesDispersas;

import java.util.Scanner;

public class Forma1 {

	private Nodo mainHead;

	public Forma1() {
		super();
		this.mainHead = null;
	}

	public Nodo getMainHead() {
		return mainHead;
	}

	public void setMainHead(Nodo mainHead) {
		this.mainHead = mainHead;
	}

	public void createMatriz(int matriz[][]) {
		if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
			setMainHead(null);
			return;
		}

		phaseOne(matriz.length, matriz[0].length);
		phaseTwo(matriz);
		phaseThree(matriz);

	}

	public void phaseOne(int rows, int columns) {
		int major = rows > columns ? rows : columns;

		Nodo mainHeadNode = new Nodo(rows, columns, 0);
		setMainHead(mainHeadNode);

		if (major == 0) {
			mainHeadNode.setLiga(mainHeadNode);
			return;
		}

		Nodo previous = mainHeadNode;

		for (int i = 0; i < major; i++) {
			Nodo current = new Nodo(i, i, 0);
			previous.setLiga(current);
			previous = current;
		}

		previous.setLiga(mainHeadNode);
	}

	public void phaseTwo(int[][] matriz) {
		if (mainHead == null || matriz == null || matriz.length == 0 || matriz[0].length == 0) {
			return;
		}

		Nodo indexNode = mainHead.getLiga();

		while (indexNode != mainHead) {
			int currentRow = indexNode.getFila();
			Nodo previousNode = indexNode;

			if (currentRow < matriz.length) {
				for (int col = 0; col < matriz[0].length; col++) {
					int matrixValue = matriz[currentRow][col];

					if (matrixValue != 0) {
						Nodo dataNode = new Nodo(currentRow, col, matrixValue);
						previousNode.setLigaFila(dataNode);
						previousNode = dataNode;
					}
				}
			}

			previousNode.setLigaFila(indexNode);
			indexNode = indexNode.getLiga();
		}

	}

	public void phaseThree(int[][] matriz) {
		if (mainHead == null || matriz == null || matriz.length == 0 || matriz[0].length == 0) {
			return;
		}

		Nodo columnHead = mainHead.getLiga();

		while (columnHead != mainHead) {
			Nodo previousColumnNode = columnHead;
			Nodo rowHead = mainHead.getLiga();

			while (rowHead != mainHead) {
				Nodo dataNode = findDataNodeByColumn(rowHead, columnHead.getColumna());

				if (dataNode != null) {
					previousColumnNode.setLigaColumna(dataNode);
					previousColumnNode = dataNode;
				}

				rowHead = rowHead.getLiga();
			}

			previousColumnNode.setLigaColumna(columnHead);
			columnHead = columnHead.getLiga();
		}

	}

	private Nodo findDataNodeByColumn(Nodo rowHead, int targetColumn) {
		if (rowHead == null) {
			return null;
		}

		Nodo currentNode = rowHead.getLigaFila();

		while (currentNode != rowHead) {
			if (currentNode.getColumna() == targetColumn) {
				return currentNode;
			}
			currentNode = currentNode.getLigaFila();
		}

		return null;
	}

	public String showSummary() {
		return "Info: filas(" + mainHead.getFila() + ") | Columnas(" + mainHead.getColumna() + ")";
	}

	public String sumRows() {
		if (mainHead == null) {
			return "";
		}

		String output = "";
		int totalRows = mainHead.getFila();
		Nodo rowHead = mainHead.getLiga();

		while (rowHead != mainHead) {
			if (rowHead.getFila() < totalRows) {
				int sumRow = 0;
				Nodo dataNode = rowHead.getLigaFila();

				while (dataNode != rowHead) {
					sumRow += dataNode.getDato();
					dataNode = dataNode.getLigaFila();
				}

				output += "Suma Fila " + rowHead.getFila() + ": " + sumRow + "\n";
			}

			rowHead = rowHead.getLiga();
		}

		return output;
	}

	public String sumCols() {
		if (mainHead == null) {
			return "";
		}

		String output = "";
		int totalCols = mainHead.getColumna();
		Nodo colHead = mainHead.getLiga();

		while (colHead != mainHead) {
			if (colHead.getColumna() < totalCols) {
				int sumCol = 0;
				Nodo dataNode = colHead.getLigaColumna();

				while (dataNode != colHead) {
					sumCol += dataNode.getDato();
					dataNode = dataNode.getLigaColumna();
				}

				output += "Suma Columna " + colHead.getColumna() + ": " + sumCol + "\n";
			}

			colHead = colHead.getLiga();
		}

		return output;
	}

	public void insertDato(Scanner scanner) {
		if (scanner == null || mainHead == null) {
			return;
		}

		System.out.println("Fila:");
		int insertRow = scanner.nextInt();
		System.out.println("Columna:");
		int insertCol = scanner.nextInt();
		System.out.println("Dato:");
		int insertValue = scanner.nextInt();

		if (insertRow < 0 || insertCol < 0) {
			System.out.println("Coordenadas inválidas.");
			return;
		}

		int rows = mainHead.getFila();
		int cols = mainHead.getColumna();
		int newRows = rows > insertRow ? rows : insertRow + 1;
		int newCols = cols > insertCol ? cols : insertCol + 1;

		int[][] rebuiltMatrix = toDenseMatrix(newRows, newCols);
		int currentValue = rebuiltMatrix[insertRow][insertCol];

		if (currentValue != 0) {
			System.out.println("Dato actual: " + currentValue + ", desea sumar (1) o Reemplazar (2)?");
			int decision = scanner.nextInt();

			if (decision == 1) {
				rebuiltMatrix[insertRow][insertCol] = currentValue + insertValue;
			} else if (decision == 2) {
				rebuiltMatrix[insertRow][insertCol] = insertValue;
			} else {
				System.out.println("Saliendo de inserción.");
				return;
			}
		} else {
			rebuiltMatrix[insertRow][insertCol] = insertValue;
		}

		createMatriz(rebuiltMatrix);
	}

	public void deleteByCord(int row, int col) {
		if (mainHead == null || row < 0 || col < 0) {
			System.out.println("No se encontró la coordenada");
			return;
		}

		int rows = mainHead.getFila();
		int cols = mainHead.getColumna();

		if (row >= rows || col >= cols) {
			System.out.println("No se encontró la coordenada");
			return;
		}

		int[][] rebuiltMatrix = toDenseMatrix(rows, cols);

		if (rebuiltMatrix[row][col] == 0) {
			System.out.println("No se encontró la coordenada");
			return;
		}

		rebuiltMatrix[row][col] = 0;
		createMatriz(rebuiltMatrix);
	}

	public void deleteByDato(int dato) {
		if (mainHead == null) {
			System.out.println("No se encontró la coordenada");
			return;
		}

		int rows = mainHead.getFila();
		int cols = mainHead.getColumna();
		int[][] rebuiltMatrix = toDenseMatrix(rows, cols);
		boolean found = false;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (rebuiltMatrix[row][col] == dato) {
					rebuiltMatrix[row][col] = 0;
					found = true;
				}
			}
		}

		if (!found) {
			System.out.println("No se encontró la coordenada");
			return;
		}

		createMatriz(rebuiltMatrix);
	}

	public void sumMatrix(Forma1 formaToSum) {
		if (mainHead == null || formaToSum == null || formaToSum.getMainHead() == null) {
			return;
		}

		int rows = mainHead.getFila() > formaToSum.getMainHead().getFila() ? mainHead.getFila()
				: formaToSum.getMainHead().getFila();
		int cols = mainHead.getColumna() > formaToSum.getMainHead().getColumna() ? mainHead.getColumna()
				: formaToSum.getMainHead().getColumna();

		int[][] matrizA = toDenseMatrix(rows, cols);
		int[][] matrizB = formaToSum.toDenseMatrix(rows, cols);
		int[][] resultMatrix = new int[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				resultMatrix[row][col] = matrizA[row][col] + matrizB[row][col];
			}
		}

		createMatriz(resultMatrix);
	}

	public void multiplyMatrix(Forma1 formaToMultiply) {
		if (mainHead == null || formaToMultiply == null || formaToMultiply.getMainHead() == null) {
			return;
		}

		int rows = mainHead.getFila() > formaToMultiply.getMainHead().getFila() ? mainHead.getFila()
				: formaToMultiply.getMainHead().getFila();
		int cols = mainHead.getColumna() > formaToMultiply.getMainHead().getColumna() ? mainHead.getColumna()
				: formaToMultiply.getMainHead().getColumna();

		int[][] matrizA = toDenseMatrix(rows, cols);
		int[][] matrizB = formaToMultiply.toDenseMatrix(rows, cols);
		int[][] resultMatrix = new int[rows][cols];

		// Coordinate-wise multiplication (not matrix multiplication).
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				resultMatrix[row][col] = matrizA[row][col] * matrizB[row][col];
			}
		}

		createMatriz(resultMatrix);
	}

	private int[][] toDenseMatrix(int rows, int cols) {
		int[][] denseMatrix = new int[rows][cols];

		if (mainHead == null) {
			return denseMatrix;
		}

		Nodo rowHead = mainHead.getLiga();

		while (rowHead != mainHead) {
			Nodo dataNode = rowHead.getLigaFila();

			while (dataNode != rowHead) {
				int row = dataNode.getFila();
				int col = dataNode.getColumna();

				if (row < rows && col < cols) {
					denseMatrix[row][col] = dataNode.getDato();
				}

				dataNode = dataNode.getLigaFila();
			}

			rowHead = rowHead.getLiga();
		}

		return denseMatrix;
	}

	public String showForma() {
		String output = "";

		output += "## FORMA 1 ##\n";
		output += "----------\n";

		Nodo indexNode = mainHead;

		do {
			if (indexNode == mainHead) {
				output += "Punta: " + indexNode.toString().split("@")[1] + " | Filas: " + indexNode.getFila()
						+ " , Columnas: " + indexNode.getColumna() + "\n";
			} else {
				output += "Nodo Cabeza: " + indexNode.toString().split("@")[1] + " | Fila: " + indexNode.getFila()
						+ " , Columna: " + indexNode.getColumna() + " ,LigaFila: "
						+ indexNode.getLigaFila().toString().split("@")[1] + " ,LigaColumna: "
						+ indexNode.getLigaColumna().toString().split("@")[1] + "\n";
				Nodo dataNode = indexNode.getLigaFila();

				while (dataNode != indexNode) {
					output += "-- Nodo: " + dataNode.toString().split("@")[1] + " | Fila: " + dataNode.getFila() + " ,Columna: "
							+ dataNode.getColumna() + " ,Dato: " + dataNode.getDato() + " ,LigaFila: "
							+ dataNode.getLigaFila().toString().split("@")[1] + " ,LigaColumna: "
							+ dataNode.getLigaColumna().toString().split("@")[1] + "\n";
					dataNode = dataNode.getLigaFila();
				}

			}
			indexNode = indexNode.getLiga();

		} while (indexNode != mainHead);

		return output;
	}

}
