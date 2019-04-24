import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
    ArrayList<String> options; // all options
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
    QuestionNode(String topic, String metadata, String questionText, String image, ArrayList<String> options){
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
   * No-arg Constructor for Question class
   */
  public Question() {
    topics = new ArrayList<>();
    numQuestions = 0;
    isCorrect = new ArrayList<>(); // might not need?
  }
  
  /**
   * Returns a number of questions in a particular topic group in random order
   * 
   * @param topic
   * @param numQuestions
   */
  public ArrayList<QuestionNode> getQuestions(String topic, int numQuestions){
    ArrayList<QuestionNode> result = new ArrayList<QuestionNode>();
    for (int i = 0; i < topics.size(); ++i) {
      if (topics.get(i).get(1).topic.equals(topic)) {
        Random r = new Random();
        while (numQuestions > 0) {
          int index = r.nextInt(topics.get(i).size());
          result.add(topics.get(i).get(index));
          --numQuestions;
        }
      }
    }
  
    return result;
  }
  
  /**
   * Adds a node to the topicList according to its topic
   * @param node
   * @return
   */
  public boolean addQuestionNode(QuestionNode node) {
	  return true;
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
	JSONObject jo = (JSONObject) obj;
	JSONArray questionArray = (JSONArray) jo.get("questionArray");
	
	for (int i = 0; i < questionArray.size(); i ++) {
		JSONObject jsonQuestion = (JSONObject) questionArray.get(i);
		String metaData = (String) jsonQuestion.get("meta-data"); // meta data
		String question = (String) jsonQuestion.get("question"); // question
		String topic = (String) jsonQuestion.get("topic");
		String image = (String) jsonQuestion.get("image");
		// need to interate through the choices
		JSONArray answerArray = (JSONArray) jsonQuestion.get("choiceArray");
		ArrayList<String> choices = new ArrayList<>(); 
		for (Object answer : answerArray) {
			choices.add((String)((ArrayList) answer).get(3));
			if (((String)((ArrayList) answer).get(1)).equals("T")) {
				String correctAnswer = (String)((ArrayList) answer).get(1); // right answer				
			}
		}
		QuestionNode newQuestion = new QuestionNode(topic, metaData, question, image, choices);
		
		// create the new node here 
		// write method to add the node 
		
	}
		
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
   * @param topic - the topic which we are finding number of questions for
   * @return number of questions for a specific topic, or -1 if topic does not exist
   */
  public int getSize(String topic) {
    for (int i = 0; i < topics.size(); ++i) {
      if (topics.get(i).get(1).topic.equals(topic)) {
        return topics.get(i).size();
      }
    }
    return -1;
  }
}
