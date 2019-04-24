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
<<<<<<< HEAD
=======
    String topic;
>>>>>>> branch 'master' of https://github.com/Rockapella/Quiz-Generator2.0
    
    /**
     * TODO
     * 
     * @param metadata
     * @param questionText
     * @param image
     * @param options
     */
<<<<<<< HEAD
    QuestionNode(String metadata, String questionText, String image, String[] options){
      this.metadata = metadata;
      this.question = questionText;
      this.options = options;
      img = new ImageView(new Image(image));
      answer = findAnswer(); // detect the correct answer, do we need this?
=======
    QuestionNode(String metadata, String questionText, String image, String[] options, String topic){
      this.metadata = metadata;
      this.question = questionText;
      this.options = options;
      img = new ImageView(new Image(image));
      findAnswer(); // detect the correct answer, do we need this?
      this.topic = topic;
>>>>>>> branch 'master' of https://github.com/Rockapella/Quiz-Generator2.0
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
  }
}
