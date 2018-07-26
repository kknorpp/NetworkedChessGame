package chessGame;

import java.io.Serializable;

public class ChessGameInput implements Serializable {

	static final int CONNECTING = 0;
	static final int MOUSE_PRESSED = 1;
	static final int MOUSE_RELEASED = 2;
	static final int MOUSE_DRAGGED = 3;

	String sendersName;
	int cmd = CONNECTING;
	DPoint dpoint = new DPoint(-1, -1);

	public void setName(String n) {

		this.sendersName = n;
	}

	public void setMouseCmd(int cmd, DPoint dp) {

		this.cmd = cmd;
		this.dpoint = dp;
	}

}
