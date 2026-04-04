package enums;

import enums.MatrizOption;

public enum MatrizOption {
	EXIT(0), NEW(1), SHOW_FORMA(2), SUMAR_FILAS(3), SUMAR_COLUMNAS(4), INSERT_DATO(5), DELETE_DATO(6), SUMAR_MATRICES(7), MULTIPLICAR_MATRICES(8), INVALID(999);

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
