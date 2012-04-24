package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ServerReadThread extends Thread {

	private Mailbox mailbox;
	private Participant participant;

	public ServerReadThread(Mailbox m, Participant p) {
		mailbox = m;
		participant = p;
	}

	@Override
	public void run() {
		Socket socket = participant.getSocket();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			while (socket.isConnected()) {
				String line = in.readLine();
				if (line == null || line.length() < 3) {
					// Error. Ignore.
					continue;
				}
				if (line.charAt(1) != ':') {
					// Error. Ignore.
					continue;
				}
				char cmd = line.charAt(0);
				String msg = line.substring(2);
				switch (cmd) {
				case 'M':
					mailbox.putCommand(new MessageCommand(participant, msg));
					break;
				case 'E':
					mailbox.putCommand(new EchoCommand(participant, msg));
					break;
				case 'Q':
					mailbox.putCommand(new QuitCommand(participant));
					break;
				default:
					break;
				}
			}
			socket.close();
		} catch (SocketException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
