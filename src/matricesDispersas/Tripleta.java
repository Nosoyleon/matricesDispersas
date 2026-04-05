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
		int datosRow = 1;
		
		while(datosRow <= numDatos) {			
			int randomRow = (int) (Math.random() * ((rows - 1) - 0 + 1)) + 0;
			int randomCol = (int) (Math.random() * ((cols - 1) - 0 + 1)) + 0;
			int randomDato = (int) (Math.random() * (20 - (-20) + 1)) + (-20);
			
			boolean existCord = false;
			
			// Check if dato in cord was added
			
			for(int j = 1; j<= numDatos; j++) {
				if(datos[j][0] == randomRow && datos[j][1] == randomCol) {
					existCord = true;
				}
			}
			
			if(!existCord) {
				
				datos[datosRow][0] = randomRow;
				datos[datosRow][1] = randomCol;
				datos[datosRow][2] = randomDato;
				datosRow++;
			}
			
		}
	
		
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
}
