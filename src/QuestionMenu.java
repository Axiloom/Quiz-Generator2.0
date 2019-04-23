import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuestionMenu {
  
  private Stage primaryStage;
  private Button stats;

  public QuestionMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    stats = new Button("STATISTICS");
  }
  
  public BorderPane initialize() {
    Label label = new Label("Question 1/X");
    BorderPane root = new BorderPane();

    Image one = new Image("150x50blank.png");
    ImageView img = new ImageView(one);
    
    VBox leftVBox = new VBox(label);
    VBox bottomVBox = new VBox(stats);
    
    root.setBottom(bottomVBox);
    root.setLeft(leftVBox);

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
    if (event.getSource() == stats)
      primaryStage.setScene(statsScene);
  }
}
