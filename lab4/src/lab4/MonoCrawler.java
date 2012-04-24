package lab4;

import java.net.MalformedURLException;
import java.net.URL;

public class MonoCrawler {

	public static void main(String[] args) {
		try {
			URL start = new URL("http://cs.lth.se/eda095/");
			Thread t = new CrawlerThread(start);
			t.start();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
