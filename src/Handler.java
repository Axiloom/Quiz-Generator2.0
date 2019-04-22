import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class Handler implements EventHandler<ActionEvent> {
  Button button;
  
  Handler(Button load){
    this.button = load;
  }
  
  @Override
  public void handle(ActionEvent arg0) {
    //TODO
  }
}
