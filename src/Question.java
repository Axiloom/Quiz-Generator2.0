import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileNotFoundException;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Main Question Class to retrieve anything question related
 */
public class Question {

  private Hashtable<String,ArrayList<QuestionNode>> topics; // Hash table to store
  // questions
  private int numQuestions; // total number of questions available
  
  /**
   * Each node represents a single question and all attributes associated with that question
   * 
   * @author ATeam-99
   *
   */
  class QuestionNode{
    String metadata; // metadata for a question
    String question; // question
    ArrayList<String> options; // all options
    String answer; // correct answer
    ImageView img; // image going along with the question
    String topic; // topic associated with this question

    /**
     * Each node represents a single question and all attributes associated with that question
     *
     * @param topic the topic associated with this question
     * @param metadata the metadata associated with this question
     * @param questionText the text associated with this question
     * @param image the image associated with this question
     * @param options the possible answers associated with this question
     * @param correctAnswer the correct answer for this questions
     */
    QuestionNode(String topic, String metadata, String questionText, String image, 
    		ArrayList<String> options, String correctAnswer){
      this.metadata = metadata;
      this.question = questionText;
      this.options = options;
      if(!image.equals("")) {
        img = new ImageView(new Image(image));
      }
      this.answer = correctAnswer;
      this.topic = topic;
    }
  }

  /**
   * No-arg Constructor for Question class
   */
  public Question() {
    topics = new Hashtable<>();
    numQuestions = 0;
  }
  
  /**
   * Returns an arrayList of questions for a particular topic group in random order
   * 
   * @param topic the topic which we want to search for
   * @param numQuestions the number of questions from that topic
   */
  public ArrayList<QuestionNode> getQuestions(String topic, int numQuestions){

    // Random generator
    Random rand = new Random();

    // Array to store our questions
    ArrayList<QuestionNode> questions = new ArrayList<>();

    // Get random questions
    for (int i = 0 ; i < numQuestions ; i++){
      QuestionNode randomQuestion = topics.get(topic).get(rand.nextInt(topics.get(topic).size()));
      if (!questions.contains(randomQuestion))
        questions.add(randomQuestion);
    }

    return questions;
  }

  /**
   * Adds a node to the topicList according to its topic
   *
   * @param topic the topic of the question
   * @param questionText the text of the question
   * @param metadata the meta of the question
   * @param options the options for the question
   * @param correctAnswer the correct answer for the question
   * @param image the image for the question
   */
  public void addQuestion(String topic, String questionText, String metadata,
      ArrayList<String> options, String correctAnswer, String image) {

    // Question to be added
    QuestionNode question = new QuestionNode(topic, metadata, questionText, image, options,
            correctAnswer);

    // Create topic if it doesnt exist
    if (!topics.contains(question.topic)) {
      topics.put(question.topic, new ArrayList<>());
    }
    // Add question
	topics.get(question.topic).add(question);
	++numQuestions;

  }
  
  /**
   * Parses a json file and adds the information to the data fields
   * 
   * @param jsonFilePath the path to the JSON file
   * @return true if the file was loaded correctly
   * @throws ParseException error with parsing
   * @throws IOException error with reading file
   * @throws FileNotFoundException file does not exist
   */
  public boolean loadJSON(String jsonFilePath) throws FileNotFoundException, IOException, ParseException {
	    // Make as many private helper methods as we need
    Object obj = new JSONParser().parse(new FileReader(jsonFilePath));
	  JSONObject jo = (JSONObject) obj;
	  JSONArray questionArray = (JSONArray) jo.get("questionArray");

    for (Object aQuestionArray : questionArray) {
      JSONObject jsonQuestion = (JSONObject) aQuestionArray;
      String metaData = (String) jsonQuestion.get("meta-data"); // meta data
      String question = (String) jsonQuestion.get("question"); // question
      String topic = (String) jsonQuestion.get("topic");
      String image = (String) jsonQuestion.get("image");
      // need to interate through the choices
      JSONArray answerArray = (JSONArray) jsonQuestion.get("choiceArray");
      ArrayList<String> choices = new ArrayList<>();
      String correctAnswer = "";
      for (Object answer : answerArray) {
        if (((JSONObject) answer).get("isCorrect").equals("T")) {
          correctAnswer = (String) ((JSONObject) answer).get("choice");
        }
        choices.add((String) ((JSONObject) answer).get("choice"));
      }

      // Add Question
      addQuestion(topic,question,metaData,choices,correctAnswer,image);
    }
		
    return true;
  }
  
  /**
   * Saves all questions in a new json file
   * 
   * @param nameOfJson - name of the new JSON file
   * @return true if the file was successfully saved
   */
  public boolean saveToJSON(String nameOfJson) {
    // TODO check for duplicate json name
    return true;
  }
  
  /**
   * Gets the total number of questions available across all topics
   * 
   * @return the total number of questions
   */
  public int getSize() {
    return numQuestions;
  }
  
  /**
   * Gets the number of questions available for a particular topic
   * 
   * @param topic - the topic which we are finding number of questions for
   * @return number of questions for a specific topic, or -1 if topic does not exist
   */
  public int getSize(String topic) {
    return -1;
  }
  
  /**
   * Given a node, returns the question for that node in a string
   * 
   * @return String of a question
   */
  public String getQuestion(QuestionNode node) {
    return node.question;
  }

  /**
   * gets all topics created
   *
   * @return set containing all topics
   */
  public Set<String> getTopics(){
    return topics.keySet();
  }
}
