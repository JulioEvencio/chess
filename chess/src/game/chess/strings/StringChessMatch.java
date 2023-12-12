package game.chess.strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import game.resources.exceptions.ResourcesException;
import game.strings.StringGame;

public class StringChessMatch {

	public static String Promotion = "Promotion";

	public static String ChooseAPiece = "Choose a piece";

	public static String Queen = "Queen";
	public static String Rook = "Rook";
	public static String Bishop = "Bishop";
	public static String Knight = "Knight";

	public static String ThereIsNoPieceToBePromoted = "There is no piece to be promoted!";

	public static String Checkmate = "Checkmate";

	public static String BlackWon = "Black won!";
	public static String WhiteWon = "White won!";

	public static String AnUnexpectedErrorOccurred = "An unexpected error occurred...";

	public static void load(String language) throws ResourcesException {
		try {
			String fileName = String.format("/language/game/chess/%s-chessmatch.txt", language);

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringGame.class.getResourceAsStream(fileName)))) {
				// Promotion
				String content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.Promotion = content;

				// ChooseAPiece
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.ChooseAPiece = content;

				// Queen
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.Queen = content;

				// Rook
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.Rook = content;

				// Bishop
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.Bishop = content;

				// Knight
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.Knight = content;

				// ThereIsNoPieceToBePromoted
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.ThereIsNoPieceToBePromoted = content;

				// Checkmate
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.Checkmate = content;

				// BlackWon
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.BlackWon = content;

				// WhiteWon
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.WhiteWon = content;

				// AnUnexpectedErrorOccurred
				content = reader.readLine();

				if (content == null) {
					throw new RuntimeException();
				}

				StringChessMatch.AnUnexpectedErrorOccurred = content;
			}
		} catch (Exception e) {
			throw new ResourcesException("Erro ao carregar arquivos!");
		}
	}

}
