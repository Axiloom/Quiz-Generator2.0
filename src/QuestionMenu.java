import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class QuestionMenu {

  public QuestionMenu() {
  }
  
  public BorderPane initialize() {
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
}
