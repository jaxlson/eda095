package lab1;

import java.net.*;
import java.io.*;

public class MCAddressServer {

	public static void main(String args[]) {
		try {
			MulticastSocket ms = new MulticastSocket(4099);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
			while (true) {
				byte[] buf = new byte[65536];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ms.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength());
				System.out.println("Received: " + s);
				
				InetAddress responseAddress = dp.getAddress();
				InetAddress local = InetAddress.getLocalHost();
				String data = local.getHostAddress();
				DatagramSocket socket = new DatagramSocket();
				dp = new DatagramPacket(data.getBytes(), data.getBytes().length, responseAddress, 4099);
				socket.send(dp);
			}
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}

}
