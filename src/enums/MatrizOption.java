package enums;

import enums.MatrizOption;

public enum MatrizOption {
	EXIT(0), NEW(1), REPLACE(2), SHOW_FORMA(3), SUMAR_FILAS(4), SUMAR_COLUMNAS(5), INSERT_DATO(6), DELETE_DATO(7), SUMAR_MATRICES(8), MULTIPLICAR_MATRICES(9), INVALID(999);

	private final int value;

	MatrizOption(int value) {
		this.value = value;
	}

	public static MatrizOption fromInt(int value) {
		for (MatrizOption option : MatrizOption.values()) {
			if (option.value == value) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid option: " + value);
	}
}
