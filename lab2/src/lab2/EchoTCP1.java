package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {

	public static void main(String[] args) throws IOException {
		int port = 30000;
		ServerSocket ss = new ServerSocket(port);
		while (true) {
			Socket socket = ss.accept();
			System.out.println(socket.getInetAddress());
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
		}
	}

}
