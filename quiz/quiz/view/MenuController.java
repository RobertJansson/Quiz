package quiz.view;

import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * MenuController for Menu
 * Menu is the stage (window) and includes the menu bar
 * 
 * @since JavaFX 8u40
 * @author Robert Jansson
 * @see <A href="https://github.com/RobertJansson">https://github.com/RobertJansson</A>
 * @version 1.0
 */
public class MenuController {

	private Controller mainApp;
	@FXML private MenuItem mIRestart;

	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp is Mork, the controller
	 */
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;	// Talk later, Nanoo, Nanoo
	}
	
	// JavaFX "action listeners"
	// The smallest possible File-menu:
	@FXML private void restartGame() throws Exception{ mainApp.restartQuiz(); }
	@FXML private void loadNewGame() throws Exception{ mainApp.loadQuiz(); }
	@FXML private void quit() { System.exit(0); }

	public void enableRestartMenuItem(){
		mIRestart.setVisible(true);
		mIRestart.setDisable(false);
	}
}
