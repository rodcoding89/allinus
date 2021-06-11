package AllinusView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import AllinusControler.PageControler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PageUser {
	
	private Button btMsg,btUsername,btout,btfriend,btmsgchat;
	private Text logoText,msgText;
	private Circle cercle,onCercle;
	private TextArea textarea,messagearea;
	private TextField inputSearch;
	private VBox rigthContent,Boxarea,msgBox,headerBox,messageBox,UserBox,
	receivBox,senderBox,onBox;
	private Group OnImgBox,groupStatus,gpMsg;
	private HBox fotoBox,Boxmsg,contentBox,outBox,searchBox,leftContent,addFBox;
	private GridPane gridBox,ContentHeader,leftGrid,gridMsg;
	private Scene scene;
	private Button send,addCon,userbt;
	private int userPageId;
	private ScrollPane sp;
	private Stage stage;
	private VBox botomBox;
	private HBox toRight,panel;
	private Boolean isStyle;
	private Label user,lbp,lb,mail;
	private Label conv;
	private String gname = "";
	private int boxIndex;
	private StackPane infoContent,overPane,panelBox;
	private Map<Boolean,VBox> vlist = new HashMap<>();
	
	
	public PageUser(Stage s) {
		this.stage = s;
	}
	public Scene initialise() {
		
		overPane = new StackPane();
		overPane.setPrefHeight(600);
		overPane.setPrefWidth(800);
		overPane.setId("overpane");
		
		Image onprofil = new Image(getClass().getResource("images/identity_black_big.png").toString());
		cercle = new Circle();
		cercle.setCenterX(10.0f);
		cercle.setCenterY(10.0f);
		cercle.setRadius(14.0f);
		cercle.setFill(new ImagePattern(onprofil));
		cercle.setStroke(Color.GRAY);
		onCercle = new Circle();
		onCercle.setCenterX(22.0f);
		onCercle.setCenterY(17.0f);
		onCercle.setRadius(4.0f);
		
		onCercle.setFill(Color.web("#7bba80"));
		OnImgBox = new Group();
		OnImgBox.getChildren().addAll(cercle,onCercle);
		VBox circleBox = new VBox();
		circleBox.getChildren().add(OnImgBox);
		circleBox.setPadding(new Insets(3,0,5,0));
		
		
		HBox onBox = new HBox();
		user = new Label();
		Label isonline = new Label("Is now Online");
		isonline.setId("isonline");
		isonline.setPadding(new Insets(3,0,10,10));
		user.setPadding(new Insets(5,0,3,8));
		user.setId("user");
		VBox uBox = new VBox();
		uBox.getChildren().addAll(user,isonline);
		//uBox.setAlignment(Pos.TOP_LEFT);
		uBox.setPadding(new Insets(0,0,0,0));
		onBox.getChildren().addAll(circleBox,uBox);
		onBox.setPadding(new Insets(10));
		onBox.setAlignment(Pos.TOP_LEFT);
		onBox.setPrefWidth(180);
		onBox.setPrefHeight(120);
		onBox.setId("circlebox");
		
		
		
		rigthContent = new VBox();
		
		HBox headerBox = new HBox();
		
		inputSearch = new TextField();
		inputSearch.setId("inputSearch");
		//inputSearch.setPrefColumnCount(14);
		inputSearch.setPrefHeight(40);
		inputSearch.setPromptText("Find your friend throw his Phone Number or his Name");
		inputSearch.setPrefWidth(455);
		inputSearch.setOnAction(e -> {
			try {
				PageControler.handleSearch();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		searchBox = new HBox();
		
		searchBox.getChildren().add(inputSearch);
		searchBox.setPrefHeight(80);
		searchBox.setPadding(new Insets(0,20,0,20));
		searchBox.setId("searchbox");
		searchBox.setPrefWidth(455);
		
		//headerBox.getChildren().add(searchBox);
		
		btout = new Button("Logout");
		btout.setId("btout");
		
		outBox = new HBox();
		outBox.setAlignment(Pos.TOP_RIGHT);
		outBox.setPadding(new Insets(20,20,20,80));
		outBox.getChildren().add(btout);
		
		Image impch = new Image(getClass().getResource("images/chat_black_small.png").toString());
		Button btch = new Button("",new ImageView(impch));
		
		btch.setId("btf");
		btch.setPadding(new Insets(0));
		btmsgchat = new Button("0");
		btmsgchat.setLayoutY(11);
		btmsgchat.setLayoutX(17);
		btmsgchat.setId("btfd");
		Circle c = new Circle();
		c.setRadius(12.0f);
		c.setFill(Color.BLACK);
		c.setCenterX(28);
		c.setCenterY(19);
		Group g = new Group();
		g.getChildren().addAll(c,btmsgchat);
		Group grp = new Group();
		grp.getChildren().addAll(btch,g);
		
		btch.setOnAction(e -> PageControler.handleOffMessage());
		
		Image imsch = new Image(getClass().getResource("images/search_black_small.png").toString());
		Button btsch = new Button("",new ImageView(imsch));
		btsch.setId("btf");
		btsch.setPadding(new Insets(0));
		
		/*btsch.setOnAction( new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(searchBox.getChildren().contains(inputSearch)) {
					searchBox.getChildren().remove(inputSearch);
				}else {
					searchBox.getChildren().add(inputSearch);
				}
			}
			
		});*/
		
		
		
		HBox btschBox = new HBox();
		btschBox.getChildren().addAll(grp);
		btschBox.setPadding(new Insets(0,0,0,0));
		btschBox.setPrefWidth(50);
		
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
		
		socialBox.setPadding(new Insets(3,20,0,0));
		socialBox.setPrefWidth(150);
		
		toRight = new HBox();
		toRight.setAlignment(Pos.TOP_LEFT);
		toRight.getChildren().addAll(btschBox);
		toRight.setPadding(new Insets(10));
		toRight.setPrefWidth(300);
		
		VBox headBox = new VBox();
		headBox.setPrefWidth(455);
		headBox.setPrefHeight(127);
		headBox.setId("headbox");
		
		headerBox.setPrefHeight(40);
		headerBox.setPrefWidth(455);
		headerBox.setAlignment(Pos.TOP_RIGHT);
		
		headerBox.setId("headerbox");
		headerBox.getChildren().addAll(toRight,socialBox);
		
		headBox.getChildren().addAll(headerBox,searchBox);
		
		messageBox = new VBox();
		messageBox.setPrefHeight(405);
		messageBox.setPrefWidth(455);
	
		
		messagearea = new TextArea();
		messagearea.setWrapText(true);
		messagearea.setPromptText("leave or write a Message");
		//messagearea.setPrefColumnCount(37);
		messagearea.setPrefHeight(70);
		messagearea.setId("messagearea");
		messagearea.setOnKeyPressed(e -> {
			try {
				PageControler.handleMessage(e);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		
		
		Boxarea = new VBox();
		Boxarea.setPadding(new Insets(10,5,10,0));
		Boxarea.getChildren().add(messagearea);
		Boxarea.setAlignment(Pos.BOTTOM_RIGHT);
		Boxarea.setPrefHeight(100);
		Boxarea.setPrefWidth(455);
		Boxarea.setId("boxarea");
		Group gparea = new Group();
		//gparea.getChildren().add(Boxarea);
		groupStatus = new Group();
		
		//Separator sepaH = new Separator();
		//sepaH.setOrientation(Orientation.HORIZONTAL);
		
		//sepaH.setLayoutX(2);
		//sepaH.setPrefWidth(550);
		ScrollPane spmsgarea = new ScrollPane();
		spmsgarea.setHbarPolicy(ScrollBarPolicy.NEVER);
		spmsgarea.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		//messageBox.setPrefHeight(405);
		messageBox.setPadding(new Insets(0,20,0,20));
		spmsgarea.setContent(messageBox);
		
		final URL aturl = getClass().getResource("images/attach_file_black_small.png");
		Image imgat = new Image(aturl.toString());
		Button btat = new Button("",new ImageView(imgat));
		btat.setId("btf");
		
		final URL kburl = getClass().getResource("images/keyboard_voice_black_small.png");
		Image imgkb = new Image(kburl.toString());
		Button btkb = new Button("",new ImageView(imgkb));
		btkb.setId("btf");
		
		final URL tgurl = getClass().getResource("images/tag_faces_black_small.png");
		Image imgtg = new Image(tgurl.toString());
		Button bttg = new Button("",new ImageView(imgtg));
		bttg.setId("btf");
		
		VBox areaBox = new VBox();
		areaBox.getChildren().addAll(btat,btkb,bttg);
		Popup popup = new Popup();
		popup.getContent().add(areaBox);
		//popup.show(stage);
		areaBox.setId("");
		areaBox.setPrefHeight(70);
		areaBox.setPrefWidth(40);
		
		GridPane grid = new GridPane();
		grid.add(btat, 0, 0);
		grid.add(btkb, 1, 0);
		grid.add(bttg, 2, 0);
		//grid.add(null, 1, 1);
		grid.setAlignment(Pos.BOTTOM_LEFT);
		grid.setPadding(new Insets(0,0,10,0));
		gparea.getChildren().add(grid);
		//grid.setLayoutX(353);
		//grid.setLayoutY(57);
		grid.setHgap(-10);
		grid.setId("grid");
		
		
		VBox centerCont = new VBox();
		centerCont.getChildren().addAll(headBox,spmsgarea,Boxarea);
		centerCont.setPrefWidth(455);
		
		/*infoContent = new StackPane();
		infoContent.setPrefWidth(110);
		infoContent.setPrefHeight(600);
		infoContent.getChildren().add(onBox);
		infoContent.setId("infocontent");*/
		
		conv = new Label();
		conv.setTextAlignment(TextAlignment.CENTER);
		conv.setAlignment(Pos.CENTER);
		conv.setId("conv");
		//infoContent.getChildren().add(conv);
		//infoContent.getChildren().add(grid);
		VBox emoBox = new VBox();
		emoBox.setPrefHeight(70);
		emoBox.setPrefWidth(40);
		emoBox.setAlignment(Pos.BOTTOM_CENTER);
		HBox centInfBox = new HBox();
		centInfBox.getChildren().addAll(centerCont);
		
		rigthContent.getChildren().add(centInfBox);
		rigthContent.setId("rightpart");
		leftContent = new HBox();
		
		//searchBox.setAlignment(Pos.CENTER);
		
		UserBox = new VBox();
		UserBox.setPadding(new Insets(5,0,0,0));
		UserBox.setPrefHeight(483);
		UserBox.setPrefWidth(205);
		
		/*for(int i = 0; i< database.length; i++) {
				UserBox.getChildren().addAll(onLineUser(database[i][0].toString(),"","",
						database[i][1].toString(),database[i][3].toString(),database[i][4].toString()));
		}*/
		
		UserBox.setId("userbox");
		
		Image imf = new Image(getClass().getResource("images/phone_black_small.png").toString());
		Button btf = new Button("",new ImageView(imf));
		btf.setId("btf");
		
		Image imv = new Image(getClass().getResource("images/videocam_black_small.png").toString());
		Button btv = new Button("",new ImageView(imv));
		btv.setId("btf");
		
		Image imsh = new Image(getClass().getResource("images/share_black_small.png").toString());
		Button btsh = new Button("",new ImageView(imsh));
		btsh.setId("btf");
		
		Image impw = new Image(getClass().getResource("images/power_black_small.png").toString());
		Button btpw = new Button("",new ImageView(impw));
		btfriend = new Button();
		btpw.setId("btf");
		btpw.setOnAction(e -> {
			try {
				PageControler.handleLogout();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		Image imgprofil = new Image(getClass().getResource("images/identity_black_small.png").toString());
		Button btu = new Button("",new ImageView(imgprofil));
		btu.setId("btf");
		btu.setOnAction(e -> {
			try {
				PageControler.handleDoingRequest();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		/*Circle cercle = new Circle();
		cercle.setCenterX(20.0f);
		cercle.setCenterY(20.0f);
		cercle.setRadius(7.0f);
		cercle.setStroke(Color.GRAY);
		cercle.setFill(new ImagePattern(imgprofil));
		cercle.setLayoutX(-10);
		cercle.setLayoutY(10);*/
		
		userbt = new Button("0");
		userbt.setLayoutY(11);
		userbt.setLayoutX(17);
		userbt.setId("btfd");
		Circle c2 = new Circle();
		c2.setRadius(12.0f);
		c2.setFill(Color.BLACK);
		c2.setCenterX(28);
		c2.setCenterY(19);
		Group g2 = new Group();
		g2.getChildren().addAll(c2,userbt);
		userbt.setOnAction(e -> {
			try {
				PageControler.handleDoingRequest();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Group g3 = new Group();
		g3.getChildren().addAll(btu,g2);
		
		Image imgp = new Image(getClass().getResource("images/group_add_black_small.png").toString());
		Button btgp = new Button("",new ImageView(imgp));
		btgp.setId("btf");
		btfriend = new Button("0");
		btfriend.setLayoutY(11);
		btfriend.setLayoutX(17);
		btfriend.setId("btfd");
		Circle c1 = new Circle();
		c1.setRadius(12.0f);
		c1.setFill(Color.BLACK);
		c1.setCenterX(28);
		c1.setCenterY(19);
		Group g1 = new Group();
		g1.getChildren().addAll(c1,btfriend);
		btfriend.setOnAction(e -> {
			try {
				PageControler.handleFriendRequest();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btgp.setOnAction(e -> {
			try {
				PageControler.handleFriendRequest();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Group gp = new Group();
		gp.getChildren().addAll(btgp,g1);
		
		VBox lfBox = new VBox();
		lfBox.setPrefHeight(600);
		lfBox.setPrefWidth(40);
		lfBox.getChildren().addAll(g3,btf,btv,btsh,gp,btpw);
		lfBox.setAlignment(Pos.CENTER_LEFT);
		lfBox.setId("lfbox");
		
		Label contact = new Label("Contacts");
		contact.setAlignment(Pos.TOP_LEFT);
		contact.setPadding(new Insets(0,0,10,10));
		contact.setStyle("-fx-text-fill:rgba(51,51,58,1);");
		
		Button menuB1 = new Button("Complet your Profil");
		menuB1.setPadding(new Insets(10));
		menuB1.setId("btmn");
		Button menuB2 = new Button("Change your status");
		menuB2.setPadding(new Insets(10));
		menuB2.setId("btmn");
		Button menuB3 = new Button("Parameter");
		menuB3.setPadding(new Insets(10));
		menuB3.setId("btmn");
		
		VBox mBox = new VBox();
		mBox.getChildren().addAll(menuB1,menuB2,menuB3);
		mBox.setId("mbox");
		mBox.setPadding(new Insets(10));
		mBox.setPrefWidth(180);
		mBox.setPrefHeight(120);
		
		 DropShadow ds1 = new DropShadow();
	     ds1.setOffsetY(2.0f);
	     ds1.setOffsetX(2.0f);
	     ds1.setColor(Color.GRAY);
	     
	     mBox.setEffect(ds1);
		
		Popup up = new Popup();
		up.getContent().add(mBox);
		up.setAutoFix(true);
		up.setAutoHide(true);
		
		
		
		
		//ContextMenu cm = new ContextMenu();
		
		/*MenuItem mi1 = new MenuItem("Complet your profil");
		MenuItem mi2 = new MenuItem("Change your status");
		MenuItem mi3 = new MenuItem("Parameter");
		
		cm.getItems().addAll(mi1,mi2,mi3);*/
		//moreBox.setX(460);
		//moreBox.setY(100);
	    //moreBox.setHeight(300);
		
		Image imgm = new Image(getClass().getResource("images/more_black_big.png").toString(),30,30,false,false);
		Button btm = new Button("",new ImageView(imgm));
		btm.setId("btf");
		btm.setAlignment(Pos.TOP_RIGHT);
		btm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!up.isShowing()) {
					javafx.geometry.Point2D point = btm.localToScene(0.0,  0.0);
		          	up.setX(stage.getX() + point.getX() - 150);
		          	up.setY(stage.getY() + point.getY() + 117 / 2); 
                    up.show(stage);
				}else {
					up.hide();
				}
			}
			
		});
	
		
		//btm.setAlignment(Pos.TOP_RIGHT);
		//btm.setPadding(new Insets(20,0,0,30));
		
		
		
		HBox imgmBox = new HBox();
		imgmBox.setAlignment(Pos.TOP_RIGHT);
		imgmBox.setPrefWidth(20);
		imgmBox.getChildren().add(btm);
		imgmBox.setPadding(new Insets(15,0,0,0));
		
		HBox contactBox = new HBox();
		contactBox.getChildren().addAll(contact);
		contactBox.setId("contactbox");
		contactBox.setAlignment(Pos.TOP_LEFT);
		//contactBox.setPrefWidth(180);
		contactBox.setPrefHeight(60);
		
		HBox cmBox = new HBox();
		cmBox.getChildren().addAll(onBox,imgmBox);
		cmBox.setId("contactbox");
		cmBox.setPrefHeight(100);
		cmBox.setPrefWidth(205);
		VBox lhead = new VBox();
		lhead.setPrefHeight(137);
		lhead.setPrefWidth(205);
		lhead.getChildren().addAll(cmBox,contactBox);
		
		VBox suBox = new VBox();
		suBox.getChildren().addAll(lhead,UserBox);
		suBox.setPrefHeight(600);
		suBox.setPrefWidth(205);
		
		
		ScrollPane sp = new ScrollPane();
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		sp.setContent(suBox);
		//sp.setPrefHeight(600);
		//sp.setPrefWidth(20);
		
		
		//suBox.setAlignment(Pos.CENTER_RIGHT);
		
		VBox userListBox = new VBox();
		
		
		leftContent.getChildren().addAll(lfBox,sp);
		
		
		
		fotoBox = new HBox();
		
		panel = new HBox();
		panel.getChildren().addAll(leftContent,rigthContent);
		panel.setAlignment(Pos.CENTER);
		
		panelBox = new StackPane();
		panelBox.getChildren().add(panel);
		
		scene = new Scene(panelBox,700,600);
		scene.getStylesheets().add(PageUser.class.getResource("myStyle.css").toString());
		return scene;
	}
	public void show(Stage primaryStage,Scene scene, String username){
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle(username+"@allinus");
		//primaryStage.centerOnScreen();
		Rectangle2D bound = Screen.getPrimary().getBounds();
		primaryStage.setX((bound.getWidth() / 2) - primaryStage.getWidth() / 2);
		primaryStage.setY((bound.getHeight() / 2) - primaryStage.getHeight() / 2);
		primaryStage.show();
	}
	
	public HBox handleContact(String name,String phone,int friendID,String email,String check) {
		addFBox = new HBox();
		
		VBox textEl = new VBox();
		Image imgprofil = new Image(getClass().getResource("images/identity_black_small.png").toString());
		Circle cercle = new Circle();
		cercle.setCenterX(30.0f);
		cercle.setCenterY(30.0f);
		cercle.setRadius(15.0f);
		cercle.setStroke(Color.GRAY);
		cercle.setFill(new ImagePattern(imgprofil));
		lb = new Label();
		lb.setText(name);
		lb.setId("name");
		Label rphone = new Label();
		rphone.setText(phone.substring(0, 6)+"...");
		rphone.setId("ph");
		gname = name;
		mail = new Label();
		mail.setText(email);
		lbp = new Label();
		lbp.setText(phone);
		lbp.setId("ph");
		textEl.getChildren().addAll(lb,rphone);
		Button addCon = new Button("Add "+name);
		HBox box = addFBox;
		if(check.equals("addcontact")){
			addCon.setOnAction(e -> {
				try {
					PageControler.handleAddContact(e,name,phone,email,box);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			//Button test = new Button(name);
			
			addFBox.getChildren().addAll(cercle,textEl,addCon);
			addFBox.setSpacing(20);
			addFBox.setPadding(new Insets(20,0,0,0));
		}else if(check.equals("confirm")){
			Button confirm = new Button("Confirm the request");
			confirm.setOnAction(e -> {
				try {
					PageControler.handleConfirmFriendRequest(e,name,phone,box,String.valueOf(friendID));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			//Button test = new Button(name);
			
			addFBox.getChildren().addAll(cercle,textEl,confirm);
			addFBox.setSpacing(20);
			addFBox.setPadding(new Insets(20,0,0,0));
		}else {
			addFBox.getChildren().addAll(cercle,textEl);
			addFBox.setSpacing(20);
			addFBox.setPadding(new Insets(20,0,0,0));
		}
		
		return addFBox;
	}
	
	public VBox onLineUser(String username,String onoff,String start,String color,String status,String phone) {
		//onBox = new VBox();
		onBox = new VBox();
		onBox.setPadding(new Insets(15,5,15,10));
		int i = 1;
		Image imgprofil = new Image(getClass().getResource("images/identity_white_small.png").toString());
		Circle cercle = new Circle();
		cercle.setCenterX(30.0f);
		cercle.setCenterY(30.0f);
		cercle.setRadius(15.0f);
		cercle.setStroke(Color.WHITE);
		cercle.setFill(new ImagePattern(imgprofil));
		
		Circle onCercle = new Circle();
		onCercle.setCenterX(5.0f);
		onCercle.setCenterY(5.0f);
		onCercle.setRadius(4.0f);
		onCercle.setLayoutX(155);
		onCercle.setLayoutY(35);
		onCercle.setFill(Color.web(color));
		
		HBox circleBox = new HBox();
		circleBox.getChildren().add(cercle);
		
		circleBox.setPadding(new Insets(10,0,0,5));
		
		Label offon = new Label("Status: "+onoff);
		offon.setId("onoffInfo");
		offon.setPadding(new Insets(-3,3,2,10));
		
		
		HBox offonBox = new HBox();
		offonBox.getChildren().addAll(offon,onCercle);
		
		Button btUsername = new Button();
		btUsername.setText(username);
		btUsername.setId("btusername");
		btUsername.setPadding(new Insets(-5,0,3,0));
		VBox box = onBox;
		btUsername.setOnAction(e -> PageControler.HandlerUserData(e,phone,onoff,username,box,status));
		
		HBox onoffInf = new HBox();
		onoffInf.getChildren().add(circleBox);
		VBox onffstatus = new VBox();
		onffstatus.getChildren().addAll(btUsername,offonBox);
		onoffInf.getChildren().add(onffstatus);
		onoffInf.setPadding(new Insets(10));
		
		Label phLab = new Label(phone);
		
		
		Label startc = new Label(start);
		startc.setId("onoffInfo");
		
		
		VBox boxRest = new VBox();
		boxRest.getChildren().addAll(startc);
		boxRest.setPadding(new Insets(0,0,0,10));
		
		
		
		Separator sepaH = new Separator();
		sepaH.setOrientation(Orientation.HORIZONTAL);
		sepaH.setStyle("-fx-border-color:white;-fx-background-color:white;-fx-border-width:0 0 1 0;-fx-border-style:solid");
		sepaH.setLayoutX(15);
		sepaH.setMaxWidth(150);
		sepaH.setPrefWidth(70);
		
		HBox sBox = new HBox();
		sBox.getChildren().add(sepaH);
		sBox.setPadding(new Insets(15,0,0,25));
		sBox.setPrefWidth(75);
		//boxRest.setMargin(sepaH, new Insets(15,0,0,0));

		onBox.getChildren().addAll(onoffInf,boxRest,sBox);
		onBox.setPrefWidth(250);
		onBox.setId("onbox");
		
		return onBox;

	}
	public StackPane messageChatDecor(String msg,String user,String date) {
		StackPane sp = new StackPane();
		HBox decorBox = new HBox(15);
		decorBox.setPadding(new Insets(10));
		decorBox.setId("decorbox");
		decorBox.setMaxWidth(255);
		Image imgprofil = new Image(getClass().getResource("images/identity_black_small.png").toString());
		Circle cercle = new Circle();
		cercle.setCenterX(30.0f);
		cercle.setCenterY(30.0f);
		cercle.setRadius(15.0f);
		cercle.setStroke(Color.GRAY);
		cercle.setFill(new ImagePattern(imgprofil));
		Label lb = new Label(user);
		lb.setId("lb");
		lb.setPadding(new Insets(0,0,0,5));
		VBox imgBox = new VBox(5);
		imgBox.getChildren().addAll(cercle,lb);
		VBox msgBox = new VBox();
		Text tx = new Text();
		tx.setId("tx");
		tx.setText(msg);
		Label lbdate = new Label(date);
		lbdate.setId("lbdate");
		lbdate.setPadding(new Insets(-5,0,0,0));
		msgBox.getChildren().addAll(tx,lbdate);
		decorBox.getChildren().addAll(imgBox,msgBox);
		sp.getChildren().add(decorBox);
		return sp;
	}
	public Button getBtUsername() {
		return btUsername;
	}
	public void setBtUsername(Button btUsername) {
		this.btUsername = btUsername;
	}
	public Label getUser() {
		return user;
	}
	public TextField getInputSearch() {
		return inputSearch;
	}
	public VBox getBoxarea() {
		return Boxarea;
	}
	public VBox getMessageBox() {
		return messageBox;
	}
	public VBox getUserBox() {
		return UserBox;
	}
	public int getBoxIndex() {
		return boxIndex;
	}
	public Label getLbp() {
		return lbp;
	}
	public Label getLb() {
		return lb;
	}
	public Label getMail() {
		return mail;
	}
	public Button getAddCon() {
		return addCon;
	}
	public String getGname() {
		return gname;
	}
	public HBox getAddFBox() {
		return addFBox;
	}
	public Map<Boolean, VBox> getVlist() {
		return vlist;
	}
	public Label getConv() {
		return conv;
	}
	public VBox getOnBox() {
		return onBox;
	}
	public TextArea getMessagearea() {
		return messagearea;
	}
	public Button getBtfriend() {
		return btfriend;
	}
	public Button getBtmsgchat() {
		return btmsgchat;
	}
	public StackPane getOverPane() {
		return overPane;
	}
	public HBox getPanel() {
		return panel;
	}
	public StackPane getPanelBox() {
		return panelBox;
	}
	public Button getUserbt() {
		return userbt;
	}
}
