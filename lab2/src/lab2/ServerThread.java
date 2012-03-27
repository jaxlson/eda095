package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket s) {
		socket = s;
	}

	@Override
	public void run() {
		System.out.println(socket.getInetAddress());
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			while (socket.isConnected()) {
				String s = new String();
				int b;
				do {
					b = in.read();
					s += (char) b;
				} while (b != '\n');
				System.out.println(s);
				out.write(s.getBytes());
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
