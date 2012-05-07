package lab4;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;

public class URLQueue {
	
	private Deque<URL> queue;
	
	public URLQueue() {
		queue = new ArrayDeque<URL>();
	}
	
	public synchronized void push(URL url) {
		queue.push(url);
		notifyAll();
	}
	
	public synchronized URL pop() {
		while (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return queue.pop();
	}
	
	public synchronized boolean isEmpty() {
		return queue.isEmpty();
	}

}
