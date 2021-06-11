package AllinusModel;

public class SendMessageHandler {
	private String from;
	private String to;
	private String messg;
	private String date;
	private String messtype;
	private String telFrom;
	private String telTo;
	private String status;
	public SendMessageHandler(String from, String to, String messg, String date, String messtype,String telFrom,String telTo,String status) {
		super();
		this.from = from;
		this.to = to;
		this.messg = messg;
		this.date = date;
		this.telFrom = telFrom;
		this.telTo = telTo;
		this.status = status;
		this.messtype = messtype;
	}
	
	public String sendMessage() {
		return from+","+to+","+messg+","+date+","+telFrom+","+telTo+","+status+"~"+messtype;
	}
}
