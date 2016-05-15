package quiz.model;

import quiz.MyRuntimeException;
import java.util.LinkedList;
import java.util.List;

/**
 * The Model<br>
 * Class to compose a Quiz-puzzle as a list of {@code AQuiz}
 */
public class QuizModel {
	private List<AQuiz> quiz;
	private List<AQuiz> game;
	
	// Simple debug method, just flip: LOG
	private static final boolean LOG = false;
	private static void log(String s){ if (LOG) System.out.println(s); };

	public QuizModel() throws Exception{
		quiz = new LinkedList<AQuiz>();
		FileImport.importQuiz(this);
		startGame();
		log(game.toString());
	}
	
	/**
	 * Copy the Quiz loaded from file to start or restart a game.<br>
	 * Called from constructor or if user choose to restart the same game.<br>
	 * Should not be called if user select to answer the questions which
	 * were not correct.
	 */
	public void startGame(){
		game = new LinkedList<AQuiz>(quiz);
	}
	
	/**
	 * Setter for the FileImport to build the list of {@code AQuiz}
	 * @param list
	 */
	public void addAQuiz(List<String> list){
		quiz.add(new AQuiz(list));
	}
	
	// Getters for the controller:

	/**
	 * Getter for the Question
	 * @param index in the list where the AQuiz is stored
	 * @return The Question of {@code AQuiz} as a {@code String}
	 */
	public String getQuestion(int index){
		return game.get(index).getQuestion();
	}
	
	/**
	 * Getter for the Correct answer
	 * @param index in the list where the AQuiz is stored
	 * @return The Correct answer of {@code AQuiz} as a {@code String}
	 */
	public String getCorrect(int index){
		return game.get(index).getCorrect();
	}
	
	/**
	 * Getter for the array of all possible answers
	 * @param index in the list where the AQuiz is stored
	 * @return The answers of {@code AQuiz} as a {@code String[]} (array)
	 */
	public String[] getAllAnswers(int index){
		return game.get(index).getAllAnswers();
	}

	/**
	 * Getter for the number of {@code AQuiz} in the list
	 * @return size of quiz as an {@code int}
	 */
	public int getQuizSize(){
		return quiz.size();
	}

	/**
	 * Getter for the number of {@code AQuiz} in the list
	 * @return size of quiz as an {@code int}
	 */
	public int getGameSize(){
		return game.size();
	}

	/**
	 * Getter for the number of {@code AQuiz} in the list
	 * @return size of quiz as an {@code int}
	 */
	public void remove(int index){
		if (index < game.size())
			game.remove(index);
		else
			throw new MyRuntimeException("Game out of bounce, trying to remove AQuiz " + index);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "QUIZ\n";
		for (int i = 0; i < quiz.size(); i++){
			str = str + (i+1) + ": " + quiz.get(i).toString() + "\n";
		}
		return str;
	}
}
