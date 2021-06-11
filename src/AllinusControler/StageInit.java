package AllinusControler;

import javafx.stage.Stage;

public class StageInit {
	private Stage rootStage = null;
	public StageInit(Stage rootStage) {
		this.rootStage = rootStage;
	}
	public Stage getRootStage() {
		return rootStage;
	}
	public void setRootStage(Stage rootStage) {
		this.rootStage = rootStage;
	}
}
