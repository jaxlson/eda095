package lab1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayDate {

	static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG,
			Locale.GERMAN);
	static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT,
			Locale.GERMAN);

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println(timeFormat.format(date));
	}

}
