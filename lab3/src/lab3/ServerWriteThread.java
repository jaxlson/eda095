package lab3;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerWriteThread extends Thread {

	private Mailbox mailbox;
	private List<Participant> participants;

	public ServerWriteThread(Mailbox m, List<Participant> ps) {
		mailbox = m;
		participants = ps;
	}

	@Override
	public void run() {
		while (true) {
			Command cmd = mailbox.getCommand();
			if (cmd instanceof MessageCommand) {
				MessageCommand c = (MessageCommand) cmd;
				for (Participant p : participants) {
					try {
						PrintWriter out = new PrintWriter(
								new BufferedOutputStream(p.getSocket()
										.getOutputStream()), true);
						out.println(cmd.getSender().getName() + ": "
								+ c.getMessage());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (cmd instanceof EchoCommand) {
				EchoCommand c = (EchoCommand) cmd;
				try {
					PrintWriter out = new PrintWriter(new BufferedOutputStream(
							cmd.getSender().getSocket().getOutputStream()),
							true);
					out.println(cmd.getSender().getName() + ": "
							+ c.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (cmd instanceof QuitCommand) {
				Participant p = cmd.getSender();
				Socket socket = p.getSocket();
				participants.remove(p);
				try {
					PrintWriter out = new PrintWriter(new BufferedOutputStream(
							socket.getOutputStream()), true);
					out.println("Goodbye!");
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
