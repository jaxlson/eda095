package lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) throws IOException {
		int port = 30000;
		ServerSocket ss = new ServerSocket(port);
		while (true) {
			Socket socket = ss.accept();
			Thread thread = new ServerThread(socket);
			thread.start();
		}
	}

}
