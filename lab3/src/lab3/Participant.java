package lab3;

import java.net.Socket;

public class Participant {

	private Socket socket;
	private String name;
	
	public Participant(Socket s) {
		socket = s;
		name = socket.getInetAddress().getHostName();
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public String getName() {
		return name;
	}
	
}
