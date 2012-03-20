package lab1;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServer2 {

	static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG,
			Locale.GERMAN);
	static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.GERMAN);

	public static final int DATE = 0;
	public static final int TIME = 1;

	private static final int BUFFER_SIZE = 512;

	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[BUFFER_SIZE];
		while (true) {
			int i = 0;
			byte b = (byte) System.in.read();
			while (b != -1 && b != '\n') {
				buf[i++] = b;
				b = (byte) System.in.read();
				if (b == '\r') {
					b = (byte) System.in.read();
				}
			}
			String s = new String(buf, 0, i);
			int command = Integer.parseInt(s);
			Date date = new Date();
			String result = null;
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
			System.out.println(result);
		}
	}
}
