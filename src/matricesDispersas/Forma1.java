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
		
		phaseOne(matriz.length, matriz[0].length);
		phaseTwo();
		phaseThree();
		
	}
	
	public void phaseOne(int rows, int columns) {
		
		int major = rows> columns ? rows : columns;
		Nodo p,x;
		
		x = new Nodo(rows, columns, 0);
		
		for(int i = 0; i < major; i ++) {
			append(i, i, 0);
		}
		
	}
	
	public void phaseTwo() {
		
	}
	
	public void phaseThree() {
		
	}
	
	public void append(int row, int col, int value) {
		
	}
	
	
	
}
