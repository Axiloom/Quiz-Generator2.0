import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
    Quiz quiz = new Quiz(primaryStage);
    saveScene = new Scene(saveMenu.initalize(), 500,500);
    addScene = new Scene(addMenu.initialize(),500, 500);
    quizScene = new Scene(quiz.initalize(),500, 500);
    add = new Button("  Add Questions  ");
    save = new Button("  Save Questions ");
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
    topicBox.setPromptText("  Set Topic");

    // BorderPane to add buttons to
    BorderPane root = new BorderPane();

    // Handlers for buttons
    save.setOnAction(this);
    add.setOnAction(this);
    start.setOnAction(this);

    // Images to alter screen
    Image one = new Image("100x100blank.png");
    Image two = new Image("150x150blank.png");
    Image three = new Image("100x300blank.png");

    // Create imageViews for GUI
    ImageView img1 = new ImageView(two);
    ImageView img2 = new ImageView(three);
    ImageView img3 = new ImageView(one);

    // Create boxes
    VBox leftVBox = new VBox(label, img1);
    VBox rightVBox = new VBox(numQuestions, img2, start);
    VBox centerVBox = new VBox(img3, add, save, topicBox);

    // Place boxes on screen
    root.setCenter(centerVBox);
    root.setLeft(leftVBox);
    root.setRight(rightVBox);

    // return this Main
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
