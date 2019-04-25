import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddMenu extends Main implements EventHandler<ActionEvent> {

    Stage primaryStage;
    Button back;
    Button submit;

    public AddMenu(Stage primaryStage) {
      this.primaryStage = primaryStage;
      submit = new Button("SUBMIT");
      back = new Button("BACK");
    }
    
    public BorderPane initialize() {
      Label label = new Label("Add Menu");
      Label numQuestions = new Label(getQuestion().getSize() + " questions available");
      numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
      label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

      BorderPane root = new BorderPane();
      
      // Set background color of root
      root.setStyle("-fx-background-color: #c0c0c5");

      Label topicLabel = new Label("Enter Topic: ");
      TextField topic = new TextField("Enter Topic here");
      HBox topicHBox = new HBox(topicLabel, topic);
      topicHBox.setSpacing(24);
      
      Label questionLabel = new Label("Enter Question: ");
      TextField question = new TextField("Enter Question here");
      HBox questionHBox = new HBox(questionLabel, question);
      
      Label answerLabel = new Label("Enter Answer: ");
      TextField answer = new TextField("Enter Answer here");
      HBox answerHBox = new HBox(answerLabel, answer);
      answerHBox.setSpacing(11);
      
      Label or = new Label("OR");
      or.setFont(Font.font("Arial", FontWeight.BOLD, 16));
      
      Label loadLabel = new Label("Load JSON: ");
      TextField jsonLoad = new TextField("Enter JSON File name");
      HBox loadHBox = new HBox(loadLabel, jsonLoad);
      loadHBox.setSpacing(25);
      
      Label blank = new Label("");
      Label blank2 = new Label("");
      
      // Listeners
      back.setOnAction(this);
      submit.setOnAction(this);
      
      // Scroll-over effects
      back.setOnMouseEntered(e -> back.setStyle("-fx-font-size: 14pt;"));
      back.setOnMouseExited(e -> back.setStyle("-fx-font-size: 12pt;"));
      submit.setOnMouseEntered(e -> submit.setStyle("-fx-font-size: 14pt;"));
      submit.setOnMouseExited(e -> submit.setStyle("-fx-font-size: 12pt;"));
      
      // Formatting Buttons and Boxes
      back.setPrefSize(100,50);
      submit.setPrefSize(100,50);

      // Top Panel
      HBox topPanel = new HBox(label, numQuestions);
      topPanel.setPadding(new Insets(10,50,10,50));
      topPanel.setSpacing(150);
      root.setTop(topPanel);
      topPanel.setStyle("-fx-background-color: #9fb983");

      // Center Panel
      VBox topVBox = new VBox(topicHBox, questionHBox, answerHBox);
      VBox bottomVBox = new VBox(loadHBox);
      VBox centerVBox = new VBox(topVBox, blank, or, blank2, bottomVBox);
      centerVBox.setPadding(new Insets(50,80,50,80));
      root.setCenter(centerVBox);

      // Bottom Panel
      HBox bottomHBox = new HBox(back,submit);
      bottomHBox.setPadding(new Insets(0,100,65,100));
      bottomHBox.setSpacing(100);
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

    if (event.getSource() == back)
      primaryStage.setScene(Main.getMainScene());

    else if (event.getSource() == submit)
      primaryStage.setScene(Main.getMainScene());
      // TODO make this formally save or add the questions
  }
}

