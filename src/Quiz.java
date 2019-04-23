import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Quiz extends Main {
  Stage primaryStage;

  public Quiz(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public BorderPane initalize(){
    Button mainMenu = new Button("mainMenu");

    // BorderPane to add buttons to
    BorderPane root = new BorderPane();

    return root;
  }
}
