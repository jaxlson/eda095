package lab3;

import java.util.Vector;

public class Mailbox {

	private static final int BUFFER_SIZE = 5;

	private Vector<Command> commands;

	public Mailbox() {
		commands = new Vector<Command>(BUFFER_SIZE);
	}

	public synchronized void putCommand(Command c) {
		while (commands.size() == BUFFER_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		commands.add(c);
		notifyAll();
	}

	public synchronized Command getCommand() {
		while (commands.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Command temp = commands.remove(0);
		notifyAll();
		return temp;
	}
}
