package game.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;

import game.chess.exceptions.ChessException;
import game.chess.pieces.King;
import game.chess.pieces.Piece;
import game.chess.pieces.Rook;
import game.main.Game;

public class ChessMatch {

	private boolean check;
	private game.chess.Color currentPlayer;

	private final Board board;

	private Position clickPosition;
	private Position sourcePosition;
	private Position targetPosition;

	public ChessMatch() throws IOException {
		this.check = false;
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

	public void mouseReleased(MouseEvent e) throws ChessException {
		int row = e.getY() / (Game.HEIGHT / this.board.getROW());
		int column = e.getX() / (Game.WIDTH / this.board.getCOLUMN());

		this.clickPosition = new Position(row, column);

		this.performChessMove();
	}

	private void performChessMove() throws ChessException {
		if (this.sourcePosition != null && this.validateTargetPosition()) {
			this.targetPosition = this.clickPosition;

			Piece pieceCaptured = this.makeMove();
			
			if (this.testCheck(this.currentPlayer)) {
				this.undoMove(pieceCaptured);
			} else {
				this.nextTurn();
			}
			
			this.check = this.testCheck(this.getOpponent(this.currentPlayer));
			this.sourcePosition = null;
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

		return pieceCaptured;
	}
	
	private void undoMove(Piece pieceCaptured) {
		Piece piece = this.board.removePiece(this.targetPosition);
		
		this.board.placePiece(piece, this.sourcePosition);
		
		if (pieceCaptured != null) {
			this.board.placePiece(pieceCaptured, this.targetPosition);
		}
	}
	
	private void nextTurn() {
		this.currentPlayer = this.getOpponent(this.currentPlayer);
	}
	
	private game.chess.Color getOpponent(game.chess.Color color) {
		return (this.currentPlayer == game.chess.Color.WHITE) ? game.chess.Color.BLACK : game.chess.Color.WHITE;
	}
	
	private Piece getKing(game.chess.Color color) throws ChessException {
		for (int i = 0; i < this.board.getROW(); i++) {
			for (int j = 0; j < this.board.getCOLUMN(); j++) {
				Piece piece = this.board.getPiece(i, j);
				
				if (piece instanceof King && piece.getColor() == this.currentPlayer) {
					return piece;
				}
			}
		}
		
		throw new ChessException("An unexpected error occurred...");
	}
	
	private boolean testCheck(game.chess.Color color) throws ChessException {
		Position kingPosition = this.getKing(color).getPosition();
		
		for (int i = 0; i < this.board.getROW(); i++) {
			for (int j = 0; j < this.board.getCOLUMN(); j++) {
				Piece piece = this.board.getPiece(i, j);
				
				if (piece != null && piece.getColor() == this.getOpponent(color)) {
					boolean[][] mat = piece.possibleMoves();
					
					if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
						return true;
					}
				}
			}
		}
		
		return false;
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
					if (this.check && piece instanceof King && piece.getColor() == this.currentPlayer) {
						this.renderRect(render, Color.RED, squareX, squareY, squareWidth, squareHeight);
					}
					
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
