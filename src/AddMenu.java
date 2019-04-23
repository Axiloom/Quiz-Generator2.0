import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddMenu extends Main implements EventHandler<ActionEvent> {
  private Stage primaryStage;
  private Button back;
  private Button submit;

  public AddMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    back = new Button("BACK");
    submit = new Button("SUBMIT");
  }
  
  public BorderPane initialize() {
    
    // Create Labels
    Label label = new Label("Add Menu");
    Label numQuestions = new Label("X Questions available");
    
    // Styling
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Borderpane to add buttons to
    BorderPane root = new BorderPane();
    
    // Handler for Add
    back.setOnAction(this);
    submit.setOnAction(this);

    // Menu labels and textfields
    Label topicLabel = new Label("Enter Topic:");
    TextField topic = new TextField("Enter Topic here");
    Label questionLabel = new Label("Enter Question:");
    TextField question = new TextField("Enter Question here");
    Label answerLabel = new Label("Enter Answer:");
    TextField answer = new TextField("Enter Answer here");
    Label or = new Label("OR");
    or.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    Label load = new Label("Load from JSON:");
    TextField jsonLoad = new TextField("Enter JSON File name");
    Label blank = new Label("");
    Label blank2 = new Label("");

    // Images to alter screen
    Image one = new Image("150x50blank.png");
    Image two = new Image("100x300blank.png");
    
    // Create imageViews for GUI
    ImageView img = new ImageView(one);
    ImageView img1 = new ImageView(two);
    ImageView img2 = new ImageView(two);

    // Create boxes
    VBox leftVBox = new VBox(label, img1, back);
    VBox rightVBox = new VBox(numQuestions, img2, submit);
    VBox centerVBox = new VBox(img, topicLabel, topic, questionLabel, question, answerLabel, answer,
        blank, or, blank2, load, jsonLoad);
    
    // place boxes on screen
    root.setCenter(centerVBox);
    root.setLeft(leftVBox);
    root.setRight(rightVBox);

    return root;
  }
  
  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  public void handle(ActionEvent event) {
    MainMenu mainMenu = new MainMenu(primaryStage);
    Scene mainScene = new Scene(mainMenu.initialize(),500,500);
    if (event.getSource() == back)
      primaryStage.setScene(mainScene);
    else if (event.getSource() == submit)
      primaryStage.setScene(mainScene);
      // TODO make this formally save or add the questions
  }
}
