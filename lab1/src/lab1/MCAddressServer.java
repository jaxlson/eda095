package lab1;

import java.net.*;
import java.io.*;

public class MCAddressServer {

	public static void main(String args[]) {
		try {
			MulticastSocket ms = new MulticastSocket(5013);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
			while (true) {
				byte[] buf = new byte[65536];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ms.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength());
				System.out.println("Received: " + s);
				
				InetAddress address = dp.getAddress();
				int port = dp.getPort();
				InetAddress local = InetAddress.getLocalHost();
				String data = "I am active and this is my name: " + local.getHostAddress();
				DatagramSocket socket = new DatagramSocket();
				dp = new DatagramPacket(data.getBytes(), data.getBytes().length, address, port);
				socket.send(dp);
				socket.close();
			}
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}

}
