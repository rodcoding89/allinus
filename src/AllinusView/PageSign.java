package AllinusView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import AllinusControler.PageControler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageSign {
	private GridPane signPane,fotoPane,boxPane;
	private Button next,signbt,btproblem,valide,profilFoto,msgbtn;
	private Label dpLab,nameLab,phone,bornDate,email,passLab,
	confirmPassLab,ftLab,connect,infoL,oldLab,errLab,succLab,errName,errMail,errPassword,errPhone,errPassWordConfig,errBornDate;
	private TextField dpLabTF,nameTF,lastnameTF,emailTF,phoneTF,bornDateTF,ftTF;
	private PasswordField passPF,confirmPF;
	private HBox titelBox,fotoBox,btBox,gridRBox,gridLBox,infoBox,textBox,signBox,checkBox,telBox;
	private VBox DateBox;
	private Text logoText,infoText,textInfo;
	private Scene scene,scene1;
	private Image logimg;
	private ImageView imgshow;
	private StackPane overPane;
	private CheckBox Mcheck;
	private CheckBox Wcheck;
	private Group group,groupOver;
	private ProgressIndicator loading;
	private ListView listView = null;
	private Stage stage;
	private Button preview;
	private Button validSign;
	private PageLogin login;
	private BorderPane root;
	private StackPane parent;
	private List<TextField> listTF = new ArrayList<>();
	
	public PageSign(Stage s) {
		this.stage = s;
	}
	public Scene initialise() {
		listView = new ListView();
		group = new Group();
		groupOver = new Group();
		overPane = new StackPane();
		
		overPane.setPrefHeight(600);
		overPane.setPrefWidth(450);
		overPane.setId("overpane");
		
		parent = new StackPane();
		root = new BorderPane();
		
		errName = new Label();
		errName.setId("errlab");
		
		succLab = new Label();
		succLab.setId("suclab");
		
		/*VBox ftBox = new VBox(10);
		ftBox.setPadding(new Insets(0,0,25,0));
		ftLab = new Label("Fonction*");
		ftBox.getChildren().add(ftLab);
		
		ftTF = new TextField();
		ftTF.setId("input");
		ftTF.setPromptText("Web Developer");
		ftTF.setPrefHeight(40);
		ftBox.getChildren().add(ftTF);
		ftBox.setPrefWidth(195);*/
		
		/*VBox dpBox = new VBox(10);
		dpBox.setPadding(new Insets(0,0,25,0));
		dpLab = new Label("Department*");
		dpBox.getChildren().add(dpLab);
		
		dpLabTF = new PasswordField();
		dpLabTF.setId("input");
		dpLabTF.setPromptText("Developer");
		dpLabTF.setPrefHeight(40);
		dpBox.getChildren().add(dpLabTF);
		
		dpBox.setPrefWidth(195);*/
		
		VBox signfield2 = new VBox(10);
		signfield2.setPadding(new Insets(0,0,25,0));
		email = new Label("Email Adresse*");
		signfield2.getChildren().add(email);
		
		
		emailTF = new TextField();
		emailTF.setId("input");
		emailTF.setPromptText("test@mail.com");
		emailTF.setPrefHeight(40);
		listTF.add(emailTF);
		emailTF.setOnKeyPressed(e -> PageControler.handleInputEmail());
		
		errMail = new Label();
		errMail.setId("errlab");
		signfield2.getChildren().addAll(emailTF,errMail);
		signfield2.setPrefWidth(195);
		
		
		VBox signfield3 = new VBox(10);
		signfield3.setPadding(new Insets(0,0,25,0));
		bornDate = new Label("Born Date*");
		signfield3.getChildren().add(bornDate);
		
		bornDateTF = new TextField();
		bornDateTF.setId("input");
		bornDateTF.setPromptText("1988-12-02");
		bornDateTF.setPrefHeight(40);
		listTF.add(bornDateTF);
		bornDateTF.setOnKeyPressed(e -> PageControler.handleInputBornDate());
		
		errBornDate = new Label();
		errBornDate.setId("errlab");
		signfield3.getChildren().addAll(bornDateTF,errBornDate);
		signfield3.setPrefWidth(195);
		
		VBox signfield = new VBox(10);
		signfield.setPadding(new Insets(0,0,25,0));
		nameLab = new Label("Username*");
		signfield.getChildren().add(nameLab);
		
		nameTF = new TextField();
		nameTF.setId("input");
		nameTF.setPromptText("Dub");
		nameTF.setPrefHeight(40);
		signfield.getChildren().addAll(nameTF,errName);
		signfield.setPrefWidth(195);
		listTF.add(nameTF);
		nameTF.setOnKeyPressed(e -> {
			try {
				PageControler.handleInputUser();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		
		VBox signfield1 = new VBox(10);
		signfield1.setPadding(new Insets(0,0,25,0));
		
		errPhone = new Label();
		errPhone.setId("errlab");
		
		phone = new Label("Phone Number*");
		signfield1.getChildren().add(phone);
		
		phoneTF = new TextField();
		phoneTF.setId("input");
		phoneTF.setPromptText("076-523895");
		phoneTF.setPrefHeight(40);
		listTF.add(phoneTF);
		phoneTF.setOnKeyPressed(e -> PageControler.handleInputPhone());
		
		signfield1.getChildren().addAll(phoneTF,errPhone);
		signfield1.setPrefWidth(195);
		
		
		
		VBox passBox = new VBox(10);
		passBox.setPadding(new Insets(0,0,25,0));
		passLab = new Label("Password*");
		passBox.getChildren().add(passLab);
		
		passPF = new PasswordField();
		passPF.setId("input");
		passPF.setPromptText("Enter your password");
		passPF.setPrefHeight(40);
		listTF.add(passPF);
		passPF.setOnKeyPressed(e -> PageControler.handleInputPass());
		
		errPassword = new Label();
		errPassword.setId("errlab");
		
		passBox.getChildren().addAll(passPF,errPassword);
		
		passBox.setPrefWidth(195);
		
		VBox confirmBox = new VBox(10);
		confirmBox.setPadding(new Insets(0,0,25,0));
		confirmPassLab = new Label("Password Confirmation*");
		confirmBox.getChildren().add(confirmPassLab);
		
		confirmPF = new PasswordField();
		confirmPF.setId("input");
		confirmPF.setPromptText("Enter your password");
		confirmPF.setPrefHeight(40);
		listTF.add(confirmPF);
		confirmPF.setOnKeyPressed(e -> PageControler.handleInputConfirmPass());
		
		errPassWordConfig = new Label();
		errPassWordConfig.setId("errlab");
		
		confirmBox.getChildren().addAll(confirmPF,errPassWordConfig);
		confirmBox.setPrefWidth(195);
		

		final URL imgurl = getClass().getResource("images/allinuslog.png");
		
		Image img = new Image(imgurl.toString());
		ImageView imgv = new ImageView(img);
		VBox imgBox = new VBox();
		imgBox.getChildren().add(imgv);
		imgBox.setAlignment(Pos.TOP_LEFT);
		imgBox.setPadding(new Insets(50,0,0,20));
		
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
		socialBox.setPadding(new Insets(5,20,0,0));
		
		
		final URL imgback = getClass().getResource("images/back_black.png");
		
		Image imgbk = new Image(imgback.toString());
		ImageView imgvbk = new ImageView(imgbk);
		Button btbk = new Button("",imgvbk);
		btbk.setOnAction(e -> {
			try {
				PageControler.backOnLogin();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btbk.setId("btbk");
		VBox imgbkBox = new VBox();
		imgbkBox.getChildren().add(btbk);
		imgbkBox.setAlignment(Pos.TOP_LEFT);
		imgbkBox.setPadding(new Insets(-5,0,0,0));
		
		GridPane gridBoxSign = new GridPane();
		//gridRBox.setAlignment(Pos.CENTER_LEFT)
		//gridBoxSign.add(imgBox, 0, 0);
		//gridBoxSign.add(ftBox, 0, 0);
		//gridBoxSign.add(dpBox, 1, 0);
		gridBoxSign.add(signfield, 0, 0);
		gridBoxSign.add(signfield3, 1, 0);
		
		gridBoxSign.add(signfield1, 0, 1);
		gridBoxSign.add(signfield2, 1, 1);
		gridBoxSign.add(passBox, 0, 2);
		gridBoxSign.add(confirmBox, 1, 2);
		
		gridBoxSign.setPrefWidth(400);
		gridBoxSign.setPrefHeight(350);
		gridBoxSign.setHgap(10);
		gridBoxSign.setVgap(-20);
		//gridBoxLogin.setPadding(new Insets(0,20,20,0));
		gridBoxSign.setAlignment(Pos.CENTER);
		
		VBox signContentBox = new VBox();
		
		validSign = new Button("SAVE");
		validSign.setOnAction(e -> PageControler.HandlerSignData());
		validSign.setPrefWidth(400);
		//validSign.setPadding(new Insets(0,0,0,0));
		VBox signBox = new VBox(40);
		//validSign.setAlignment(Pos.BOTTOM_CENTER);
		signBox.getChildren().addAll(validSign);
		signBox.setAlignment(Pos.BOTTOM_CENTER);
		//btnext.getChildren().add(next);
		signBox.setPadding(new Insets(0,0,30,0));
		//signBox.setPrefHeight(50);
		//signBox.setPrefWidth(400);
		
		StackPane socialBackBox = new StackPane();
		socialBackBox.setPrefWidth(400);
		socialBackBox.getChildren().addAll(socialBox,imgbkBox);
		
		VBox stpBox = new VBox();
		stpBox.getChildren().addAll(socialBackBox,imgBox);
		stpBox.setPrefWidth(400);
		
		signContentBox.getChildren().add(gridBoxSign);
		signContentBox.setPrefWidth(400);
		signContentBox.setPadding(new Insets(30,0,0,0));
		//root.setTop(titelBox);
		root.setTop(stpBox);
		root.setCenter(signContentBox);
		root.setBottom(signBox);
		//root.setId("rootsign");
		//root.setPrefHeight(600);
		
		
		
		parent.getChildren().addAll(root);
		//parent.setPrefWidth(400);
		//root.setMargin(gridBoxSign, new Insets(30,0,0,0));
		
		
		VBox signContent = new VBox();
		//signContent.getChildren().addAll(imgBox,gridBoxSign,validSign);
		
		
		//signContent.getChildren().add(grid);
		/*loading = new ProgressIndicator();
		loading.setLayoutX(300);
		loading.setLayoutY(100);
		loading.setId("loading");
		loading.setMinWidth(55);
		loading.setMinHeight(55);*/
		//root.setPrefHeight(650);
		//root.setPrefWidth(600);
		
		//groupOver.getChildren().add(signContent);
		//parent.getChildren().add(groupOver);
		scene = new Scene(parent,450,600);
		//scene.getStylesheets().add("Style/styles.css");
		scene.getStylesheets().add(PageSign.class.getResource("myStyle.css").toString());
		return scene;
	}
	public void show(Stage primaryStage,Scene scene){
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Sign Page");
		primaryStage.show();
	}
	
	public Button getMsgbtn() {
		return msgbtn;
	}
	
	public Label getErrName() {
		return errName;
	}
	public void setErrName(Label errName) {
		this.errName = errName;
	}
	public Label getErrMail() {
		return errMail;
	}
	public TextField getDpLabTF() {
		return dpLabTF;
	}
	public void setDpLabTF(TextField dpLabTF) {
		this.dpLabTF = dpLabTF;
	}
	public TextField getNameTF() {
		return nameTF;
	}
	public void setNameTF(TextField nameTF) {
		this.nameTF = nameTF;
	}
	public TextField getLastnameTF() {
		return lastnameTF;
	}
	public void setLastnameTF(TextField lastnameTF) {
		this.lastnameTF = lastnameTF;
	}
	public TextField getEmailTF() {
		return emailTF;
	}
	public void setEmailTF(TextField emailTF) {
		this.emailTF = emailTF;
	}
	public TextField getPhoneTF() {
		return phoneTF;
	}
	public void setPhoneTF(TextField phoneTF) {
		this.phoneTF = phoneTF;
	}
	public TextField getbornDateTF() {
		return bornDateTF;
	}
	public void setbornDateTF(TextField bornDateTF) {
		this.bornDateTF = bornDateTF;
	}
	public PasswordField getPassPF() {
		return passPF;
	}
	public void setPassPF(PasswordField passPF) {
		this.passPF = passPF;
	}
	public PasswordField getConfirmPF() {
		return confirmPF;
	}
	public void setConfirmPF(PasswordField confirmPF) {
		this.confirmPF = confirmPF;
	}
	public void setErrMail(Label errMail) {
		this.errMail = errMail;
	}
	public Label getErrPassword() {
		return errPassword;
	}
	public void setErrPassword(Label errPassword) {
		this.errPassword = errPassword;
	}
	public Label getErrPhone() {
		return errPhone;
	}
	public void setErrPhone(Label errPhone) {
		this.errPhone = errPhone;
	}
	public Label getErrPassWordConfig() {
		return errPassWordConfig;
	}
	public void setErrPassWordConfig(Label errPassWordConfig) {
		this.errPassWordConfig = errPassWordConfig;
	}
	public Label getErrBornDate() {
		return errBornDate;
	}
	public void setErrBornDate(Label errBornDate) {
		this.errBornDate = errBornDate;
	}
	
	public void signAnimation() {
		
	}

	public Button getNext() {
		return next;
	}
	public Button getBtproblem() {
		return btproblem;
	}
	public Button getValide() {
		return valide;
	}
	public Button getSignbt() {
		return signbt;
	}
	public Button getPreview() {
		return preview;
	}
	public Button getValidSign() {
		return validSign;
	}

	public BorderPane getRoot() {
		return root;
	}
	public void setRoot(BorderPane root) {
		this.root = root;
	}
	public StackPane getOverPane() {
		return overPane;
	}
	public void setOverPane(StackPane overPane) {
		this.overPane = overPane;
	}
	public StackPane getParent() {
		return parent;
	}
	public void setParent(StackPane parent) {
		this.parent = parent;
	}
	public Label getErrLab() {
		return errLab;
	}
	public void setErrLab(Label errLab) {
		this.errLab = errLab;
	}
	public Label getSuccLab() {
		return succLab;
	}
	public void setSuccLab(Label succLab) {
		this.succLab = succLab;
	}
	public List<TextField> getListTF() {
		return listTF;
	}
	public void setListTF(List<TextField> listTF) {
		this.listTF = listTF;
	}
}
