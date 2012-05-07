

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Client {
	Set<URL> visited;
	ArrayList<WebCrawler> servers;
	ArrayList<String> hosts;

	public Client() {
		Set<URL> visited = Collections.synchronizedSet(new HashSet<URL>());
		servers = new ArrayList<WebCrawler>();
		ArrayList<String> hosts = new ArrayList<String>();
		hosts.add("varg-2");
		ArrayList<URL> startAddresses = new ArrayList<URL>();
		ArrayList<URL> startVisited = new ArrayList<URL>();
		try {
			startAddresses.add(new URL("http://www.lth.se/"));
			startAddresses.add(new URL("http://www.aftonbladet.se/"));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try {
			for (int i = 0 ; i < hosts.size() ; i++) {
				System.out.println("add host");
				Registry registry = LocateRegistry.getRegistry(hosts.get(i));
				WebCrawler crawler = (WebCrawler) registry.lookup("Server");
				servers.add(crawler);
				List<URL>[] input = (ArrayList<URL>[]) new ArrayList[2];
				List<URL> start = new ArrayList<URL>();
				start.add(startAddresses.get(i));
				input[0] = start;
				input[1] = startVisited;
				System.out.println("innan fetchandset");
				crawler.fetchAndSet(input);
				System.out.println("innan startcrawl");
				crawler.startCrawling();
				System.out.println("efter startcrawl");
			}
			System.out.println("innan loop");
			while(true) {
				try {
					Thread.sleep(3000);
					for (WebCrawler crawler : servers) {
						System.out.println("fetch?");
						List<URL>[] result = crawler.fetch();
						System.out.println("fetched!");
						for (URL url : result[0]) {
							System.out.println(url.toString());
						}
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client();
	}
	
	public void printResult() {
		System.out.println("List of URLs:");
		for (URL s : visited) {
			System.out.println(s.toString());
		}
	}

}
