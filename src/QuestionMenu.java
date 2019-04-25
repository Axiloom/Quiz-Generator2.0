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
import javafx.scene.layout.Background;
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

public class QuestionMenu extends Main implements EventHandler<ActionEvent> {
  
  private Stage primaryStage;
  private Button next;
  private int numQuestions;
  private int currQuestion;
  Question.QuestionNode question;

  public QuestionMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    next = new Button("NEXT");
    numQuestions = 0;
    currQuestion = 0;
    
  }
  
  public BorderPane initialize() {

    // Labels
    Label label = new Label("Quiz");
    Label numQuestions = new Label("Question 1/N"); // update with the questions

    // Style
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Question checkboxes
    ArrayList<CheckBox> activeBoxes = new ArrayList<>();
    ArrayList<CheckBox> boxes = new ArrayList<>();

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
    for (int i = 0 ; i < 5 ; i++){
      CheckBox checkBox = new CheckBox("Answer " + (i + 1));
      checkBox.setFont(Font.font("Arial", FontWeight.BOLD, 15));
      checkBox.selectedProperty().addListener(listener);
      checkBox.setWrapText(true);
      boxes.add(checkBox);
    }

    // TODO: Replace above with this when we get question.options working
//    for (int i = 0 ; i < question.options.size() ; i++){
//      CheckBox checkBox = new CheckBox(question.options.get(i));
//      checkBox.setFont(Font.font("Arial", FontWeight.BOLD, 15));
//      checkBox.selectedProperty().addListener(listener);
//      boxes.add(checkBox);
//    }
    
    Label questionExample = new Label("What kind of wood would a wood chuck ckuck, if a woodchuck" +
            " could chuck wood?");

    questionExample.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // main pane
    BorderPane root = new BorderPane();
    
    // Set background color of root
    root.setStyle("-fx-background-color: #c0c0c5");

    // Listeners
    next.setOnAction(this);
    
    // Scroll-over effect
    next.setOnMouseEntered(e -> next.setStyle("-fx-font-size: 14pt;"));
    next.setOnMouseExited(e -> next.setStyle("-fx-font-size: 12pt;"));

    // Set button size
    next.setPrefSize(100,50);

    // Top Panel
    HBox topPanel = new HBox(label, numQuestions);
    topPanel.setPadding(new Insets(10,50,10,50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    root.setTop(topPanel);
    topPanel.setStyle("-fx-background-color: #9fb983");

    // Center Panel

    // Image
    ImageView getImage =
            new ImageView(new Image(QuestionMenu.class.getResourceAsStream("no-image.png")));
    getImage.setPreserveRatio(true);
    getImage.setFitHeight(150);

    // Different Boxes
    VBox image = new VBox(getImage);
    VBox answers = new VBox(boxes.get(0), boxes.get(1), boxes.get(2), boxes.get(3), boxes.get(4));
    HBox answersAndPicture = new HBox(answers,image);
    VBox questionAnswerBox = new VBox(questionExample, answersAndPicture);

    // Allow Text Wrapping for question
    questionExample.setWrapText(true);
    questionExample.setPrefWidth(root.getPrefWidth());
    questionExample.setPrefHeight(Integer.MAX_VALUE);

    // Move Boxes to look good on screen
    answers.setSpacing(25);
    answersAndPicture.setSpacing(100);
    answersAndPicture.setPadding(new Insets(25,0,0,0));
    questionAnswerBox.setPadding(new Insets(5,10,0,10));
    questionAnswerBox.setSpacing(5);

    // add to main pane
    root.setCenter(questionAnswerBox);

    // Bottom Panel
    HBox bottomHBox = new HBox(next);
    bottomHBox.setPadding(new Insets(0,0,65,300));
    root.setBottom(bottomHBox);

    return root;
  }

  /**
   * Set next question to be asked
   * @param question the current question being asked
   */
  public void setQuestion(Question.QuestionNode question){
    this.question = question;
  }
  
  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  public void handle(ActionEvent event) {

    if (event.getSource() == next && currQuestion == numQuestions) {
      primaryStage.setScene(Main.getStatisticsScene());
    }

    else if(event.getSource() == next) {
      // read through options (a,b,c,d, and f), detect which is true ( e.g. a.isSelected() ),
      // compare if its the correct answer, update Question.isCorrect()
      if(currQuestion == numQuestions) {
        primaryStage.setScene(Main.getStatisticsScene());
      } else {
        // display next question
      }
    }
  }
}
