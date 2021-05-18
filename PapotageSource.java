// import
import java.util.ArrayList;


public class PapotageSource
  extends Bavard{
    // attributes
    // list of people with access to the message
    protected ArrayList<BavardListener> listeners = new ArrayList<BavardListener>();

    // builder
    public PapotageSource(){
      super();
    }

    public ArrayList<BavardListener> getListeners() {
      return listeners;
    }

    public void addPapotageListener(BavardListener listener){
      this.listeners.add(listener);
      System.out.println("you just added a listener.");
    }

    public void removePapotageListener(BavardListener listener){
      if (this.listeners.size() == 0){
        System.out.println("there is no any members in the group");
      }
      else{
        for (BavardListener bavard : this.listeners){
          if (bavard.getId() == listener.getId()){
            this.listeners.remove(bavard);
            System.out.println("you just removed a listener.");
          }
        }
      }
    }

    public void generateMessage(String subject, String body, BavardListener author){
      PapotageEvent message = new PapotageEvent(this, subject, body, author);
      for (BavardListener bavard : this.listeners){
        bavard.sendMessageToMe(message);
      }
    }

}
