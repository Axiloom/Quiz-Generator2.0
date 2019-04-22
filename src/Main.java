import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.event.MouseEvent;

/**
 * TODO
 * 
 * @author 
 *
 */
public class Main extends Application {

  /**
   * TODO
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      // Boolean running = true;
      // while(running) {
      //
      // }
      Scene scene = new Scene(getMainMenu(), 500, 500);
      Scene saveScene = new Scene(getSaveMenu(), 500,500);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();

      // Event Handler Functional Interface
      EventHandler<ActionEvent> actionEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          if ()
        }
      };

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Constructs the Main menu, displaying the options to add a question, save questions,
   * see and select the topics available, and starts the quiz.
   * 
   * @return BorderPane of the Main menu
   */
  public BorderPane getMainMenu() {

    Label label = new Label("Main Menu");
    Label numQuestions = new Label("N Questions available");
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    ObservableList<String> topics =
        FXCollections.observableArrayList("Linux", "ADTs", "Search Trees");
    ComboBox<String> topicBox = new ComboBox<>(topics);
    topicBox.setPromptText("  Set Topic");

    BorderPane root = new BorderPane();

    Button add = new Button("  Add Questions  ");
    Button save = new Button("  Save Questions ");
    Button start = new Button("START");

    // WIP on handlers
    Handler handler2 = new Handler(add);
    add.setOnAction(handler2);
    Handler handler3 = new Handler(save);
    save.setOnAction(handler3);
    Handler handler4 = new Handler(start);
    start.setOnAction(handler4);


    // Way to add an image as a button

    // Image start = new Image("start.png");
    // Button startButton = new Button("", new ImageView(start));

    Image one = new Image("100x100blank.png");
    Image two = new Image("150x150blank.png");
    Image three = new Image("100x300blank.png");
    ImageView img1 = new ImageView(two);
    ImageView img2 = new ImageView(three);
    ImageView img3 = new ImageView(one);

    VBox leftVBox = new VBox(label, img1);
    VBox rightVBox = new VBox(numQuestions, img2, start);
    VBox centerVBox = new VBox(img3, add, save, topicBox);
    root.setCenter(centerVBox);
    root.setLeft(leftVBox);
    root.setRight(rightVBox);

    return root;
  }

  /**
   * Constructs the Save menu allowing you to save to a json file
   * 
   * @return BorderPane of the Save Menu
   */
  public BorderPane getSaveMenu() {
    Label label = new Label("Save Menu");
    Label numQuestions = new Label("X Questions available");
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    BorderPane root = new BorderPane();

    Label jsonLabel = new Label("Save as JSON:");
    TextField jsonName = new TextField("Enter JSON File here");

    Button next = new Button("NEXT");
    Button back = new Button("BACK");

    Image one = new Image("150x150blank.png");
    Image two = new Image("100x300blank.png");
    ImageView img = new ImageView(one);
    ImageView img1 = new ImageView(two);
    ImageView img2 = new ImageView(two);

    VBox leftVBox = new VBox(label, img1, back);
    VBox rightVBox = new VBox(numQuestions, img2, next);
    VBox centerVBox = new VBox(img, jsonLabel, jsonName);
    root.setCenter(centerVBox);
    root.setLeft(leftVBox);
    root.setRight(rightVBox);

    return root;
  }

  /**
   * Constructs an Add menu, including a way to add a question or load questions from a json
   * file
   * 
   * @return BorderPane of the Add Menu
   */
  public BorderPane getAddMenu() {

    Label label = new Label("Add Menu");
    Label numQuestions = new Label("X Questions available");
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    BorderPane root = new BorderPane();

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

    Button next = new Button("NEXT");
    Button back = new Button("BACK");

    Image one = new Image("150x50blank.png");
    Image two = new Image("100x300blank.png");
    ImageView img = new ImageView(one);
    ImageView img1 = new ImageView(two);
    ImageView img2 = new ImageView(two);

    VBox leftVBox = new VBox(label, img1, back);
    VBox rightVBox = new VBox(numQuestions, img2, next);
    VBox centerVBox = new VBox(img, topicLabel, topic, questionLabel, question, answerLabel, answer,
        blank, or, blank2, load, jsonLoad);
    root.setCenter(centerVBox);
    root.setLeft(leftVBox);
    root.setRight(rightVBox);

    return root;
  }

  /**
   * Constructs the Question menu to display a question and answer selections
   * 
   * @return BorderPane of the Question Menu
   */
  public BorderPane getQuestionMenu() {
    Label label = new Label("Add Menu");
    Label numQuestions = new Label("X Questions available");
    numQuestions.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    BorderPane root = new BorderPane();

    CheckBox a = new CheckBox();
    Label topicLabel = new Label("Enter Topic:");
    TextField topic = new TextField("Enter Topic here");
    Label questionLabel = new Label("Enter Question:");
    TextField question = new TextField("Enter Question here");
    Label answerLabel = new Label("Enter Answer:");
    TextField answer = new TextField("Enter Answer here");

    Button next = new Button("NEXT");
    Button back = new Button("BACK");

    Image two = new Image("100x300blank.png");
    Image three = new Image("100x100blank.png");
    ImageView img = new ImageView(three);
    ImageView img1 = new ImageView(two);
    ImageView img2 = new ImageView(two);

    VBox leftVBox = new VBox(a, img1, back);
    VBox rightVBox = new VBox(numQuestions, img2, next);
    VBox centerVBox =
        new VBox(img, topicLabel, topic, questionLabel, question, answerLabel, answer);
    root.setCenter(centerVBox);
    root.setLeft(leftVBox);
    root.setRight(rightVBox);

    return root;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
