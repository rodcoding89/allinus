package AllinusModel;

public class SignMessageHandler {
	private String name;
	private String email;
	private String phone;
	private String bornDate;
	private String pass;
	private String connectionType;
	
	public SignMessageHandler(String name, String email, String phone, String bornDate, String pass,
			String connectionType) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.bornDate = bornDate;
		this.pass = pass;
		this.connectionType = connectionType;
	}
	
	public String sendSignData() {
		return "["+name+","+email+","+phone+","+bornDate+","+pass+"]~"+connectionType;
	}
}
