package lab1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServer1 {

	static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG,
			Locale.GERMAN);
	static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.GERMAN);

	public static final int DATE = 0;
	public static final int TIME = 1;

	public static void main(String[] args) {
		Date date = new Date();
		int command = -1;
		if (args.length > 0) {
			command = Integer.parseInt(args[0]);
		}
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
