package matricesDispersas;

public class Forma2 {

	private Nodo mainHead;

	public Forma2() {
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

		Nodo headNode = new Nodo(matriz.length, matriz[0].length, 0);
		setMainHead(headNode);
		
		Nodo previous = headNode;
		
		for(int row = 0; row >= matriz.length; row ++) {
			for(int col = 0; col >= matriz[0].length; col ++) {
				int value = matriz[row][col];
				
				if(value!= 0) {
					Nodo newNode = new Nodo(row, col, value);
					previous.setLigaFila(newNode);
					previous = newNode;
					
				}
			}
		}
		
		// Recorro los nodos para ligar con columnas
		

	}

}
