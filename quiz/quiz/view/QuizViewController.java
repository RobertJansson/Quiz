package quiz.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import quiz.Controller;

public class QuizViewController
{
	private Controller mainApp;		// Reference to the main application
	@FXML private Label question;	// The question
	@FXML private RadioButton rbA;
	@FXML private RadioButton rbB;
	@FXML private RadioButton rbC;
	@FXML private RadioButton rbD;
	@FXML private RadioButton rbE;
	@FXML private RadioButton rbF;
	@FXML private ToggleGroup rbGroup;
	private RadioButton[] rB;		// For iterations
	@FXML private ProgressBar pB;
	@FXML private Button bNext;

	/**
	 * Private method for debugging.
	 * @param s is the String to print to console
	 */
	private static final boolean LOG = true;
	private void log(String s){ if (LOG) System.out.println(s); }

	/**
	 * The constructor is called before the initialize() method.
	 */
	public QuizViewController() {
		question = new Label("");
		rbGroup = new ToggleGroup();
		pB = new ProgressBar(0);
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML private void initialize() {
		rB = new RadioButton[6];
		rB[0] = rbA;
		rB[1] = rbB;
		rB[2] = rbC;
		rB[3] = rbD;
		rB[4] = rbE;
		rB[5] = rbF;
		rbA.setToggleGroup(rbGroup);
		rbB.setToggleGroup(rbGroup);
		rbC.setToggleGroup(rbGroup);
		rbD.setToggleGroup(rbGroup);
		rbE.setToggleGroup(rbGroup);
		rbF.setToggleGroup(rbGroup);
	}

	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp
	 */
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Called when the user type on keyboard
	 */
	@FXML
	private void keyPressed(KeyEvent ke){
		log("Key getCode: " + ke.getCode());
		log("Key getText: " + ke.getText());

		switch (ke.getText()){
		case "1": rbA.setSelected(true); break;
		case "2": rbB.setSelected(true); break;
		case "3": rbC.setSelected(true); break;
		case "4": rbD.setSelected(true); break;
		case "5": rbE.setSelected(true); break;
		case "6": rbF.setSelected(true); break;
		default:
			switch (ke.getCode().toString()){
			case "ENTER": getNextQuiz(); break;
			case "RIGHT_ARROW": getNextQuiz(); break;
//			case "BACK_SPACE": getPreviousQuiz(); break;
//			case "LEFT_ARROW": getPreviousQuiz(); break;
			default: break;
			}
		}
	}

	/**
	 * Listeners for when the user clicks a button
	 */
	@FXML private void rbClicked()	{ bNext.setVisible(true); }
	@FXML private void bNext()		{ getNextQuiz(); }

	/**
	 * Tell controller to evaluate our expression
	 * Public: To let mainApp start an evaluation (menubar conversions)
	 */
	private void getNextQuiz() {
		String picked = ((Labeled) rbGroup.getSelectedToggle()).getText();
		mainApp.stepQuiz(picked);
	}

	public void showQuiz(String question, String[] allAnswers){
		this.question.setText(question);
		this.question.setVisible(true);
		this.bNext.setVisible(false);
		for (int i = 0; i < 6; i++){
			if (i >= allAnswers.length) {
				rB[i].setText("");
				rB[i].setSelected(false);
				rB[i].setVisible(false);
			} else {
				rB[i].setSelected(false);
//				rB[i].setPickOnBounds(false);
				rB[i].setText(allAnswers[i]);
				rB[i].setVisible(true);
			}
		}
	}
	
	/**
	 * Update progress bar
	 * @param double, 0.0 is empty and 1.0 is full
	 */
	public void setProgress(double d){
		pB.setProgress(d);
	}

}
