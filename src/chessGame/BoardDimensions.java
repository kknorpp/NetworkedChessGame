package chessGame;

import java.awt.Graphics;

public class BoardDimensions {

	private int xstart = -1, ystart = -1;
	double square_width = -1, square_height = -1;

	public void setParms(int xstart, int ystart, double square_width, double square_height) {
		this.xstart = xstart;
		this.ystart = ystart;
		this.square_width = square_width;
		this.square_height = square_height;

	}

	DPoint getDpoint(int x, int y) {
		double dx = (x - this.xstart) / this.square_width;
		double dy = (y - this.ystart) / this.square_height;
		if (dx < 0.0 || dx >= 8.0) {
			return null;
		} else {
			return new DPoint(dx, dy);
		}
	}

	public void boardDraw(Graphics g, PieceType pieceType, ColorType color, double x, double y) {
		ChessPiece chessPiece = ChessPiece.chessPieces[color.ordinal()][pieceType.ordinal()];
		int xPixel = this.xstart + (int) (this.square_width * x);
		int yPixel = this.ystart + (int) (this.square_height * y);
		chessPiece.draw(g, xPixel, yPixel, (int) (this.square_width), (int) this.square_height);

	}
}