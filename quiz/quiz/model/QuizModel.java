package quiz.model;

import quiz.MyRuntimeException;

import java.util.LinkedList;
import java.util.List;

/**
 * The Model<br>
 * Class to compose a Quiz-puzzle as a list of {@code AQuiz}
 *
 * @author Robert Jansson
 * @see <A href="https://github.com/RobertJansson">https://github.com/RobertJansson</A>
 * @version 1.0
 */
public class QuizModel {
	private List<AQuiz> quiz;	// Quiz as loaded from file.
	private List<AQuiz> game;	// Shallow copy of quiz so game can remove correctly
								// answered questions and let user revisit the others.
	
	// Simple debug method, just flip: LOG = true;
	private static final boolean LOG = false;
	private static void log(String s){ if (LOG) System.out.println(s); };

	/**
	 * Create a quiz and call FileImport to get data from file.
	 * Will also create a game from the data model (quiz).
	 * @throws Exception when file not found
	 */
	public QuizModel() throws Exception{
		quiz = new LinkedList<AQuiz>();
		FileImport.importQuiz(this);
		startGame();								log(game.toString());
	}
	
	/**
	 * Copy the Quiz loaded from file to start or restart a game.<br>
	 * Called from constructor or if user choose to restart the same game.<br>
	 * Should not be called if user select to answer the questions which
	 * were not correct.
	 */
	@SuppressWarnings("unchecked")
	public void startGame(){

		// I really try to make a shallow copy but as far as understand
		// the debugger in Eclipse it does not point to the same address.
		// I might get an AQuiz-object that in its case points to the same
		// data but it's beyond my scope. I trust Oracle this is how it should be done.
		game = (List<AQuiz>) ((LinkedList<AQuiz>) quiz).clone();

		// This should be a deep copy and in the debugger it looks the same,
		// except being synchronized by collection:
//		game = Collections.synchronizedList(new LinkedList<AQuiz>(quiz));
		// Also: import java.util.Collections;
	}
	
	/**
	 * Setter for the FileImport to build the list of {@code AQuiz}
	 * @param list of strings with question, answer, wrong answers
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
	public int getGameSize(){
		return game.size();
	}

	/**
	 * Get the total score
	 * @return size of quiz as an {@code int}
	 */
	public int getTotalScore(){
		return quiz.size();
	}
	
	/**
	 * Compute the present score
	 * @return score of present game
	 */
	public int getScore(){
		return quiz.size() - game.size();
	}
	
	/**
	 * Method to remove {@code AQuiz} from the game-list
	 * Used when a correct answer is given.
	 * @param index to remove
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
