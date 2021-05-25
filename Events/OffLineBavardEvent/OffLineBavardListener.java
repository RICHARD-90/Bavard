
// import
import Bavard.*;

public interface OffLineBavardListener{

  public void turnCapteurOff();

  public OffLineBavardEvent getLogOutEvent();

  public void setLogOutEvent();

  public void logOutNotification(BavardListener b);
}
