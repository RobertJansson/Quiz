# Quiz
Simple Quiz-program using MVC design in JavaFX8 with FXML (HiG-Lab7-VT2016)<p>
Quiz has been compiled into a program so try that unless you are interested in source code:<br>
OS X: <A HREF=https://kvicktajm.se/apps/osx/Quiz.dmg>Quiz.dmg</A><br>
Debian x86 (32bit): <A HREF=https://kvicktajm.se/apps/debian/Quiz_x86.tar.gz>Quiz_x86.tar.gz</A><p>

<p><b>Controller</b><br>
Controller.java - The main controller.

<p><b>View</b><br>
Menu.fxml -> MenuController.java<br>
-RootLayout which define the stage and menubar as the program start.<br>
LoadView.fxml -> LoadViewController.java<br>
-LoadView is the landing scene, where the user load/restart/resume a game and also double to show the result.<br>
QuizView.fxml -> QuizViewController.java<br>
-A scene to show each Quiz. This is the scene for the on-going game.<br>

<p><b>Model</b><br>
QuizModel.java - The model of the Quiz. (A list of AQuiz.)<br>
AQuiz.java  - One Quiz, representing the Question, correct answer and all possible answers.<br>
FileImport.java - Load a new Quiz-game from file. See example file.
<p>
Note: First line = Question, Second line = Correct answer, then all bogous answers (viewer will adapt to max 6).<br>
Empty line is divider and lines starting with "//" are skipped.
<p>
MyRuntimeException.java - to explore the new exception-system (with special windows) of JFX8 (technically the JRE version 8u40).
<p>
Tooling: Java 8 (latest) and an IDE with JavaFX.<br>
For Eclipse, update everything from within the program and go here for the e(fx)clipse toolset:<br> http://www.eclipse.org/efxclipse/install.html<br>
