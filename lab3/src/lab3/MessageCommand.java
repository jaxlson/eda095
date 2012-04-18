package lab3;

public class MessageCommand extends Command {

	private String message;
	
	public MessageCommand(Participant p, String s) {
		super(p);
		message = s;
	}
	
	public String getMessage() {
		return message;
	}
	
}
