package matricesDispersas;

public class Tripleta {
	private int datos[][];
	
	public Tripleta(int rows, int cols) {
		this.datos = new int[rows][cols];
		
	}

	public Tripleta createNewRandom(int rows, int cols) {
		Tripleta newTripleta = new Tripleta(rows, cols);
		
		return newTripleta;
	}
}
