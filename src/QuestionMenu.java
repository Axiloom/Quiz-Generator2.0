import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class QuestionMenu extends Main implements EventHandler<ActionEvent> {
  
  private Stage primaryStage;
  private Button next;
  private int numQuestions;
  private int currQuestion;

  public QuestionMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    next = new Button("NEXT");
    numQuestions = 0;
    currQuestion = 0;
  }
  
  public BorderPane initialize() {

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
    
    // Left Panel
    VBox options = new VBox(a,b,c,d,f);
    options.setPadding(new Insets(85,0,0,60));
    options.setSpacing(25);
    root.setLeft(options);
    // TODO add picture to left panel on the bottom corner or bottom panel
    // TODO make sure the option is deselected after the quiz?
    
    // Center Panel
    VBox questionAnswerBox = new VBox(question, opt1, opt2, opt3, opt4, opt5);
    questionAnswerBox.setPadding(new Insets(35,0,0,15));
    questionAnswerBox.setSpacing(30);
    Label placeholder = new Label("PICTURE HERE");
    VBox leftPanel = new VBox(questionAnswerBox, placeholder);
    leftPanel.setSpacing(70);
    root.setCenter(leftPanel);

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
      primaryStage.setScene(Main.getStatisticsScene());
      //TODO change this so that it goes to next question somehow...instead of statsScene
    }
  }
}
