package matricesDispersas;

import java.util.Scanner;

import enums.MainMenuOption;
import enums.MatrizOption;

public class Menu {

	private final Scanner scanner;

	public Menu(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	
	public MainMenuOption showMainMenu() {
		
		System.out.println("Bienvenido - Tripletas");
		System.out.println("");
		
		System.out.println("1 - Menú Tripleta");		
		System.out.println("2 - Menú Forma 1");
		System.out.println("3 - Menú Forma 2");
		
		System.out.println("0 - Salir");
		
		try {
			MainMenuOption input = MainMenuOption.fromInt(scanner.nextInt());
			
			return input;
			
		} catch (Exception e) {
			return MainMenuOption.INVALID;
		}
		
	}
	
	public MatrizOption showMatrizMenu() {
		
		System.out.println("");
	
		System.out.println("1 - Nueva Matriz");
		System.out.println("2 - Reemplazar Matriz");
		System.out.println("3 - Mostrar Forma");
		System.out.println("4 - Sumar Filas");
		System.out.println("5 - Sumar Columnas");
		System.out.println("6 - Insertar Dato");
		System.out.println("7 - Eliminar Dato");
		System.out.println("8 - Sumar Matrices");
		System.out.println("9 - Multiplicar Matrices");
	
		
		System.out.println("0 - Volver");
		
		try {
			MatrizOption input = MatrizOption.fromInt(scanner.nextInt());
			
			return input;
			
		} catch (Exception e) {
			return MatrizOption.INVALID;
		}
		
	}
	
}
