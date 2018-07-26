package chessGame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

class ChessPiece {

	private static PieceType[] pieceType = PieceType.values();
	private static ColorType[] colorType = ColorType.values();

	public static ChessPiece[][] chessPieces = new ChessPiece[colorType.length][pieceType.length];

	public static void readInImages() {
		for (int i = 0; i < colorType.length; i++) {
			for (int j = 0; j < pieceType.length; j++) {
				chessPieces[i][j] = new ChessPiece(i, j);
			}
		}
	}

	public void draw(Graphics g, int x, int y, int wSpace, int hSpace) {
		double scale_width = (double) wSpace / this.width;
		double scale_height = (double) hSpace / this.height;

		double scale = Math.min(scale_width, scale_height);
		int newHeight = (int) (this.height * scale);
		int newWidth = (int) (this.width * scale);
		x = x + wSpace / 2 - newWidth / 2;
		y = y + hSpace / 2 - newHeight / 2;
		g.drawImage(this.pieceImg, x, y, newWidth, newHeight, null);
	}

	private Image pieceImg;
	private int width, height;

	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();

	}

	private ChessPiece(int colorIndex, int pieceIndex) {
		String imageName = colorType[colorIndex].toString() + pieceType[pieceIndex].toString() + ".gif";

		this.pieceImg = loadImage(imageName);
		this.width = this.pieceImg.getWidth(null);
		this.height = this.pieceImg.getHeight(null);
	}
}
// public void drawInPosition(Graphics g, BoardDimensions boardDimensions) {
// // TODO Auto-generated method stub
//
// }
// }

// class BoardDimensions {
// int left, top, cellWidth, cellHeight;
//
// BoardDimensions(int left, int top, int cellWidth, int cellHeight) {
// this.left = left;
// this.top = top;
// this.cellWidth = cellWidth;
// this.cellHeight = cellHeight;
// }
// }

// class Piece {
// int xSquare, ySquare;
// ColorType color;
// PieceType pieceType;
//
// public Piece(ColorType color, PieceType p, int xSquare, int ySquare) {
// this.pieceType = p;
// this.color = color;
// this.xSquare = xSquare;
// this.ySquare = ySquare;
//
// }
//
// public void drawInPosition(Graphics g, BoardDimensions b) {
// ChessPiece chessPiece =
// ChessPiece.chessPieces[this.color.ordinal()][this.pieceType.ordinal()];
// int x = b.left + this.xSquare * b.cellWidth;
//
// int y = b.top + this.ySquare * b.cellHeight;
// chessPiece.draw(g, x, y, b.cellWidth, b.cellHeight);
// }
//
// }