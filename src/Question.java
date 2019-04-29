import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Main Question Class to retrieve anything question related
 * 
 * @author ATeam-99
 */
public class Question {

  private Hashtable<String, ArrayList<QuestionNode>> topics; // Hash table to store questions
  private int numQuestions; // total number of questions available

  /**
   * Each node represents a single question and all attributes associated with that question
   * 
   * @author ATeam-99
   *
   */
  class QuestionNode {
    String metadata; // metadata for a question
    String question; // question text
    ArrayList<String> options; // all options as answers
    String answer; // correct answer
    ImageView img; // image going along with the question
    String topic; // topic associated with this question

    /**
     * Each node represents a single question and all attributes associated with that question
     *
     * @param topic - the topic associated with this question
     * @param metadata - the metadata associated with this question
     * @param questionText - the text associated with this question
     * @param image - the image associated with this question
     * @param options - the possible answers associated with this question
     * @param correctAnswer - the correct answer for this questions
     */
    QuestionNode(String topic, String metadata, String questionText, String image,
        ArrayList<String> options, String correctAnswer) {

      if (!image.equals("none")) {
        img = new ImageView(new Image(image));
      }

      this.metadata = metadata;
      this.question = questionText;
      this.options = options;
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
   * Returns an ArrayList of questions for a particular topic group in random order
   * 
   * @param topic the topic which we want to search for
   * @param numQuestions the number of questions from that topic
   */
  public ArrayList<QuestionNode> getQuestions(String topic, int numQuestions) {

    // Random generator
    Random rand = new Random();

    // Array to store our questions
    ArrayList<QuestionNode> questions = new ArrayList<>();

    // Get random questions
    for (int i = 0; i < numQuestions; i++) {
      QuestionNode randomQuestion = topics.get(topic).get(rand.nextInt(topics.get(topic).size()));

      while (questions.contains(randomQuestion)) {
        randomQuestion = topics.get(topic).get(rand.nextInt(topics.get(topic).size()));
      }
      questions.add(randomQuestion);
    }
    return questions;
  }

  /**
   * Gets an ArrayList of QuestionNodes containing the questions for a given topic
   * 
   * @param topic - topic of questions being retrieved
   * @return ArrayList<QuestionNode> of questions
   */
  public ArrayList<QuestionNode> getQuestions(String topic) {
    return topics.get(topic);
  }

  /**
   * Adds a node to the topicList according to its topic
   *
   * @param topic - the topic of the question
   * @param questionText - the text of the question
   * @param metadata - the meta of the question
   * @param options - the options for the question
   * @param correctAnswer - the correct answer for the question
   * @param image - the image for the question
   */
  public void addQuestion(String topic, String questionText, String metadata,
      ArrayList<String> options, String correctAnswer, String image) {

    // Question to be added
    QuestionNode question =
        new QuestionNode(topic, metadata, questionText, image, options, correctAnswer);

    // Create topic if it doesnt exist
    if (!topics.containsKey(question.topic))
      topics.put(question.topic, new ArrayList<>());

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
  public boolean loadJSON(String jsonFilePath)
      throws FileNotFoundException, IOException, ParseException, URISyntaxException {

    // Read file from .jar
    File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    String inputFilePath = jarFile.getParent() + File.separator + jsonFilePath;
    FileInputStream fis = new FileInputStream(new File(inputFilePath));
    BufferedReader in = new BufferedReader(new InputStreamReader(fis));
    Object obj = new JSONParser().parse(in);

    JSONObject jo = (JSONObject) obj;
    JSONArray questionArray = (JSONArray) jo.get("questionArray");

    for (Object aQuestionArray : questionArray) {
      JSONObject jsonQuestion = (JSONObject) aQuestionArray;
      String metaData = (String) jsonQuestion.get("meta-data"); // meta data
      String question = (String) jsonQuestion.get("questionText"); // question
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
      addQuestion(topic, question, metaData, choices, correctAnswer, image);
    }
    return true;
  }

  /**
   * Saves all questions in a new json file
   * 
   * @param jsonFile - name of the new JSON file
   * @return true if the file was successfully saved
   */
  public boolean saveToJSON(String jsonFile) {
    // TODO check for duplicate json name
    
    // WORKING EXAMPLE
//    JSONObject obj = new JSONObject();
//    obj.put("Name", "crunchify.com");
//    obj.put("Author", "App Shah");
//
//    JSONArray company = new JSONArray();
//    company.add("Compnay: eBay");
//    company.add("Compnay: Paypal");
//    company.add("Compnay: Google");
//    obj.put("Company List", company);
//
//    // try-with-resources statement based on post comment below :)
//    try (FileWriter file = new FileWriter("/Users/<username>/Documents/file1.txt")) {
//        file.write(obj.toJSONString());
//        System.out.println("Successfully Copied JSON Object to File...");
//        System.out.println("\nJSON Object: " + obj);
//    }
    
    JSONObject questionJSON = new JSONObject();
    JSONArray questionArray = new JSONArray();
    List<String> topicList = getTopics();
    
    for(String topic : topicList) {
      ArrayList<Question.QuestionNode> questionList = getQuestions(topic);
      for(QuestionNode question : questionList) {
        JSONObject aQuestion = new JSONObject();
        aQuestion.put("meta-data", question.metadata);
        aQuestion.put("questionText", question.question);
        aQuestion.put("topic", question.topic);
        aQuestion.put("image", question.img);
        
        JSONArray choiceArray = new JSONArray();
        for(String option : question.options) {
          JSONObject opt = new JSONObject();
          if(option.equals(question.answer)) {
            opt.put("isCorrect", "T");
            opt.put("choice", option);
          }
          else {
            opt.put("isCorrect", "F");
            opt.put("choice", option);
          }
          choiceArray.add(opt);
        }
        aQuestion.put("choiceArray", choiceArray);
        
        questionArray.add(aQuestion);
      }
    }
    questionJSON.put("questionArray", questionArray);
    
    StringWriter out = new StringWriter();
    try {
      questionJSON.writeJSONString(out);
    } catch (Exception e) {
      System.out.println("test");
      return false;
    }
    
    String jsonText = out.toString();
    System.out.print(jsonText);
    
//    try (FileWriter file = new FileWriter(jsonFile)) {
//      file.write(topics.toJSONString());
//      System.out.println("good");
//    } catch (Exception e) {
//      System.out.println("error");
//    }
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

    if (topic == null)
      return -1;

    else if (!topics.containsKey(topic)) {
      return 0;
    }

    return topics.get(topic).size();
  }

  /**
   * Gets a set of alphabetized topics
   *
   * @return List containing all topics in the data set
   */
  public List<String> getTopics() {

    List<String> alphabetizedList = new ArrayList<>(topics.keySet());

    Collections.sort(alphabetizedList);

    return alphabetizedList;
  }
}
