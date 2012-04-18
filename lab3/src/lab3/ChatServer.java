package lab3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	public ChatServer(int port) {
		init(port);
	}

	private void init(int port) {
		try {
			Mailbox mailbox = new Mailbox();
			List<Participant> participants = new ArrayList<Participant>();
			Thread writeThread = new ServerWriteThread(mailbox, participants);
			writeThread.start();
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				Socket socket = ss.accept();
				Participant p = new Participant(socket);
				participants.add(p);
				Thread readThread = new ServerReadThread(mailbox, p);
				readThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int port = 30000;
		new ChatServer(port);
	}

}
