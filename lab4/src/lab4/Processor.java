package lab4;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLEditorKit;

public class Processor extends Thread {

	private static final int MAX_URLS = 50;

	private List<URL> queue;
	private Set<String> visited;
	private Set<String> urls;
	private Set<String> addresses;

	public Processor(List<URL> q, Set<String> v, Set<String> u, Set<String> a) {
		queue = q;
		visited = v;
		urls = u;
		addresses = a;
	}

	@Override
	public void run() {
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();
		Spider callback = new Spider(queue, visited, urls, addresses);
		int numVisited = 1;
		while (!queue.isEmpty() && numVisited < MAX_URLS) {
			try {
				URL url = queue.remove(0);
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
	}
}
