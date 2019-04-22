import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenu extends Main {
  
  public MainMenu() {
  }
  
  public BorderPane initialize() {
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
}