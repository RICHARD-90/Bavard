public class PapotageEvent
  extends Bavard{
    // attributes
    protected String subject, body;
    protected BavardListener author;

    // builder
    public PapotageEvent(Object source, String p_subject, String p_body, BavardListener author){
      super();
      this.subject = p_subject;
      this.body = p_body;
      this.author = author;
    }

    // getters & setters
	public String getSubject() {
    /**
    * Returns value of subject
    */
		return subject;
	}


	public void setSubject(String subject) {
    /**
  	* Sets new value of subject
  	*/
		this.subject = subject;
	}


	public String getBody() {
    /**
    * Returns value of body
    */
		return body;
	}


	public void setBody(String body) {
    /**
  	* Sets new value of body
  	*/
		this.body = body;
	}

  public BavardListener getAuthor(){
    /**
    * Returns value of body
    */
    return author;

  }
}
