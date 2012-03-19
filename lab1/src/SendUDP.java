import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class SendUDP {
	
	private static final int BUFFER_SIZE = 512;

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Wrong usage. Arguments should be <address> <port> <message>");
			System.exit(0);
		}
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String message = args[2];
		InetAddress address = InetAddress.getByName(host);
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);
		byte[] buf = new byte[BUFFER_SIZE];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String response = new String(packet.getData());
		System.out.println(response);
	}

}
