package AllinusView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;


import AllinusControler.PageControler;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PageLogin {
	
	private GridPane loginPane,loginPane1,loginPane2,gridBoxLogin;
	private Button logbt,signbt,btproblem;
	private Label userLab,passLab,oder,connect,spaceLabel,infoL;
	private TextField userTF;
	private PasswordField passPF;
	private HBox titelBox,fotoBox,btBox,infoBox,textBox,btTitelBox;
	VBox gridRBox;
	private VBox gridLBox,loginContent;
	private Text logoText,infoText;
	private Scene scene;
	private Image logimg;
	private ImageView imgshow;
	private StackPane overPane,centerContent;
	private ProgressIndicator loading;
	private Group group,groupOver;
	private PageSign sign;
	private Stage stage;
	private PageControler pagecontroler;
	private StackPane parent;
	private Label errLabEmail,errLabPass;
	private VBox loginfield1,loginfield3;
	
	public PageLogin(Stage s) throws UnknownHostException, SocketException {
		this.stage = s;
	}
	public Scene initialisation() throws FileNotFoundException {
		
		overPane = new StackPane();
		overPane.setPrefHeight(600);
		overPane.setPrefWidth(450);
		overPane.setId("overpane");
		errLabEmail = new Label();
		errLabEmail.setId("errlab");
		errLabPass = new Label();
		errLabPass.setId("errlab");
		
		parent = new StackPane();
		
		centerContent = new StackPane();
		BorderPane root = new BorderPane();
        //root.setPadding(new Insets(10,50,50,50));
        
        //inizializing gridpane
		loginfield1 = new VBox(10);
		
		
		//inizializing username and added
		userLab = new Label("Email");
		//loginPane.add(userLab, 0, 0);
		loginfield1.getChildren().add(userLab);
		//inizializing userField and added
		userTF = new TextField();
		userTF.setId("input");
		userTF.setPromptText("Enter your Email");
		userTF.setPrefHeight(40);
		userTF.setOnKeyPressed(e -> PageControler.inputEmailKeypressed());
		//userTF.setPrefWidth(250);
		//loginPane.add(userTF, 0, 1);
		loginfield1.getChildren().add(userTF);
		loginfield1.setPrefWidth(250);
		loginfield1.getChildren().add(errLabEmail);
		//inizializing password and added
		loginfield3 = new VBox(10);
	
		
		passLab = new Label("Password");
		//loginPane2.add(passLab, 0, 0);
		loginfield3.getChildren().add(passLab);
		loginfield3.setPrefWidth(250);
		
		passPF = new PasswordField();
		passPF.setId("input");
		passPF.setPromptText("Enter your Password");
		//passPF.setPrefWidth(250);
		passPF.setPrefHeight(40);
		passPF.setOnKeyPressed(e -> PageControler.inputPassKeypressed());
		final Tooltip tooltip = new Tooltip();
		tooltip.setText(
		    "Your password must be\n" +
		    "at least 6 characters in length\n"
		    + "you can use a-z,A-Z,1-9,%,$,@,#"
		);
		passPF.setTooltip(tooltip);
		//loginPane2.add(passPF, 0, 1);
		loginfield3.getChildren().add(passPF);
		loginfield3.getChildren().add(errLabPass);
		
		//inizializing button and added
		loginPane1 = new GridPane();
		
		
		
		Image imgIconLogin = new Image(getClass().getResource("images/login_white_big.png").toString(),22,22,false,false);
		ImageView imv = new ImageView(imgIconLogin);
		
		logbt = new Button("Login",imv);
		logbt.setContentDisplay(ContentDisplay.RIGHT);
		logbt.setOnAction(e -> {
			try {
				PageControler.HandlerLogin();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		logbt.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				try {
					PageControler.HandlerLogin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		VBox loginfield2 = new VBox();
		loginfield2.getChildren().add(logbt);
		loginfield2.setMargin(logbt, new Insets(10,0,0,20));
		
		Label textsign = new Label("You don't have a account? ");
		textsign.setTextAlignment(TextAlignment.LEFT);
		textsign.setStyle("-fx-text-fill:maroon");
		
		VBox btBox = new VBox();
		signbt = new Button("Sign up");
		signbt.setId("signbt");
		signbt.setOnAction(e -> {PageControler.HandlerSign();});
		
		//signbt.setAlignment(Pos.CENTER_RIGHT);
		btBox.getChildren().add(signbt);
		btBox.setPadding(new Insets(-11,0,0,-10));
		//loginPane.add(btBox, 0, 5);
		
		infoL = new Label("Probleme with Login?");
		infoL.setId("info");
		infoL.setStyle("-fx-text-fill:maroon");
		
		
		VBox helpBt = new VBox();
		btproblem = new Button("Help!");
		btproblem.setOnAction(e -> PageControler.HandlerForgotLoginData());
		helpBt.getChildren().addAll(btproblem);
		btproblem.setId("helpbt");
		helpBt.setPadding(new Insets(0));
		//loginPane.add(btTitelBox, 0, 7);
		
		
		//root.setTop(titelBox);
		
		//HBox tbox = new HBox();
		//tbox.setId("tbox");
		//title.setText("AllInUs Login Data");
		//tbox.setPadding(new Insets(30,30,0,30));
		//tbox.setPrefSize(135, 45);
		//tbox.setAlignment(Pos.TOP_CENTER);
		
		gridBoxLogin = new GridPane();
		
		VBox vbox = new VBox();
		
		//gridRBox.setAlignment(Pos.CENTER_LEFT)
		//gridBoxLogin.add(tbox, 0, 0);
		gridBoxLogin.add(loginfield1, 0, 0);
		gridBoxLogin.add(loginfield2, 2, 1);
		gridBoxLogin.add(loginfield3, 0, 2);
		gridBoxLogin.setPrefWidth(400);
		gridBoxLogin.setPrefHeight(300);
		vbox.setPadding(new Insets(40,0,0,0));
		gridBoxLogin.setAlignment(Pos.BOTTOM_CENTER);
		vbox.getChildren().add(gridBoxLogin);
		btBox.setAlignment(Pos.CENTER_LEFT);
		gridBoxLogin.setVgap(-10);
		
		HBox grid1 = new HBox();
		grid1.getChildren().addAll(textsign,btBox);
		
		grid1.setPadding(new Insets(25,0,10,30));
		
		infoL.setTextAlignment(TextAlignment.LEFT);
		helpBt.setAlignment(Pos.CENTER_LEFT);
		
		VBox grid2 = new VBox();
		grid2.getChildren().addAll(infoL,helpBt);
		
		helpBt.setPadding(new Insets(5,0,0,-20));
		grid2.setPadding(new Insets(25,0,10,30));
		
		VBox box = new VBox();
		box.getChildren().addAll(grid1,grid2);
		
		
		final URL imgurl = getClass().getResource("images/allinuslog.png");
		
		Image img = new Image(imgurl.toString());
		ImageView imgv = new ImageView(img);
		VBox imgBox = new VBox(20);
		imgBox.getChildren().add(imgv);
		imgBox.setAlignment(Pos.TOP_LEFT);
		imgBox.setPadding(new Insets(20,0,0,30));
		
		HBox socialBox = new HBox();
		Label lb = new Label("Folow us");
		lb.setId("lb");
		lb.setPadding(new Insets(7,5,0,0));
		socialBox.getChildren().add(lb);
		
		final URL furl = getClass().getResource("images/facebook.png");
		
		Image fimg = new Image(furl.toString());
		ImageView fimgv = new ImageView(fimg);
		Button fbt = new Button("",fimgv);
		fbt.setId("sbt");
		socialBox.getChildren().add(fbt);
		
		final URL turl = getClass().getResource("images/twitter.png");
		
		Image timg = new Image(turl.toString());
		ImageView timgv = new ImageView(timg);
		Button tbt = new Button("",timgv);
		tbt.setId("sbt");
		socialBox.getChildren().add(tbt);
		
		final URL yurl = getClass().getResource("images/youtube.png");
		
		Image yimg = new Image(yurl.toString());
		ImageView yimgv = new ImageView(yimg);
		Button ybt = new Button("",yimgv);
		ybt.setId("sbt");
		socialBox.getChildren().add(ybt);
		
		socialBox.setAlignment(Pos.TOP_RIGHT);
		
		socialBox.setPadding(new Insets(-45,20,0,0));
		
		centerContent = new StackPane();
		centerContent.getChildren().addAll(imgBox,socialBox,vbox);
		centerContent.setAlignment(Pos.CENTER);
		centerContent.setPrefWidth(400);
		//centerContent.setTop(title);
		//centerContent.setId("centerpane");
		//centerContent.setMargin(gridBoxLogin, new Insets(30));
		//parent.getChildren().addAll(centerContent);
		
		
		loginContent = new VBox();
		
		loginContent.setAlignment(Pos.CENTER);
		loginContent.getChildren().add(centerContent);
		loginContent.getChildren().add(box);
		
		loginContent.setPrefWidth(400);
		//groupOver.getChildren().add(loginContent);
		parent.setAlignment(Pos.CENTER);
		parent.getChildren().addAll(loginContent);
		parent.setPrefWidth(450);
		scene = new Scene(parent,450,600);
		scene.getStylesheets().add(PageLogin.class.getResource("myStyle.css").toString());
		//scene.getStylesheets().add(getClass().getResource("style/styles.css").toExternalForm());
		
		return scene;
	}
	public Button getLogbt() {
		return logbt;
	}
	public void setLogbt(Button logbt) {
		this.logbt = logbt;
	}
	public Button getSignbt() {
		return signbt;
	}
	public void setSignbt(Button signbt) {
		this.signbt = signbt;
	}
	public Button getBtproblem() {
		return btproblem;
	}
	public void setBtproblem(Button btproblem) {
		this.btproblem = btproblem;
	}
	public TextField getUserTF() {
		return userTF;
	}
	public void setUserTF(TextField userTF) {
		this.userTF = userTF;
	}
	public PasswordField getPassPF() {
		return passPF;
	}
	public void setPassPF(PasswordField passPF) {
		this.passPF = passPF;
	}
	public void show(Stage primaryStage,Scene scene){
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login Page");
		primaryStage.show();
	}
	
	
	public StackPane getParent() {
		return parent;
	}
	public void setParent(StackPane parent) {
		this.parent = parent;
	}
	public StackPane getOverPane() {
		return overPane;
	}
	public void setOverPane(StackPane overPane) {
		this.overPane = overPane;
	}
	public VBox getLoginfield1() {
		return loginfield1;
	}
	public void setLoginfield1(VBox loginfield1) {
		this.loginfield1 = loginfield1;
	}
	public VBox getLoginfield3() {
		return loginfield3;
	}
	public void setLoginfield3(VBox loginfield3) {
		this.loginfield3 = loginfield3;
	}
	public Label getErrLabEmail() {
		return errLabEmail;
	}
	public void setErrLabEmail(Label errLabEmail) {
		this.errLabEmail = errLabEmail;
	}
	public Label getErrLabPass() {
		return errLabPass;
	}
	public void setErrLabPass(Label errLabPass) {
		this.errLabPass = errLabPass;
	}
}
