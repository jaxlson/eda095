

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit;

public class Server extends UnicastRemoteObject implements WebCrawler {

	private List<URL> queue;
	private List<URL> visited;

	public Server() throws RemoteException {
		queue = new ArrayList<URL>();
		visited = new ArrayList<URL>();
	}
	
    public static void main(String[] args) {
        try {
            WebCrawler crawler = new Server();
            Naming.rebind("Server", crawler);
            System.out.println("RMI server running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void startCrawling() {
		CrawlThread c = new CrawlThread(queue, visited);
		c.start();
	}

	@Override
	public List<URL>[] fetch() throws RemoteException {
		List<URL>[] result = (ArrayList<URL>[]) new ArrayList[2];
		result[0] = queue;
		result[1] = visited;
		return result;
	}

	@Override
	public List<URL>[] fetchAndSet(List<URL>[] input) throws RemoteException {
		List<URL>[] result = fetch();
		queue = input[0];
		visited = input[1];
		return result;
	}
}
