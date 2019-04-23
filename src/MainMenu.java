import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Main implements EventHandler<ActionEvent> {

  private Stage primaryStage;
  private Scene saveScene;
  private Scene addScene;
  private Scene quizScene;
  private Button add;
  private Button save;
  private Button start;

  // Constructor
  public MainMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    SaveMenu saveMenu = new SaveMenu(primaryStage);
    AddMenu addMenu = new AddMenu(primaryStage);
    QuestionMenu quiz = new QuestionMenu(primaryStage, 10); // updates with numQuestions selected
    saveScene = new Scene(saveMenu.initialize(), 500,500);
    addScene = new Scene(addMenu.initialize(),500, 500);
    quizScene = new Scene(quiz.initialize(),500, 500);
    add = new Button(" Add/Load Questions ");
    save = new Button("      Save Questions    ");
    start = new Button("START");
  }

  // Initalize MainMenu
  public BorderPane initialize() {

    // Create Labels
    Label label = new Label("Main Menu");
    Label numQuestions = new Label("N Questions available");

    // Styling
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Drop down list of topics
    ObservableList<String> topics =
        FXCollections.observableArrayList("Topic 1", "Topic 2", "Topic 3");
    ComboBox<String> topicBox = new ComboBox<>(topics);
    topicBox.setPromptText("Set Topic  ");
    topicBox.setPadding(new Insets(0,0,0,40));

    // BorderPane to add buttons to
    BorderPane root = new BorderPane();
    
    // Set background color of root
    root.setStyle("-fx-background-color: #c0c0c5");

    // Handlers for buttons
    save.setOnAction(this);
    add.setOnAction(this);
    start.setOnAction(this);

    // Create boxes
    VBox centerVBox = new VBox(add, save, topicBox);
    centerVBox.setPadding(new Insets(160,0,0,80));
    HBox bottomHBox = new HBox(start);
    bottomHBox.setPadding(new Insets(0,0,30,400));

    // Place boxes on screen
    root.setCenter(centerVBox);
    root.setLeft(label);
    root.setRight(numQuestions);
    root.setBottom(bottomHBox);

    // return this menu
    return root;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  public void handle(ActionEvent event) {
    if (event.getSource() == save)
      primaryStage.setScene(saveScene);

    else if (event.getSource() == add)
      primaryStage.setScene(addScene);

    else if (event.getSource() == start)
      primaryStage.setScene(quizScene);
  }
}
