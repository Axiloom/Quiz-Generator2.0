package application;

///////////////////////////////////////////////////////////////////////////////
//
// Assignment: Quiz-Generator Team Project
// Due: 5-2-19
// Title: Add Menu 
// Files: application.AddMenu.java
// Course: CS 400, Spring 2019, Lec 001
//
// Authors: A-Team 99 
//          (John Bednarczyk, Joseph Lessner, Joshua Liberko, Shefali Mukerji, Mitchell Sutrick)
// Lecturer's Name: Deb Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * application.AddMenu Class constructs the GUI for the application.AddMenu to
 * display how to add or load questions
 * 
 * @author ATeam-99
 *
 */
public class AddMenu extends Main {

	private Stage primaryStage; // stage being displayed on
	private BorderPane root; // BorderPane being constructed
	private Button back; // back button
	private Button submit; // submit button
	private TextField topic; // records the question topic
	private TextField question; // records the question text
	private TextField answer; // records the answer to the question
	private TextField picture; // records the picture file name
	private TextField option1; // records alternative choice
	private TextField option2; // records alternative choice
	private TextField option3; // records alternative choice
	private TextField option4; // records alternative choice
	private TextField jsonLoad; // json file name to load
	private Alert alert; // alert for failure to load files
	private FileChooser jsonFileChooser; // popup file system for browsing files
	private FileChooser pictureFileChooser; // popup file system for browsing picture files
	private Button jsonFileChooserButton; // browse button
	private Button pictureFileChooserButton; // brose button
	private File pictureFile; // file of the picture for the question being added
	private File jsonFile; // file of the jsonFile being loaded

	/**
	 * application.AddMenu Constructor that declares the field variables and sets
	 * the background color
	 * 
	 * @param primaryStage
	 *            - stage being displayed on
	 */
	public AddMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
		root = new BorderPane();
		submit = new Button("SUBMIT");
		back = new Button("BACK");
		topic = new TextField("");
		question = new TextField("");
		answer = new TextField("");
		picture = new TextField("");
		option1 = new TextField("");
		option2 = new TextField("");
		option3 = new TextField("");
		option4 = new TextField("");
		jsonLoad = new TextField("");
		jsonFileChooser = new FileChooser();
		pictureFileChooser = new FileChooser();
		jsonFileChooserButton = new Button("Browse...");
		pictureFileChooserButton = new Button("Browse...");

		root.setStyle("-fx-background-color: #c0c0c5");
	}

	/**
	 * Initializes a BorderPane of the application.AddMenu screen
	 * 
	 * @return root - BorderPane of the application.AddMenu screen
	 */
	public BorderPane initialize() {

		setTopPanel();
		setCenterPanel();
		setBottomPanel();

		return root;
	}

	/**
	 * Constructs the top panel in the BorderPane
	 */
	private void setTopPanel() {
		// Labels
		Label label = new Label("Add Menu");
		String qLabel = "" + getQuestion().getSize();
		if (getQuestion().getSize() == 1) {
			qLabel += " question available";
		} else {
			qLabel += " questions available";
		}
		Label qAvailableLabel = new Label(qLabel);

		// Style
		qAvailableLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		// Top Panel
		HBox topPanel = new HBox(label, qAvailableLabel);
		topPanel.setPadding(new Insets(10, 50, 10, 50));
		topPanel.setSpacing(150);
		topPanel.setAlignment(Pos.CENTER);
		topPanel.setStyle("-fx-background-color: #9fb983");

		root.setTop(topPanel);
	}

	/**
	 * Constructs the center panel in the BorderPane
	 */
	private void setCenterPanel() {

		Label topicLabel = new Label("Enter Topic: ");
		HBox top = new HBox(topicLabel, topic);

		Label questionLabel = new Label("Enter application.Question: ");
		HBox que = new HBox(questionLabel, question);

		Label pictureLabel = new Label("Load Picture: ");
		HBox pic = new HBox(pictureLabel, picture, pictureFileChooserButton);

		Label answerLabel = new Label("Enter Answer: ");
		HBox ans = new HBox(answerLabel, answer);

		Label optionsLabel = new Label("Enter Other Options: ");
		Label blank1 = new Label("");
		Label blank2 = new Label("");
		Label blank3 = new Label("");
		HBox opt = new HBox(optionsLabel, option1);
		HBox opt1 = new HBox(blank1, option2);
		HBox opt2 = new HBox(blank2, option3);
		HBox opt3 = new HBox(blank3, option4);
		VBox opt4 = new VBox(opt1, opt2, opt3);

		VBox left = new VBox(top, que, pic);
		left.setAlignment(Pos.CENTER);
		VBox right = new VBox(ans, opt, opt4);
		right.setPadding(new Insets(50, 0, 0, 0));
		HBox topBox = new HBox(left, right);

		pictureFileChooser.setTitle("Browse for picture");
		picture.setTooltip(new Tooltip("picture must be located in the same directory as .jar"));
		pictureFileChooserButton.setTooltip(new Tooltip("picture must be located in the same directory as .jar"));

		Label or = new Label("OR");
		or.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		or.setPadding(new Insets(20, 0, 20, 0));

		Label loadLabel = new Label("Load JSON: ");
		Label typeLabel = new Label(".json");
		typeLabel.setFont(Font.font("Arial", 14));
		HBox loadHBox = new HBox(loadLabel, jsonLoad, typeLabel, jsonFileChooserButton);
		jsonFileChooser.setTitle("Browse for .json");
		jsonLoad.setTooltip(new Tooltip(".json must be located in the same directory as .jar"));
		jsonFileChooserButton.setTooltip(new Tooltip(".json must be located in the same directory as .jar"));

		VBox centerVBox = new VBox(topBox, or, loadHBox);

		// Formatting
		questionLabel.setPrefWidth(106);
		topicLabel.setPrefWidth(106);
		pictureLabel.setPrefWidth(106);
		answerLabel.setPrefWidth(141);
		optionsLabel.setPrefWidth(141);
		blank1.setPrefWidth(141);
		blank2.setPrefWidth(141);
		blank3.setPrefWidth(141);
		topic.setPrefWidth(177);
		question.setPrefWidth(177);
		picture.setPrefWidth(177);
		answer.setPrefWidth(177);
		option1.setPrefWidth(177);
		option2.setPrefWidth(177);
		option3.setPrefWidth(177);
		option4.setPrefWidth(177);
		or.setPadding(new Insets(0, 0, 0, 329));
		loadHBox.setPadding(new Insets(0, 0, 0, 150));
		typeLabel.setPadding(new Insets(10, 20, 0, 0));
		loadLabel.setPrefWidth(80);
		centerVBox.setSpacing(20);

		// Listeners
		jsonFileChooserButton.setOnAction(event -> {
			jsonFile = jsonFileChooser.showOpenDialog(primaryStage);
			if (jsonFile != null && jsonFile.exists()) {		
				jsonLoad.setText(jsonFile.getName().substring(0, jsonFile.getName().lastIndexOf(".")));
			}
		});

		pictureFileChooserButton.setOnAction(event -> {
			pictureFile = pictureFileChooser.showOpenDialog(primaryStage);
			if (pictureFile != null && pictureFile.exists())
				picture.setText(pictureFile.getName());
		});

		root.setCenter(centerVBox);
	}

	/**
	 * Constructs the bottom panel in the BorderPane
	 */
	private void setBottomPanel() {
		// Style
		back.setPrefSize(100, 50);
		submit.setPrefSize(100, 50);
		back.setOnMouseEntered(e -> back.setStyle("-fx-font-size: 14pt;"));
		back.setOnMouseExited(e -> back.setStyle("-fx-font-size: 12pt;"));
		submit.setOnMouseEntered(e -> submit.setStyle("-fx-font-size: 14pt;"));
		submit.setOnMouseExited(e -> submit.setStyle("-fx-font-size: 12pt;"));

		// Back Listener
		back.setOnAction(event -> {
			topic.clear();
			question.clear();
			answer.clear();
			option1.clear();
			option2.clear();
			option3.clear();
			option4.clear();
			jsonLoad.clear();
			picture.clear();
			primaryStage.setScene(Main.getMainScene());
		});

		// Submit Listener
		submit.setOnAction(event -> {
			// Add question with picture
			if (!topic.getText().equals("") && !question.getText().equals("") && !answer.getText().equals("")
					&& !option1.getText().equals("") && !option2.getText().equals("") && !option3.getText().equals("")
					&& !option4.getText().equals("") && !picture.getText().equals("")
					&& jsonLoad.getText().equals("")) {
				ArrayList<String> options = new ArrayList<>();
				options.add(answer.getText());
				options.add(option1.getText());
				options.add(option2.getText());
				options.add(option3.getText());
				options.add(option4.getText());

				super.getQuestion().addQuestion(topic.getText(), question.getText(), "", options, answer.getText(),
						picture.getText());
				alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added question!");
				alert.setHeaderText("Success.");
				alert.showAndWait().filter(response -> response == ButtonType.OK);

				// Clear TextFields
				topic.clear();
				question.clear();
				answer.clear();
				option1.clear();
				option2.clear();
				option3.clear();
				option4.clear();
				jsonLoad.clear();
				picture.clear();

				// Update menu displays
				super.getMainMenu().initialize();
				super.getAddMenu().initialize();
				super.getSaveMenu().initialize();
			} // Add question with no picture
			else if (!topic.getText().equals("") && !question.getText().equals("") && !answer.getText().equals("")
					&& !option1.getText().equals("") && !option2.getText().equals("") && !option3.getText().equals("")
					&& !option4.getText().equals("") && picture.getText().equals("") && jsonLoad.getText().equals("")) {
				ArrayList<String> options = new ArrayList<>();
				options.add(answer.getText());
				options.add(option1.getText());
				options.add(option2.getText());
				options.add(option3.getText());
				options.add(option4.getText());
				picture.setText("none");

				super.getQuestion().addQuestion(topic.getText(), question.getText(), "", options, answer.getText(),
						picture.getText());
				alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added question!");
				alert.setHeaderText("Success.");
				alert.showAndWait().filter(response -> response == ButtonType.OK);

				// Clear TextFields
				topic.clear();
				question.clear();
				answer.clear();
				option1.clear();
				option2.clear();
				option3.clear();
				option4.clear();
				jsonLoad.clear();
				picture.clear();

				// Update menu displays
				super.getMainMenu().initialize();
				super.getAddMenu().initialize();
				super.getSaveMenu().initialize();
			} // Load Questions
			else if (topic.getText().equals("") && question.getText().equals("") && answer.getText().equals("")
					&& option1.getText().equals("") && option2.getText().equals("") && option3.getText().equals("")
					&& option4.getText().equals("") && picture.getText().equals("") && !jsonLoad.getText().equals("")) {
				try {

					super.getQuestion().loadJSON(jsonLoad.getText() + ".json");
					jsonLoad.clear();
					// Update menu displays
					super.getMainMenu().initialize();
					super.getAddMenu().initialize();
					super.getSaveMenu().initialize();
					alert = new Alert(Alert.AlertType.INFORMATION, "Successfully added from JSON!");
					alert.setHeaderText("Success.");
					alert.showAndWait().filter(response -> response == ButtonType.OK);
				} catch (Exception e) {
					e.printStackTrace();
					// Throw alert if failure to load file
					alert = new Alert(Alert.AlertType.CONFIRMATION, "Error: " + e.getMessage());
					alert.setHeaderText("Error loading file.");
					alert.showAndWait().filter(response -> response == ButtonType.OK);
					jsonLoad.clear();
				}
			} // Trying to add and load at same time
			else if (!topic.getText().equals("") && !question.getText().equals("") && !answer.getText().equals("")
					&& !option1.getText().equals("") && !option2.getText().equals("") && !option3.getText().equals("")
					&& !option4.getText().equals("") && !picture.getText().equals("")
					&& !jsonLoad.getText().equals("")) {

				// Throw alert if add question and parse json are entered
				alert = new Alert(Alert.AlertType.WARNING, "Cannot import json and add question at the same " + "time");
				alert.setHeaderText("Error adding question.");
				alert.showAndWait().filter(response -> response == ButtonType.OK);
			} else {
				// Throw alert if failure to add question
				alert = new Alert(Alert.AlertType.WARNING, "Enter all fields to add question.");
				alert.setHeaderText("Error adding question.");
				alert.showAndWait().filter(response -> response == ButtonType.OK);
			}
			primaryStage.setScene(Main.getAddScene());
		});

		// Bottom Panel
		HBox bottomHBox = new HBox(back, submit);
		bottomHBox.setPadding(new Insets(0, 100, 65, 200));
		bottomHBox.setSpacing(100);

		root.setBottom(bottomHBox);
	}
}
