package quiz;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Runtime exception canned for javafx
 * @since JavaFX 8u40
 * @author Robert Jansson
 * @see <A href="https://github.com/RobertJansson">https://github.com/RobertJansson</A>
 * @version 1.0
 */
public class MyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyRuntimeException (String msg){
		// super (msg);
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("A runtime exception occured");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
