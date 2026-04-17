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
		initializeHeadSelfLinks();
	}

	private void initializeHeadSelfLinks() {
		if (mainHead == null) {
			return;
		}

		Nodo headNode = mainHead.getLiga();
		while (headNode != mainHead) {
			headNode.setLigaFila(headNode);
			headNode.setLigaColumna(headNode);
			headNode = headNode.getLiga();
		}
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

		expandTo(insertRow + 1, insertCol + 1);

		Nodo existingNode = findNodeByCoordinate(insertRow, insertCol);

		if (existingNode != null) {
			System.out.println("Dato actual: " + existingNode.getDato() + ", desea sumar (1) o Reemplazar (2)?");
			int decision = scanner.nextInt();
			int newValue;

			if (decision == 1) {
				newValue = existingNode.getDato() + insertValue;
			} else if (decision == 2) {
				newValue = insertValue;
			} else {
				System.out.println("Saliendo de inserción.");
				return;
			}

			if (newValue == 0) {
				removeNodeByCoordinate(insertRow, insertCol);
			} else {
				existingNode.setDato(newValue);
			}
		} else {
			if (insertValue != 0) {
				insertNodeInStructure(insertRow, insertCol, insertValue);
			}
		}
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

		if (!removeNodeByCoordinate(row, col)) {
			System.out.println("No se encontró la coordenada");
		}
	}

	public void deleteByDato(int dato) {
		if (mainHead == null) {
			System.out.println("No se encontró la coordenada");
			return;
		}

		boolean found = false;
		Nodo rowHead = mainHead.getLiga();

		while (rowHead != mainHead) {
			Nodo previousNode = rowHead;
			Nodo currentNode = rowHead.getLigaFila();

			while (currentNode != rowHead) {
				if (currentNode.getDato() == dato) {
					previousNode.setLigaFila(currentNode.getLigaFila());
					unlinkNodeFromColumn(currentNode);
					currentNode = previousNode.getLigaFila();
					found = true;
				} else {
					previousNode = currentNode;
					currentNode = currentNode.getLigaFila();
				}
			}

			rowHead = rowHead.getLiga();
		}

		if (!found) {
			System.out.println("No se encontró la coordenada");
		}
	}

	public void sumMatrix(Forma1 formaToSum) {
		if (mainHead == null || formaToSum == null || formaToSum.getMainHead() == null) {
			return;
		}

		int rows = mainHead.getFila() > formaToSum.getMainHead().getFila() ? mainHead.getFila()
				: formaToSum.getMainHead().getFila();
		int cols = mainHead.getColumna() > formaToSum.getMainHead().getColumna() ? mainHead.getColumna()
				: formaToSum.getMainHead().getColumna();

		Forma1 result = new Forma1();
		result.phaseOne(rows, cols);
		result.initializeHeadSelfLinks();
		copyAllNodesTo(result, mainHead);

		Nodo rowHead = formaToSum.getMainHead().getLiga();
		while (rowHead != formaToSum.getMainHead()) {
			Nodo dataNode = rowHead.getLigaFila();

			while (dataNode != rowHead) {
				int row = dataNode.getFila();
				int col = dataNode.getColumna();
				int value = dataNode.getDato();

				Nodo existingNode = result.findNodeByCoordinate(row, col);

				if (existingNode == null) {
					result.insertNodeInStructure(row, col, value);
				} else {
					int newValue = existingNode.getDato() + value;

					if (newValue == 0) {
						result.removeNodeByCoordinate(row, col);
					} else {
						existingNode.setDato(newValue);
					}
				}

				dataNode = dataNode.getLigaFila();
			}

			rowHead = rowHead.getLiga();
		}

		setMainHead(result.getMainHead());
	}

	public void multiplyMatrix(Forma1 formaToMultiply) {
		if (mainHead == null || formaToMultiply == null || formaToMultiply.getMainHead() == null) {
			return;
		}

		int rows = mainHead.getFila() > formaToMultiply.getMainHead().getFila() ? mainHead.getFila()
				: formaToMultiply.getMainHead().getFila();
		int cols = mainHead.getColumna() > formaToMultiply.getMainHead().getColumna() ? mainHead.getColumna()
				: formaToMultiply.getMainHead().getColumna();

		Forma1 result = new Forma1();
		result.phaseOne(rows, cols);
		result.initializeHeadSelfLinks();

		Nodo rowHead = mainHead.getLiga();
		while (rowHead != mainHead) {
			Nodo dataNode = rowHead.getLigaFila();

			while (dataNode != rowHead) {
				int row = dataNode.getFila();
				int col = dataNode.getColumna();
				Nodo otherNode = formaToMultiply.findNodeByCoordinate(row, col);

				if (otherNode != null) {
					int product = dataNode.getDato() * otherNode.getDato();
					if (product != 0) {
						result.insertNodeInStructure(row, col, product);
					}
				}

				dataNode = dataNode.getLigaFila();
			}

			rowHead = rowHead.getLiga();
		}

		setMainHead(result.getMainHead());
	}

	private void expandTo(int minRows, int minCols) {
		if (mainHead == null) {
			phaseOne(minRows, minCols);
			initializeHeadSelfLinks();
			return;
		}

		int currentRows = mainHead.getFila();
		int currentCols = mainHead.getColumna();

		if (minRows <= currentRows && minCols <= currentCols) {
			return;
		}

		int rows = minRows > currentRows ? minRows : currentRows;
		int cols = minCols > currentCols ? minCols : currentCols;
		Nodo oldMain = mainHead;

		Forma1 expanded = new Forma1();
		expanded.phaseOne(rows, cols);
		expanded.initializeHeadSelfLinks();
		copyAllNodesTo(expanded, oldMain);
		setMainHead(expanded.getMainHead());
	}

	private void copyAllNodesTo(Forma1 target, Nodo sourceMain) {
		if (target == null || sourceMain == null) {
			return;
		}

		Nodo rowHead = sourceMain.getLiga();
		while (rowHead != sourceMain) {
			Nodo dataNode = rowHead.getLigaFila();

			while (dataNode != rowHead) {
				target.insertNodeInStructure(dataNode.getFila(), dataNode.getColumna(), dataNode.getDato());
				dataNode = dataNode.getLigaFila();
			}

			rowHead = rowHead.getLiga();
		}
	}

	private Nodo getHeadNode(int index) {
		if (mainHead == null || index < 0) {
			return null;
		}

		Nodo headNode = mainHead.getLiga();
		int current = 0;

		while (headNode != mainHead && current < index) {
			headNode = headNode.getLiga();
			current++;
		}

		if (headNode == mainHead) {
			return null;
		}

		return headNode;
	}

	private Nodo findNodeByCoordinate(int row, int col) {
		if (mainHead == null || row < 0 || col < 0) {
			return null;
		}

		if (row >= mainHead.getFila() || col >= mainHead.getColumna()) {
			return null;
		}

		Nodo rowHead = getHeadNode(row);
		if (rowHead == null) {
			return null;
		}

		Nodo currentNode = rowHead.getLigaFila();
		while (currentNode != rowHead) {
			if (currentNode.getColumna() == col) {
				return currentNode;
			}
			currentNode = currentNode.getLigaFila();
		}

		return null;
	}

	private void insertNodeInStructure(int row, int col, int value) {
		if (value == 0) {
			return;
		}

		Nodo rowHead = getHeadNode(row);
		Nodo colHead = getHeadNode(col);

		if (rowHead == null || colHead == null) {
			return;
		}

		Nodo prevRow = rowHead;
		Nodo currentRow = rowHead.getLigaFila();
		while (currentRow != rowHead && currentRow.getColumna() < col) {
			prevRow = currentRow;
			currentRow = currentRow.getLigaFila();
		}

		Nodo prevCol = colHead;
		Nodo currentCol = colHead.getLigaColumna();
		while (currentCol != colHead && currentCol.getFila() < row) {
			prevCol = currentCol;
			currentCol = currentCol.getLigaColumna();
		}

		Nodo newNode = new Nodo(row, col, value);
		newNode.setLigaFila(currentRow);
		prevRow.setLigaFila(newNode);

		newNode.setLigaColumna(currentCol);
		prevCol.setLigaColumna(newNode);
	}

	private boolean removeNodeByCoordinate(int row, int col) {
		Nodo rowHead = getHeadNode(row);
		if (rowHead == null) {
			return false;
		}

		Nodo prevRow = rowHead;
		Nodo currentRow = rowHead.getLigaFila();

		while (currentRow != rowHead && currentRow.getColumna() != col) {
			prevRow = currentRow;
			currentRow = currentRow.getLigaFila();
		}

		if (currentRow == rowHead) {
			return false;
		}

		prevRow.setLigaFila(currentRow.getLigaFila());
		unlinkNodeFromColumn(currentRow);
		return true;
	}

	private void unlinkNodeFromColumn(Nodo nodeToRemove) {
		if (nodeToRemove == null) {
			return;
		}

		Nodo colHead = getHeadNode(nodeToRemove.getColumna());
		if (colHead == null) {
			return;
		}

		Nodo prevCol = colHead;
		Nodo currentCol = colHead.getLigaColumna();

		while (currentCol != colHead && currentCol != nodeToRemove) {
			prevCol = currentCol;
			currentCol = currentCol.getLigaColumna();
		}

		if (currentCol == nodeToRemove) {
			prevCol.setLigaColumna(nodeToRemove.getLigaColumna());
		}
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
