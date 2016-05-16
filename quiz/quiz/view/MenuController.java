package quiz.view;

import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuController {

	private Controller mainApp;
	@FXML private MenuItem mIRestart;

	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp
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
