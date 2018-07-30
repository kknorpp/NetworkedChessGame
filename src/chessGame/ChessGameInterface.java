package chessGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import gameNet.GameNet_UserInterface;
import gameNet.GamePlayer;

public class ChessGameInterface extends JFrame implements GameNet_UserInterface {
	GamePlayer myGamePlayer;
	ChessGameInput chessGameInput;
	ChessGame chessgame = null;

	Image offScreenImage = null;

	int lastWidth = -1, lastHeight = -1;

	BoardDimensions boardDimensions = new BoardDimensions();

	ChessGameInterface() {
		super("The chessboard is the world, the chessPieces are the phenomena of the universe");
		setSize(600, 600);

		ChessPiece.readInImages();

		MouseClickMonitor ml = new MouseClickMonitor();
		addMouseListener(ml);
		addMouseMotionListener(ml);

		addWindowListener(new Termination());
		// setResizable(true);
	}

	@Override
	public void startUserInterface(GamePlayer player) {
		this.myGamePlayer = player;

		this.chessGameInput = new ChessGameInput();
		this.chessGameInput.setName(player.getPlayerName());
		this.myGamePlayer.sendMessage(this.chessGameInput);// default is "connecting cmd."

		setVisible(true);

	}

	@Override
	public void receivedMessage(Object ob) {

		ChessGameOutput chessGameOutput = (ChessGameOutput) ob;
		this.chessgame = chessGameOutput.chessGame;
		repaint();

	}

	@Override
	public void paint(Graphics screen) {
		Dimension d = getSize();
		if (d.width != this.lastWidth || d.height != this.lastHeight) {
			this.lastWidth = d.width;
			this.lastHeight = d.height;
			this.offScreenImage = createImage(this.lastWidth, this.lastHeight);

		}
		Graphics g = this.offScreenImage.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, d.width, d.height);

		Insets insets = getInsets();
		int top = insets.top;
		int left = insets.left;
		int square_width = (d.width - left - insets.right) / 8;
		int square_height = (d.height - top - insets.bottom) / 8;
		this.boardDimensions.setParms(left, top, square_width, square_height);

		// color in the squares...

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int x = left + j * square_width;
				int y = top + i * square_height;
				if ((i + j) % 2 == 1) {
					g.setColor(new Color(10, 100, 220));
					g.fillRect(x, y, square_width, square_height);

				} else {
					g.setColor(Color.gray);
					g.fillRect(x, y, square_width, square_height);
				}

			}
		} // end of outer loop
			// draw pieces in current location
		if (this.chessgame != null) {
			this.chessgame.drawInPosition(g, this.boardDimensions);
		} // to avoid flicker we copy the offscreeniage to the real screen
		screen.drawImage(this.offScreenImage, 0, 0, this);
	}

	// ANOTHER INNER CLASS TO HANDLE MOUSE EVENTS

	class MouseClickMonitor extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			DPoint dpoint = ChessGameInterface.this.boardDimensions.getDpoint(e.getX(), e.getY());
			if (dpoint != null) {
				ChessGameInterface.this.chessGameInput.setMouseCmd(ChessGameInput.MOUSE_PRESSED, dpoint);
				ChessGameInterface.this.myGamePlayer.sendMessage(ChessGameInterface.this.chessGameInput);

			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			DPoint dpoint = ChessGameInterface.this.boardDimensions.getDpoint(e.getX(), e.getY());
			if (dpoint != null) {
				ChessGameInterface.this.chessGameInput.setMouseCmd(ChessGameInput.MOUSE_RELEASED, dpoint);
				ChessGameInterface.this.myGamePlayer.sendMessage(ChessGameInterface.this.chessGameInput);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			DPoint dpoint = ChessGameInterface.this.boardDimensions.getDpoint(e.getX(), e.getY());
			if (dpoint != null) {

				ChessGameInterface.this.chessGameInput.setMouseCmd(ChessGameInput.MOUSE_DRAGGED, dpoint);
				ChessGameInterface.this.myGamePlayer.sendMessage(ChessGameInterface.this.chessGameInput);

			}
		}
	}
	// another INNER CLASS

	class Termination extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			ChessGameInterface.this.myGamePlayer.doneWithGame();
			System.exit(0);
		}
	}
}
