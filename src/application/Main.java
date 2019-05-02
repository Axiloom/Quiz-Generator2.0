package application;

///////////////////////////////////////////////////////////////////////////////
//
// Assignment: Quiz-Generator Team Project
// Due: 5-2-19
// Title: application.Main
// Files: application.Main.java
// Course: CS 400, Spring 2019, Lec 001
//
// Authors: A-Team 99 
//          (John Bednarczyk, Joseph Lessner, Joshua Liberko, Shefali Mukerji, Mitchell Sutrick)
// Lecturer's Name: Deb Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;

/**
 * application.Main initializes the GUI and creates instances of the various Scenes and Menus that are used
 * throughout the operation of the program.
 * 
 * @author ATeam-99
 *
 */
public class Main extends Application {

  private static Scene mainScene; // main scene instance
  private static Scene saveScene; // save scene instance
  private static Scene addScene; // add scene instance
  private static Scene quizScene; // quiz scene instance
  private static Scene statisticsScene; // statistics scene instance
  private static Scene exitScene; // exit scene instance
  private static Question question; // application.Question Class instance
  private static MainMenu mainMenu; // instance of Class that constructs the main menu
  private static SaveMenu saveMenu; // instance of Class that constructs the save menu
  private static AddMenu addMenu; // instance of Class that constructs the add menu
  private static QuizMenu quizMenu; // instance of Class that constructs the quiz menu
  private static StatisticsMenu statisticsMenu; // instance of Class that constructs the stats menu

  /**
   * Start the application here (initial setup)
   */
  @Override
  public void start(Stage primaryStage) {
    try {

      // Instances of menu's
      question = new Question();
      mainMenu = new MainMenu(primaryStage);
      saveMenu = new SaveMenu(primaryStage);
      addMenu = new AddMenu(primaryStage);
      quizMenu = new QuizMenu(primaryStage);
      statisticsMenu = new StatisticsMenu(primaryStage);
      ExitMenu exitMenu = new ExitMenu(primaryStage);

      // Create Scenes
      mainScene = new Scene(mainMenu.initialize(), 700, 500);
      saveScene = new Scene(saveMenu.initialize(), 700, 500);
      addScene = new Scene(addMenu.initialize(), 700, 500);
      exitScene = new Scene(exitMenu.initialize(), 700, 500);

      // Style for main menu
      mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

      // Present stage
      primaryStage.setScene(mainScene);
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();
      primaryStage.resizableProperty().setValue(false);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * application.Main method that launches the program
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Getter for main scene
   *
   * @return the main scene
   */
  protected static Scene getMainScene() {
    return mainScene;
  }

  /**
   * Getter for save scene
   *
   * @return the save scene
   */
  protected static Scene getSaveScene() {
    return saveScene;
  }

  /**
   * Getter for add scene
   *
   * @return the add scene
   */
  protected static Scene getAddScene() {
    return addScene;
  }

  /**
   * Getter for quiz scene
   *
   * @return the quiz scene
   */
  protected static Scene getQuizScene() {
    return quizScene;
  }

  /**
   * Getter for statistics scene
   *
   * @return the statistics scene
   */
  protected static Scene getStatisticsScene() {
    return statisticsScene;
  }

  /**
   * Getter for exit scene
   *
   * @return the exit scene
   */
  protected static Scene getExitScene() {
    return exitScene;
  }

  /**
   * Getter for the application.Question instance
   * 
   * @return instance of application.Question
   */
  protected static Question getQuestion() {
    return question;
  }

  /**
   * Getter for the application.MainMenu instance
   * 
   * @return instance of application.MainMenu
   */
  protected static MainMenu getMainMenu() {
    return mainMenu;
  }

  /**
   * Getter for the application.SaveMenu instance
   * 
   * @return instance of application.SaveMenu
   */
  protected static SaveMenu getSaveMenu() {
    return saveMenu;
  }

  /**
   * Getter for the application.AddMenu instance
   * 
   * @return instance of application.AddMenu
   */
  protected static AddMenu getAddMenu() {
    return addMenu;
  }

  /**
   * Getter for the Questionmenu instance
   * 
   * @return instance of QuestionMenu
   */
  protected static QuizMenu getQuizMenu() {
    return quizMenu;
  }

  /**
   * Getter for the application.StatisticsMenu instance
   *
   * @return instance of application.StatisticsMenu
   */
  protected static StatisticsMenu getStatisticsMenu() {
    return statisticsMenu;
  }

  /**
   * Creates the quizScene
   */
  protected static void setupQuizScene(ArrayList<Question.QuestionNode> questions) {
    if (quizScene == null) {
      quizScene = new Scene(quizMenu.initialize(questions), 700, 500);
    } else {
      quizScene.setRoot(quizMenu.initialize(questions));
    }
  }

  /**
   * Creates the statisticsScene
   *
   * @param numQuestions the number of questions in the quiz
   */
  protected static void setupStatisticsScene(int numQuestions) {
    if (statisticsScene == null) {
      statisticsScene = new Scene(statisticsMenu.initialize(numQuestions), 700, 500);
    } else {
      statisticsScene.setRoot(statisticsMenu.initialize(numQuestions));
    }
  }
}
