# Quiz
Simple Quiz-program using MVC design in JavaFX8 with FXML<p>
Quiz has been compiled into a program so try that unless you are interested in source code:<br>
OS X: <A HREF=https://kvicktajm.se/apps/osx/Quiz.dmg>Quiz.dmg</A><br>
Debian x86 (32bit): <A HREF=https://kvicktajm.se/apps/debian/Quiz_x86.tar.gz>Quiz_x86.tar.gz</A><br>
JAR-file (require JRE 8u40 or later): <A HREF=https://kvicktajm.se/apps/jar8/Quiz.jar.zip>Quiz.jar.zip</A>
(also take the <A HREF=https://kvicktajm.se/apps/jar8/QuizExample.txt.zip>QuizExample.txt.zip</A>)<p>

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
QuizModel.java - The model of the Quiz. Make a list of AQuiz and call FileImport to load it up.<br>
AQuiz.java  - One Quiz, representing the Question, correct answer and all possible answers.<br>
FileImport.java - Load a new set of quiz from file. See example file.
<p>
Note: First line = Question, Second line = Correct answer, then all bogous answers (viewer adapt to max 5).<br>
Empty line is divider and lines starting with "//" are skipped.<br>

<p><b>Exceptions</b><br>
MyRuntimeException.java - to explore the new alert-windows of JavaFX8 (technically: JRE version 8u40).

<p>
<p><b>Tooling</b><br>
JavaSE 8 (latest) and an IDE with JavaFX libraries and tools.<br>
For Eclipse, update everything from within the program and go here for the e(fx)clipse toolset:<br> http://www.eclipse.org/efxclipse/install.html<br>
