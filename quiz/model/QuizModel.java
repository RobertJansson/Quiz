package quiz.model;

import java.util.LinkedList;
import java.util.List;

/**
 * The Model<br>
 * Class to compose a Quiz-puzzle as a list of {@code AQuiz}
 */
public class QuizModel {
	private List<AQuiz> quiz;
	
	// Simple debug method, just flip: LOG = true
	private static final boolean LOG = false;
	private static void log(String s){ if (LOG) System.out.println(s); };

	public QuizModel() throws Exception{
		quiz = new LinkedList<AQuiz>();
		FileImport.importQuiz(this);
		log(quiz.toString());
	}
	
	/**
	 * Setter for the FileImport to build the list of {@code AQuiz}
	 * @param list
	 */
	public void addAQuiz(List<String> list){
		quiz.add(new AQuiz(list));
	}
	
	// Getters for the controller:

	public String getQuestion(int index){
		return quiz.get(index).getQuestion();
	}
	
	public String getCorrect(int index){
		return quiz.get(index).getCorrect();
	}
	
	public String[] getAllAnswers(int index){
		return quiz.get(index).getAllAnswers();
	}

	public int getQuizSize(){
		return quiz.size();
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
