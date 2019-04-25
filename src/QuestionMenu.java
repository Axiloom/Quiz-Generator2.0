import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
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

import java.io.FileInputStream;

public class QuestionMenu extends Main implements EventHandler<ActionEvent> {
  
  private Stage primaryStage;
  private Button next;
  private int numQuestions;
  private int currQuestion;
  private CheckBox answer1;
  private CheckBox answer2;
  private CheckBox answer3;
  private CheckBox answer4;
  private CheckBox answer5;
  private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
  private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
  private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);
  private final int maxNumSelected = 1;

  public QuestionMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    next = new Button("NEXT");
    numQuestions = 0;
    currQuestion = 0;
    
  }
  
  public BorderPane initialize(Question.QuestionNode question) {

    // Labels
    Label label = new Label("Quiz"); // update with the questions
    Label numQuestions = new Label("Question 1/N");

    // Style
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Question Image
//    Image img = new Image("200x200black.png");
//    ImageView questionImg = new ImageView(img);

    // Question options
    CheckBox a = new CheckBox();
    CheckBox b = new CheckBox();
    CheckBox c = new CheckBox();
    CheckBox d = new CheckBox();
    CheckBox f = new CheckBox();
    
    Label question = new Label("What is the best way to wrap a question around the page?");
    Label opt1 = new Label("option 1");
    Label opt2 = new Label("option 2");
    Label opt3 = new Label("option 3");
    Label opt4 = new Label("option 4");
    Label opt5 = new Label("option 5");
    question.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    opt1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    opt2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    opt3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    opt4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    opt5.setFont(Font.font("Arial", FontWeight.BOLD, 15));

    HBox a1 = new HBox(a, opt1);
    a1.setSpacing(25);
    HBox a2 = new HBox(b, opt2);
    a2.setSpacing(25);
    HBox a3 = new HBox(c, opt3);
    a3.setSpacing(25);
    HBox a4 = new HBox(d, opt4);
    a4.setSpacing(25);
    HBox a5 = new HBox(f, opt5);
    a5.setSpacing(25);

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
    question.setText("Hello My name is john lkajsdf asldk asdk falksd flka sdka asdf asd  as df a" +
            " ds df a sd f a sdf a sdf asd lf Hello");

    ImageView image = new ImageView(new Image(QuestionMenu.class.getResourceAsStream("example.jpg")));

    VBox questionAnswerBox = new VBox(
            question,
            // All in left VBox
            new HBox(a, opt1),
            new HBox(b, opt2),
            new HBox(c, opt3),
            new HBox(d, opt4),
            new HBox(f, opt5),
            // In right VBox
            image);

    question.setWrapText(true);

    questionAnswerBox.setPadding(new Insets(40,0,0,15));
    questionAnswerBox.setSpacing(30);

    root.setCenter(questionAnswerBox);

    // Bottom Panel
    HBox bottomHBox = new HBox(next);
    bottomHBox.setPadding(new Insets(0,0,65,300));
    root.setBottom(bottomHBox);

    return root;
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
