package chessGame;

import java.io.Serializable;

public class ChessGameOutput implements Serializable {

	private static final long serialVersionUID = 2758470914337643699L;

	public ChessGame chessGame = null;

	public ChessGameOutput(ChessGame cg) {
		this.chessGame = cg;

	}
}
