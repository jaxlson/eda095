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
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				Socket socket = ss.accept();
				Participant p = new Participant(socket);
				participants.add(p);
				Thread writeThread = new WriteThread(mailbox, p, participants);
				Thread readThread = new ReadThread(mailbox, p, participants);
				writeThread.start();
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
