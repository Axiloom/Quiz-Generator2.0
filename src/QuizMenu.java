import javafx.beans.property.ReadOnlyProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.beans.value.ChangeListener;
import java.util.ArrayList;
import java.util.Optional;

/**
 * QuestionMenu Class constructs the GUI for the QuestionMenu to display the questions during the
 * quiz.
 * 
 * @author ATeam-99
 *
 */
public class QuizMenu extends Main {

  private Stage primaryStage; // stage being displayed on
  private Button next; // next button
  private Button quit; // quit button
  private BorderPane root; // BorderPane being constructed
  private int currentQuestion; // current question number of the quiz
  private Alert alert;
  private ArrayList<Question.QuestionNode> questions;
  private ArrayList<CheckBox> activeBoxes = new ArrayList<>();
  private ArrayList<CheckBox> boxes = new ArrayList<>();

  /**
   * QuestionMenu Constructor that declares the field variables and sets the background color
   *
   * @param primaryStage - stage being displayed on
   */
  public QuizMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    root = new BorderPane();
    next = new Button("NEXT");
    quit = new Button("QUIT");
    root.setStyle("-fx-background-color: #c0c0c5");
  }

  /**
   * Initializes a BorderPane of the QuestionMenu screen
   *
   * @return root - BorderPane of the QuestionMenu screen
   */
  public BorderPane initialize(ArrayList<Question.QuestionNode> questions) {

    this.questions = questions;

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
    Label label = new Label("Quiz");
    Label questionLabel = new Label("Question " + currentQuestion + "/" + questions.size());

    // Style
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    questionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Top Panel
    HBox topPanel = new HBox(label, questionLabel);
    topPanel.setPadding(new Insets(10, 50, 10, 50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    topPanel.setStyle("-fx-background-color: #9fb983");

    root.setTop(topPanel);
  }

  /**
   * Constructs the center panel in the BorderPane
   */
  private void setCenterPanel() {

    // Setup Question
    Label question = new Label(questions.get(currentQuestion).question);
    question.setWrapText(true);
    question.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Make checkbox only have 1 answer TODO: NOT SURE HOW THIS WORKS; GOT OFF GITHUB
    // https://stackoverflow.com/questions/51568622/restrict-checkboxes-checked-javafx
    // Number of different check boxes the user can select
    int maxNumberOfSelections = 1;
    ChangeListener<Boolean> listener = (o, oldValue, newValue) -> {
      // get checkbox containing property
      CheckBox cb = (CheckBox) ((ReadOnlyProperty) o).getBean();

      if (newValue) {
        activeBoxes.add(cb);
        if (activeBoxes.size() > maxNumberOfSelections) {
          // get first checkbox to be activated
          cb = activeBoxes.iterator().next();

          // unselect; change listener will remove
          cb.setSelected(false);
        }
      } else {
        activeBoxes.remove(cb);
      }
    };

    // setup possible answers
    for (int i = 0; i < questions.get(currentQuestion).options.size(); i++) {
      CheckBox checkBox = new CheckBox(questions.get(currentQuestion).options.get(i));
      checkBox.setFont(Font.font("Arial", FontWeight.BOLD, 15));
      checkBox.selectedProperty().addListener(listener);
      checkBox.setMaxWidth(400);
      boxes.add(checkBox);
    }

    ImageView image = new ImageView(new Image(QuizMenu.class.getResourceAsStream("no-image.png")));

    if (questions.get(currentQuestion).img != null) {
      image = questions.get(currentQuestion).img;
    }

    image.setPreserveRatio(true);
    image.setFitHeight(200);
    VBox answers = new VBox(boxes.get(0), boxes.get(1), boxes.get(2), boxes.get(3), boxes.get(4));
    VBox displayImage = new VBox(image);
    BorderPane answersAndPicture = new BorderPane();
    answersAndPicture.setLeft(answers);
    answersAndPicture.setRight(displayImage);

    // Format Box Location
    answers.setSpacing(25);
    answers.setPadding(new Insets(25, 0, 0, 0));
    answersAndPicture.setPadding(new Insets(0, 25, 0, 0));
    displayImage.setPadding(new Insets(25, 0, 0, 0));

    // Center Panel
    VBox questionAnswerBox = new VBox(question, answersAndPicture);
    questionAnswerBox.setPadding(new Insets(40, 0, 0, 15));
    questionAnswerBox.setSpacing(30);

    root.setCenter(questionAnswerBox);
  }

  /**
   * Constructs the bottom panel in the BorderPane
   */
  private void setBottomPanel() {
    // Style
    next.setPrefSize(100, 50);
    quit.setPrefSize(100, 50);

    // Listener for next button
    next.setOnAction(e -> {

      // Make sure an answer was selected
      if (activeBoxes.isEmpty()) {
        // Throw alert if no answer is selected
        alert = new Alert(Alert.AlertType.ERROR, "Please select an answer.");
        alert.setHeaderText("No answer selected.");
        alert.showAndWait().filter(response -> response == ButtonType.OK);
      }

      else {
        // Update statistics if answer was correct
        if (activeBoxes.get(0).getText().equals(questions.get(currentQuestion).answer)) {
          Main.getStatisticsMenu().updateCount();
          Main.getStatisticsMenu().initialize(questions.size());
        }

        // Clear all check boxes
        activeBoxes.clear();
        boxes.clear();

        // Exit if last question was answered
        if (currentQuestion == questions.size()-1) {
          primaryStage.setScene(Main.getStatisticsScene());
        }
        else {
          currentQuestion++;
          Main.getQuizMenu().initialize(questions);
        }
      }
    });

    // Listener for quit button
    quit.setOnAction(e -> {

      alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?");
      alert.setHeaderText("Quit Quiz?");
      Optional<ButtonType> button = alert.showAndWait();

      if (button.get().equals(ButtonType.OK)) { // quits
        primaryStage.setScene(Main.getStatisticsScene());
      }
    });

    next.setOnMouseEntered(e -> next.setStyle("-fx-font-size: 14pt;"));
    next.setOnMouseExited(e -> next.setStyle("-fx-font-size: 12pt;"));
    quit.setOnMouseEntered(e -> quit.setStyle("-fx-font-size: 14pt;"));
    quit.setOnMouseExited(e -> quit.setStyle("-fx-font-size: 12pt;"));

    // Bottom Panel
    HBox bottomHBox = new HBox(quit, next);
    bottomHBox.setSpacing(300);
    bottomHBox.setPadding(new Insets(0, 0, 45, 100));

    root.setBottom(bottomHBox);
  }
}
