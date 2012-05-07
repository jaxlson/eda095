import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface WebCrawler extends Remote {
	
	public void startCrawling() throws RemoteException; 
	
	public List<URL>[] fetch() throws RemoteException;
	
	public List<URL>[] fetchAndSet(List<URL>[] u) throws RemoteException;

}
