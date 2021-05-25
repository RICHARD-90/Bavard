
//import
import java.time.LocalTime;
import Bavard.*;


public class OnLineBavardEvent extends BavardListener{
  protected LocalTime date;

  public OnLineBavardEvent(Object source){
    this.date = LocalTime.now();
  }


  public LocalTime getDate(){
    return date;
  }
}
