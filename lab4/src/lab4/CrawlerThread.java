package lab4;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javax.swing.text.html.HTMLEditorKit;

public class CrawlerThread extends Thread {

	private static final int MAX_URLS = 100;

	private Queue<URL> queue;
	private Set<String> visited;
	private Set<String> urls;
	private Set<String> addresses;

	public CrawlerThread(URL start) {
		queue = new ArrayDeque<URL>();
		visited = new HashSet<String>();
		urls = new HashSet<String>();
		addresses = new HashSet<String>();

		queue.add(start);
	}

	@Override
	public void run() {
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();
		LinkGetter callback = new LinkGetter(queue, visited, urls, addresses);
		int numVisited = 1;
		while (!queue.isEmpty() && numVisited < MAX_URLS) {
			try {
				URL url = queue.poll();
				callback.setBaseUrl(url);
				URLConnection con = url.openConnection();
				String type = con.getContentType();
				if (type != null && type.contains("text/html")) {
					InputStream in = new BufferedInputStream(con
							.getInputStream());
					InputStreamReader r = new InputStreamReader(in);
					parser.parse(r, callback, true);
					numVisited++;
				}
			} catch (IOException e) {
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
		// print lists
	}
}
