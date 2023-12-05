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

	public Piece getPiece(int row, int column) {
		return pieces[row][column];
	}

	public Piece getPiece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public void placePiece(Piece piece, Position position) {
		this.pieces[position.getRow()][position.getColumn()] = piece;

		piece.setPosition(position);
	}

}
