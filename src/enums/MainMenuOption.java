package enums;

import enums.MainMenuOption;

public enum MainMenuOption {
	EXIT(0), TRIPLETA(1), FORMA_1(2), FORMA_2(3), INVALID(999);

	private final int value;

	MainMenuOption(int value) {
		this.value = value;
	}

	public static MainMenuOption fromInt(int value) {
		for (MainMenuOption option : MainMenuOption.values()) {
			if (option.value == value) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid option: " + value);
	}
}