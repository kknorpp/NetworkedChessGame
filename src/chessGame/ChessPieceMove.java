package chessGame;

public class ChessPieceMove {

	public static boolean validMove(ChessGame chessGame, DPoint from, DPoint to) {
		// if open move to space
		chessGame.getPiece(from);
		Piece movedPiece = chessGame.getPiece(from);
		Piece destinationPiece = chessGame.getPiece(to);

		boolean isValid = destinationPiece == null;
		if (destinationPiece == null) {
			return true;
		}
		/// if moved piece is black and destination has a white piece. if a valid move
		/// remove the white piece
		// if moved piece is white and destination is black. if a valid move remove the
		/// black piece
		// if moved piece is the same color as a piece in an occupied space move is
		/// invalid

		if (movedPiece.color == destinationPiece.color) {
			return false;

		} else {
			return true;
		}

	}

}
