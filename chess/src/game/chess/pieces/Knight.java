package game.chess.pieces;

import java.io.IOException;

import game.chess.Board;
import game.chess.Color;
import game.chess.Position;
import game.resources.Spritesheet;

public class Knight extends Piece {

	public Knight(Color color, Board board) throws IOException {
		super(color, board);

		Spritesheet spritesheet = new Spritesheet("/sprites/chess.png");

		if (super.getColor() == Color.BLACK) {
			super.sprite = spritesheet.getSprite(128 * 1, 0, 128, 128);
		} else {
			super.sprite = spritesheet.getSprite(128 * 1, 128, 128, 128);
		}
		
		Spritesheet spritesheetInverse = new Spritesheet("/sprites/chess-inverse.png");

		if (super.getColor() == Color.BLACK) {
			super.spriteInverse = spritesheetInverse.getSprite(128 * 4, 128, 128, 128);
		} else {
			super.spriteInverse = spritesheetInverse.getSprite(128 * 4, 0, 128, 128);
		}
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[super.getBoard().getROW()][super.getBoard().getCOLUMN()];

		Position position = new Position(0, 0);

		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn() - 2);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		position.setRow(this.getPosition().getRow() - 2);
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		position.setRow(this.getPosition().getRow() - 2);
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		position.setRow(this.getPosition().getRow() - 1);
		position.setColumn(this.getPosition().getColumn() + 2);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn() + 2);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		position.setRow(this.getPosition().getRow() + 2);
		position.setColumn(this.getPosition().getColumn() + 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		position.setRow(this.getPosition().getRow() + 2);
		position.setColumn(this.getPosition().getColumn() - 1);

		if (super.getBoard().positionExists(position) && this.canMove(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		position.setRow(this.getPosition().getRow() + 1);
		position.setColumn(this.getPosition().getColumn() - 2);

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
