package quiz.view;

import quiz.Controller;
import javafx.fxml.FXML;

public class MenuController {

	private Controller mainApp;

	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp
	 */
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}

	// JavaFX "action listeners"

	// File-menu:
	@FXML public void loadNewGame() throws Exception{ mainApp.loadQuiz(); }
	@FXML private void quit() { System.exit(0); }

}
