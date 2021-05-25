// import
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Gestionnaire{
  // attributes
  protected PapotageSource classeroom = new PapotageSource();
  protected ArrayList<BavardListener> bavrdConnected = new ArrayList<BavardListener>();
  protected DefaultListModel model = new DefaultListModel();
  protected JFrame frame;

  // builder
  public Gestionnaire(){
    super();
  }


  public ArrayList<BavardListener> getListeners(){
    return classeroom.getListeners();
  }


  public PapotageSource getSource(){
    return classeroom;
  }


  public ArrayList<Integer> verifyMyId(int id){
    ArrayList<Integer> list = new ArrayList<Integer>();
    int found = 0; // this value means that the id is not found
    int idIndex = 1000;
    for (int index = 0; index <this.getListeners().size(); index++){
      BavardListener bavard = this.getListeners().get(index);
      if (bavard.getId() == id){
        idIndex = index;
        found = 1;
      }
    }
    list.add(found);
    list.add(idIndex);
    return list;
  }


  public void connectMe(int id){
    if (verifyMyId(id).get(0) == 1){
      BavardListener bavard = this.getListeners().get(verifyMyId(id).get(1));
      launchMyWindow(bavard);
    }
    else{
      launchErrorIdWindow();
    }
  }

  public void setFrame(JFrame frame){
    this.frame = frame;
  }


  public JFrame getFrame(){
    return frame;
  }


  public void addNewBavardConnected(BavardListener bavard){
    this.bavrdConnected.add(bavard);
  }


  public ArrayList<BavardListener> getListBavardConnected(){
    return bavrdConnected;
  }


  public void launchMyWindow(BavardListener bavard){
    bavard.iAmConnected();
    bavard.getWindow().setVisible(true);
    this.setConnectedList(bavard);
  }


  public void setDynamicList(String b){
    if (this.model.size() == 0)
      this.model.addElement("All");
    this.model.addElement(b);
  }


  public DefaultListModel getDynamicList(){
    return model;
  }


  public void addMe(int idInsValue, String firstNameValue,String lastNameValue){
    if (verifyMyId(idInsValue).get(0) == 1){
      launcErrorIdChooseWindow();
    }
    else{
      BavardListener student = new BavardListener(idInsValue, firstNameValue, lastNameValue);
      MyWindow window = new MyWindow(student, this);
      student.setWindow(window);
      student.setManager(this);
      classeroom.addPapotageListener(student);
      this.setDynamicList(student.getFirstName()  + " " + student.getLastName());
    }
  }


  public void launcErrorIdChooseWindow(){
    JOptionPane.showMessageDialog(this.getFrame(),
                              "This id is already taken. please choose another",
                              "Id already used",
                              JOptionPane.INFORMATION_MESSAGE);
  }


  public void launchErrorIdWindow(){
    JOptionPane.showMessageDialog(this.getFrame(),
                              "your id is not recognized",
                              "Wrong Id",
                              JOptionPane.INFORMATION_MESSAGE);
  }


  public void sendMyText(String subject, String text, BavardListener bavard){
    classeroom.generateMessage(subject, text, bavard);
  }


  public void setConnectedList(BavardListener bavard){
    /*
    * sets the list of connected person
    */
    this.bavrdConnected.add(bavard);
    for (BavardListener b : this.getListeners()){
      b.notifyMe();
    }
  }


  public void deleteFromList(BavardListener b){
    for (int i = 0; i < this.getListBavardConnected().size(); i++){
      if (this.getListBavardConnected().get(i) == b){
        this.getListBavardConnected().remove(i);
        b.turnCapteurOff();
      }
    }
  }

  // public PapotageSource getClass
  public void logOut(BavardListener bavard){
    // remove the bavard from the list of connected baverd
    this.deleteFromList(bavard);
    // send the notification to each bavard in the group
    for (BavardListener b : this.getListeners()){
        b.logOutNotification(bavard);
    }
  }


public JFrame setJFrame(String name){
    JFrame window = new JFrame();
    // Define a title for our window
    window.setTitle(name);
    // Defines its size: 320 pixels wide and 568 pixels high
    //window.setSize(320, 568);
    // End the process when clicking on the red cross
    // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // And finally, make it visible
    window.setVisible(true);
    return window;
  }

}
