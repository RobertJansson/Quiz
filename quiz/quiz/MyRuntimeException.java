package quiz;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyRuntimeException (String msg){
//		super (msg);
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Never expected this to happen");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
