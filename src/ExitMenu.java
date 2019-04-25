import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ExitMenu extends Main implements EventHandler<ActionEvent>{

  private Stage primaryStage;
  private Button save;
  private Button exit;

  public ExitMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    save = new Button("SAVE AND EXIT");
    exit = new Button("EXIT WITHOUT SAVING");
  }
  
  public BorderPane initialize() {
    
    Label label = new Label("Exit Menu"); // update with the questions
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    BorderPane root = new BorderPane();
 // Set background color of root
    root.setStyle("-fx-background-color: #c0c0c5");
 // Scroll-over effects
    save.setOnMouseEntered(e -> save.setStyle("-fx-font-size: 14pt;"));
    save.setOnMouseExited(e -> save.setStyle("-fx-font-size: 12pt;"));
    exit.setOnMouseEntered(e -> exit.setStyle("-fx-font-size: 14pt;"));
    exit.setOnMouseExited(e -> exit.setStyle("-fx-font-size: 12pt;"));
    save.setPrefSize(250,100);
    exit.setPrefSize(250,100);
 // Listeners
    save.setOnAction(this);
    exit.setOnAction(this);
    // Top Panel
    HBox topPanel = new HBox(label);
    topPanel.setPadding(new Insets(10,50,10,50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    root.setTop(topPanel);
    topPanel.setStyle("-fx-background-color: #9fb983");
    // Center Panel
    VBox midPanel = new VBox(save, exit);
    midPanel.setSpacing(50);
    midPanel.setPadding(new Insets(60,0,0,130));
    root.setCenter(midPanel);
    return root;
  }
  
  public void handle(ActionEvent event) {

    if (event.getSource() == exit) {
      // TODO: show alert message displaying their choice and goodbye
      try {
        Platform.exit();
      }catch (Exception e) {
      }
    }

    else if(event.getSource() == save) {
      //TODO save to JSON then close program
      // TODO: Show alert message displaying their choice and goodbye
      try {
        Platform.exit();
      } catch(Exception e) {
      }
    }
  }
}
