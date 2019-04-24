import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SaveMenu extends Main implements EventHandler<ActionEvent> {

  private Stage primaryStage;
  private Button back;
  private Button submit;

  // Constructor
  public SaveMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    back = new Button("BACK");
    submit = new Button("SUBMIT");
  }

  public BorderPane initialize(){

    Label label = new Label("Save Menu");
    Label numQuestions = new Label("N Questions available");

    // Styling
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Pane to hold everything
    BorderPane root = new BorderPane();

    // Buttons/Labels
    Label jsonLabel = new Label("Save as JSON:");
    TextField jsonName = new TextField("Enter JSON File here");

    // Listeners
    back.setOnAction(this);
    submit.setOnAction(this);

    // Scroll-over effect
    back.setOnMouseEntered(e -> back.setStyle("-fx-font-size: 14pt;"));
    back.setOnMouseExited(e -> back.setStyle("-fx-font-size: 12pt;"));
    submit.setOnMouseEntered(e -> submit.setStyle("-fx-font-size: 14pt;"));
    submit.setOnMouseExited(e -> submit.setStyle("-fx-font-size: 12pt;"));
    
    // Set Button sizes
    back.setPrefSize(100,50);
    submit.setPrefSize(100,50);

    // Set background color of root
    root.setStyle("-fx-background-color: #c0c0c5");

    // Top Panel
    HBox topPanel = new HBox(label, numQuestions);
    topPanel.setPadding(new Insets(10,50,10,50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    root.setTop(topPanel);
    topPanel.setStyle("-fx-background-color: #9fb983");

    // Center Panel
    VBox centerPanel = new VBox(jsonLabel,jsonName);
    centerPanel.setPadding(new Insets(150,100,50,100));
    root.setCenter(centerPanel);

    // Bottom Panel
    HBox bottomPanel = new HBox(back,submit);
    bottomPanel.setPadding(new Insets(0,100,65,100));
    bottomPanel.setSpacing(100);
    root.setBottom(bottomPanel);

    return root;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {

    if (event.getSource() == back){
      primaryStage.setScene(Main.getMainScene());
    }

    else if(event.getSource() == submit) {
      primaryStage.setScene(Main.getMainScene());
      //TODO actually save the data some time
    }
  }
}
