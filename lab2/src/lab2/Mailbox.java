package lab2;

import java.util.Vector;

public class Mailbox {
	
	private int waitCount;
	private Vector<String> messages;

	public Mailbox() {
		waitCount = 0;
		messages = new Vector<String>(5);
	}

	public synchronized void putMessage(String s) {
		while (messages.size() == 5) {
			try {
				waitCount++;
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		messages.add(s);
		System.out.println(waitCount);
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
