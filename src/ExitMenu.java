import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * ExitMenu Class constructs the GUI for the ExitMenu to display whether to save and exit or to
 * exit without saving.
 * 
 * @author ATeam-99
 *
 */
public class ExitMenu extends Main {

  private Stage primaryStage; // stage being displayed on
  private BorderPane root; // BorderPane being constructed
  private Button save; // save button
  private Button exit; // exit button
  private TextField fileName; // textfield to get the file name
  private Alert alert; // confirmation alert screen

  /**
   * ExitMenu Constructor that declares the field variables and sets the background color
   * 
   * @param primaryStage - stage being displayed on
   */
  public ExitMenu(Stage primaryStage) {
    this.primaryStage = primaryStage;
    root = new BorderPane();
    save = new Button("SAVE AND EXIT");
    exit = new Button("EXIT WITHOUT SAVING");
    root.setStyle("-fx-background-color: #c0c0c5");
    fileName = new TextField("Enter file name");
  }

  /**
   * Initializes a BorderPane of the ExitMenu screen
   * 
   * @return root - BorderPane of the ExitMenu screen
   */
  public BorderPane initialize() {

    setTopPanel();
    setCenterPanel();
    
    return root;
  }
  
  /**
   * Constructs the top panel in the BorderPane
   */
  private void setTopPanel() {
    // Label
    Label label = new Label("Exit Menu"); // update with the questions
    
    // Style
    label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    
    // Top Panel
    HBox topPanel = new HBox(label);
    topPanel.setPadding(new Insets(10, 50, 10, 50));
    topPanel.setSpacing(100);
    topPanel.setAlignment(Pos.CENTER);
    topPanel.setStyle("-fx-background-color: #9fb983");
    
    root.setTop(topPanel);
  }
  
  /**
   * Constructs the center panel in the border pane
   */
  private void setCenterPanel() {
    // Label
    Label saveJSON = new Label("Enter File: ");
    
    // Style
    save.setPrefSize(250, 100);
    exit.setPrefSize(250, 100);
    save.setOnMouseEntered(e -> save.setStyle("-fx-font-size: 14pt;"));
    save.setOnMouseExited(e -> save.setStyle("-fx-font-size: 12pt;"));
    exit.setOnMouseEntered(e -> exit.setStyle("-fx-font-size: 14pt;"));
    exit.setOnMouseExited(e -> exit.setStyle("-fx-font-size: 12pt;"));
    
    
    // Listeners
    save.setOnAction(e -> {
      String file = fileName.getText();
      alert = new Alert(Alert.AlertType.CONFIRMATION, "Save to " + file + "?");
      alert.setHeaderText("Save Questions");
      Optional<ButtonType> buttonType = alert.showAndWait();
      if(buttonType.get().equals(ButtonType.OK)) {
        super.getQuestion().saveToJSON(file);
        try {
          Platform.exit();
        } catch (Exception f) {
        }
      }
      if(buttonType.get().equals(ButtonType.CANCEL)) {
        primaryStage.setScene(Main.getExitScene());
      }
    });

    exit.setOnAction(e -> {
      alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit without saving?");
      alert.setHeaderText("Exit Without Saving");
      Optional<ButtonType> buttonType = alert.showAndWait();
      if(buttonType.get().equals(ButtonType.OK)) {
        try {
          Platform.exit();
        } catch (Exception f) {
        }
      }
      if(buttonType.get().equals(ButtonType.CANCEL)) {
        primaryStage.setScene(Main.getExitScene());
      }
    });

    // Center Panel
    HBox saveBox = new HBox(saveJSON, fileName);
    fileName.setPrefWidth(180);
    saveBox.setPadding(new Insets(50,0,0,0));
    VBox midPanel = new VBox(saveBox, save, exit);
    midPanel.setSpacing(20);
    midPanel.setPadding(new Insets(0, 0, 0, 230));
    
    root.setCenter(midPanel);
  }
}
