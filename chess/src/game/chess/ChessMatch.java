package game.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import game.chess.pieces.King;
import game.chess.pieces.Piece;
import game.chess.pieces.Rook;
import game.main.Game;

public class ChessMatch {

	private final Board board;

	public ChessMatch() throws IOException {
		this.board = new Board();

		this.board.placePiece(new Rook(game.chess.Color.BLACK, this.board), new Position(0, 0));
		this.board.placePiece(new King(game.chess.Color.BLACK, this.board), new Position(0, 4));
		this.board.placePiece(new Rook(game.chess.Color.BLACK, this.board), new Position(0, 7));

		this.board.placePiece(new Rook(game.chess.Color.WHITE, this.board), new Position(7, 7));
		this.board.placePiece(new King(game.chess.Color.WHITE, this.board), new Position(7, 4));
		this.board.placePiece(new Rook(game.chess.Color.WHITE, this.board), new Position(7, 0));
	}

	public void render(Graphics render) {
		int squareWidth = Game.WIDTH / this.board.getROW();
		int squareHeight = Game.HEIGHT / this.board.getCOLUMN();

		for (int column = 0; column < this.board.getROW(); column++) {
			for (int row = 0; row < this.board.getCOLUMN(); row++) {
				int squareX = column * squareWidth;
				int squareY = row * squareHeight;

				if ((column + row) % 2 == 0) {
					render.setColor(Color.LIGHT_GRAY);
				} else {
					render.setColor(Color.GRAY);
				}

				render.fillRect(squareX, squareY, squareWidth, squareHeight);

				Piece piece = this.board.getPiece(row, column);

				if (piece != null) {
					piece.render(render, squareX, squareY, squareWidth, squareHeight);
				}
			}
		}
	}

}
