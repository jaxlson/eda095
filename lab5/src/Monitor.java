import java.net.URL;
import java.util.ArrayList;

public class Monitor {

	private ArrayList<URL> queue;
	private ArrayList<URL> visited;

	public Monitor() {
		queue = new ArrayList<URL>();
		visited = new ArrayList<URL>();
	}

	public synchronized ArrayList<URL>[] fetch() {
		ArrayList<URL>[] result = (ArrayList<URL>[]) new ArrayList[2];
		result[0] = (ArrayList<URL>) queue.clone();
		result[1] = (ArrayList<URL>) visited.clone();
		return result;
	}

	public synchronized void set(ArrayList<URL>[] input) {
		queue = input[0];
		visited = input[1];
	}

	public synchronized void addToQueue(URL url) {
		if (!visited.contains(url)) {
			queue.add(url);
			visited.add(url);
		}
		notifyAll();
	}

	public synchronized URL popFromQueue() {
		try {
			while (queue.isEmpty())
				wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return queue.remove(0);
	}

}
