import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface WebCrawler extends Remote {
	
	public void startCrawling(URL startURL) throws RemoteException; 
	
	public ArrayList<URL>[] fetch() throws RemoteException;
	
	public void set(ArrayList<URL>[] u) throws RemoteException;

}
