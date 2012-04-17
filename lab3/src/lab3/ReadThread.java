package lab3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class ReadThread extends Thread {

	private Mailbox mailbox;
	private Participant participant;
	private List<Participant> participants;

	public ReadThread(Mailbox m, Participant p, List<Participant> ps) {
		mailbox = m;
		participant = p;
	}

	@Override
	public void run() {
		String name = participant.getName(); 
		Socket socket = participant.getSocket();
		BufferedReader in = new BufferedReader(new InputStreamReader(socket
				.getInputStream()));
		while (socket.isConnected()) {
			String line = in.readLine();
			if (line.length() < 3) {
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
				mailbox.putMessage(participant, msg);
				break;
			case 'E':
				mailbox.putMessage(participant, msg);
				break;
			case 'Q':
				participants.remove(participant);
				socket.close();
				break;
			default:
				break;
			}
		}
		socket.close();
	}
}
