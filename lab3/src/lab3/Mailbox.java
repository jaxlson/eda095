package lab3;

import java.util.Vector;

public class Mailbox {
	
	private static final int BUFFER_SIZE = 5;
	
	private Vector<String> messages;

	public Mailbox() {
		messages = new Vector<String>(BUFFER_SIZE);
	}

	public synchronized void putMessage(String s) {
		while (messages.size() == BUFFER_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		messages.add(s);
		notifyAll();
	}

	public synchronized String getMessage() {
		while (messages.isEmpty()) {			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String temp = messages.remove(0);
		notifyAll();
		return temp;
	}
}
