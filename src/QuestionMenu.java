import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.beans.value.ChangeListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * QuestionMenu Class constructs the GUI for the QuestionMenu to display the questions during the
 * quiz.
 * 
 * @author ATeam-99
 *
 */
public class QuestionMenu extends Main implements EventHandler<ActionEvent> {

  private Stage primaryStage; // stage being displayed on
  private Button next; // next button
  private BorderPane root; // BorderPane being constructed
  private int numQuestions; // number of questions in the current quiz
  private int currQuestion; // current question number of the quiz

  /**
   * QuestionMenu Constructor that declares the field variables and sets the background color
   * 
   * @param primaryStage - stage being displayed on
   */
  public QuestionMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    root = new BorderPane();
    next = new Button("NEXT");
    root.setStyle("-fx-background-color: #c0c0c5");
    numQuestions = 0;
    currQuestion = 0;
  }

  /**
   * Initializes a BorderPane of the QuestionMenu screen
   * 
   * @return root - BorderPane of the QuestionMenu screen
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
    Label label = new Label("Quiz");
    Label questionLabel = new Label("Question 1/" + numQuestions); // update with the questions

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
    // Question CheckBoxes
    ArrayList<CheckBox> activeBoxes = new ArrayList<>();
    ArrayList<CheckBox> boxes = new ArrayList<>();
    Label question = new Label("What is the best way to wrap a question around the page, "
        + "I looked on stackOverflow and couldnt find the answer?");
    // TODO replace with reading in Question.getQuestion(QuestionNode) possibly in a loop
    question.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Make checkbox only have 1 answer TODO: NOT SURE HOW THIS WORKS; GOT OFF GITHUB
    // https://stackoverflow.com/questions/51568622/restrict-checkboxes-checked-javafx
    int maxCount = 1;
    ChangeListener<Boolean> listener = (o, oldValue, newValue) -> {
      // get checkbox containing property
      CheckBox cb = (CheckBox) ((ReadOnlyProperty) o).getBean();

      if (newValue) {
        activeBoxes.add(cb);
        if (activeBoxes.size() > maxCount) {
          // get first checkbox to be activated
          cb = activeBoxes.iterator().next();

          // unselect; change listener will remove
          cb.setSelected(false);
        }
      } else {
        activeBoxes.remove(cb);
      }
    };

    // TODO: REMOVE WHEN WE GET question.options working

    for (int i = 0; i < 5; i++) {
      CheckBox checkBox = new CheckBox("Question" + (i + 1));
      checkBox.setFont(Font.font("Arial", FontWeight.BOLD, 15));
      checkBox.selectedProperty().addListener(listener);
      boxes.add(checkBox);
    }

    // Replace above with this when we get this working
    // for (int i = 0 ; i < question.options.size() ; i++){
    // CheckBox checkBox = new CheckBox(question.options.get(i));
    // checkBox.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    // checkBox.selectedProperty().addListener(listener);
    // boxes.add(checkBox);
    // }

    ImageView image =
        new ImageView(new Image(QuestionMenu.class.getResourceAsStream("example.jpg")));
    VBox answers = new VBox(boxes.get(0), boxes.get(1), boxes.get(2), boxes.get(3), boxes.get(4));
    VBox displayImage = new VBox(image);
    HBox answersAndPicture = new HBox(answers, displayImage);

    // Format Box Location
    answers.setSpacing(25);
    answersAndPicture.setSpacing(250);
    answersAndPicture.setPadding(new Insets(25, 0, 0, 0));
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

    // Listeners
    next.setOnAction(this);
    next.setOnMouseEntered(e -> next.setStyle("-fx-font-size: 14pt;"));
    next.setOnMouseExited(e -> next.setStyle("-fx-font-size: 12pt;"));

    // Bottom Panel
    HBox bottomHBox = new HBox(next);
    bottomHBox.setPadding(new Insets(0, 0, 45, 525));

    root.setBottom(bottomHBox);
  }

  /**
   * Invoked when a specific event of the type for which this handler is registered happens.
   *
   * @param event the event which occurred
   */
  public void handle(ActionEvent event) {

    if (event.getSource() == next && currQuestion == numQuestions) {
      primaryStage.setScene(Main.getStatisticsScene());
    }

    else if (event.getSource() == next) {
      // read through options (a,b,c,d, and f), detect which is true ( e.g. a.isSelected() ),
      // compare if its the correct answer, update Question.isCorrect()
      if (currQuestion == numQuestions) {
        primaryStage.setScene(Main.getStatisticsScene());
      } else {
        // display next question
      }
    }
  }
}
