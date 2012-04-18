package lab3;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

	public ChatClient(String machine, int port) {
		try {
			Socket s = new Socket(machine, port);
			Thread readThread = new ClientReadThread(s);
			readThread.start();
			Thread writeThread = new ClientWriteThread(s);
			writeThread.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java ChatClient <machine> <port>");
		}
		String machine = args[0];
		int port = Integer.parseInt(args[1]);
		new ChatClient(machine, port);
	}

}
