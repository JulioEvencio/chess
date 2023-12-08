package game.chess.pieces;

import java.io.IOException;

import game.chess.Board;
import game.chess.Color;
import game.chess.Position;
import game.resources.Spritesheet;

public class King extends Piece {

	public King(Color color, Board board) throws IOException {
		super(color, board);

		Spritesheet spritesheet = new Spritesheet("/sprites/chess.png");

		if (super.getColor() == Color.BLACK) {
			super.sprite = spritesheet.getSprite(128 * 5, 0, 128, 128);
		} else {
			super.sprite = spritesheet.getSprite(128 * 5, 128, 128, 128);
		}
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[super.getBoard().getROW()][super.getBoard().getCOLUMN()];

		Position position = new Position(0, 0);

		// Above
		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn());

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Below
		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn());

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Left
		position.setRow(this.getPosition().getRow());
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Right
		position.setRow(this.getPosition().getRow());
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// NW
		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// NE
		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// SW
		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// SE
		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		return mat;
	}

	private boolean canMove(Position position) {
		Piece piece = super.getBoard().getPiece(position);

		return piece == null || this.getColor() != piece.getColor();
	}

}
