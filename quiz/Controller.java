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


public class Controller extends Application
{
	private Stage primaryStage;
	private BorderPane rootLayout;
	private QuizModel model;
	private QuizViewController view;
	private LoadViewController load;
	private int currentIndex;
	private int score;

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
//		AquaFx.style();				// OS X adaption - comment this out and you must
		initRootLayout();			// ...change height of root window: 360 -> 389
		showLoadView();
	}
	
	/**
	 * Initializes the root layout with menu.
	 */
	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/Menu.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			MenuController viewController = loader.getController();
			viewController.setMainApp(this);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/****  The methods above are initializers only 	****/
	//-------------------------------------------------//
	/**** 		From here the program return 		****/
	
	/**
	 * Initialize the Load-view.
	 */
	private void showLoadView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/LoadView.fxml"));
			AnchorPane loadView = (AnchorPane) loader.load();
			rootLayout.setCenter(loadView);
			LoadViewController viewController = loader.getController();
			viewController.setMainApp(this);
			load = viewController;		// Save reference to view
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load a new game into a new model and start the QuizView
	 * @throws Exception
	 */
	public void loadQuiz() throws Exception{
		currentIndex = 0;
		score = 0;
		this.model = new QuizModel();
		showQuizView();
	}
	
	/**
	 * Initializes the Quiz-game view
	 */
	private void showQuizView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/QuizView.fxml"));
			AnchorPane quizView = (AnchorPane) loader.load();
			rootLayout.setCenter(quizView);
			QuizViewController viewController = loader.getController();
			viewController.setMainApp(this);
			view = viewController;			// Save reference to view
			showQuiz(currentIndex);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Private method to show the particular quiz.<br>
	 * Called when a new quiz should show, but also from stepQuiz().
	 * @param index to show
	 */
	private void showQuiz(int index){
		if (index < model.getQuizSize())
			view.showQuiz(model.getQuestion(index), model.getAllAnswers(index));
		else throw new RuntimeException("QuizModel out of bounds:" + index + " of " + model.getQuizSize());
	}

	/**
	 * Method to step to the next Quiz
	 * @param pick is the answer given on the last question
	 */
	public void stepQuiz(String pick){
		if (pick.equals(model.getCorrect(currentIndex)))
			score = score +1;
		currentIndex = currentIndex + 1;
		view.setProgress((double) currentIndex / (double) model.getQuizSize());

		if (currentIndex < model.getQuizSize()){
			showQuiz(currentIndex);
		} else {
			showResult(score, model.getQuizSize());
		}
	}
	
	/**
	 * Show result, called when quiz is at the end
	 * @param score is the number of correct answers
	 * @param max is the maximum number of scores
	 */
	private void showResult(int score, int max){
		showLoadView();
		load.showResult(score, max);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.model.toString();
	}
}
