package gameNet;

import java.io.ObjectInputStream;
import java.net.Socket;

class GamePlayerProcess2 extends Thread {
	GameServer mom;
	Socket sock;
	ObjectInputStream in = null;
	int myIndex;

	GamePlayerProcess2(Socket s, GameServer m, int index) {
		this.sock = s;
		this.mom = m;
		this.myIndex = index;
	}

	@Override
	public void run() {
		// Read from socket and put the string read into all message queues for
		// all conversations.

		try {

			this.in = new ObjectInputStream(this.sock.getInputStream());
			Object inputObj;

			while ((inputObj = this.in.readObject()) != null) // Read from socket
			{
				this.mom.putInputMsgs(inputObj);
			} // end of while loop
		} catch (ClassNotFoundException e) {
			System.out.println("GamePlayerProcess2.run Class Not Found Err: " + e);
			e.printStackTrace(System.out);
		} catch (Exception e) {
			System.out.println("GamePlayerProcess2.run Err: " + e);
			e.printStackTrace();
		}
		try { // I'm annoyed that I need try ... catch to keep the compiler happy here

			this.in.close();
			this.sock.close();
			System.out.println("GamePlayerProcess2.run terminating: " + this.myIndex);

			this.mom.removeMe(this.myIndex); // just remove me
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}// end of run routine
}
