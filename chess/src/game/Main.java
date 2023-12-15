package game;

import javax.swing.SwingUtilities;

import game.resources.Language;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Language.load();

			new Thread(new Game()).start();
		});
	}

}
