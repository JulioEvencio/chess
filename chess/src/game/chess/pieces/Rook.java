package game.chess.pieces;

import java.io.IOException;

import game.chess.Board;
import game.chess.Color;
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

}
