package game.chess;

import java.awt.Color;
import java.awt.Graphics;

import game.chess.pieces.Piece;
import game.main.Game;

public class ChessMatch {

	private final Board board;

	public ChessMatch() {
		this.board = new Board();
	}

	public void render(Graphics render) {
		int squareWidth = Game.WIDTH / this.board.getROW();
		int squareHeight = Game.HEIGHT / this.board.getCOLUMN();

		for (int row = 0; row < this.board.getROW(); row++) {
			for (int column = 0; column < this.board.getCOLUMN(); column++) {
				int squareX = row * squareWidth;
				int squareY = column * squareHeight;

				if ((row + column) % 2 == 0) {
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
