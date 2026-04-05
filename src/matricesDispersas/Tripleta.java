package matricesDispersas;

import java.util.ArrayList;
import java.util.Arrays;

public class Tripleta {
	private int datos[][];

	public Tripleta(int rows, int cols, int numDatos) {
		datos = new int[numDatos + 1][3];
		datos[0][0] = rows;
		datos[0][1] = cols;
		datos[0][2] = numDatos;
	}

	public void fillTripleta() {
		int rows = datos[0][0];
		int cols = datos[0][1];
		int numDatos = datos[0][2];
		int datosRow = 1;

		while (datosRow <= numDatos) {
			int randomRow = (int) (Math.random() * ((rows - 1) - 0 + 1)) + 0;
			int randomCol = (int) (Math.random() * ((cols - 1) - 0 + 1)) + 0;
			int randomDato = (int) (Math.random() * (20 - (-20) + 1)) + (-20);

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
		
		for(int i = 0; i < orderedOutput.length; i++) {
			if(orderedOutput[i] != null) {
				output+= orderedOutput[i] + "\n";
			}
		}

		return output;
	}

	private void orderMatriz() {
		Arrays.sort(datos, 1, datos.length, (a, b) -> {
			if (a[0] != b[0]) {
				return Integer.compare(a[0], b[0]); // sort by column 0 first
			} else {
				return Integer.compare(a[1], b[1]); // if equal, sort by column 1
			}
		});

	}
}
