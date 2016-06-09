package quiz;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import quiz.model.QuizModel;
import quiz.view.LoadViewController;
import quiz.view.MenuController;
import quiz.view.QuizViewController;

/**
 * Controller for the program Quiz
 * @since JavaFX 8u40
 * @author Robert Jansson
 * @see <A href="https://github.com/RobertJansson">https://github.com/RobertJansson</A>
 * @version 1.0
 */
public class Controller extends Application
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private QuizModel model;
	private QuizViewController view;
	private LoadViewController load;
	private MenuController menu;
	private int currentIndex;
	private int attempt;

	// Simple debug method, just flip the boolean
	private static final boolean LOG = true;
	@SuppressWarnings("unused")
	private static void log(String s){ if (LOG) System.out.println(s); };

	// Initiate the launch-sequence
	public static void main(String[] args) {
		launch(args);
	}

	// Finish the launch-sequence
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Quiz");
		initRootLayout();
		showLoadView();
	}

	/**
	 * Initializes the root layout with a tiny menu.
	 * Will catch IOExceptions and present message in an alert window
	 */
	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/Menu.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			MenuController viewController = loader.getController();
			viewController.setMainApp(this);	// Mork calling Orson, come in Orson
			menu = viewController;				// Save reference to menu
			primaryStage.show();

		} catch (IOException e) {
			throw new MyRuntimeException(e.getMessage());
		}
	}

	/****  The methods above are initializers only 	****/
	//-------------------------------------------------//
	/**** 		From here the program return 		****/

	/**
	 * Initialize the Load-view.
	 * Start of the program from a users view
	 * Will catch IOExceptions and present message in an alert window
	 */
	private void showLoadView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/LoadView.fxml"));
			AnchorPane loadView = (AnchorPane) loader.load();
			rootLayout.setCenter(loadView);
			LoadViewController viewController = loader.getController();
			viewController.setMainApp(this);	// Mork calling Orson, come in Orson
			load = viewController;				// Save reference to load-view

		} catch (IOException e) {
			throw new MyRuntimeException(""+e.getMessage());
		}
	}

	/**
	 * Revisit the unresolved questions
	 */
	public void resumeQuiz(){
		showQuizView();
	}

	/**
	 * Restart the same Quiz by having the model copy the quiz into the game
	 */
	public void restartQuiz() {
		attempt = 0;
		model.startGame();
		showQuizView();
	}

	/**
	 * Load a new game into a new model and start the game in a QuizView
	 * @throws Exception when file not found
	 */
	public void loadQuiz() throws Exception{
		attempt = 0;
		this.model = new QuizModel();
		showQuizView();
	}

	/**
	 * Initializes the Quiz-game view
	 * Will catch IOExceptions and present message in an alert window
	 */
	private void showQuizView() {
		currentIndex = 0;
		attempt = attempt + 1;
		if (model.getGameSize() > 0) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Controller.class.getResource("view/QuizView.fxml"));
				AnchorPane quizView = (AnchorPane) loader.load();
				rootLayout.setCenter(quizView);
				QuizViewController viewController = loader.getController();
				viewController.setMainApp(this);	// Mork calling Orson, come in Orson
				view = viewController;				// Save reference to quiz-view
				showQuiz(currentIndex);

			} catch (IOException e) {
				throw new MyRuntimeException(e.getMessage());
			}
		}
	}

	/**
	 * Private method to show the particular {@code AQuiz}.<br>
	 * Called when a new quiz-game begins, but also during stepQuiz().
	 * @param index to show
	 * @throws MyRuntimeException if game is out of bounce
	 */
	private void showQuiz(int index){
		view.setProgress((double) (1 + currentIndex + model.getScore()) / (double) model.getTotalScore());
		if (index < model.getGameSize())
			view.showQuiz(model.getQuestion(index), model.getAllAnswers(index));
		else
			throw new MyRuntimeException("Game is out of bounds: " + index + " of " + model.getGameSize());
	}

	/**
	 * Method to step to the next Quiz
	 * @param pick is the answer given for the last question
	 */
	public void stepQuiz(String pick){
		if (pick.equals(model.getCorrect(currentIndex)))
			model.remove(currentIndex);
		else
			currentIndex = currentIndex + 1;

		if (currentIndex < model.getGameSize()){
			showQuiz((currentIndex));
		} else {
			showResult();
		}
	}

	/**
	 * Show result, called when quiz is finished
	 */
	private void showResult(){
		showLoadView();
		menu.enableRestartMenuItem();
		load.showResult(model.getScore(), model.getTotalScore(), attempt);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.model.toString();
	}
}
