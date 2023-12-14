package game.chess.pieces;

import java.io.IOException;

import game.chess.Board;
import game.chess.Color;
import game.chess.Position;
import game.resources.Spritesheet;

public class Bishop extends Piece {

	public Bishop(Color color, Board board) throws IOException {
		super(color, board);

		Spritesheet spritesheet = new Spritesheet("/sprites/chess.png");

		if (super.getColor() == Color.BLACK) {
			super.sprite = spritesheet.getSprite(128 * 2, 0, 128, 128);
		} else {
			super.sprite = spritesheet.getSprite(128 * 2, 128, 128, 128);
		}
		
		Spritesheet spritesheetInverse = new Spritesheet("/sprites/chess-inverse.png");

		if (super.getColor() == Color.BLACK) {
			super.spriteInverse = spritesheetInverse.getSprite(128 * 3, 128, 128, 128);
		} else {
			super.spriteInverse = spritesheetInverse.getSprite(128 * 3, 0, 128, 128);
		}
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[super.getBoard().getROW()][super.getBoard().getCOLUMN()];

		Position position = new Position(0, 0);

		// NW
		position.setRow(super.getPosition().getRow() - 1);
		position.setColumn(super.getPosition().getColumn() - 1);

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setRow(position.getRow() - 1);
			position.setColumn(position.getColumn() - 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// NE
		position.setRow(super.getPosition().getRow() - 1);
		position.setColumn(super.getPosition().getColumn() + 1);

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setRow(position.getRow() - 1);
			position.setColumn(position.getColumn() + 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// SE
		position.setRow(super.getPosition().getRow() + 1);
		position.setColumn(super.getPosition().getColumn() + 1);

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setRow(position.getRow() + 1);
			position.setColumn(position.getColumn() + 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// SW
		position.setRow(super.getPosition().getRow() + 1);
		position.setColumn(super.getPosition().getColumn() - 1);

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setRow(position.getRow() + 1);
			position.setColumn(position.getColumn() - 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		return mat;
	}

}
