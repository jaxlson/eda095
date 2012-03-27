package lab2;


public class PrintThread extends Thread {
	
	private String name;
	
	public PrintThread(String n) {
		name = n;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(name);
			try {
				sleep((int) (10 * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new PrintThread("Thread " + (i + 1));
			t.start();
		}
	}

}
