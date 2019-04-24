import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Question {

  ArrayList<ArrayList<QuestionNode>> topics; // holds ArrayList of questions for specific topics
  ArrayList<Boolean> isCorrect; // keeps track of correct/incorrect answers throughout the quiz
  
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
     * 
     * @param topic
     * @return
     */
    public boolean checkTopic(String topic) {
      return true;
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
   */
  public boolean loadJSON(String jsonFilePath) {
    // Make as many private helper methods as we need
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
}
