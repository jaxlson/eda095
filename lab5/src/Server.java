

import java.net.URL;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements WebCrawler {

	private static final long serialVersionUID = 1L;
	private Monitor monitor;
	private static final int NUMBER_OF_THREADS = 10;

	public Server() throws RemoteException {
		monitor = new Monitor();
	}

	@Override
	public void startCrawling(URL startURL) {
		monitor.addToQueue(startURL);
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			Thread t = new CrawlThread(monitor);
			t.start();
		}
	}

	@Override
	public ArrayList<URL>[] fetch() throws RemoteException {
		return monitor.fetch();
	}

	@Override
	public void set(ArrayList<URL>[] input) throws RemoteException {
		monitor.set(input);
	}
	
    public static void main(String[] args) {
    	if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security Manager installed");
        }
        try {
            WebCrawler crawler = new Server();
            Registry registry = LocateRegistry.getRegistry(1212);
            registry.rebind("WebCrawler", crawler);
            System.out.println("RMI server running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
