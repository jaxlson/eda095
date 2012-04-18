package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ClientReadThread extends Thread {
	
	private Socket socket;
	
	public ClientReadThread(Socket s) {
		socket = s;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			while (!socket.isClosed()) {
				String line = in.readLine();
				if (line.equals("Goodbye!"))
					break;
				else
					System.out.println(line);
			}
			socket.close();
		} catch (SocketException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
