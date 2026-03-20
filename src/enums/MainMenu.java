package enums;

import enums.MainMenu;

public enum MainMenu {
	EXIT(0), TRIPLETA(1), FORMA_1(2), FORMA_2(3), INVALID(999);

	private final int value;

	MainMenu(int value) {
		this.value = value;
	}

	public static MainMenu fromInt(int value) {
		for (MainMenu option : MainMenu.values()) {
			if (option.value == value) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid option: " + value);
	}
}