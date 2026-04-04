package matricesDispersas;

public class Tripleta {
	private int datos[][];

	public Tripleta(int rows, int cols, int numDatos) {
		datos = new int[numDatos + 1][3];
		datos[0][0] = rows;
		datos[0][1] = cols;
		datos[0][2] = numDatos;
	}

	public void filTripleta() {
		int rows = datos[0][0];
		int cols = datos[0][1];
		int numDatos = datos[0][2];

		int datosFilled = 0;

		while (datosFilled < numDatos) {
			int randomRow = (int) (Math.random() * ((rows -1) - 0 + 1)) + 0;
			int randomCol = (int) (Math.random() * ((cols -1) - 0 + 1)) + 0;
			int randomDato = (int) (Math.random() * (20 - (-20) + 1)) + (-20);

			if (datos[randomRow][randomCol] == 0) {
				datos[randomRow][randomCol] = randomDato;
				datosFilled++;
			}
		}
	}

	public String showSummary() {
		return "Info: filas(" + datos[0][0] + ") | Columnas(" + datos[0][1] + ") | Datos(" + datos[0][1] + ")";
	}
}
