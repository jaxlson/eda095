package lab4;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	private static final int NUMBER_OF_THREADS = 10;

	public Main() {
		URLQueue queue = new URLQueue();
		Set<String> visited = Collections.synchronizedSet(new HashSet<String>());
		Set<String> urls = Collections.synchronizedSet(new HashSet<String>());
		Set<String> addresses = Collections.synchronizedSet(new HashSet<String>());
		List<Thread> threads = new ArrayList<Thread>();
		try {
			URL start = new URL("http://cs.lth.se/eda095/");
			queue.push(start);
			for (int i = 0; i < NUMBER_OF_THREADS; i++) {
				Thread t = new Processor(queue, visited, urls, addresses);
				threads.add(t);
				t.start();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("List of URLs:");
		for (String s : urls) {
			System.out.println(s);
		}
		System.out.println("List of addresses:");
		for (String s : addresses) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
