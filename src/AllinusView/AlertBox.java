package AllinusView;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	private static HBox labBox,box;
	private static Label label;
	private static Button bt;
	private static VBox layout;
	
	public static void Alert(String title,String message) {
		final Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		
		labBox = new HBox();
		labBox.setPrefHeight(120);
		labBox.setPrefWidth(400);
		labBox.setPadding(new Insets(50,30,30,15));
		labBox.setId("labBox");
		label = new Label(message);
		
		label.setStyle("-fx-font-family:'Monotype Corsiva'; -fx-font-size: 23px;-fx-font-weight:bold;-fx-text-fill:white;");
		
		labBox.getChildren().add(label);
		box = new HBox();
		box.setAlignment(Pos.TOP_CENTER);
		bt = new Button("Close");
		bt.setStyle("-fx-text-fill: white;-fx-font-weight:bold;-fx-font-family:'Arial Helvetica';-fx-font-size: 15px;-fx-background-insets: 0,1,2,0;");
		box.getChildren().add(bt);
		
		bt.setOnAction(new EventHandler<ActionEvent>() {

	        public void handle(ActionEvent event) {
	            window.close();
	        }
	    });
		
		layout = new VBox();
		layout.getChildren().addAll(labBox,box);
		layout.setStyle("-fx-background-color:#260069;");
		Scene sc = new Scene(layout,400,150);
		
		//window.getIcons().add(new Image("Images/logo.png"));
		window.setResizable(false);
		window.setScene(sc);
		window.showAndWait();
	}
}
