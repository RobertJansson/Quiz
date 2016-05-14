# Quiz
Simple Quiz-program
HiG Lab7
JavaFX8-version of my Quiz using strict MVC (and FXML)

Controller
Controller.java - The main controller.

View
Menu.fxml -> MenuController.java
RootLayout set up the window and menu-bar as the program start.
LoadView.fxml -> LoadViewController.java
LoadView is where the user load a new game from file but also double to show the result.
QuizView.fxml -> QuizViewController.java
A viewer to show each Quiz. This is the view for the ongoing game.

Model
QuizModel.java - The model of the Quiz. (A list of AQuiz.)
AQuiz.java  - One Quiz, representing the Question, correct answer and all possible answers.
FileImport.java - Load a new Quiz-game from file. See example file.

Note: First line: Question, Second line: Correct answer, then bogous answers (max 6).
Empty line is divider and lines starting with "//" are parsed off.

MyRuntimeException.java - to explore the new exception-system of JFX.

Tooling: Java 8 and an IDE with JavaFX. For Eclipse, update everything and go here for e(fx)clipse toolset: http://www.eclipse.org/efxclipse/install.html
CalculatorFX is also using aguafx (http://aquafx-project.com). That move the programs menubar from its window into the menubar of the computer. The constructor in CalculatorFX.java (or FX8-start method) has a note what to change if that is not used. Menu bar height of 29 pixels must be added to viewer.
