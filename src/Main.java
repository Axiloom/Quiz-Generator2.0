//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Quiz Generator
// Due: 25 April 2019
// Course: CS400, Spring 2019
//
// Author: John Bednarczyk, Joe Lessner, Josh Liberko, Shefali Mukerji, & Mitch Sutrick
// Email: jbednarczyk@wisc.edu, jalessner@wisc.edu, jliberko@wisc.edu, mukerji2@wisc.edu, &
//        sutrick@wisc.edu
// Lecturer's Name: Debra Deppeler
// Lecture #: 001
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////// Known Bugs //////////////////////////////////
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Initalize GUI
 * 
 * @author 
 *
 */
public class Main extends Application {

  private static Scene mainScene;
  private static Scene saveScene;
  private static Scene addScene;
  private static Scene questionScene;
  private static Scene statisticsScene;

  /**
   * TODO
   */
  @Override
  public void start(Stage primaryStage) {
    try {

      // Instances of menu's
      MainMenu mainMenu = new MainMenu(primaryStage);
      SaveMenu saveMenu = new SaveMenu(primaryStage);
      AddMenu addMenu = new AddMenu(primaryStage);
      QuestionMenu quizMenu = new QuestionMenu(primaryStage);
      StatisticsMenu statisticsMenu = new StatisticsMenu(primaryStage);
      Question question = new Question();

      // Create Scenes
      mainScene = new Scene(mainMenu.initialize(), 500, 500);
      saveScene = new Scene(saveMenu.initialize() , 500, 500);
      addScene = new Scene(addMenu.initialize(), 500, 500);
      questionScene = new Scene(quizMenu.initialize(), 500, 500);
      statisticsScene = new Scene(statisticsMenu.initialize(), 500, 500);

      // Style for main menu
      mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

      // present stage
      primaryStage.setScene(mainScene);
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();
      primaryStage.resizableProperty().setValue(false);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Getter for main scene
   *
   * @return the main scene
   */
  protected static Scene getMainScene(){
    return mainScene;
  }

  /**
   * Getter for save scene
   *
   * @return the save scene
   */
  protected static Scene getSaveScene(){
    return saveScene;
  }

  /**
   * Getter for add scene
   *
   * @return the add scene
   */
  protected static Scene getAddScene(){
    return addScene;
  }

  /**
   * Getter for quiz scene
   *
   * @return the quiz scene
   */
  protected static Scene getQuizScene(){
    return questionScene;
  }

  /**
   * Getter for statistics scene
   *
   * @return the statistics scene
   */
  protected static Scene getStatisticsScene(){
    return statisticsScene;
  }
}