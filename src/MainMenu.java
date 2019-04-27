import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * MainMenu Class constructs the GUI for the MainMenu to display how to add/load questions, save
 * current questions, select a topic and number of questions, as well as to start the quiz.
 * 
 * @author ATeam-99
 *
 */
public class MainMenu extends Main {

  private Stage primaryStage; // stage being displayed on
  private BorderPane root; // BorderPane being constructed
  private Button add; // add button
  private Button save; // save button
  private Button start; // start quiz button

  /**
   * MainMenu Constructor that declares the field variables
   * 
   * @param primaryStage - stage being displayed on
   */
  public MainMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    root = new BorderPane();
    add = new Button("Add/Load Questions");
    save = new Button("Save Questions");
    start = new Button("START QUIZ");
  }

  // Initalize MainMenu
  public BorderPane initialize() {

    setBackground();
    setTopPanel();
    setLeftPanel();
    setRightPanel();
    setBottomPanel();
    
    return root;
  }
  
  /**
   * Sets the background to an image
   */
  private void setBackground() {
    BackgroundSize bSize =
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
    Image image1 = new Image("uwCrest_Dope.png");
    Background background = new Background(new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
    
    root.setBackground(background);
  }
  
  /**
   * Constructs the top panel in the BorderPane
   */
  private void setTopPanel() {
    // Labels
    Label label = new Label("Main Menu");
    Label numQuestions = new Label(getQuestion().getSize() + " questions available");

    // Style
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Top Panel
    HBox topPanel = new HBox(label, numQuestions);
    topPanel.setPadding(new Insets(10, 50, 10, 50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    topPanel.setStyle("-fx-background-color: #9fb983");
    
    root.setTop(topPanel);
  }
  
  /**
   * Constructs the left panel in the BorderPane
   */
  private void setLeftPanel() {
    // Style
    add.setPrefWidth(180);
    save.setPrefWidth(180);
    
    // Listeners
    save.setOnAction(event -> primaryStage.setScene(Main.getSaveScene()));
    add.setOnAction(event -> primaryStage.setScene(Main.getSaveScene()));
    
    // Left Panel
    VBox leftVBox = new VBox(add, save);
    leftVBox.setAlignment(Pos.TOP_RIGHT);
    leftVBox.setPadding(new Insets(20, 0, 0, 20));
    
    root.setLeft(leftVBox);
  }
  
  /**
   * Constructs the right panel in the BorderPane
   */
  private void setRightPanel() {
    // Topics ComboBox
    ObservableList<String> topics =
        FXCollections.observableArrayList("Topic 1", "Topic 2", "Topic 3");
    ComboBox<String> topicBox = new ComboBox<>(topics);
    topicBox.setPromptText("Set Topic");
    topicBox.setPrefWidth(180);
    topicBox.setVisibleRowCount(5);

    // Number of Questions ComboBox
    ObservableList<String> questions =
        FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "n-1", "n");
    ComboBox<String> questionBox = new ComboBox<>(questions);
    questionBox.setPromptText("Set # Questions");
    questionBox.setPrefWidth(180);
    questionBox.setVisibleRowCount(5);
    
    // Right Panel
    VBox rightVBox = new VBox(topicBox, questionBox);
    rightVBox.setAlignment(Pos.TOP_RIGHT);
    rightVBox.setPadding(new Insets(20, 20, 0, 0));

    root.setRight(rightVBox);
  }
  
  /**
   * Constructs the bottom panel in the BorderPane
   */
  private void setBottomPanel() {
    // Style
    start.setPrefSize(200, 50);
    start.setOnMouseEntered(e -> start.setStyle("-fx-font-size: 14pt;"));
    start.setOnMouseExited(e -> start.setStyle("-fx-font-size: 12pt;"));

    // Listeners
    start.setOnAction(event -> primaryStage.setScene(Main.getQuizScene()));
    
    // Bottom Panel
    HBox bottomHBox = new HBox(start);
    bottomHBox.setPadding(new Insets(0, 0, 65, 0));
    bottomHBox.setAlignment(Pos.CENTER);
    
    root.setBottom(bottomHBox);
  }
}
