package game.resources;

import javax.swing.JOptionPane;

import game.Game;
import game.chess.strings.StringChessMatch;
import game.resources.exceptions.ResourcesException;
import game.strings.StringError;
import game.strings.StringGame;

public class Language {

	public static void load() {
		String language = Language.selectLanguage();

		try {
			if (!language.equals("english")) {
				StringGame.load(language);
				StringError.load(language);

				StringChessMatch.load(language);
			}
		} catch (ResourcesException e) {
			Game.exitWithError(e.getMessage(), e);
		}
	}

	private static String selectLanguage() {
		String[] options = { "English", "Português" };

		String language = (String) JOptionPane.showInputDialog(null, "", StringGame.Chess, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (language == null) {
			language = "english";
		} else if (language.equals("Português")) {
			language = "portuguese";
		} else {
			language = "english";
		}

		return language;
	}
}
