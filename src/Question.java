import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 


public class Question {

  ArrayList<ArrayList<QuestionNode>> topics; // holds ArrayList of questions for specific topics
  ArrayList<Boolean> isCorrect; // keeps track of correct/incorrect answers throughout the quiz
  int numQuestions; // total number of questions available
  
  /**
   * TODO
   * 
   * @author ATeam-99
   *
   */
  class QuestionNode{
    String metadata; // metadata for a question
    String question; // question
    String[] options; // all options
    String answer; // correct answer
    ImageView img; // image going along with the question
    String topic;
    
    /**
     * TODO
     * 
     * @param metadata
     * @param questionText
     * @param image
     * @param options
     */
    QuestionNode(String topic, String metadata, String questionText, String image, String[] options){
      this.metadata = metadata;
      this.question = questionText;
      this.options = options;
      img = new ImageView(new Image(image));
      answer = findAnswer(); // detect the correct answer, do we need this?
      this.topic = topic;
    }
    
    /**
     * TODO
     * 
     * @return
     */
    private String findAnswer(){
      // find the option that has "T" as isCorrect and set it to answer
      return "";
    }
    
    /**
     * Used to correctly place questions into their topic array
     * @param topic
     * @return
     */
    public boolean checkTopic(String topic, QuestionNode node) {
      if (node.topic.equals(topic)) {
        return true;
      }
      return false;
    }
  }
  
  /**
   * Returns a number of questions in a particular topic group in random order
   * 
   * @param topic
   * @param numQuestions
   */
  public ArrayList<QuestionNode> getQuestions(String topic, int numQuestions){
    return null;
  }
  
  /**
   * Parses a json file and adds the information to the data fields
   * 
   * @param jsonFilePath
   * @return
 * @throws ParseException 
 * @throws IOException 
 * @throws FileNotFoundException 
   */
  public boolean loadJSON(String jsonFilePath) throws FileNotFoundException, IOException, ParseException {
    // Make as many private helper methods as we need
	Object obj = new JSONParser().parse(new FileReader(jsonFilePath));
	
    return true;
  }
  
  /**
   * Saves all questions in a new json file
   * 
   * @return
   */
  public boolean saveToJSON() {
    return true;
  }
  
  /**
   * Gets the total number of questions available across all topics
   * 
   * @return
   */
  public int getSize() {
    return numQuestions;
  }
  
  /**
   * Gets the number of questions available for a particular topic
   * 
   * @param topic
   * @return
   */
  public int getSize(String topic) {
    return 0;
  }
}
