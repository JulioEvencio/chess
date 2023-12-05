package game.chess.pieces;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.chess.Board;
import game.chess.Color;
import game.chess.Position;

public abstract class Piece {

	private final Color color;
	private final Position position;

	private final Board board;

	private BufferedImage sprite;

	public Piece(Color color, Position position, Board board) {
		this.color = color;
		this.position = position;

		this.board = board;

		this.sprite = null;
	}

	public Color getColor() {
		return this.color;
	}

	public Position getPosition() {
		return this.position;
	}

	public Board getBoard() {
		return this.board;
	}

	public void render(Graphics render, int x, int y, int width, int height) {
		render.drawImage(this.sprite, x, y, width, height, null);
	}

}
