package AllinusModel;

public class LoginMessageHandler {
	private String email;
	private String pass;
	private String connectionType;
	
	public LoginMessageHandler(String email, String pass, String connectionType) {
		super();
		this.email = email;
		this.pass = pass;
		this.connectionType = connectionType;
	}
	
	public String sendLoginData() {
		return "["+email+","+pass+"]~"+connectionType;
	}
}
