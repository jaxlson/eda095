package lab3;

public class EchoCommand extends Command {
	
	private String message;
	
	public EchoCommand(Participant p, String s) {
		super(p);
		message = s;
	}
	
	public String getMessage() {
		return message;
	}

}
