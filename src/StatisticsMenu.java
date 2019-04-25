import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
    exit = new Button("EXIT");
  }

  public BorderPane initialize(){

    // Create Labels
    Label label = new Label("Statistics Menu");
    
    Integer numQuestions = 10; // static holders
    Integer amtCorrect = 9;
    Double pct = ((double) amtCorrect / (double) numQuestions) * 100;
    Label percentCorrect = new Label(pct.toString() + "% Correct");
    Label fractionCorrect = new Label(amtCorrect.toString() + " / " + numQuestions.toString());
    Label score = new Label("Your score");
    Label blank = new Label("");
    
    // Styling
    score.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 40));
    percentCorrect.setFont(Font.font("Arial", FontWeight.BOLD, 30));
    fractionCorrect.setFont(Font.font("Arial", FontWeight.BOLD, 30));
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    // Pane to hold everything
    BorderPane root = new BorderPane();
    
    // Set background color of root
    root.setStyle("-fx-background-color: #c0c0c5");

    // Listeners
    save.setOnAction(this);
    exit.setOnAction(this);
    
    // Scroll-over effects
    save.setOnMouseEntered(e -> save.setStyle("-fx-font-size: 14pt;"));
    save.setOnMouseExited(e -> save.setStyle("-fx-font-size: 12pt;"));
    exit.setOnMouseEntered(e -> exit.setStyle("-fx-font-size: 14pt;"));
    exit.setOnMouseExited(e -> exit.setStyle("-fx-font-size: 12pt;"));
    
    save.setPrefSize(100,50);
    exit.setPrefSize(100,50);

    // Top Panel
    HBox topPanel = new HBox(label);
    topPanel.setPadding(new Insets(10,50,10,50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    root.setTop(topPanel);
    topPanel.setStyle("-fx-background-color: #9fb983");

    // Center Panel
    VBox centerVBox = new VBox(score, blank, fractionCorrect, percentCorrect);
    centerVBox.setAlignment(Pos.CENTER);
    root.setCenter(centerVBox);
    score.setStyle("-fx-underline: true");

    // Bottom Panel
    HBox bottomHBox = new HBox(save, exit);
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
  @Override
  public void handle(ActionEvent event) {

    if (event.getSource() == save){
      primaryStage.setScene(Main.getMainScene());
      //TODO actually save the data some time
    }

    else if(event.getSource() == exit) {
      primaryStage.setScene(Main.getMainScene());
    }
  }
}
