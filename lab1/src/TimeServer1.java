import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServer1 {

	static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG,
			Locale.GERMAN);
	static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.GERMAN);

	public static final String DATE = "date";
	public static final String TIME = "time";

	public static void main(String[] args) {
		Date date = new Date();
		String command = null;
		if (args.length > 0) {
			command = args[0];
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
