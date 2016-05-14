package quiz.model;

import java.util.List;

/**
 * Class to compose AQuiz from a list of string.
 * First string = question
 * Second string = correct answer
 * Consecutive strings = bogous answer (should adapt to size of list)
 */
public class AQuiz
{
	private String question;
	private String correct;
	private String[] allAnswers;
	
	/**
	 * Constructor to compose {@code AQuiz} from a list of string.<br>
	 * List-first will become the question.<br>
	 * List-second should be the correct answer.<p>
	 * This constructor will insert the correct answer with random position.<br>
	 * @param list of strings
	 */
	public AQuiz(List<String> list){
		question = list.get(0);
		correct = list.get(1);
		allAnswers = new String[list.size()-1];
		
		int correctIndex = (int) (Math.random() * allAnswers.length);
		int listIndex = 2;
		for (int i=0; i < allAnswers.length; i++){
			if (i == correctIndex){
				allAnswers[i] = correct;
			} else {
			allAnswers[i] = list.get(listIndex);
			listIndex++;
			}
		}
	}

	/**
	 * Getter for the question
	 * @return the question as {@code String}
	 */
	public String getQuestion() { return new String (question); }

	/**
	 * Getter for the correct answer
	 * @return the answer as {@code String}
	 */
	public String getCorrect() { return new String (correct); }

	/**
	 * Getter for the array of all possible answers
	 * @return allAnswers as a string-array
	 */
	public String[] getAllAnswers() { return allAnswers.clone(); }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "Question: " + question;
		for (int i = 0; i < allAnswers.length; i++)
			str = str + "Answer " + i + ":" + allAnswers[i] + "\n";
		return question + "?\n"
			+ "Correct:" + correct + "\n"
			+ str;
	}
}
