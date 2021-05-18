//import
import java.time.LocalTime;

public class OnLineBavardEvent{
  protected LocalTime date;

  public OnLineBavardEvent(Object source){
    this.date = LocalTime.now();
  }


  public LocalTime getDate(){
    return date;
  }
}
