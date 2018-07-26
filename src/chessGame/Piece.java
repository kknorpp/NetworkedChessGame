package chessGame;

import java.awt.Graphics;
import java.io.Serializable;

enum ColorType {
	Black, White
};

enum PieceType {
	Pawn, Rook, Knight, Bishop, Queen, King
};

class Piece implements Serializable {
	int xSquare, ySquare;
	PieceType pieceType;
	ColorType color;

	Piece(PieceType p, ColorType c, int xSquare, int ySquare) {

		this.pieceType = p;
		this.color = c;
		this.xSquare = xSquare;
		this.ySquare = ySquare;
	}

	void moveLoc(int x, int y) {
		this.xSquare = x;
		this.ySquare = y;
	}

	boolean areYouHere(int xSelectLoc, int ySelectLoc) {
		if (xSelectLoc == this.xSquare && ySelectLoc == this.ySquare) {
			return true;
		} else {
			return false;
		}

	}

	void drawInPosition(Graphics g, BoardDimensions boardDimensions) {
		boardDimensions.boardDraw(g, this.pieceType, this.color, this.xSquare, this.ySquare);

	}

	// follwing will be used to while dragging piece around
	void dragDraw(Graphics g, BoardDimensions boardDimensions, double xDelta, double yDelta) {
		boardDimensions.boardDraw(g, this.pieceType, this.color, this.xSquare + xDelta, this.ySquare + yDelta);
	}
}
