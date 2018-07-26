package chessGame;

import java.awt.Graphics;
import java.io.Serializable;

import gameNet.GameNet_CoreGame;

public class ChessGame extends GameNet_CoreGame implements Serializable {
	private Piece[] pieces = new Piece[32];

	private int selectedIndex = -1;

	private DPoint pressed_point = new DPoint();
	private DPoint dragged_point = new DPoint();

	public ChessGame() {

		int index = 0;

		for (int i = 0; i < 8; i++) {
			this.pieces[index++] = new Piece(PieceType.Pawn, ColorType.Black, i, 1);
		}
		for (int i = 0; i < 8; i++) {
			this.pieces[index++] = new Piece(PieceType.Pawn, ColorType.Black, i, 6);
		}

		PieceType[] startingKingRow = { PieceType.Rook, PieceType.Knight, PieceType.Bishop, PieceType.Queen,
				PieceType.King, PieceType.Bishop, PieceType.Knight, PieceType.Rook };
		for (int i = 0; i < 8; i++) {
			this.pieces[index++] = new Piece(startingKingRow[i], ColorType.Black, i, 0);
		}
		for (int i = 0; i < 8; i++) // white king in row 7
		{
			this.pieces[index++] = new Piece(startingKingRow[i], ColorType.White, i, 7);
		}

	}

	// draws all pieces at current location
	public void drawInPosition(Graphics g, BoardDimensions boardDimensions) {
		for (int i = 0; i < this.pieces.length; i++) {
			if (i != this.selectedIndex) {
				this.pieces[i].drawInPosition(g, boardDimensions);
			} else {
				// the seleceted piece is still being dragged around
				DPoint delta = this.pressed_point.deltaPoint(this.dragged_point);
				this.pieces[i].dragDraw(g, boardDimensions, delta.x, delta.y);

			}
		}
	}

	void mousePressed(DPoint dpoint) {
		this.pressed_point = dpoint;
		this.dragged_point = dpoint;

		int xSelectLoc = (int) dpoint.x;
		int ySelectLoc = (int) dpoint.y;

		for (int i = 0; i < this.pieces.length; i++) {
			if (this.pieces[i].areYouHere(xSelectLoc, ySelectLoc)) {
				;
			}
			{
				this.selectedIndex = i;
				break;
			}
		}
	}

	void mouseDragged(DPoint dpoint) {
		this.dragged_point = dpoint;

	}

	void mouseReleased(DPoint dpoint) {
		this.pressed_point = dpoint;
		if (this.selectedIndex >= 0) {
			int xSelectLoc = (int) dpoint.x;
			int ySelectLoc = (int) dpoint.y;
			if (xSelectLoc >= 0 && ySelectLoc >= 0) {
				this.pieces[this.selectedIndex].moveLoc(xSelectLoc, ySelectLoc);
			}
		}
	}

	@Override
	public Object process(Object ob) {
		ChessGameInput chessGameInput = (ChessGameInput) ob;
		switch (chessGameInput.cmd) {
		case ChessGameInput.MOUSE_PRESSED:
			mousePressed(chessGameInput.dpoint);
			break;
		case ChessGameInput.MOUSE_RELEASED:
			mouseReleased(chessGameInput.dpoint);
			break;
		case ChessGameInput.MOUSE_DRAGGED:
			mouseDragged(chessGameInput.dpoint);
			break;
		case ChessGameInput.CONNECTING:
			break;

		}
		return new ChessGameOutput(this);

	}
}
