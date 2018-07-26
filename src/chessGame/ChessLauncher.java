package chessGame;

import java.io.IOException;

import gameNet.GameCreator;
import gameNet.GameNet_CoreGame;
import gameNet.GameNet_UserInterface;

public class ChessLauncher extends GameCreator {

	@Override
	public GameNet_CoreGame createGame() {
		return new ChessGame();
	}

	public static void main(String[] args) throws IOException {
		ChessLauncher myMain = new ChessLauncher();
		GameNet_UserInterface myUserInterface = new ChessGameInterface();

		myMain.enterGame(myUserInterface);
	}// end of main
}// end of class
