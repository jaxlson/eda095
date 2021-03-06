package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServerUDP {

	static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG,
			Locale.GERMAN);
	static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.GERMAN);

	public static final int DATE = 0;
	public static final int TIME = 1;

	private static final int BUFFER_SIZE = 65536;

	public static void main(String[] args) throws IOException {
		int port = 30000;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		DatagramSocket socket = new DatagramSocket(port);
		byte[] buf = new byte[BUFFER_SIZE];
		while (true) {
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			byte[] data = packet.getData();
			int length = packet.getLength();
			InetAddress address = packet.getAddress();
			port = packet.getPort();

			Date date = new Date();
			String result = null;
			String s = new String(buf, 0, length);
			System.out.println(s);
			int command = Integer.parseInt(s);
			switch (command) {
			case DATE:
				result = dateFormat.format(date);
				break;
			case TIME:
				result = timeFormat.format(date);
				break;
			default:
				result = "Invalid command";
				break;
			}
			packet = new DatagramPacket(result.getBytes(), result.getBytes().length,
					address, port);
			socket.send(packet);
		}
	}
}
