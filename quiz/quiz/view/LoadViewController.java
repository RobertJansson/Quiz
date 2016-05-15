package quiz.view;

import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoadViewController
{
	private Controller mainApp;		// Reference to the main application
	@FXML private Label headline;	// The headline
	@FXML private Label result;		// The result
	@FXML private Button bResume;
	@FXML private Button bRestart;
	@FXML private Button bLoad;

	/**
	 * The constructor for the LoadFile-viewer
	 */
	public LoadViewController() {
		headline = new Label();
		result = new Label();
	}

	/**
	 * Initializes the controller class after the fxml file.
	 */
	@FXML private void initialize() {	}

	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp
	 */
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Listeners, for when the user clicks a button
	 * @throws Exception 
	 */
	@FXML private void bResume() throws Exception	{ mainApp.resumeQuiz(); }
	@FXML private void bRestart() throws Exception	{ mainApp.restartQuiz(); }
	@FXML private void bLoad() throws Exception	{ mainApp.loadQuiz(); }

	/**
	 * Called to use the load view to display results from last game
	 * @param score is the correct answers
	 * @param max is the maximum score possible
	 */
	public void showResult(int score, int max, int attempt){
		bRestart.setVisible(true);
		headline.setText("Result from Quiz:");
		if (score == max) {
			result.setText("Congratulations, you scored the full " + score + " points!\n"
					+ "(It took " + attempt + " attempt" + (attempt > 1 ? "s)" :")"));
			bResume.setVisible(false);
		} else if (score == 0) {
			result.setText("Sorry, you didn't have any correct answer.");
			bResume.setVisible(true);
		} else {
			result.setText("You scored " + score + " out of " + max + " points.");
			bResume.setVisible(true);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoadViewController [" + (mainApp != null ? "mainApp=" + mainApp + ", " : "")
				+ (headline != null ? "headline=" + headline + ", " : "")
				+ (result != null ? "result=" + result + ", " : "")
				+ (bResume != null ? "bResume=" + bResume + ", " : "")
				+ (bRestart != null ? "bRestart=" + bRestart + ", " : "") + (bLoad != null ? "bLoad=" + bLoad : "")
				+ "]";
	}
	
	
}