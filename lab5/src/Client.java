import java.net.URL;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Client {

	ArrayList<WebCrawler> crawlers;
	ArrayList<String> hosts;

	public Client() {
		crawlers = new ArrayList<WebCrawler>();
		hosts = new ArrayList<String>();

		hosts.add("localhost");
		hosts.add("hacke-10");
	}

	public void init() throws Exception {
		ArrayList<URL> startAddresses = new ArrayList<URL>();

		startAddresses.add(new URL("http://www.lth.se/"));
		startAddresses.add(new URL("http://www.aftonbladet.se/"));

		for (int i = 0; i < hosts.size(); i++) {
			WebCrawler crawler = (WebCrawler) Naming.lookup("rmi://"
					+ hosts.get(i) + ":1212/WebCrawler");
			crawlers.add(crawler);
			crawler.startCrawling(startAddresses.get(i));
		}

		while (true) {
			Thread.sleep(3000);
			Set<URL> mergedQueue = new HashSet<URL>();
			Set<URL> mergedVisited = new HashSet<URL>();
			for (WebCrawler crawler : crawlers) {
				List<URL>[] result = crawler.fetch();
				mergedQueue.addAll(result[0]);
				mergedVisited.addAll(result[1]);
			}
			
			System.out.println("Total queue: " + mergedQueue.size());
			System.out.println("Total visited: " + mergedVisited.size());
			
			int splitQueueSize = 1 + mergedQueue.size() / hosts.size();
			ArrayList<URL> visited = new ArrayList<URL>(mergedVisited);
			ArrayList<URL>[] result = (ArrayList<URL>[]) new ArrayList[2];
			result[1] = visited;
			Iterator<URL> itr = mergedQueue.iterator();
			for (int i = 0; i < hosts.size(); i++) {
				ArrayList<URL> queue = new ArrayList<URL>();
				int j = 0;
				while (j < splitQueueSize && itr.hasNext()) {
					queue.add(itr.next());
					j++;
				}
				result[0] = queue;
				crawlers.get(i).set(result);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security Manager installed");
		}
		Client client = new Client();
		client.init();
	}

}
