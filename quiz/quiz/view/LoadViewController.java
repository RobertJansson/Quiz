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
	@FXML private Button bLoad;

	/**
	 * The constructor is called before the initialize() method.
	 */
	public LoadViewController() {
		headline = new Label();
		result = new Label();
	}

	/**
	 * Initializes the controller class. This method is automatically
	 * called after the fxml file has been loaded.
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
	 * Listeners for when the user clicks a button
	 * @throws Exception 
	 */
	@FXML private void bLoad() throws Exception	{ mainApp.loadQuiz(); }

	/**
	 * Called to use the load view to display results from last game
	 * @param score is the correct answers
	 * @param max is the maximum score possible
	 */
	public void showResult(int score, int max){
		headline.setText("Result from Quiz:");
		if (score == max)
			result.setText("Congratulations, you scored a full " + score + "points!");
		else if (score == 0)
			result.setText("Sorry, you didn't have a correct answer at all.");
		else
			result.setText("You scored " + score + " out of " + max + " points.");
	}
}
