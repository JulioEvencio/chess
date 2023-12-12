package game.strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import game.resources.exceptions.ResourcesException;

public class StringGame {

	public static String Chess = "Chess";

	public static String Game = "Game";
	public static String NewGame = "New Game";
	public static String Confirm = "Confirm";
	public static String Cancel = "Cancel";
	public static String DoYouWantToStartANewGameTheCurrentGameWillBeLost = "Do you want to start a new game?\nThe current game will be lost...";

	public static String About = "About";
	public static String Info = "Info";
	public static String InfoMessage = "Chess - version " + game.Game.VERSION + "\n\nSoftware developed by Júlio Igreja\nhttps://github.com/JulioEvencio\n\nThis project is open source and you can download the source code at:\nhttps://github.com/JulioEvencio/chess";

	public static void load(String language) throws ResourcesException {
		try {
			String fileName = String.format("/language/game/%s-game.txt", language);

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringGame.class.getResourceAsStream(fileName)))) {
				// Chess
				String content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.Chess = content.replace("\\n", "\n");

				// Game
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.Game = content.replace("\\n", "\n");

				// NewGame
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.NewGame = content.replace("\\n", "\n");

				// Confirm
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.Confirm = content.replace("\\n", "\n");

				// Cancel
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.Cancel = content.replace("\\n", "\n");

				// DoYouWantToStartANewGameTheCurrentGameWillBeLost
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.DoYouWantToStartANewGameTheCurrentGameWillBeLost = content.replace("\\n", "\n");

				// About
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.About = content.replace("\\n", "\n");

				// Info
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.Info = content.replace("\\n", "\n");

				// InfoMessage
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.InfoMessage = content.replace("\\n", "\n").replace("${version}", game.Game.VERSION);
			}
		} catch (Exception e) {
			throw new ResourcesException("Erro ao carregar arquivos!");
		}
	}

}
