package enums;

import enums.Operaciones;

public enum Operaciones {
	EXIT(0), REPLACE(1), SHOW_FORMA(2), SUMAR_FILAS(3), SUMAR_COLUMNAS(4), INSERT_DATO(5), DELETE_DATO(6), SUMAR_MATRICES(7), MULTIPLICAR_MATRICES(0);

	private final int value;

	Operaciones(int value) {
		this.value = value;
	}

	public static Operaciones fromInt(int value) {
		for (Operaciones option : Operaciones.values()) {
			if (option.value == value) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid option: " + value);
	}
}
