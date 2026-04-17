package matricesDispersas;

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
