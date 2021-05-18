// import
import java.util.ArrayList;

public interface PapotageListener{

  public int getId();
  public String getFirstName();
  public String getLastName();
  public ArrayList<PapotageEvent> getHistorical();
  public void sendMessageToMe(PapotageEvent event);
}
