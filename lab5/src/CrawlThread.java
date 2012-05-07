import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit;


public class CrawlThread extends Thread {
	List<URL> queue;
	List<URL> visited;
	
	public CrawlThread(List<URL> q, List<URL> v) {
		queue = q;
		visited = v;
	}
	
	@Override
	public void run() {
		ParserGetter kit = new ParserGetter();
		HTMLEditorKit.Parser parser = kit.getParser();
		ServerSpider callback = new ServerSpider(queue, visited);
		while (!queue.isEmpty()) {
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
				}
			} catch (IOException e) {
			}
		}
	}
}
