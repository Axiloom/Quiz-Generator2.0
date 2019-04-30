
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * AddMenu Class constructs the GUI for the AddMenu to display how to add or load questions
 * 
 * @author ATeam-99
 *
 */
public class AddMenu extends Main {

  private Stage primaryStage; // stage being displayed on
  private BorderPane root; // BorderPane being constructed
  private Button back; // back button
  private Button submit; // submit button
  private TextField topic; // records the question topic
  private TextField question; // records the question text
  private TextField answer; // records the answer to the question
  private TextField picture;
  private TextField option1; // records alternative choice
  private TextField option2; // records alternative choice
  private TextField option3; // records alternative choice
  private TextField option4; // records alternative choice
  private TextField jsonLoad; // file name to load
  private Alert alert; // alert for failure to load files
  private FileChooser jsonFileChooser;
  private FileChooser pictureFileChooser;
  private Button jsonFileChooserButton;
  private Button pictureFileChooserButton;
  private File pictureFile;
  private File jsonFile;

  /**
   * AddMenu Constructor that declares the field variables and sets the background color
   * 
   * @param primaryStage - stage being displayed on
   */
  public AddMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    root = new BorderPane();
    submit = new Button("SUBMIT");
    back = new Button("BACK");
    topic = new TextField("");
    question = new TextField("");
    answer = new TextField("");
    picture = new TextField("");
    option1 = new TextField("");
    option2 = new TextField("");
    option3 = new TextField("");
    option4 = new TextField("");
    jsonLoad = new TextField("");
    jsonFileChooser = new FileChooser();
    pictureFileChooser = new FileChooser();
    jsonFileChooserButton = new Button("Browse...");
    pictureFileChooserButton = new Button("Browse...");

    root.setStyle("-fx-background-color: #c0c0c5");
  }

  /**
   * Initializes a BorderPane of the AddMenu screen
   * 
   * @return root - BorderPane of the AddMenu screen
   */
  public BorderPane initialize() {

    setTopPanel();
    setCenterPanel();
    setBottomPanel();

    return root;
  }

  /**
   * Constructs the top panel in the BorderPane
   */
  private void setTopPanel() {
    // Labels
    Label label = new Label("Add Menu");
    String qLabel = "" + getQuestion().getSize();
    if(getQuestion().getSize() == 1) {
      qLabel += " question available";
    } else {
      qLabel += " questions available";
    }
    Label qAvailableLabel = new Label(qLabel);
    
    // Style
    qAvailableLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Top Panel
    HBox topPanel = new HBox(label, qAvailableLabel);
    topPanel.setPadding(new Insets(10, 50, 10, 50));
    topPanel.setSpacing(150);
    topPanel.setAlignment(Pos.CENTER);
    topPanel.setStyle("-fx-background-color: #9fb983");

    root.setTop(topPanel);
  }

  /**
   * Constructs the center panel in the BorderPane
   */
  private void setCenterPanel() {
    Label topicLabel = new Label("Enter Topic: ");
    BorderPane topicPane = new BorderPane();
    topicPane.setLeft(topicLabel);
    topicPane.setRight(topic);
    topicPane.setMaxWidth(320);

    Label questionLabel = new Label("Enter Question: ");
    BorderPane questionPane = new BorderPane();
    questionPane.setLeft(questionLabel);
    questionPane.setRight(question);
    questionPane.setMaxWidth(320);

    Label pictureLabel = new Label("Load Picture: ");
    HBox pictureHBox = new HBox(pictureLabel,picture,pictureFileChooserButton);
    pictureFileChooser.setTitle("Browse for picture");
    picture.setTooltip(new Tooltip("picture must be located in the same directory as .jar"));
    pictureFileChooserButton.setTooltip(new Tooltip("picture must be located in the same directory as .jar"));
    pictureLabel.setPrefWidth(149);
    picture.setPrefWidth(171);
    pictureHBox.setPadding(new Insets(0,0,10,0));

    Label answerLabel = new Label("Enter Answer: ");
    BorderPane answerPane = new BorderPane();
    answerPane.setLeft(answerLabel);
    answerPane.setRight(answer);
    answerPane.setMaxWidth(320);
    
    Label optionsLabel = new Label("Enter Other Options: ");
    BorderPane optionPane = new BorderPane();
    optionPane.setLeft(optionsLabel);
    optionsLabel.setWrapText(true);
    optionsLabel.setPrefWidth(100);
    VBox optionsBox = new VBox(option1, option2, option3, option4);
    optionPane.setRight(optionsBox);
    optionPane.setMaxWidth(320);

    Label or = new Label("OR");
    or.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    or.setPadding(new Insets(20, 0, 20, 0));

    // Set project directory for browse window todo not sure whats wrong, but this doesnt work with executable
//    try {
//      jsonFileChooser.setInitialDirectory(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
//    }
//    catch (URISyntaxException e){
//      e.printStackTrace();
//      System.out.println("Error while retrieving project directory");
//    }

    Label loadLabel = new Label("Load JSON: ");
    Label typeLabel = new Label(".json");
    typeLabel.setFont(Font.font("Arial", 14));
    HBox loadHBox = new HBox(loadLabel,jsonLoad,typeLabel,jsonFileChooserButton);
    jsonFileChooser.setTitle("Browse for .json");
    jsonLoad.setTooltip(new Tooltip(".json must be located in the same directory as .jar"));
    jsonFileChooserButton.setTooltip(new Tooltip(".json must be located in the same directory as .jar"));
    typeLabel.setPadding(new Insets(10,20,0,0));
    loadLabel.setPrefWidth(150);
    jsonLoad.setPrefWidth(170);

    // Center Panel
    VBox topVBox = new VBox(topicPane, questionPane, pictureHBox, answerPane, optionPane);
    VBox bottomVBox = new VBox(loadHBox);
    bottomVBox.setSpacing(10);
    VBox centerVBox = new VBox(topVBox, or, bottomVBox);
    centerVBox.setPadding(new Insets(50, 20, 30, 200));

    // Listeners
    jsonFileChooserButton.setOnAction(event -> {
      jsonFile = jsonFileChooser.showOpenDialog(primaryStage);
      if (jsonFile != null && jsonFile.exists()){
        jsonLoad.setText(jsonFile.getName().substring(0,jsonFile.getName().indexOf(".")));
      }
    });

    pictureFileChooserButton.setOnAction(event -> {
      pictureFile = pictureFileChooser.showOpenDialog(primaryStage);
      if (pictureFile != null && pictureFile.exists())
        picture.setText(pictureFile.getName());
    });

    root.setCenter(centerVBox);
  }

  /**
   * Constructs the bottom panel in the BorderPane
   */
  private void setBottomPanel() {
    // Style
    back.setPrefSize(100, 50);
    submit.setPrefSize(100, 50);
    back.setOnMouseEntered(e -> back.setStyle("-fx-font-size: 14pt;"));
    back.setOnMouseExited(e -> back.setStyle("-fx-font-size: 12pt;"));
    submit.setOnMouseEntered(e -> submit.setStyle("-fx-font-size: 14pt;"));
    submit.setOnMouseExited(e -> submit.setStyle("-fx-font-size: 12pt;"));

    // Back Listener
    back.setOnAction(event -> {
      topic.clear();
      question.clear();
      answer.clear();
      option1.clear();
      option2.clear();
      option3.clear();
      option4.clear();
      jsonLoad.clear();
      picture.clear();
      primaryStage.setScene(Main.getMainScene());
    });

    // Submit Listener
    submit.setOnAction(event -> {
      // Add question
      if (!topic.getText().equals("") && !question.getText().equals("")
          && !answer.getText().equals("") && !option1.getText().equals("")
          && !option2.getText().equals("") && !option3.getText().equals("")
          && !option4.getText().equals("") && !picture.getText().equals("")
              && jsonLoad.getText().equals("")) {
        ArrayList<String> options = new ArrayList<>();
        options.add(answer.getText());
        options.add(option1.getText());
        options.add(option2.getText());
        options.add(option3.getText());
        options.add(option4.getText());

        if (picture.getText().equals("")){
          picture.setText("none");
        }

        super.getQuestion().addQuestion(topic.getText(), question.getText(), "", options,
            answer.getText(), picture.getText());
        alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added question!");
        alert.setHeaderText("Success.");
        alert.showAndWait().filter(response -> response == ButtonType.OK);

        // Clear TextFields
        topic.clear();
        question.clear();
        answer.clear();
        option1.clear();
        option2.clear();
        option3.clear();
        option4.clear();
        jsonLoad.clear();
        picture.clear();

        // Update menu displays
        super.getMainMenu().initialize();
        super.getAddMenu().initialize();
        super.getSaveMenu().initialize();
      } // Load Questions
      else if (topic.getText().equals("") && question.getText().equals("")
          && answer.getText().equals("") && option1.getText().equals("")
          && option2.getText().equals("") && option3.getText().equals("")
          && option4.getText().equals("") && picture.getText().equals("")
          && !jsonLoad.getText().equals("")) {
        try {

          super.getQuestion().loadJSON(jsonLoad.getText() + ".json");
          jsonLoad.clear();
          // Update menu displays
          super.getMainMenu().initialize();
          super.getAddMenu().initialize();
          super.getSaveMenu().initialize();
          alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added from JSON!");
          alert.setHeaderText("Success.");
          alert.showAndWait().filter(response -> response == ButtonType.OK);
        } catch (Exception e) {
          // Throw alert if failure to load file
          alert = new Alert(Alert.AlertType.CONFIRMATION, "Error: " + e.getMessage());
          alert.setHeaderText("Error loading file.");
          alert.showAndWait().filter(response -> response == ButtonType.OK);
          jsonLoad.clear();
        }
      } // Trying to add and load at same time
      else if (!topic.getText().equals("") && !question.getText().equals("")
          && !answer.getText().equals("") && !option1.getText().equals("")
          && !option2.getText().equals("") && !option3.getText().equals("")
          && !option4.getText().equals("") && !picture.getText().equals("")
              && !jsonLoad.getText().equals("")) {

        // Throw alert if add question and parse json are entered
        alert = new Alert(Alert.AlertType.WARNING,
            "Cannot import json and add question at the same " + "time");
        alert.setHeaderText("Error adding question.");
        alert.showAndWait().filter(response -> response == ButtonType.OK);
      } else {
        // Throw alert if failure to add question
        alert = new Alert(Alert.AlertType.WARNING, "Enter all fields to add question.");
        alert.setHeaderText("Error adding question.");
        alert.showAndWait().filter(response -> response == ButtonType.OK);
      }
      primaryStage.setScene(Main.getAddScene());
    });

    // Bottom Panel
    HBox bottomHBox = new HBox(back, submit);
    bottomHBox.setPadding(new Insets(0, 100, 65, 200));
    bottomHBox.setSpacing(100);

    root.setBottom(bottomHBox);
  }
}
