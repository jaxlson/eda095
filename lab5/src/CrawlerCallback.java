import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class CrawlerCallback extends HTMLEditorKit.ParserCallback {

	private Monitor monitor;
	private URL baseUrl;

	public CrawlerCallback(Monitor m) {
		monitor = m;
	}

	public void setBaseUrl(URL url) {
		baseUrl = url;
	}

	@Override
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int pos) {
		getLinks(tag, a);
	}

	@Override
	public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet a, int pos) {
		if (tag == HTML.Tag.BASE) {
			String href = (String) a.getAttribute(HTML.Attribute.HREF);
			try {
				setBaseUrl(new URL(href));
			} catch (MalformedURLException e) {
				//ignored
			}
		} else {
			getLinks(tag, a);
		}
	}

	private void getLinks(HTML.Tag tag, MutableAttributeSet a) {
		String href = null;
		if (tag == HTML.Tag.A) {
			href = (String) a.getAttribute(HTML.Attribute.HREF);
		} else if (tag == HTML.Tag.FRAME) {
			href = (String) a.getAttribute(HTML.Attribute.SRC);
		} else {
			return;
		}
		if (href == null) {
			return;
		}
		URL url = getAbsoluteUrl(href);
		if (url != null) {
			monitor.addToQueue(url);
		}
	}

	private URL getAbsoluteUrl(String u) {
		URL url = null;
		try {
			if (!new URI(u).isAbsolute()) {
				url = new URL(baseUrl, u);
			} else {
				url = new URL(u);
			}
		} catch (URISyntaxException e) {
		} catch (MalformedURLException e) {
		}
		return url;
	}

}
