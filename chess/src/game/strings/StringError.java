package game.strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import game.resources.exceptions.ResourcesException;

public class StringError {

	public static String Error = "Error";
	public static String AnUnexpectedErrorOccurred = "An unexpected error occurred...";

	public static void load(String language) throws ResourcesException {
		try {
			String fileName = String.format("/language/game/%s-error.txt", language);

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringGame.class.getResourceAsStream(fileName)))) {
				// Error
				String content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringError.Error = content;

				// AnUnexpectedErrorOccurred
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringError.AnUnexpectedErrorOccurred = content;
			}
		} catch (Exception e) {
			throw new ResourcesException("Erro ao carregar arquivos!");
		}
	}

}
