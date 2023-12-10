package game.chess.pieces;

import java.io.IOException;

import game.chess.Board;
import game.chess.ChessMatch;
import game.chess.Color;
import game.chess.Position;
import game.resources.Spritesheet;

public class King extends Piece {

	private ChessMatch chessMatch;

	public King(Color color, Board board, ChessMatch chessMatch) throws IOException {
		super(color, board);

		Spritesheet spritesheet = new Spritesheet("/sprites/chess.png");

		if (super.getColor() == Color.BLACK) {
			super.sprite = spritesheet.getSprite(128 * 5, 0, 128, 128);
		} else {
			super.sprite = spritesheet.getSprite(128 * 5, 128, 128, 128);
		}

		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[super.getBoard().getROW()][super.getBoard().getCOLUMN()];

		Position position = new Position(0, 0);

		// Above
		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn());

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Below
		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn());

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Left
		position.setRow(this.getPosition().getRow());
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Right
		position.setRow(this.getPosition().getRow());
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// NW
		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// NE
		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// SW
		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// SE
		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position) && !this.testCheckKing(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Special move castling
		if (this.getMoveCount() == 0 && !this.chessMatch.isCheck()) {
			// Kingside rook
			Position positionT1 = new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 3);

			if (this.testRookCastling(positionT1)) {
				Position p1 = new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 1);
				Position p2 = new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 2);
				Position king = new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 1);

				if (super.getBoard().getPiece(p1) == null && super.getBoard().getPiece(p2) == null && !this.testCheckKing(king)) {
					mat[this.getPosition().getRow()][this.getPosition().getColumn() + 2] = true;
				}
			}

			// Queenside rook
			Position positionT2 = new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 4);

			if (this.testRookCastling(positionT2)) {
				Position p1 = new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 1);
				Position p2 = new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 2);
				Position p3 = new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 3);
				Position king = new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 1);

				if (super.getBoard().getPiece(p1) == null && super.getBoard().getPiece(p2) == null && super.getBoard().getPiece(p3) == null && !this.testCheckKing(king)) {
					mat[this.getPosition().getRow()][this.getPosition().getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}

	private boolean canMove(Position position) {
		Piece piece = super.getBoard().getPiece(position);

		return piece == null || this.getColor() != piece.getColor();
	}

	private boolean testRookCastling(Position position) {
		Piece piece = super.getBoard().getPiece(position);

		return piece instanceof Rook && piece.getColor() == this.getColor() && piece.getMoveCount() == 0;
	}

	private boolean testCheckKing(Position position) {
		for (int i = 0; i < super.getBoard().getROW(); i++) {
			for (int j = 0; j < super.getBoard().getCOLUMN(); j++) {
				Piece piece = super.getBoard().getPiece(i, j);

				if (piece != null && !(piece instanceof King) && piece.getColor() != this.getColor()) {
					boolean[][] mat = piece.possibleMoves();

					if (mat[position.getRow()][position.getColumn()]) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
}
