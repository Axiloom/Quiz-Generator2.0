import java.util.ArrayList;

public class Question {

  ArrayList<ArrayList<QuestionNode>> topics; // holds ArrayList of questions for specific topics
  
  class QuestionNode{
    String question; // question
    String[] options; // all options
    String answer; // correct answer
    
    QuestionNode(){
      
    }
  }
}
