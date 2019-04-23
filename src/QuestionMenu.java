import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class QuestionMenu extends Main implements EventHandler<ActionEvent> {
  
  private Stage primaryStage;
  private Button next;
  private int numQuestions;
  private int currQuestion;

  public QuestionMenu(Stage primaryStage, int numQuestions) {
    this.primaryStage = primaryStage;
    next = new Button("NEXT");
    numQuestions = 10;
    currQuestion = 0;
  }
  
  public BorderPane initialize() {
    Label label = new Label("Question 1/N"); // update with the questions
    label.setFont(Font.font("Arial", FontWeight.BOLD, 22));
    
    BorderPane root = new BorderPane();
    
    // Set background color of root
    root.setStyle("-fx-background-color: #c0c0c5");
    
    next.setOnAction(this);
    
    next.setPrefSize(100,50);
    
    HBox bottomHBox = new HBox(next);
    bottomHBox.setPadding(new Insets(0,0,40,300));
    
    root.setBottom(bottomHBox);
    root.setLeft(label);

    return root;
  }
  
  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  public void handle(ActionEvent event) {
    StatisticsMenu statsMenu = new StatisticsMenu(primaryStage);
    Scene statsScene = new Scene(statsMenu.initialize(),500,500);
    if (event.getSource() == next && currQuestion == numQuestions) {
      primaryStage.setScene(statsScene);
    } 
    else if(event.getSource() == next) {
      primaryStage.setScene(statsScene);
      //TODO change this so that it goes to next question somehow...instead of statsScene
    }
  }
}
