package lab3;

public class Command {
	
	private Participant sender;
	
	public Command(Participant p) {
		sender = p;
	}
	
	public Participant getSender() {
		return sender;
	}

}
