package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SendUDP2 {

	private static final int BUFFER_SIZE = 512;

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out
					.println("Wrong usage. Arguments should be <port> <message>");
			System.exit(0);
		}
		int port = Integer.parseInt(args[0]);
		String message = args[1];

		MulticastSocket ms = new MulticastSocket();
		ms.setTimeToLive(1);
		InetAddress ia = InetAddress.getByName("experiment.mcast.net");
		String data = "PC LOAD LETTER";
		DatagramPacket packet = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ia, 4099);
		ms.send(packet);

		DatagramSocket socket = new DatagramSocket(4099);
		byte[] buf = new byte[BUFFER_SIZE];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String s = new String(packet.getData(), 0, packet.getLength());
		System.out.println("Received: " + s);
		String response = new String(packet.getData());

		InetAddress address = InetAddress.getByName(response);
		packet = new DatagramPacket(message.getBytes(),
				message.getBytes().length, address, port);
		socket.send(packet);
	}

}
