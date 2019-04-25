import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Main implements EventHandler<ActionEvent> {

  private Stage primaryStage;
  private Button add;
  private Button save;
  private Button start;

  // Constructor
  public MainMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    add = new Button("Add/Load Questions");
    save = new Button("Save Questions");
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
    topicBox.setPromptText("Set Topic");
    topicBox.setPrefWidth(180);
    topicBox.setVisibleRowCount(5);

    // Drop down list of questions
    ObservableList<String> questions =
        FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "n-1", "n");
    ComboBox<String> questionBox = new ComboBox<>(questions);
    questionBox.setPromptText("Set # Questions");
    questionBox.setPrefWidth(180);
    questionBox.setVisibleRowCount(5);

    // BorderPane to add buttons to
    BorderPane root = new BorderPane();

    // Set background color of root
    // root.setStyle("-fx-background-color: #c0c0c5");

//    // Josh Edits FIXME
//    BackgroundSize bSize =
//        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
//    Image image1 = new Image("uwCrest_Dope.png");
//    Background background = new Background(new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT,
//        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
//    root.setBackground(background);
//    // Close Josh FIXME


    add.setPrefWidth(180);
    save.setPrefWidth(180);

    // Handlers for buttons
    save.setOnAction(this);
    add.setOnAction(this);
    start.setOnAction(this);

    // Scroll-over effect
    start.setOnMouseEntered(e -> start.setStyle("-fx-font-size: 14pt;"));
    start.setOnMouseExited(e -> start.setStyle("-fx-font-size: 12pt;"));

    start.setPrefSize(100, 50);

    // Top Panel
    HBox topPanel = new HBox(label, numQuestions);
    topPanel.setPadding(new Insets(10, 50, 10, 50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    root.setTop(topPanel);
    topPanel.setStyle("-fx-background-color: #9fb983");

    // Center Panel (GOOD)
    VBox topRightVBox = new VBox(add, save);
    VBox bottomRightVBox = new VBox(topicBox, questionBox);
    topRightVBox.setAlignment(Pos.TOP_RIGHT);
    bottomRightVBox.setAlignment(Pos.TOP_RIGHT);

    topRightVBox.setPadding(new Insets(20, 0, 0, 20)); // set padding alignments (top left box)
    bottomRightVBox.setPadding(new Insets(20, 20, 0, 0)); // top right box

    root.setLeft(topRightVBox);
    root.setRight(bottomRightVBox);

    // Bottom Panel
    HBox bottomHBox = new HBox(start);
    bottomHBox.setPadding(new Insets(0, 0, 65, 0));
    bottomHBox.setAlignment(Pos.CENTER);
    root.setBottom(bottomHBox);

    // return this menu
    return root;
  }

  /**
   * Invoked when a specific event of the type for which this handler is registered happens.
   *
   * @param event the event which occurred
   */
  public void handle(ActionEvent event) {
    if (event.getSource() == save)
      primaryStage.setScene(Main.getSaveScene());

    else if (event.getSource() == add)
      primaryStage.setScene(Main.getAddScene());

    else if (event.getSource() == start)
      primaryStage.setScene(Main.getQuizScene());
  }
}
