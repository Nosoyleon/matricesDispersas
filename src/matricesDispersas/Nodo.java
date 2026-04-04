package matricesDispersas;

public class Nodo {
	
	private int value, row, col;
	private Nodo liga, ligaFila, ligaColumna;
	
	
	public Nodo(int row, int col, int value) {
		super();
		this.value = value;
		this.row = row;
		this.col = col;
		this.liga = null;
		this.ligaFila = null;
		this.ligaColumna = null;
	}

	
	public int getDato() {
		return value;
	}
	

	public void setDato(int dato) {
		this.value = dato;
	}


	public int getFila() {
		return row;
	}


	public void setFila(int fila) {
		this.row = fila;
	}


	public int getColumna() {
		return col;
	}


	public void setColumna(int columna) {
		this.col = columna;
	}


	public Nodo getLiga() {
		return liga;
	}


	public void setLiga(Nodo liga) {
		this.liga = liga;
	}


	public Nodo getLigaFila() {
		return ligaFila;
	}


	public void setLigaFila(Nodo ligaFila) {
		this.ligaFila = ligaFila;
	}


	public Nodo getLigaColumna() {
		return ligaColumna;
	}


	public void setLigaColumna(Nodo ligaColumna) {
		this.ligaColumna = ligaColumna;
	}

}
