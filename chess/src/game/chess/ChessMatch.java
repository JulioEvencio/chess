package game.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import game.chess.exceptions.ChessException;
import game.chess.pieces.Bishop;
import game.chess.pieces.King;
import game.chess.pieces.Knight;
import game.chess.pieces.Pawn;
import game.chess.pieces.Piece;
import game.chess.pieces.Queen;
import game.chess.pieces.Rook;
import game.main.Game;

public class ChessMatch {

	private boolean check;
	private boolean checkmate;
	private game.chess.Color currentPlayer;

	private final Board board;

	private Position clickPosition;
	private Position sourcePosition;
	private Position targetPosition;

	public ChessMatch() throws IOException {
		this.check = false;
		this.checkmate = false;
		this.currentPlayer = game.chess.Color.WHITE;

		this.board = new Board();

		this.clickPosition = new Position(5, 5);
		this.sourcePosition = null;
		this.targetPosition = null;

		this.board.placePiece(new Rook(game.chess.Color.BLACK, this.board), new Position(0, 0));
		this.board.placePiece(new Knight(game.chess.Color.BLACK, this.board), new Position(0, 1));
		this.board.placePiece(new Bishop(game.chess.Color.BLACK, this.board), new Position(0, 2));
		this.board.placePiece(new Queen(game.chess.Color.BLACK, this.board), new Position(0, 3));
		this.board.placePiece(new King(game.chess.Color.BLACK, this.board), new Position(0, 4));
		this.board.placePiece(new Bishop(game.chess.Color.BLACK, this.board), new Position(0, 5));
		this.board.placePiece(new Knight(game.chess.Color.BLACK, this.board), new Position(0, 6));
		this.board.placePiece(new Rook(game.chess.Color.BLACK, this.board), new Position(0, 7));
		
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 0));
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 1));
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 2));
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 3));
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 4));
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 5));
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 6));
		this.board.placePiece(new Pawn(game.chess.Color.BLACK, this.board), new Position(1, 7));

		this.board.placePiece(new Rook(game.chess.Color.WHITE, this.board), new Position(7, 0));
		this.board.placePiece(new Knight(game.chess.Color.WHITE, this.board), new Position(7, 1));
		this.board.placePiece(new Bishop(game.chess.Color.WHITE, this.board), new Position(7, 2));
		this.board.placePiece(new Queen(game.chess.Color.WHITE, this.board), new Position(7, 3));
		this.board.placePiece(new King(game.chess.Color.WHITE, this.board), new Position(7, 4));
		this.board.placePiece(new Bishop(game.chess.Color.WHITE, this.board), new Position(7, 5));
		this.board.placePiece(new Knight(game.chess.Color.WHITE, this.board), new Position(7, 6));
		this.board.placePiece(new Rook(game.chess.Color.WHITE, this.board), new Position(7, 7));
		
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 0));
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 1));
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 2));
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 3));
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 4));
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 5));
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 6));
		this.board.placePiece(new Pawn(game.chess.Color.WHITE, this.board), new Position(6, 7));
	}

	public boolean isCheckmate() {
		return this.checkmate;
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

			Piece pieceCaptured = this.makeMove(this.sourcePosition, this.targetPosition);

			if (this.testCheck(this.currentPlayer)) {
				this.undoMove(this.sourcePosition, this.targetPosition, pieceCaptured);
			} else {
				this.nextTurn();
			}

			if (this.testCheckMate(this.getOpponent(this.currentPlayer))) {
				String message;

				this.checkmate = true;

				if (this.currentPlayer == game.chess.Color.WHITE) {
					message = "Black won";
				} else {
					message = "White won";
				}

				Thread thread = new Thread(() -> {
					JOptionPane.showMessageDialog(null, message, "Checkmate", JOptionPane.INFORMATION_MESSAGE);
				});

				thread.start();
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

	private Piece makeMove(Position source, Position target) {
		Piece piece = this.board.removePiece(source);
		Piece pieceCaptured = this.board.removePiece(target);

		this.board.placePiece(piece, target);
		
		piece.increaseMoveCount();

		return pieceCaptured;
	}

	private void undoMove(Position source, Position target, Piece pieceCaptured) {
		Piece piece = this.board.removePiece(target);
		
		this.board.placePiece(piece, source);
		
		piece.decreaseMoveCount();

		if (pieceCaptured != null) {
			this.board.placePiece(pieceCaptured, target);
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

	private boolean testCheckMate(game.chess.Color color) throws ChessException {
		if (!this.testCheck(color)) {
			return false;
		}

		for (int i = 0; i < this.board.getROW(); i++) {
			for (int j = 0; j < this.board.getCOLUMN(); j++) {
				Piece piece = this.board.getPiece(i, j);

				if (piece != null && piece.getColor() == this.currentPlayer) {
					boolean[][] mat = piece.possibleMoves();

					for (int x = 0; x < mat.length; x++) {
						for (int y = 0; y < mat.length; y++) {
							if (mat[x][y]) {
								Position source = piece.getPosition();
								Position target = new Position(x, y);
								Piece pieceCaptured = this.makeMove(source, target);

								boolean testCheck = this.testCheck(color);

								this.undoMove(source, target, pieceCaptured);

								if (!testCheck) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true;
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
