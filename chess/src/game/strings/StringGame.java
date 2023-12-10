package game.strings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import game.resources.exceptions.ResourcesException;

public class StringGame {

	public static String Chess = "Chess";

	public static void load(String language) throws ResourcesException {
		try {
			String fileName = String.format("/language/game/%s-game.txt", language);

			InputStream file = StringGame.class.getResourceAsStream(fileName);

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
				String content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringGame.Chess = content;
			}
		} catch (Exception e) {
			throw new ResourcesException("Erro ao carregar arquivos!");
		}
	}

}
