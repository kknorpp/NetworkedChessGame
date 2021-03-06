package chessGame;

public class ChessPieceMove {

	public static boolean validMove(ChessGame chessGame, DPoint from, DPoint to) {
		// Find the moved piece
		// switch on piece type
		Piece movedPiece = chessGame.getPiece(from);
		if (movedPiece == null) {
			return false;
		}
		switch (movedPiece.pieceType) {
		case Bishop:
			if (bishopMove(chessGame, from, to) != true) {
				return false;
			}

			break;
		case King:
			if (kingMove(chessGame, from, to) != true) {
				return false;
			}
			break;
		case Knight:

			if (knightMove(chessGame, from, to) != true) {
				return false;
			}
			break;
		case Pawn:
			if (pawnMove(chessGame, from, to) != true) {
				return false;
			}
			break;
		case Queen:
			if (queenMove(chessGame, from, to) != true) {
				return false;
			}
			break;
		case Rook:
			if (rookMove(chessGame, from, to) != true) {
				return false;
			}
			break;
		default:
			break;

		}

		// if open move to space
		Piece destinationPiece = chessGame.getPiece(to);
		if (destinationPiece == null) {
			return true;
		}
		// if moved piece is black and destination has a white piece. if a valid move
		// remove the white piece

		// if moved piece is white and destination is black. if a valid move remove the
		// black piece

		// if moved piece is the same color as a piece in an occupied space move is
		// invalid

		if (movedPiece.color == destinationPiece.color) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean queenMove(ChessGame chessGame, DPoint from, DPoint to) {
		int x = (int) from.x;
		int y = (int) from.y;
		int x2 = (int) to.x;
		int y2 = (int) to.y;

		return rookMove(chessGame, from, to) || bishopMove(chessGame, from, to);
		// for (int i = 1; i < 9; i++) {
		// if ((x + i == x2 && y + i == y2) || (x + i == x2 && y == y2)) {
		// return true;
		// }
		// if ((x - i == x2 && y - i == y2) || (x - i == x2 && y == y2)) {
		// return true;
		// }
		// if ((x + i == x2 && y - i == y2) || (y + i == y2 && x == x2)) {
		// return true;
		// }
		// if ((x - i == x2 && y + i == y2) || (y - i == y2 && x == x2)) {
		// return true;
		// }
		// }
		// return false;
	}

	public static boolean kingMove(ChessGame chessGame, DPoint from, DPoint to) {
		int x = (int) from.x;
		int y = (int) from.y;
		int x2 = (int) to.x;
		int y2 = (int) to.y;
		if (x + 1 == x2 && y + 1 == y2) {
			return true;
		}
		if (x - 1 == x2 && y + 1 == y2) {
			return true;
		}
		if (x + 1 == x2 && y - 1 == y2) {
			return true;
		}
		if (x - 1 == x2 && y - 1 == y2) {
			return true;
		}
		if (x + 1 == x2 && y == y2) {
			return true;
		}
		if (x - 1 == x2 && y == y2) {
			return true;
		}
		if (x == x2 && y + 1 == y2) {
			return true;
		}
		if (x == x2 && y - 1 == y2) {
			return true;
		}
		return false;
	}

	public static boolean bishopMove(ChessGame chessGame, DPoint from, DPoint to) {
		int x = (int) from.x;
		int y = (int) from.y;
		int x2 = (int) to.x;
		int y2 = (int) to.y;

		for (int i = 1; i < 9; i++) {
			if (x + i == x2 && y + i == y2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x + j, y + j)) != null) {
						return false;
					}
				}
				return true;

			}
			if (x - i == x2 && y - i == y2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x - j, y - j)) != null) {
						return false;
					}
				}
				return true;
			}
			if (x + i == x2 && y - i == y2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x + j, y - j)) != null) {
						return false;
					}
				}
				return true;
			}
			if (x - i == x2 && y + i == y2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x - j, y + j)) != null) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean rookMove(ChessGame chessGame, DPoint from, DPoint to) {
		int x = (int) from.x;
		int y = (int) from.y;
		int x2 = (int) to.x;
		int y2 = (int) to.y;
		for (int i = 1; i < 9; i++) {
			if (x + i == x2 && y == y2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x + j, y)) != null) {
						return false;
					}
				}
				return true;

			}
			if (x - i == x2 && y == y2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x - j, y)) != null) {
						return false;
					}
				}
				return true;
			}

			if (y + i == y2 && x == x2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x, y + j)) != null) {
						return false;
					}
				}
				return true;

			}
			if (y - i == y2 && x == x2) {
				for (int j = 1; j < i; j++) {
					if (chessGame.getPiece(new DPoint(x, y - j)) != null) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public static boolean knightMove(ChessGame chessGame, DPoint from, DPoint to) {
		int x = (int) from.x;
		int y = (int) from.y;
		int x2 = (int) to.x;
		int y2 = (int) to.y;

		if (x + 2 == x2 && y + 1 == y2) {
			return true;
		}
		if (x - 2 == x2 && y - 1 == y2) {
			return true;
		}
		if (x - 2 == x2 && y + 1 == y2) {
			return true;
		}
		if (x + 2 == x2 && y - 1 == y2) {
			return true;
		}
		if (x - 1 == x2 && y - 2 == y2) {
			return true;
		}
		if (x + 1 == x2 && y - 2 == y2) {
			return true;
		}
		if (x - 1 == x2 && y + 2 == y2) {
			return true;
		}
		if (x + 1 == x2 && y + 2 == y2) {
			return true;
		}
		return false;
	}

	public static boolean pawnMove(ChessGame chessGame, DPoint from, DPoint to) {
		int x = (int) from.x;
		int y = (int) from.y;
		int x2 = (int) to.x;
		int y2 = (int) to.y;

		if ((ColorType.Black == chessGame.getPiece(from).color)) {

			if (y + 1 == y2 && x == x2) {
				return true;
			}
			if (y + 2 == y2 && x == x2 && y2 <= 3) {
				return true;
			}
			if (chessGame.isPieceOpen(to) != true) {
				Piece destinationPiece = chessGame.getPiece(to);
				if (destinationPiece.color == ColorType.Black) {
					return false;

				}
				if (x + 1 == x2 && y + 1 == y2) {
					return true;
				}
				if (x - 1 == x2 && y + 1 == y2) {
					return true;
				}
			}
		} else {
			if (y - 1 == y2 && x == x2) {
				return true;
			}
			if (y - 2 == y2 && x == x2 && y2 >= 4) {
				return true;
			}
			if (chessGame.isPieceOpen(to) != true) {
				Piece destinationPiece = chessGame.getPiece(to);
				if (destinationPiece.color == ColorType.White) {
					return false;
				}
				if (x + 1 == x2 && y - 1 == y2) {
					return true;
				}
				if (x - 1 == x2 && y - 1 == y2) {
					return true;
				}
			}

		}
		return false;
	}
}
