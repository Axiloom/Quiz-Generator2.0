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

public class StatisticsMenu extends Main implements EventHandler<ActionEvent> {

  private Stage primaryStage;
  private Button save;
  private Button exit;

  // Constructor
  public StatisticsMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    save = new Button("SAVE");
    exit = new Button("EXIT WITHOUT SAVING");
  }

  public BorderPane initialize(){

    Label label = new Label("Statistics Menu");
    
    Integer numQuestions = 10; // static holders
    Integer amtCorrect = 9;
    Double pct = ((double) amtCorrect / (double) numQuestions) * 100;
    Label percentCorrect = new Label(pct.toString() + "% Correct");
    Label fractionCorrect = new Label(amtCorrect.toString() + " / " + numQuestions.toString());
    Label blank = new Label("");
    
    // Styling
    percentCorrect.setFont(Font.font("Arial", FontWeight.BOLD, 35));
    fractionCorrect.setFont(Font.font("Arial", FontWeight.BOLD, 35));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Pane to hold everything
    BorderPane root = new BorderPane();

    // Listeners
    save.setOnAction(this);
    exit.setOnAction(this);

    Image one = new Image("150x150blank.png");
    Image two = new Image("100x300blank.png");
    Image three = new Image("150x50blank.png");
    ImageView img = new ImageView(one);
    ImageView img1 = new ImageView(two);
    ImageView img2 = new ImageView(two);
    ImageView img3 = new ImageView(three);

    VBox leftVBox = new VBox(label);
    VBox bottomVBox = new VBox(save, exit);
    VBox centerVBox = new VBox(img, fractionCorrect, blank, percentCorrect);
    root.setCenter(centerVBox);
    root.setLeft(leftVBox);
    root.setBottom(bottomVBox);

    return root;
  }


  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    MainMenu mainMenu = new MainMenu(primaryStage);
    Scene mainScene = new Scene(mainMenu.initialize(),500,500);
    if (event.getSource() == save){
      primaryStage.setScene(mainScene);
    //TODO actually save the data some time
    }
    else if(event.getSource() == exit) {
      primaryStage.setScene(mainScene);
    }
  }
}