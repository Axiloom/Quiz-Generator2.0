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

/**
 * StatisticsMenu Class constructs the GUI for the StatisticsMenu to display the quiz results.
 * 
 * @author ATeam-99
 *
 */
public class StatisticsMenu extends Main implements EventHandler<ActionEvent> {

  private Stage primaryStage; // stage being displayed on
  private BorderPane root; // BorderPane being constructed
  private Button cont; // continue button to start new quiz
  private Button exit; // exit button

  /**
   * Initializes a BorderPane of the StatisticsMenu screen
   * 
   * @return root - BorderPane of the StatisticsMenu screen
   */
  public StatisticsMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    root = new BorderPane();
    cont = new Button("NEW QUIZ");
    exit = new Button("EXIT");
    root.setStyle("-fx-background-color: #c0c0c5");
  }
  
  /**
   * Initializes a BorderPane of the StatisticsMenu screen
   * 
   * @return root - BorderPane of the StatisticsMenu screen
   */
  public BorderPane initialize(){

    setTopPanel();
    setCenterPanel();
    setBottomPanel();
    
    return root;
  }
  
  /**
   * Constructs the top panel in the BorderPane
   */
  private void setTopPanel() {
    // Label
    Label label = new Label("Statistics Menu");
    
    // Style
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    
    // Top Panel
    HBox topPanel = new HBox(label);
    topPanel.setPadding(new Insets(10,50,10,50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    topPanel.setStyle("-fx-background-color: #9fb983");

    root.setTop(topPanel);
  }
  
  /**
   * Constructs the center panel in the BorderPane
   */
  private void setCenterPanel() {
    Integer numQuestions = 10; // TODO make the calculation functional
    Integer amtCorrect = 9;
    Double pct = ((double) amtCorrect / (double) numQuestions) * 100;
    Label percentCorrect = new Label(pct.toString() + "% Correct");
    Label fractionCorrect = new Label(amtCorrect.toString() + " / " + numQuestions.toString());
    Label score = new Label("Your score");
    Label blank = new Label(""); // spacer
    
    // Style
    score.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 40));
    percentCorrect.setFont(Font.font("Arial", FontWeight.BOLD, 30));
    fractionCorrect.setFont(Font.font("Arial", FontWeight.BOLD, 30));

    // Center Panel
    VBox centerVBox = new VBox(score, blank, fractionCorrect, percentCorrect);
    centerVBox.setAlignment(Pos.CENTER);
    score.setStyle("-fx-underline: true");

    root.setCenter(centerVBox);
  }
  
  /**
   * Constructs the bottom panel in the BorderPane
   */
  private void setBottomPanel() {
    // Style
    cont.setPrefSize(150,50);
    exit.setPrefSize(150,50);
    
    // Listeners
    cont.setOnAction(this);
    exit.setOnAction(this);
    cont.setOnMouseEntered(e -> cont.setStyle("-fx-font-size: 14pt;"));
    cont.setOnMouseExited(e -> cont.setStyle("-fx-font-size: 12pt;"));
    exit.setOnMouseEntered(e -> exit.setStyle("-fx-font-size: 14pt;"));
    exit.setOnMouseExited(e -> exit.setStyle("-fx-font-size: 12pt;"));
    
    // Bottom Panel
    HBox bottomHBox = new HBox(cont, exit);
    bottomHBox.setPadding(new Insets(0,100,65,150));
    bottomHBox.setSpacing(100);
    
    root.setBottom(bottomHBox);
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {

    if (event.getSource() == cont){
      primaryStage.setScene(Main.getMainScene());
    }

    else if(event.getSource() == exit) {
      primaryStage.setScene(Main.getExitScene());
    }
  }
}
