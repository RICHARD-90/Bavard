//import
import java.time.LocalTime;

public class OffLineBavardEvent{
  protected LocalTime date;

  public OffLineBavardEvent(Object source){
    this.date = LocalTime.now();
  }


  public LocalTime getDate(){
    return date;
  }
}
