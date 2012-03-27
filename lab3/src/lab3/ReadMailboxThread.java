package lab3;

public class ReadMailboxThread extends Thread {

	private Mailbox mailbox;

	public ReadMailboxThread(Mailbox m) {
		mailbox = m;
	}

	@Override
	public void run() {
		while (true) {
			String message = mailbox.getMessage();
			System.out.println(message);
		}
	}

	public static void main(String[] args) {
		Mailbox m = new Mailbox();
		Thread t;
		for (int i = 0; i < 500; i++) {
			t = new WriteMailboxThread("Thread " + (i + 1), m);
			t.setPriority(NORM_PRIORITY);
			t.start();
		}
		t = new ReadMailboxThread(m);
		t.setPriority(MAX_PRIORITY);
		t.start();
	}

}
