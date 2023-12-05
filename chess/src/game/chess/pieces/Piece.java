package game.chess.pieces;

import game.chess.Board;
import game.chess.Position;

public abstract class Piece {

	private Board board;
	private final Position position;

	public Piece(Board board) {
		this.board = board;
		this.position = null;
	}

	public Board getBoard() {
		return board;
	}

}
