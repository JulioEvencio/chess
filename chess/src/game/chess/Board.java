package game.chess;

import game.chess.pieces.Piece;

public class Board {

	private final int ROW;
	private final int COLUMN;
	private final Piece[][] pieces;

	public Board() {
		this.ROW = 8;
		this.COLUMN = 8;
		this.pieces = new Piece[this.ROW][this.COLUMN];
	}

	public int getROW() {
		return ROW;
	}

	public int getCOLUMN() {
		return COLUMN;
	}

}
