package AllinusModel;

import AllinusControler.PageControler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ContactUserHandler {
	private String user;
	private String status;
	private Boolean isStyleUsed;
	private String info;
	public ContactUserHandler(String user, String status, Boolean isStyleUsed, String info) {
		super();
		this.user = user;
		this.status = status;
		this.isStyleUsed = isStyleUsed;
		this.info = info;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getIsStyleUsed() {
		return isStyleUsed;
	}
	public void setIsStyleUsed(Boolean isStyleUsed) {
		this.isStyleUsed = isStyleUsed;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
