package game.chess.pieces;

import java.io.IOException;

import game.chess.Board;
import game.chess.Color;
import game.chess.Position;
import game.resources.Spritesheet;

public class Rook extends Piece {

	public Rook(Color color, Board board) throws IOException {
		super(color, board);

		Spritesheet spritesheet = new Spritesheet("/sprites/chess.png");

		if (super.getColor() == Color.BLACK) {
			super.sprite = spritesheet.getSprite(128 * 3, 0, 128, 128);
		} else {
			super.sprite = spritesheet.getSprite(128 * 3, 128, 128, 128);
		}
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[super.getBoard().getROW()][super.getBoard().getCOLUMN()];

		Position position = new Position(0, 0);

		// Above
		position.setRow(super.getPosition().getRow() - 1);
		position.setColumn(super.getPosition().getColumn());

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setRow(position.getRow() - 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Left
		position.setRow(super.getPosition().getRow());
		position.setColumn(super.getPosition().getColumn() - 1);

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setColumn(position.getColumn() - 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Right
		position.setRow(super.getPosition().getRow());
		position.setColumn(super.getPosition().getColumn() + 1);

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setColumn(position.getColumn() + 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		// Below
		position.setRow(super.getPosition().getRow() + 1);
		position.setColumn(super.getPosition().getColumn());

		while (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;

			position.setRow(position.getRow() + 1);
		}

		if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
			mat[position.getRow()][position.getColumn()] = true;
		}

		return mat;
	}

}
