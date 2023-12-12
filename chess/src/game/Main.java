package game;

import game.resources.Language;

public class Main {

	public static void main(String[] args) {
		Language.load();

		new Thread(new Game()).start();
	}

}
