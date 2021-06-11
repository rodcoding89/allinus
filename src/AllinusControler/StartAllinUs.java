package AllinusControler;

import AllinusModel.Message;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartAllinUs extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StageInit stageInit = new StageInit(primaryStage);	
		PageControler pageControler = new PageControler(stageInit.getRootStage());
		
		//new Thread(pageControler).start();
		pageControler.displayLoginPage();
		
		//Message msg = new Message("user1","bonjours","21.02.1898","L","user2","0756564595");
		
		/*primaryStage.setTitle("login");
		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root,500,600));
		primaryStage.show();*/
	}

}
