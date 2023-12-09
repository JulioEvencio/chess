package game.chess.pieces;

import java.io.IOException;

import game.chess.Board;
import game.chess.Color;
import game.chess.Position;
import game.resources.Spritesheet;

public class Pawn extends Piece {

	public Pawn(Color color, Board board) throws IOException {
		super(color, board);

		Spritesheet spritesheet = new Spritesheet("/sprites/chess.png");

		if (super.getColor() == Color.BLACK) {
			super.sprite = spritesheet.getSprite(0, 0, 128, 128);
		} else {
			super.sprite = spritesheet.getSprite(0, 128, 128, 128);
		}
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[super.getBoard().getROW()][super.getBoard().getCOLUMN()];

		Position position = new Position(0, 0);

		if (super.getColor() == Color.WHITE) {
			position.setRow(super.getPosition().getRow() - 1);
			position.setColumn(super.getPosition().getColumn());

			if (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
				mat[position.getRow()][position.getColumn()] = true;
			}
			
			position.setRow(super.getPosition().getRow() - 2);
			position.setColumn(super.getPosition().getColumn());

			Position positionAux = new Position(super.getPosition().getRow() - 2, super.getPosition().getColumn());

			if (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position) && super.getBoard().positionExists(positionAux) && !super.getBoard().thereIsAPiece(positionAux) && super.getMoveCount() == 0) {
				mat[position.getRow()][position.getColumn()] = true;
			}

			position.setRow(super.getPosition().getRow() - 1);
			position.setColumn(super.getPosition().getColumn() - 1);
			
			if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				mat[position.getRow()][position.getColumn()] = true;
			}
			
			position.setRow(super.getPosition().getRow() - 1);
			position.setColumn(super.getPosition().getColumn() + 1);
			
			if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				mat[position.getRow()][position.getColumn()] = true;
			}
		} else {
			position.setRow(super.getPosition().getRow() + 1);
			position.setColumn(super.getPosition().getColumn());

			if (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position)) {
				mat[position.getRow()][position.getColumn()] = true;
			}
			
			position.setRow(super.getPosition().getRow() + 2);
			position.setColumn(super.getPosition().getColumn());

			Position positionAux = new Position(super.getPosition().getRow() + 2, super.getPosition().getColumn());

			if (super.getBoard().positionExists(position) && !super.getBoard().thereIsAPiece(position) && super.getBoard().positionExists(positionAux) && !super.getBoard().thereIsAPiece(positionAux) && super.getMoveCount() == 0) {
				mat[position.getRow()][position.getColumn()] = true;
			}

			position.setRow(super.getPosition().getRow() + 1);
			position.setColumn(super.getPosition().getColumn() - 1);
			
			if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				mat[position.getRow()][position.getColumn()] = true;
			}
			
			position.setRow(super.getPosition().getRow() + 1);
			position.setColumn(super.getPosition().getColumn() + 1);
			
			if (super.getBoard().positionExists(position) && this.isThereOpponentPiece(position)) {
				mat[position.getRow()][position.getColumn()] = true;
			}
		}

		return mat;
	}

}
