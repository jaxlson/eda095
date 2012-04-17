package lab3;

import java.util.List;

public class WriteThread extends Thread {

	private Mailbox mailbox;
	private Participant participant;
	private List<Participant> participants;

	public WriteThread(Mailbox m, Participant p, List<Participant> ps) {
		mailbox = m;
		participant = p;
	}

	@Override
	public void run() {
		String message = mailbox.getMessage();
		System.out.println(message);

		while (socket.isConnected()) {
			String s = new String();
			int b;
			do {
				b = in.read();
				s += (char) b;
			} while (b != '\n');
			System.out.println(s);
			out.write(s.getBytes());
		}
		socket.close();
	}

}
