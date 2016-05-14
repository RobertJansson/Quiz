# Quiz
Simple Quiz-program. See the wiki page for a link to an OS X compiled version (https://github.com/RobertJansson/Quiz/wiki).<br>
HiG - Lab7<br>
JavaFX8-version of my Quiz using strict MVC (and FXML)<p>

<p><b>Controller</b><br>
Controller.java - The main controller.

<p><b>View</b><br>
Menu.fxml -> MenuController.java<br>
RootLayout set up the window and menu-bar as the program start.<br>
LoadView.fxml -> LoadViewController.java<br>
LoadView is where the user load a new game from file but also double to show the result.<br>
QuizView.fxml -> QuizViewController.java<br>
A viewer to show each Quiz. This is the view for the ongoing game.<br>

<p><b>Model</b><br>
QuizModel.java - The model of the Quiz. (A list of AQuiz.)<br>
AQuiz.java  - One Quiz, representing the Question, correct answer and all possible answers.<br>
FileImport.java - Load a new Quiz-game from file. See example file.
<p>
Note: First line: Question, Second line: Correct answer, then bogous answers (max 6).<br>
Empty line is divider and lines starting with "//" are parsed off.
<p>
MyRuntimeException.java - to explore the new exception-system of JFX.
<p>
Tooling: Java 8 and an IDE with JavaFX. For Eclipse, update everything and go here for e(fx)clipse toolset:<br> http://www.eclipse.org/efxclipse/install.html<br>
