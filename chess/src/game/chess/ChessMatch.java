package game.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;

import game.chess.pieces.King;
import game.chess.pieces.Piece;
import game.chess.pieces.Rook;
import game.main.Game;

public class ChessMatch {

	private game.chess.Color currentPlayer;

	private final Board board;

	private Position clickPosition;
	private Position sourcePosition;
	private Position targetPosition;

	public ChessMatch() throws IOException {
		this.currentPlayer = game.chess.Color.WHITE;
		
		this.board = new Board();

		this.clickPosition = new Position(5, 5);
		this.sourcePosition = null;
		this.targetPosition = null;

		this.board.placePiece(new Rook(game.chess.Color.BLACK, this.board), new Position(0, 0));
		this.board.placePiece(new King(game.chess.Color.BLACK, this.board), new Position(0, 4));
		this.board.placePiece(new Rook(game.chess.Color.BLACK, this.board), new Position(0, 7));

		this.board.placePiece(new Rook(game.chess.Color.WHITE, this.board), new Position(7, 7));
		this.board.placePiece(new King(game.chess.Color.WHITE, this.board), new Position(7, 4));
		this.board.placePiece(new Rook(game.chess.Color.WHITE, this.board), new Position(7, 0));
	}

	public void mouseReleased(MouseEvent e) {
		int row = e.getY() / (Game.HEIGHT / this.board.getROW());
		int column = e.getX() / (Game.WIDTH / this.board.getCOLUMN());

		this.clickPosition = new Position(row, column);

		this.performChessMove();
	}

	private void performChessMove() {
		if (this.sourcePosition != null && this.validateTargetPosition()) {
			this.targetPosition = this.clickPosition;

			this.makeMove();
			this.nextTurn();
		} else if (this.validateSourcePosition()) {
			this.sourcePosition = this.clickPosition;
		}
	}

	private boolean validateTargetPosition() {
		if (this.board.getPiece(this.sourcePosition).possibleMove(this.clickPosition)) {
			return true;
		} else {
			this.sourcePosition = null;
			return false;
		}
	}
	
	private boolean validateSourcePosition() {
		Piece piece = this.board.getPiece(this.clickPosition);
		
		return piece != null && piece.isThereAnyPossibleMove() && piece.getColor() == this.currentPlayer;
	}

	private Piece makeMove() {
		Piece piece = this.board.removePiece(this.sourcePosition);
		Piece pieceCaptured = this.board.removePiece(this.targetPosition);

		this.board.placePiece(piece, this.targetPosition);

		this.sourcePosition = null;

		return pieceCaptured;
	}
	
	private void nextTurn() {
		this.currentPlayer = (this.currentPlayer == game.chess.Color.WHITE) ? game.chess.Color.BLACK : game.chess.Color.WHITE;
	}

	public void render(Graphics render) {
		int squareWidth = Game.WIDTH / this.board.getROW();
		int squareHeight = Game.HEIGHT / this.board.getCOLUMN();

		for (int column = 0; column < this.board.getROW(); column++) {
			for (int row = 0; row < this.board.getCOLUMN(); row++) {
				int squareX = column * squareWidth;
				int squareY = row * squareHeight;

				if ((column + row) % 2 == 0) {
					this.renderRect(render, Color.LIGHT_GRAY, squareX, squareY, squareWidth, squareHeight);
				} else {
					this.renderRect(render, Color.GRAY, squareX, squareY, squareWidth, squareHeight);
				}

				if (this.board.getPiece(this.clickPosition) != null && this.board.getPiece(this.clickPosition).getColor() == this.currentPlayer && this.clickPosition.getRow() == row && this.clickPosition.getColumn() == column) {
					this.renderRect(render, Color.CYAN, squareX, squareY, squareWidth, squareHeight);
				}

				if (this.targetPosition != null && this.targetPosition.getRow() == row && this.targetPosition.getColumn() == column) {
					this.renderRect(render, Color.YELLOW, squareX, squareY, squareWidth, squareHeight);
				}
				
				if (this.sourcePosition != null) {
					Piece pieceSelected = this.board.getPiece(this.sourcePosition);

					if (pieceSelected != null && pieceSelected.possibleMoves()[row][column]) {
						this.renderRect(render, Color.GREEN, squareX, squareY, squareWidth, squareHeight);
					}
				}

				Piece piece = this.board.getPiece(row, column);

				if (piece != null) {
					piece.render(render, squareX, squareY, squareWidth, squareHeight);
				}
			}
		}
	}

	private void renderRect(Graphics render, Color color, int x, int y, int width, int height) {
		render.setColor(color);
		render.fillRect(x, y, width, height);

		render.setColor(Color.BLACK);
		render.drawRect(x, y, width, height);
	}

}
