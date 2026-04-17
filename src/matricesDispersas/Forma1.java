package matricesDispersas;

public class Forma1 {

	private Nodo head;

	public Forma1() {
		super();
		this.head = null;
	}

	public Nodo getHead() {
		return head;
	}

	public void setHead(Nodo head) {
		this.head = head;
	}

	public void createMatriz(int matriz[][]) {
		if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
			setHead(null);
			return;
		}

		phaseOne(matriz.length, matriz[0].length);
		phaseTwo();
		phaseThree();

	}

	public void phaseOne(int rows, int columns) {
		int major = rows > columns ? rows : columns;

		Nodo mainHeadNode = new Nodo(rows, columns, 0);
		setHead(mainHeadNode);

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

	public void phaseTwo() {

	}

	public void phaseThree() {

	}

	public String showSummary() {
		return "Info: filas(" + head.getFila() + ") | Columnas(" + head.getColumna() + ")";
	}

	public String showForma() {
		String output = "";

		output += "## FORMA 1 ##\n";
		output += "----------\n";

		Nodo indexNode = head;

		do {
			if (indexNode == head) {
				output += "Punta | (Filas: " + indexNode.getFila() + " , Columnas: " + indexNode.getFila() + "\n";
			} else {
				output += "Nodo | (Fila: " + indexNode.getFila() + " , Columna: " + indexNode.getFila()+ "\n";
			}
			indexNode = indexNode.getLiga();

		} while (indexNode != head);

		return output;
	}

}
