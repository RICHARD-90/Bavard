
// import
import java.util.ArrayList;
import javax.swing.JFrame;

public class BavardListener
  extends Bavard
  implements PapotageListener, OnLineBavardListener, OffLineBavardListener{
    // attributes
    // attributes used to identify a listener
    protected int id;
    protected String firstName, lastName;

    protected MyWindow window;
    protected Gestionnaire manager;

    protected boolean capteur = false;
    protected OnLineBavardEvent connectionEvent;
    protected OffLineBavardEvent logOutEvent;

    protected ArrayList<BavardListener> list = new ArrayList<BavardListener>();


    // message's historical
    ArrayList<PapotageEvent> historical = new ArrayList<PapotageEvent>();

    // builder
    public BavardListener(int id, String firstName, String lastName){
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
    }


    // getters & setters
	public int getId() {
    /**
    * Returns value of id
    */
		  return id;
	}


  public void setWindow(MyWindow w){
    this.window = w;
  }


  public MyWindow getWindow(){
    return this.window;
  }


  public void setManager(Gestionnaire g){
    this.manager = g;
  }


  public Gestionnaire getManager(){
    return this.manager;
  }


	public void setId(int id) {
    /**
  	* Sets new value of id
  	*/
		this.id = id;
	 }


	public String getFirstName() {
    /**
  	* Returns value of firstName
  	*/
		return firstName;
	}


	public void setFirstName(String firstName) {
    /**
  	* Sets new value of firstName
  	*/
		this.firstName = firstName;
	}


	public String getLastName() {
    /**
    * Returns value of lastName
    */
		return lastName;
	}


  public boolean isMe(BavardListener bavard){
    if (bavard.getId() == this.getId()){
      return true;
    }
    else {
      return false;
    }
  }


	public void setLastName(String lastName) {
    /**
    * Sets new value of lastName
    */
		this.lastName = lastName;
	}

  @Override
  public void turnCapteurOn(){
    this.capteur = true;
  }

  @Override
  public void turnCapteurOff(){
    this.capteur = false;
  }


  public boolean getCapteur(){
    return this.capteur;
  }


  public void sendMessageToMe(PapotageEvent event){
    /*
    * add each message to the hotorical of the current user
    */
    this.historical.add(event);
    String texte = "";
    if (this.isMe(event.getAuthor())){
      texte = "moi: " + event.getSubject();
    }
    else{
      texte = event.getAuthor().getFirstName() +" "+ event.getAuthor().getLastName() + ": " + event.getSubject();
    }

    this.window.getModel().addElement(texte);
  }

  @Override
  public void iAmConnected(){
    /**
    * sets a connection event when the user is connected
    */
    OnLineBavardEvent connectionMessage = new OnLineBavardEvent(this);
    this.turnCapteurOn();
    this.connectionEvent = connectionMessage;
  }

  @Override
  public OnLineBavardEvent getConnectionEvent(){
    return this.connectionEvent;
  }


  public boolean inList(ArrayList<BavardListener> list){
    boolean result = false;
    for (BavardListener b: list){
      if (b.getId() == this.getId()){
        result = true;
      }
      else result = false;
    }

    return result;
  }


  public void notifyMe(){
    for (BavardListener b: this.getManager().getListBavardConnected()){
      if (b.inList(list)){
        // do nothing
      }

      else{
        notificateConnection(b);
        list.add(b);
      }
    }
  }

  @Override
  public void notificateConnection(BavardListener bavard){
    /*
    * send the notification
    */
    if (this.isMe(bavard)){
        // do nothing
      }
      else{
          String message = bavard.getFirstName() + " " + bavard.getLastName() + " is connected      " + bavard.getConnectionEvent().getDate();
          this.getWindow().getNotificationModel().addElement(message);
      }
  }


  public ArrayList<PapotageEvent> getHistorical(){
    return historical;
  }

  @Override
  public OffLineBavardEvent getLogOutEvent(){
    return this.logOutEvent;
  }

  @Override
  public void setLogOutEvent(){
    this.logOutEvent = new OffLineBavardEvent(this);
  }

  public void deleteFromList(BavardListener b){
    for (int i = 0; i < this.list.size(); i++){
      if (b.isMe(this.list.get(i))){
        this.list.remove(i);
      }
    }
  }

  @Override
  public void logOutNotification(BavardListener b){
    /*
    * send the notification
    */
    this.deleteFromList(b);
    if (this.isMe(b)){
        // do nothing
      }
      else{
          String message = b.getFirstName() + " " + b.getLastName() + " logged out      " + b.getLogOutEvent().getDate();
          this.getWindow().getNotificationModel().addElement(message);
      }
  }


}
