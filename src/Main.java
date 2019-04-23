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

  /**
   * TODO
   */
  @Override
  public void start(Stage primaryStage) {
    try {

      // Instances of menu's
      MainMenu mainMenu = new MainMenu(primaryStage);

      // Create Scenes
      Scene mainScene = new Scene(mainMenu.initialize(), 500, 500);

      // Style for main menu
      //mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

      // present stage
      primaryStage.setScene(mainScene);
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
