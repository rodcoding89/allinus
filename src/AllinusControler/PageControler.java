package AllinusControler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AllinusModel.LoginMessageHandler;
import AllinusModel.Message;
import AllinusModel.SendMessageHandler;
import AllinusModel.SignMessageHandler;
import AllinusView.AlertBox;
import AllinusView.PageLogin;
import AllinusView.PageSign;
import AllinusView.PageUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PageControler implements Runnable{
	private static Stage rootStage;
	private static PageLogin login;
	private static PageSign sign;
	private static PageUser user;
	private String ip = "127.0.0.1";
	//private static String hostname = "localhost";
	private int port = 5252;
	private BufferedReader infromuser;
	private static Socket client;
	private static Label errLabPass,errLabEmail;
	private DatagramPacket datagrampacket;
	private InetAddress inetaddress = null;
	private SendMessageHandler receivMessage;
	private Message sendMessage;
	private static ProgressIndicator pg;
	private AlertBox albox;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private static Boolean checklogin = false;
	private static String username;
	private static String userphone;
	private static String searchData;
	private static int boxIndex = 1;
	private static String contactData;
	private static Map<String,String> lmap = new HashMap<>();
	private static List<Node> lbox = new ArrayList<>();
	private static List<Node> hbox = new ArrayList<>();
	private static String telTo;
	private static String toName;
	private static Boolean isBroacast = true;
	private static Boolean isLogged = false;
	private static int userid;
	private static int frienduserid;
	private static Boolean isLogout = false;
	private static int previewActiveUserSize = 0;
	private static HBox ubox,boxh;
	private static int userSize;
	private static String status;
	private static Boolean uValid;

	
	public PageControler(Stage rootStage) throws IOException, InterruptedException {
		this.login = new PageLogin(rootStage);
		this.sign = new PageSign(rootStage);
		this.user = new PageUser(rootStage);
		this.rootStage = rootStage;
		
		//inetaddress = InetAddress.getByName(ip);
		
		pg = new ProgressIndicator();
		pg.setId("progressindicator");
		
		//this.albox = new AlertBox();
		client = new Socket(ip, port);
		dis = new DataInputStream(client.getInputStream()); 
        
		dos = new DataOutputStream(client.getOutputStream()); 
		//handleReceivMessage();
		
		windowClose();
		new Thread(new HandleBroacast(dos,dis)).start();
		
	}
	
	
	public static void HandlerLogin() throws IOException {
		login.getOverPane().getChildren().add(pg);
		login.getParent().getChildren().add(login.getOverPane());
		
		if(!login.getUserTF().getText().equals("") && !login.getPassPF().getText().equals("")) {
			
			if(verifyEmail(login.getUserTF().getText()) && verifyPass(login.getPassPF().getText())) {
				
				onMessage(dos,new LoginMessageHandler(login.getUserTF().getText().trim(),
						login.getPassPF().getText().trim(),"login").sendLoginData());
				
			}else if(!verifyEmail(login.getUserTF().getText()) && verifyPass(login.getPassPF().getText())) {
				login.getErrLabEmail().setText("Your Email not respect the restriction");
				removeLoginLoader();
			}else if(verifyEmail(login.getUserTF().getText()) && !verifyPass(login.getPassPF().getText())) {
				login.getErrLabPass().setText("Your password not respect the restriction");
				removeLoginLoader();
			}else {
				verifyData("The giving data not respect the restriction");
				removeLoginLoader();
			}
		}else if(login.getUserTF().getText().equals("") && !login.getPassPF().getText().equals("")) {
			login.getErrLabEmail().setText("Please full the Email field");
			removeLoginLoader();
		}else if(!login.getUserTF().getText().equals("") && login.getPassPF().getText().equals("")) {
			login.getErrLabPass().setText("Please full the Password field");
			removeLoginLoader();
		}else {
			verifyData("Please full the field");
			removeLoginLoader();
		}
		//if(checkEmail && checkPass) {
			//send
		//}
		
		//displayUserPage();
		System.out.println(login.getUserTF().getText());
	}
	
	public static void HandlerSign() {
		displaySignPage();
		System.out.println("sign");
	}
	
	public static void HandlerForgotLoginData() {
		
		System.out.println("forgot data");
	}
	
	public static void HandlerSignData() {
		sign.getOverPane().getChildren().add(pg);
		sign.getParent().getChildren().add(sign.getOverPane());
		
		if(!sign.getNameTF().getText().equals("") && !sign.getEmailTF().getText().equals("") && 
				!sign.getPhoneTF().getText().equals("") && !sign.getbornDateTF().getText().equals("") &&
				!sign.getPassPF().getText().equals("") && !sign.getConfirmPF().getText().equals("")) {
			
			if(verifyEmail(sign.getEmailTF().getText()) && verifyPass(sign.getPassPF().getText()) && 
					checkDate(sign.getbornDateTF().getText()) && checkPhone(sign.getPhoneTF().getText()) && 
					checkUserName(sign.getNameTF().getText()) && uValid) {
				if(sign.getPassPF().getText().equals(sign.getConfirmPF().getText())) {
					//send data
					new Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Platform.runLater(new Runnable () {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
										onMessage(dos,new SignMessageHandler(sign.getNameTF().getText().trim(),
												sign.getbornDateTF().getText().trim(),
												sign.getPhoneTF().getText().trim(),
												sign.getEmailTF().getText().trim(),
												sign.getPassPF().getText().trim(),"sign").sendSignData());
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							});
						}
						
					}, 3000);
				}else {
					sign.getErrPassword().setText("Please check your giving password");
					sign.getErrPassWordConfig().setText("Please check your giving password");
					removeSignLoader();
				}
			}else if(!verifyEmail(sign.getEmailTF().getText()) && verifyPass(sign.getPassPF().getText()) && 
					checkDate(sign.getbornDateTF().getText()) && checkPhone(sign.getPhoneTF().getText())) {
				sign.getErrMail().setText("Your giving mail is incorrect");
				removeSignLoader();
			}else if(verifyEmail(sign.getEmailTF().getText()) && !verifyPass(sign.getPassPF().getText()) && 
					checkDate(sign.getbornDateTF().getText()) && checkPhone(sign.getPhoneTF().getText())) {
				sign.getErrPassword().setText("Your giving password is incorrect");
				removeSignLoader();
			}else if(verifyEmail(sign.getEmailTF().getText()) && verifyPass(sign.getPassPF().getText()) && 
					!checkDate(sign.getbornDateTF().getText()) && checkPhone(sign.getPhoneTF().getText())) {
				sign.getErrBornDate().setText("Your giving born date is incorrect");
				removeSignLoader();
			}else if(verifyEmail(sign.getEmailTF().getText()) && verifyPass(sign.getPassPF().getText()) && 
					checkDate(sign.getbornDateTF().getText()) && !checkPhone(sign.getPhoneTF().getText())) {
				sign.getErrPhone().setText("Your giving Phone Number is incorrect");
				removeSignLoader();
			}else if(!checkUserName(sign.getNameTF().getText()) && verifyEmail(sign.getEmailTF().getText()) && verifyPass(sign.getPassPF().getText()) && 
					checkDate(sign.getbornDateTF().getText()) && checkPhone(sign.getPhoneTF().getText())) {
				sign.getErrName().setId("errlab");
				sign.getErrName().setText("The username not respect the consign");
				
				removeSignLoader();}
			
		}else {
			removeSignLoader();
			for(int i = 0; i<sign.getListTF().size(); i++) {
				if(sign.getListTF().get(i).getText().equals("")) {
					if(sign.getNameTF() == sign.getListTF().get(i)) {
						sign.getErrName().setText("Please full the Field");
					}else if(sign.getEmailTF() == sign.getListTF().get(i)) {
						sign.getErrMail().setText("Please full the Field");
					}else if(sign.getbornDateTF() == sign.getListTF().get(i)) {
						sign.getErrBornDate().setText("Please full the Field");
					}else if(sign.getPassPF() == sign.getListTF().get(i)) {
						sign.getErrPassword().setText("Please full the Field");
					}else if(sign.getPhoneTF() == sign.getListTF().get(i)) {
						sign.getErrPhone().setText("Please full the Field");
					}if(sign.getConfirmPF() == sign.getListTF().get(i)) {
						sign.getErrPassWordConfig().setText("Please full the Field");
					}
				}
			}
		}
		//System.out.println("save signig data");
	}
	
	public static void HandlerUserData(ActionEvent e) {
		System.out.println(user.getBtUsername().toString());
	}
	
	public static void verifyData(String msg) {
		login.getErrLabEmail().setText(msg);
		login.getErrLabPass().setText(msg);
	}
	
	public static void removeLoginLoader() {
		login.getOverPane().getChildren().remove(pg);
		login.getParent().getChildren().remove(login.getOverPane());
	}
	
	public static void removeSignLoader() {
		sign.getOverPane().getChildren().remove(pg);
		sign.getParent().getChildren().remove(sign.getOverPane());
	}
	
	public static void removeUserLoader() {
		user.getOverPane().getChildren().remove(pg);
		user.getPanel().getChildren().remove(user.getOverPane());
	}
	
	public static Boolean verifyPass(String msg) {
		String regex = "((([a-z])+|([A-Z])+|([1-9])+|([#@~%$])+){6,25})";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(msg);
		Boolean check;
		if(m.find()) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}
	
	public static Boolean checkUserName(String msg) {
		String regex = "^[a-zA-Z\\d]{3,8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(msg);
		if(m.find()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean verifyEmail(String msg) {
		String regex = "([a-z-0-9.])+([@])+([a-z.])+";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(msg);
		Boolean check;
		if(m.find()) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}
	
	public static Boolean checkPhone(String msg) {
		String regex = "\\d(-)*\\d$";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(msg);
		Boolean check;
		if(m.find()) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}
	public static Boolean checkDate(String msg) {
		String regex = "([1-9]){1}([0-9]){3}-(01|02|03|04|05|06|07|08|09|10|11|12){1}-"
				+ "(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31){1}";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(msg);
		Boolean check;
		if(m.find()) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}
	
	public static void inputEmailKeypressed() {
		// TODO Auto-generated method stub
		if(!login.getErrLabEmail().getText().equals("") || !login.getErrLabPass().getText().equals("")) {
			login.getErrLabEmail().setText("");
			login.getErrLabPass().setText("");
		}
	}
	
	public static void inputPassKeypressed() {
		// TODO Auto-generated method stub
		if(!login.getErrLabEmail().getText().equals("") || !login.getErrLabPass().getText().equals("")) {
			login.getErrLabEmail().setText("");
			login.getErrLabPass().setText("");
		}
	}
	
	public static void handleInputEmail() {
		// TODO Auto-generated method stub
		if(!sign.getErrName().getText().equals("") || !sign.getEmailTF().getText().equals("") ||
				!sign.getErrBornDate().getText().equals("") || !sign.getErrPhone().getText().equals("") ||
				!sign.getErrPassword().getText().equals("") || !sign.getErrPassWordConfig().getText().equals("")) {
			sign.getErrName().setText("");
			sign.getErrBornDate().setText("");
			sign.getErrMail().setText("");
			sign.getErrPassword().setText("");
			sign.getErrPassWordConfig().setText("");
			sign.getErrPhone().setText("");
		}
		
	}

	public static void handleInputBornDate() {
		// TODO Auto-generated method stub
		if(!sign.getErrName().getText().equals("") || !sign.getEmailTF().getText().equals("") ||
				!sign.getErrBornDate().getText().equals("") || !sign.getErrPhone().getText().equals("") ||
				!sign.getErrPassword().getText().equals("") || !sign.getErrPassWordConfig().getText().equals("")) {
			sign.getErrName().setText("");
			sign.getErrBornDate().setText("");
			sign.getErrMail().setText("");
			sign.getErrPassword().setText("");
			sign.getErrPassWordConfig().setText("");
			sign.getErrPhone().setText("");
		}
	}

	public static void handleInputUser() throws IOException {
		// TODO Auto-generated method stub
		if(!sign.getErrName().getText().equals("") || !sign.getEmailTF().getText().equals("") ||
				!sign.getErrBornDate().getText().equals("") || !sign.getErrPhone().getText().equals("") ||
				!sign.getErrPassword().getText().equals("") || !sign.getErrPassWordConfig().getText().equals("")) {
			sign.getErrName().setText("");
			sign.getErrBornDate().setText("");
			sign.getErrMail().setText("");
			sign.getErrPassword().setText("");
			sign.getErrPassWordConfig().setText("");
			sign.getErrPhone().setText("");
		}
		onMessage(dos,sign.getNameTF().getText()+"~checkpresentusername");
	}

	public static void handleInputPhone() {
		// TODO Auto-generated method stub
		if(!sign.getErrName().getText().equals("") || !sign.getEmailTF().getText().equals("") ||
				!sign.getErrBornDate().getText().equals("") || !sign.getErrPhone().getText().equals("") ||
				!sign.getErrPassword().getText().equals("") || !sign.getErrPassWordConfig().getText().equals("")) {
			sign.getErrName().setText("");
			sign.getErrBornDate().setText("");
			sign.getErrMail().setText("");
			sign.getErrPassword().setText("");
			sign.getErrPassWordConfig().setText("");
			sign.getErrPhone().setText("");
		}
	}

	public static void handleInputPass() {
		// TODO Auto-generated method stub
		if(!sign.getErrName().getText().equals("") || !sign.getEmailTF().getText().equals("") ||
				!sign.getErrBornDate().getText().equals("") || !sign.getErrPhone().getText().equals("") ||
				!sign.getErrPassword().getText().equals("") || !sign.getErrPassWordConfig().getText().equals("")) {
			sign.getErrName().setText("");
			sign.getErrBornDate().setText("");
			sign.getErrMail().setText("");
			sign.getErrPassword().setText("");
			sign.getErrPassWordConfig().setText("");
			sign.getErrPhone().setText("");
		}
	}

	public static void handleInputConfirmPass() {
		// TODO Auto-generated method stub
		if(!sign.getErrName().getText().equals("") || !sign.getEmailTF().getText().equals("") ||
				!sign.getErrBornDate().getText().equals("") || !sign.getErrPhone().getText().equals("") ||
				!sign.getErrPassword().getText().equals("") || !sign.getErrPassWordConfig().getText().equals("")) {
			sign.getErrName().setText("");
			sign.getErrBornDate().setText("");
			sign.getErrMail().setText("");
			sign.getErrPassword().setText("");
			sign.getErrPassWordConfig().setText("");
			sign.getErrPhone().setText("");
		}	
	}
	// display user contact containted in the table contact+user+phone
	public static void displayUserContact(String users,String userphone) throws IOException {
		onMessage(dos,users+","+userphone+"~getcontact");
	}
	
	public static void backOnLogin() throws FileNotFoundException {
		// TODO Auto-generated method stub
		displayLoginPage();
	}
	
	public static void displayLoginPage() throws FileNotFoundException {
		login.show(rootStage, login.initialisation());
	}
	public static void displaySignPage() {
		sign.show(rootStage, sign.initialise());
	}
	public static void displayUserPage(String username) {
		user.show(rootStage, user.initialise(),username);
	}
	
	
	public static synchronized void onMessage(DataOutputStream dos,String msg) throws IOException {
		dos.writeUTF(msg);
		//dos.flush();
		//dos.close();
	}
	
	public static synchronized String onReceivMessage(DataInputStream dis) throws IOException {
		String message;
		message = dis.readUTF();
		//dis.close();
		return message;
	}
	
	public static void handleSearch() throws IOException {
		user.getMessageBox().getChildren().clear();
		String inputData = user.getInputSearch().getText().trim();
		System.out.println(inputData);
		try {
			if(detectNumber(inputData)) {
				if(inputData.contains(" ")) {
					String newinput = inputData.replace(" ", "");
					if(newinput.contains("-")) {
						String tmpData = newinput.replace("-", "");
						try {
							onMessage(dos,tmpData+"~searchbyphone");
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						try {
							onMessage(dos,newinput+"~searchbyphone");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}else if(inputData.contains("-")){
					String newinput = inputData.replace("-", "");
					if(newinput.contains(" ")) {
						String tmpData = newinput.replace(" ", "");
						try {
							onMessage(dos,tmpData+"~searchbyphone");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						try {
							onMessage(dos,newinput+"~searchbyphone");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(!inputData.contains(" ") && !inputData.contains("-")){
					onMessage(dos,inputData+"~searchbyphone");
				}
			}else {
				onMessage(dos,inputData+"~searchbyname");
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void handleAddContact(Event e,String name,String phone,String email,HBox box) throws IOException {
		// TODO Auto-generated method stub
		
		try {
			onMessage(dos,name+","+phone+","+email+"~addcontact");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ubox = (HBox)box;
					
		//remove node from the messagebox, for add anothor component in the messagebox
					
	}
	
	public static Boolean detectNumber(String input) {
		char[] inData = input.toCharArray();
		char[] data = {'0','1','2','3','4','5','6','7','8','9'};
		Boolean check = null;
		int count = 0;
		for(int i = 0; i < inData.length; i++) {
			for(int j = 0; j< data.length; j++) {
				if(inData[i] == data[j]) {
					count ++;
					break;
				}
			}
		}
		if(count > 0) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}

	public static void handleLogout() throws IOException {
		// TODO Auto-generated method stub
		user.getOverPane().getChildren().add(pg);
		user.getPanelBox().getChildren().add(user.getOverPane());
		isLogout = true;
		
			// TODO Auto-generated method stub
				
			//String msg="";
			
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					
					// TODO Auto-generated method stub
					try {
						onMessage(dos,"data~logout");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//if(msg.contains("trueandclose")) {
					
				}
				
			}, 3000);
				
			
	}
	public void windowClose() {
		isLogout = true;
		rootStage.setOnCloseRequest(e -> {
			try {
				onMessage(dos,"data~logout");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	

	public static void HandlerUserData(ActionEvent e, String phone,String onoff,String username,VBox box,String stat) {
		// TODO Auto-generated method stub
		toName = username;
		status = stat;
		if(onoff.equals("On")) {
			//user.getMessageBox().getChildren().clear();
			Alert al1 = new Alert(AlertType.INFORMATION);
			al1.setTitle("Connection established");
			al1.setContentText("You are now Connected with "+username+" send her a Message");
			al1.setHeaderText(null);
			al1.showAndWait();
			//new AlertBox().Alert("Connection established", "You are now Connected with "+username+" send her a Message");
			//user.getMessageBox().getChildren().add(new Label("You are now Connected with "+username+" send her a Message"));
		}else {
			//user.getMessageBox().getChildren().clear();
			new AlertBox().Alert("User is not Online", username+" is not more online, but you can leave a Message");
			//user.getMessageBox().getChildren().add(new Label(username+" is not more online, but you can leave a Message"));
		}
		for(Map.Entry<Boolean, VBox> entry: user.getVlist().entrySet()) {
			System.out.println("test0");
			if(entry.getValue() != null) {
				if(entry.getKey()) {
					//System.out.println(vlist.size());
					entry.getValue().setStyle("-fx-control-inner-background:none");
					user.getVlist().remove(entry.getValue());
				}else {
					
				}
				
			}
			
		}
		
		user.getVlist().put(true, box);
		box.setStyle("-fx-background-color:rgba(113,219,101,0.15)");
		telTo = phone;
		System.out.println("pp "+phone);
	}

	public static void handleMessage(KeyEvent e) throws IOException {
		// TODO Auto-generated method stub
		if(e.getCode() == KeyCode.ENTER) {
			Date d = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MMM.dd 'at' hh:mm");
			String date = ft.format(d);
			
			if(username != null && toName != null && userphone != null && telTo != null) {
				
				if(status.toLowerCase().equals("on")) {
					String messg = user.getMessagearea().getText();
					onMessage(dos,new SendMessageHandler(username,toName,messg,date,"chat",
							userphone,telTo,"on").sendMessage());
					StackPane chatBox = user.messageChatDecor(messg, username, date);
					chatBox.setAlignment(Pos.CENTER_LEFT);
					user.getMessageBox().getChildren().add(chatBox);
				}else if(status.toLowerCase().equals("off")) {
					String messg = user.getMessagearea().getText();
					onMessage(dos,new SendMessageHandler(username,toName,messg,date,"chat",
							userphone,telTo,"off").sendMessage());
				}
				
			}else {
				//System.out.println("initialise all parameter");
				//user.getMessageBox().getChildren().clear();
				//user.getMessageBox().getChildren().add(new Label("choose a contact to start a conversation"));
				//new AlertBox().Alert("Select a communication Partner", "choose a contact to start a conversation");
				Alert al2 = new Alert(AlertType.INFORMATION);
				al2.setTitle("Select a communication Partner");
				al2.setContentText("choose a contact to start a conversation");
				al2.setHeaderText(null);
				al2.showAndWait();
			}
			//System.out.println(username+toName+userphone+telTo);
			user.getMessagearea().setText("");
		}
		
	}

	public static void handleFriendRequest() throws IOException {
		// TODO Auto-generated method stub
		int requestCount = Integer.valueOf(user.getBtfriend().getText());
		if(requestCount > 0) {
			onMessage(dos,username+","+userphone+"~friendrequest");
			//System.out.println("test");
		}
		
	}
	
	public static void displayMoreItem(String[] data,String handleButton) {
		String[] inData = data[1].split("-");
		System.out.println("request "+data[1]);
		user.getMessageBox().getChildren().clear();
		hbox.clear();
		for(int i = 0; i < inData.length; i++) {
			//frienduserid = Integer.valueOf(inData[i].split(",")[0]);
			HBox box = user.handleContact(inData[i].split(",")[1], 
					inData[i].split(",")[2],Integer.valueOf(inData[i].split(",")[0]),inData[i].split(",")[2],handleButton);
			user.getMessageBox().getChildren().add(box);
			//user.getInputSearch().setText("");
			hbox.add(box);
			//lmap.put(inData[i].split(",")[0], inData[i].split(",")[1]+","+inData[i].split(",")[2]);
		}
	}
	public static void displayOneItem(String[] data,String handleButton) {
		String[] inData = data[1].split(",");
		HBox box = user.handleContact(inData[1], inData[2],Integer.valueOf(inData[0]),"",handleButton);
		
		//user.getMessageBox().getChildren().clear();
		//hbox.clear();
		user.getMessageBox().getChildren().add(box);
		//user.getInputSearch().setText("");
		hbox.add(box);
		//System.out.println("friend request "+responsemsg);
	}

	
	public static Object handleOffMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void handleConfirmFriendRequest(ActionEvent e,String name, String phone,HBox box,String friendID) throws IOException {
		// TODO Auto-generated method stub
		
		onMessage(dos,name+","+phone+","+String.valueOf(userid)+","+friendID+"~validfriendrequest");
		
		System.out.println("friendid "+friendID);
		// TODO Auto-generated method stub
		boxh = box;
		
	}

	public static void handleDoingRequest() throws IOException {
		// TODO Auto-generated method stub
		onMessage(dos,username+","+userphone+"~listofdoingrequest");
		String msg;
		user.getMessageBox().getChildren().clear();
		//msg = onReceivMessage(dis);
		
	}
	
	public static void getCountOfDoingRequest() throws IOException {
		onMessage(dos,username+","+userphone+"~getdoingrequestcount");
		String msg;
		msg = onReceivMessage(dis);
		if(msg.contains("doingrequestcount")) {
			//System.out.println("doingrequest "+msg);
			user.getUserbt().setText(msg.split("~")[1]);
		}
	}
	
	public static void getContactFriendCount() throws IOException {
		onMessage(dos,username+","+userphone+"~getcontactfriendcount");
		String msg;
		msg = onReceivMessage(dis);
		if(msg.contains("contactcounttrue")) {
			user.getBtfriend().setText(msg.split("~")[1]);
		}else {
			
		}
	}
	
	public void checkOnlineUser(int idofuser) throws IOException {
	
		onMessage(dos,username+","+userphone+","+idofuser+"~checkonlineuser");
	}
	
	public class HandleBroacast implements Runnable{
		
		DataOutputStream dos;
		DataInputStream dis;
		
		public HandleBroacast(DataOutputStream dos,DataInputStream dis) {
			this.dos = dos;
			this.dis = dis;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("class test");
			while(true) {
				//System.out.println("class test1");
					try {
						if(dis.available() > 0) {
							String msg = onReceivMessage(dis);
							System.out.println(msg);
							new Timer().schedule(new TimerTask() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									
										Platform.runLater(() ->{
											if(msg.contains("truelogin")) {
												String[] data = msg.split(",");
											//System.out.println("check "+data[1]);
												if(data.length > 0) {
													username = data[1];
													userphone = data[2];
													userid = Integer.valueOf(data[4]);
													previewActiveUserSize = Integer.valueOf(data[5]);
													displayUserPage(username);
													
													user.getUser().setText(username);
													try {
														getCountOfDoingRequest();
														getContactFriendCount();
														displayUserContact(username,userphone);
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													isLogged = true;
													removeLoginLoader();
												}
											}else if(msg.contains("falselogin")){
												
												if(msg.contains("pass")) {
													login.getErrLabPass().setText("The Giving password not match");
													removeLoginLoader();
												}else if(msg.contains("email")) {
													login.getErrLabEmail().setText("The Giving Email not match");
													removeLoginLoader();
												}
												
												
											}
										});
								}
								
							}, 3000);
							if(msg.contains("trueandclose")){
								Platform.runLater(() ->{
									removeUserLoader();
									try {
										dos.close();
										dis.close();
										client.close();

										isLogged = false;
										isBroacast = false;
										rootStage.close();
										System.exit(0);
										
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								});
								break;
							}else if(msg.contains("signtrue")) {
								new Timer().schedule(new TimerTask() {

									@Override
									public void run() {
										// TODO Auto-generated method stub
										Platform.runLater(() -> {
											try {
												displayLoginPage();
												removeSignLoader();
											} catch (FileNotFoundException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										});
									}
									
								}, 3000);
								
							}else if(msg.contains("signfalse")){
								System.out.println("Sommthing wrong try later or try again");
							}else if(msg.contains("notfoundbysearching")) {
								Platform.runLater(() -> {
									new AlertBox().Alert("Result of your Search", "The user that you search not exist.");
									/*Label err = new Label();
									err.setText("Nothing found");
									
									user.getMessageBox().getChildren().clear();
									hbox.clear();
									user.getMessageBox().getChildren().add(err);
									hbox.add(err);*/
								});
								
								//user.getInputSearch().setText("");
							}else if(msg.contains("phonetrue")){
								Platform.runLater(() -> {
									user.getMessageBox().getChildren().clear();
									hbox.clear();
									String[] data = msg.split("~");
									String[] inData = data[1].split(",");
									System.out.println(inData);
									HBox box1 = user.handleContact(inData[0], inData[1],boxIndex,inData[2],"addcontact");
									
									user.getMessageBox().getChildren().add(box1);
									//user.getInputSearch().setText("");
									hbox.add(box1);
									user.getInputSearch().setText("");
								});
								
							}else if(msg.contains("bynametrue")) {
								Platform.runLater(() -> {
									user.getMessageBox().getChildren().clear();
									hbox.clear();
									String[] data = msg.split("~");
									String[] inData = data[1].split(",");
									HBox box = user.handleContact(inData[0], inData[1],boxIndex,inData[2],"addcontact");
									//user.getMessageBox().getChildren().clear();
									user.getMessageBox().getChildren().add(box);
									//user.getInputSearch().setText("");
									hbox.add(box);
									user.getInputSearch().setText("");
								});
								
								
							}else if(msg.contains("notfound")) {
								System.out.println("notthing found");
							}else if(msg.contains("contactnotadded")) {
								//user.getMessageBox().getChildren().clear();
								Platform.runLater(() ->{
									user.getMessageBox().getChildren().add(new Label(msg));
									//user.getInputSearch().setText("");
									System.out.println("contact not added "+msg);
								});
								
							}else if(msg.contains("contactadded")) {
								Platform.runLater(() -> {
									int requestcount = Integer.valueOf(user.getUserbt().getText());
									
									user.getUserbt().setText(String.valueOf(requestcount+1));
									
									for(int i = 0; i<hbox.size(); i++) {
										
										if(ubox == hbox.get(i)) {
											user.getMessageBox().getChildren().remove(i);
											hbox.remove(i);
											//System.out.println("hbox size "+hbox.size()+" user size"+user.getMessageBox().getChildren().size());
										}
										
									}
									System.out.println("contact added "+msg+" count "+requestcount);
								});
								
								
							}else if(msg.contains("contactavaibletrue")) {
								Platform.runLater(() -> {
									String[] inData = msg.split("~");
									System.out.println(inData[1]);
									if(msg.contains("-")) {
										user.getUserBox().getChildren().clear();
										String[] data = inData[1].split("-");//name,phone,off-name,phone,on
										//String[] data2 = data[1].split("~");
										for(int i = 0; i < data.length; i++) {
											String[] data2 = data[i].split(",");
											//System.out.println(data2[2]);
											if(data2[3].equals("On")) {
												VBox box = user.onLineUser(data2[1],data2[3], "start the conversation", "#7bba80",data2[3],data2[2]);
												user.getUserBox().getChildren().add(box);
												//lbox.add(box);
											}else {
												VBox box = user.onLineUser(data2[1],data2[3], "logged since 1 hour ago", "#bf716b",data2[3],data2[2]);
												user.getUserBox().getChildren().add(box);
												//lbox.add(box);
											}
											
										}
									}else {
										Platform.runLater(() -> {
											user.getUserBox().getChildren().clear();
											String[] inData2 = inData[1].split(",");
											if(inData2[3].equals("On")) {
												VBox box = user.onLineUser(inData2[1],inData2[3], "start the a conversation", "#7bba80",inData2[3],inData2[2]);
												user.getUserBox().getChildren().add(box);
												//lbox.add(box);
											}else {
												VBox box = user.onLineUser(inData2[1],inData2[3], "logged since 1 hour ago", "#bf716b",inData2[3],inData2[2]);
												user.getUserBox().getChildren().add(box);
												//lbox.add(box);
											}
										});
									}
								});
								
							}else if(msg.contains("contactavaiblefalse")) {
								String[] msgdata = msg.split("~");
								user.getBtfriend().setText(msgdata[1]);
							}else if(msg.contains("broadcasttrue")) {
								String[] data  = msg.split("~");
								Platform.runLater(() ->{
									if(data[1].contains("-")) {
										String[] inData = data[1].split("-");
										for(int i =0; i<inData.length;i++) {
											String[] tmpData = inData[i].split(",");
											try {
												checkOnlineUser(Integer.valueOf(tmpData[2]));
												userSize = Integer.valueOf(tmpData[3]);
											} catch (NumberFormatException | IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}else {
										String inData[] = data[1].split(",");
										
										try {
											checkOnlineUser(Integer.valueOf(inData[2]));
											userSize = Integer.valueOf(inData[3]);
										} catch (NumberFormatException | IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								
							}else if(msg.contains("doingrequestcount")) {
								System.out.println("doingrequest "+msg);
								//user.getUserbt().setText(msg.split("~")[1]);
							}else if(msg.contains("contactcounttrue")) {
								System.out.println("contactcount "+msg);
								//user.getBtfriend().setText(msg.split("~")[1]);
							}else if(msg.contains("doingrequesttrue")) {
								Platform.runLater(() ->{
									System.out.println("listview "+msg);
									String[] data = msg.split("~");
									if(msg.contains("-")) {
										displayMoreItem(data,"");
									}else {
										displayOneItem(data,"");
									}
								});
								
							}else if(msg.contains("doingrequestfalse")) {
								System.out.println("listview No present");
							}else if(msg.contains("confirmrequestupdatetrue")) {
								Platform.runLater(() -> {
									int requestcount = Integer.valueOf(user.getBtfriend().getText());
									if(requestcount > 0) {
										user.getBtfriend().setText(String.valueOf(requestcount-1));
									}
									for(int i = 0; i<hbox.size(); i++) {
										
										if(boxh == hbox.get(i)) {
											System.out.println(hbox.get(i));
											user.getMessageBox().getChildren().remove(i);
											hbox.remove(i);
										}
										
									}
									try {
										displayUserContact(username,userphone);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								});	
							}else if(msg.contains("confirmrequestupdatefalse")) {
								System.out.println("update not possible");
							}else if(msg.contains("friendrequesttrue")) {
								Platform.runLater(() -> {
									String[] data = msg.split("~");
									if(data[1].contains("-")) {
										displayMoreItem(data,"confirm");
									}else {
										displayOneItem(data,"confirm");
									}
								});
								
							}else if(msg.contains("isonlineandinmycontacttrue") && userSize > 1) {
								Platform.runLater(() -> {
									try {
										displayUserContact(username,userphone);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								});
							}else if(msg.contains("isonlineandinmycontactfalse") && userSize < 2) {
								Platform.runLater(() -> {
									try {
										displayUserContact(username,userphone);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								});
							}else if(msg.contains("sendingmsgtrue")) {
								System.out.println("sending true");
							}else if(msg.contains("sendingmsgfalse")) {
								System.out.println("sending false");
							}else if(msg.contains("unamealreadypresent")) {
								System.out.println("username is already used");
								Platform.runLater(() ->{
									sign.getErrName().setId("errlab");
									sign.getErrName().setText("The username is already used");
									uValid = false;
								});
							}else if(msg.contains("unameisfree")) {
								System.out.println("username is free");
								Platform.runLater(() ->{
									sign.getErrName().setId("succ");
									sign.getErrName().setText("The username is free");
									uValid = true;
								});
								
							}else if(msg.contains("fromchatonlineuser")) {
								String[] data = msg.split("~");
								String[] inData = data[0].split(",");
								//user.getMessageBox().getChildren().clear();
								Platform.runLater(() ->{
									HBox space = new HBox();
									space.setMaxWidth(185);
									StackPane chatBox = user.messageChatDecor(inData[1], inData[0], inData[5]);
									chatBox.setAlignment(Pos.CENTER_RIGHT);
									user.getMessageBox().getChildren().add(chatBox);
								});
							}
							
							/*Platform.runLater(() -> {
							try {
								String msg = onReceivMessage(dis);
								if(msg.contains("broacasttrue")) {
									String[] data  = msg.split("~");
									if(data[1].contains("-")) {
										String[] inData = data[1].split("-");
										for(int i =0; i<inData.length;i++) {
											String[] tmpData = inData[i].split(",");
											int actuelleUserSize = Integer.valueOf(tmpData[3]);
											System.out.println("actuelleUserSize "+actuelleUserSize+" previewActiveUserSize "+previewActiveUserSize);
											if(previewActiveUserSize < actuelleUserSize) {
												//Platform.runLater(()-> {
													try {
														displayUserContact(username,userphone);
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													System.out.println("test 111");
													previewActiveUserSize = actuelleUserSize;
												//});	
											}else {
												System.out.println("the user is offline");
											}
										}
									}else {
										String inData[] = data[1].split(",");
										
										int actuelleUserSize = Integer.valueOf(inData[3]);
										if(previewActiveUserSize < actuelleUserSize) {
											
											//Platform.runLater(()-> {
												try {
													displayUserContact(username,userphone);
													previewActiveUserSize = actuelleUserSize;
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											//});
										
										}else if(previewActiveUserSize == actuelleUserSize){ // a user is deconnected
											
										}else {
											System.out.println("not online");
										}
									}
									System.out.println("class test "+msg);
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							});*/
							//if(msg.contains("broacasttrue")) {
								
							//}
							/*if(msg.contains("broacasttrue")) {
								String[] data  = msg.split("~");
								if(data[1].contains("-")) {
									String[] inData = data[1].split("-");
									for(int i =0; i<inData.length;i++) {
										String[] tmpData = inData[i].split(",");
										int actuelleUserSize = Integer.valueOf(tmpData[3]);
										System.out.println("actuelleUserSize "+actuelleUserSize+" previewActiveUserSize "+previewActiveUserSize);
										if(previewActiveUserSize < actuelleUserSize) {
											//Platform.runLater(()-> {
												try {
													displayUserContact(username,userphone);
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												System.out.println("test 111");
												previewActiveUserSize = actuelleUserSize;
											//});	
										}else {
											System.out.println("the user is offline");
										}
									}
								}else {
									String inData[] = data[1].split(",");
									
									int actuelleUserSize = Integer.valueOf(inData[3]);
									if(previewActiveUserSize < actuelleUserSize) {
										
										//Platform.runLater(()-> {
											try {
												displayUserContact(username,userphone);
												previewActiveUserSize = actuelleUserSize;
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										//});
									
									}else if(previewActiveUserSize == actuelleUserSize){ // a user is deconnected
										
									}else {
										System.out.println("not online");
									}
								}
								System.out.println("class test "+msg);
							}*/
							//});
						}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("class test");
		while(isBroacast) {
			//System.out.println("class test1");
				//if(isLogged) {
					String msg;
					try {
						//onMessage(dos,"data~broacast");
						
						msg = onReceivMessage(dis);
						System.out.println("msg");
						if(msg.contains("broacasttrue")) {
							String[] data  = msg.split("~");
							if(data[1].contains("-")) {
								String[] inData = data[1].split("-");
								for(int i =0; i<inData.length;i++) {
									String[] tmpData = inData[i].split(",");
									int actuelleUserSize = Integer.valueOf(tmpData[3]);
									System.out.println("actuelleUserSize "+actuelleUserSize+" previewActiveUserSize "+previewActiveUserSize);
									if(previewActiveUserSize < actuelleUserSize) {
										Platform.runLater(()-> {
											try {
												displayUserContact(username,userphone);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											System.out.println("test 111");
											previewActiveUserSize = actuelleUserSize;
										});	
									}else {
										System.out.println("the user is offline");
									}
								}
							}else {
								String inData[] = data[1].split(",");
								
								int actuelleUserSize = Integer.valueOf(inData[3]);
								if(previewActiveUserSize < actuelleUserSize) {
									
									Platform.runLater(()-> {
										try {
											displayUserContact(username,userphone);
											previewActiveUserSize = actuelleUserSize;
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									});
								
								}else if(previewActiveUserSize == actuelleUserSize){ // a user is deconnected
									
								}else {
									System.out.println("not online");
								}
							}
							System.out.println("class test "+msg);
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
				//}
				
			if(!isBroacast) {break;}
		}
	}

}

