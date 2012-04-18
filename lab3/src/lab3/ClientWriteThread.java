package lab3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWriteThread extends Thread {

	private Socket socket;

	public ClientWriteThread(Socket s) {
		socket = s;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			PrintWriter out = new PrintWriter(new BufferedOutputStream(socket
					.getOutputStream()), true);
			while (!socket.isClosed()) {
				String line = in.readLine();
				if (line.subSequence(0, 2).equals("Q:"))
					break;
				else
					out.println(line);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
