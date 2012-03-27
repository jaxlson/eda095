package lab3;

public class WriteMailboxThread extends Thread {

	private String name;
	private Mailbox mailbox;

	public WriteMailboxThread(String n, Mailbox m) {
		name = n;
		mailbox = m;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			mailbox.putMessage(name);
		}
	}

}
