// import
import java.util.ArrayList;


public class BavardListener
  extends Bavard
  implements PapotageListener{
    // attributes
    // attributes used to identify a listener
    protected int id;
    protected String firstName, lastName;

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


	public void setLastName(String lastName) {
    /**
    * Sets new value of lastName
    */
		this.lastName = lastName;
	}


  public void sendMessageToMe(PapotageEvent event){
    this.historical.add(event);
  }

  public ArrayList<PapotageEvent> getHistorical(){
    return historical;
  }


}
