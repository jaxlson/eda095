import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class DisplayDate {

	public static void main(String[] args) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.GERMAN);
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.GERMAN);
		System.out.println(dateFormat.format(date) + " - " + timeFormat.format(date));
	}

}
