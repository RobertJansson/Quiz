package quiz.view;

import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * QuizViewController for QuizView
 * QuizView is the on-going game scene presented in the stage [Menu]
 * 
 * @since JavaFX 8u40
 * @author Robert Jansson
 * @see <A href="https://github.com/RobertJansson">https://github.com/RobertJansson</A>
 * @version 1.0
 */
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
	private RadioButton[] rB;		// For iteration-purpose only (indexing)
	@FXML private ProgressBar pB;
	@FXML private Button bNext;

	private static final boolean LOG = true;
	/**
	 * Private method for debugging.
	 * @param s is the String to print to console
	 */
	private void log(String s){ if (LOG) System.out.println(s); }

	/**
	 * The constructor for the Quiz-view
	 */
	public QuizViewController() {
		question = new Label("");
		rbGroup = new ToggleGroup();
		pB = new ProgressBar(0);
	}

	/**
	 * Initializes the controller class after the fxml file.
	 */
	@FXML
	private void initialize() {
		rB = new RadioButton[6];
		rB[0] = rbA; rB[1] = rbB; rB[2] = rbC;	// House-keeping
		rB[3] = rbD; rB[4] = rbE; rB[5] = rbF;
		rbA.setToggleGroup(rbGroup); rbB.setToggleGroup(rbGroup);
		rbC.setToggleGroup(rbGroup); rbD.setToggleGroup(rbGroup);
		rbE.setToggleGroup(rbGroup); rbF.setToggleGroup(rbGroup);
	}

	/**
	 * Called by the main application to give a reference back to itself.
	 * @param mainApp is Mork, the controller
	 */
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;	// Talk later but keep your number, Nanoo, Nanoo
	}

	/**
	 * Keyboard listeners
	 * @param ke is KeyEvent
	 */
	@FXML
	private void keyPressed(KeyEvent ke){
		log("Key getCode: " + ke.getCode());
		log("Key getText: " + ke.getText());

		switch (ke.getText()) {
			case "1": rb(rbA); break;
			case "2": rb(rbB); break;
			case "3": rb(rbC); break;
			case "4": rb(rbD); break;
			case "5": rb(rbE); break;
			case "6": rb(rbF); break;
			default:
				switch (ke.getCode().toString()) {
					case "ENTER": getNextQuiz(); break;
					case "RIGHT_ARROW": getNextQuiz(); break;
					default: break;
				}
		}
	}
	
	/**
	 * Helper-method for keyPressed(KeyEvent)
	 * @param pressed RadioButton
	 */
	private void rb(RadioButton pressed){
		if (pressed.isVisible()){
			pressed.setSelected(true);
			bNext.setDisable(false);
		}
	}

	/**
	 * Listeners for when the user clicks a button
	 */
	@FXML private void rbClicked()	{ bNext.setDisable(false);	}
	@FXML private void bNext()		{ getNextQuiz();			}

	/**
	 * Helper-method for button-listeners.<br>
	 * Let controller evaluate the picked answer and move on in stepQuiz().
	 */
	private void getNextQuiz() {
		String picked = ((Labeled) rbGroup.getSelectedToggle()).getText();
		mainApp.stepQuiz(picked);
	}

	/**
	 * Called from Controller to show {@code AQuiz}.<br>
	 * RadioButtons for answers not in the string-array will be hidden.
	 * @param question is the question
	 * @param allAnswers are the labels of the RadioButtons
	 */
	public void showQuiz(String question, String[] allAnswers){
		this.question.setText(question + "?");
		this.question.setVisible(true);
		this.bNext.setDisable(true);
		for (int i = 0; i < 6; i++){
			if (i >= allAnswers.length) {
				rB[i].setText("");
				rB[i].setSelected(false);
				rB[i].setVisible(false);
			} else {
				rB[i].setText(allAnswers[i]);
				rB[i].setSelected(false);
				rB[i].setVisible(true);
			}
		}
	}
	
	/**
	 * Update progress bar
	 * @param d is the progress (0.0 is empty and 1.0 is full)
	 */
	public void setProgress(double d){
		pB.setProgress(d);
	}
}
