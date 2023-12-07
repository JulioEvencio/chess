package game.chess.pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.chess.Board;
import game.chess.Color;
import game.chess.Position;

public abstract class Piece {

	private final Color color;
	private Position position;

	private final Board board;

	protected BufferedImage sprite;

	public Piece(Color color, Board board) {
		this.color = color;
		this.position = null;

		this.board = board;

		this.sprite = null;
	}

	public Color getColor() {
		return this.color;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Board getBoard() {
		return this.board;
	}

	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position) {
		return this.possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean mat[][] = this.possibleMoves();

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}

		return false;
	}

	public void render(Graphics render, int x, int y, int width, int height) {
		render.drawImage(this.sprite, x, y, width, height, null);
	}

}
