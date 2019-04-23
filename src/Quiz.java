import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Quiz extends Main {
  Stage primaryStage;

  public Quiz(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public BorderPane initialize(){

    // Create Labels
    Label label = new Label("Main Menu");
    Label numQuestions = new Label("N Questions available");

    // BorderPane to add buttons to
    BorderPane root = new BorderPane();

    // Top Panel
    HBox topPanel = new HBox(label, numQuestions);
    topPanel.setPadding(new Insets(10,50,10,50));
    topPanel.setSpacing(150);
    root.setTop(topPanel);
    topPanel.setStyle("-fx-background-color: #9fb983");

    return root;
  }
}
