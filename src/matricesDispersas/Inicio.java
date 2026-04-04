package matricesDispersas;

import java.util.Scanner;

import enums.MainMenuOption;
import enums.MatrizOption;

public class Inicio {
	private Tripleta[] allTripletas;

	public static void main(String[] args) {
		System.out.println("Hello");

		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu(scanner);

		MainMenuOption userInput;

		do {
			userInput = menu.showMainMenu();

			switch (userInput) {
			case TRIPLETA: {

				caseTripleta(menu);

				break;
			}

			case FORMA_1: {

				System.out.println("FORMA 1");

				break;
			}

			case FORMA_2: {

				System.out.println("FORMA 2");

				break;
			}

			case EXIT: {
				System.out.println("ADIOS!");
				break;
			}
			default:
				System.out.println("Option Invalida");
			}

		} while (userInput != MainMenuOption.EXIT);

		scanner.close();

	}

	static void caseTripleta(Menu menu) {

		MatrizOption userInput;

		do {
			System.out.println("Menu - Tripletas");
			userInput = menu.showMatrizMenu();

			switch (userInput) {
			case NEW: {

				break;
			}

			case REPLACE: {

				System.out.println("Replace");

				break;
			}

			case SHOW_FORMA: {

				System.out.println("Show Forma");

				break;
			}

			case EXIT: {
				System.out.println("Menú Anteior!");
				break;
			}
			default:
				System.out.println("Option Invalida");
			}

		} while (userInput != MatrizOption.EXIT);

	}

}
