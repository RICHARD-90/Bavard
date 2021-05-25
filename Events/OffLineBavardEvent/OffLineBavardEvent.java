

//import
import java.time.LocalTime;
import Bavard.*;

public class OffLineBavardEvent extends BavardListener{
  protected LocalTime date;

  public OffLineBavardEvent(Object source){
    this.date = LocalTime.now();
  }


  public LocalTime getDate(){
    return date;
  }
}
