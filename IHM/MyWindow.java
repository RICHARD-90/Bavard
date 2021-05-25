//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

import Bavard.*;
import Concierge.*;
import Events.*;
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
public class MyWindow extends JFrame implements ActionListener, ListSelectionListener, WindowListener{
  // attributes
  protected ArrayList<String> synListe = new ArrayList<String>();
  protected BavardListener bavard;
  protected Gestionnaire manager;

  protected DefaultListModel model = new DefaultListModel();
  protected DefaultListModel sortedMessage = model;
  protected JList list = new JList(sortedMessage);

  protected DefaultListModel notificationModel = new DefaultListModel();
  protected JList notificationList = new JList(notificationModel);

  protected JPanel partOne = new JPanel(new GridLayout(1, 2));

  protected JPanel panelUtility = new JPanel(new GridLayout(4, 1));

  protected JPanel introText = new JPanel(new GridLayout(3, 1));
  protected Font font = new Font("Courier", Font.BOLD, 20);
  protected JLabel label = new JLabel("Write your message here: ");
  protected JLabel submitLabel = new JLabel("Subject: ");
  protected JTextField textMessage = new JTextField(100);
  protected JTextField subjectText = new JTextField(100);
  // create a new panel with GridBagLayout manager
  protected JPanel messagePanel = new JPanel(new GridLayout(1, 2));
  // create a new panel with GridBagLayout manager
  protected JPanel writerPanel = new JPanel(new GridBagLayout());
  protected JButton buttonSubmit = new JButton("Send");
  protected GridBagConstraints constraints = new GridBagConstraints();


  protected JPanel notificationPanel = new JPanel(new GridBagLayout());
  protected JPanel triBavard = new JPanel(new GridBagLayout());

  protected JLabel authorContent = new JLabel("Aucun");
  protected JLabel subjectContent = new JLabel("");
  protected JLabel bodyContent = new JLabel("");

  protected JList jListB;



  //builder
  public MyWindow(BavardListener bavard, Gestionnaire manager){
    this.manager = manager;
    this.bavard = bavard;
    //************************************************************
    this.setTitle("ChatBook");
    this.setSize(320, 568);
    this.setLayout(new GridLayout(3, 1));
    this.setLocationRelativeTo(null);
    this.addWindowListener(this);
    //************************************************************
    jListB = new JList(this.getManager().getDynamicList());
    JScrollPane sListB = new JScrollPane(jListB);
    jListB.addListSelectionListener(this);
    //************************************************************
    JLabel id = new JLabel("Id: " + bavard.getId());
    JLabel firstName = new JLabel("First Name: " + bavard.getFirstName());
    JLabel lastName = new JLabel("Last Name: " + bavard.getLastName());
    id.setFont(font);
    introText.add(id);
    firstName.setFont(font);
    introText.add(firstName);
    lastName.setFont(font);
    introText.add(lastName);
    partOne.add(introText);
    //**************************************************************
    JLabel notifLabel = new JLabel("Notifications");
    JLabel sortLabel = new JLabel("Sort by:");
    JScrollPane sNotificationList = new JScrollPane(notificationList);
    //sNotificationList.addListSelectionListener(this);
    // notificationPanel.add(sNotificationList);
    panelUtility.add(notifLabel);
    panelUtility.add(sNotificationList);
    // triBavard.add(sListB);
    panelUtility.add(sortLabel);
    panelUtility.add(sListB);
    partOne.add(panelUtility);
    this.add(partOne);
    //**************************************************************
    //the list object will set automatically with this new model list
    JScrollPane sList = new JScrollPane(list);
    list.addListSelectionListener(this);
    messagePanel.add(sList);
    //the second part of the panel is set with GridLayout
    JPanel messagePanelPart2 = new JPanel(new GridLayout(3, 1));
    messagePanelPart2.add(authorContent);
    messagePanelPart2.add(subjectContent);
    messagePanelPart2.add(bodyContent);
    messagePanel.add(messagePanelPart2);
    messagePanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Messages"));
    this.add(messagePanel);
    //**************************************************************
    buttonSubmit.addActionListener(this);
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);
    // add components to the panel
    constraints.gridx = 0;
    constraints.gridy = 0;
    writerPanel.add(submitLabel, constraints);
    constraints.gridx = 1;
    writerPanel.add(subjectText, constraints);
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    constraints.gridx = 0;
    constraints.gridy = 1;
    writerPanel.add(label, constraints);
    constraints.gridx = 1;
    writerPanel.add(textMessage, constraints);
    //++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    writerPanel.add(buttonSubmit, constraints);
    // set border for the panel
    writerPanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "text Area"));
    // add the panel to this frame
    this.add(writerPanel);
  }


  public DefaultListModel getModel(){
    return model;
  }


  public DefaultListModel getNotificationModel(){
    return notificationModel;
  }


  public void setModel(){
    /**
    * synchronization of the two lists
    */
    for (PapotageEvent message : bavard.getHistorical()){
      String texte = message.getAuthor().getFirstName() +" "+ message.getAuthor().getLastName() + ": " + message.getSubject();
      this.synListe.add(texte);
      this.model.addElement(texte);
    }
  }


  public void content(int id){
    // each message is at the same place in both lists
          PapotageEvent message = bavard.getHistorical().get(id);
          authorContent.setText("from: " + message.getAuthor().getFirstName() +" "+ message.getAuthor().getLastName());
          subjectContent.setText("Sbject: " + message.getSubject());
          bodyContent.setText("Body: " + message.getBody());
  }


  public void setTextMessage(int id){
      try{
        if (this.sortedMessage.size() != 0 )
          this.sortedMessage.removeAllElements();
        this.copyThisInto(sortedMessage, generateListMessageOf(id));
      }
      catch (Exception IndexOutOfBoundsException){
            JOptionPane.showMessageDialog(this,
                                      "the user you chose has not sent a message yet",
                                      "Empty List",
                                      JOptionPane.INFORMATION_MESSAGE);
      }
  }


  public void copyThisInto(DefaultListModel liste1, DefaultListModel liste2){
    /*
      copy liste2 into liste1
    */
    for(int i = 0; i < liste2.size(); i++){
      liste1.addElement(liste2.get(i));
    }
  }


  public DefaultListModel generateListMessageOf(int id){
    /*
      Modifies the list containing the messages
    */
    DefaultListModel list = new DefaultListModel();
    if (id == 0){
      for (PapotageEvent event : this.getBavard().getHistorical()){
        if(event.getAuthor().getId() == this.getBavard().getId()){
          String text = "moi " + ": " + event.getSubject();
          list.addElement(text);
        }
        else{
          String text = event.getAuthor().getFirstName() +" "+ event.getAuthor().getLastName() + ": " + event.getSubject();
          list.addElement(text);
        }
      }
    }
    else{
      BavardListener bavard = this.getManager().getListeners().get(id-1);
      for (PapotageEvent event : this.getBavard().getHistorical()){
        if (event.getAuthor() == bavard){
          if(event.getAuthor().getId() == this.getBavard().getId()){
            String text = "moi " + ": " + event.getSubject();
            list.addElement(text);
          }
          else{
            String text = event.getAuthor().getFirstName() +" "+ event.getAuthor().getLastName() + ": " + event.getSubject();
            list.addElement(text);
          }
        }
      }
    }
    return list;
  }


  public void valueChanged(ListSelectionEvent event){
    JList jl = (JList) event.getSource();
    if (jl == jListB){
      setTextMessage(jListB.getSelectedIndex());
    }
    else if (jl == list){
      content(list.getSelectedIndex());
    }
  }


  public Gestionnaire getManager(){
    return manager;
  }


  public BavardListener getBavard(){
    return bavard;
  }


  public void actionPerformed(ActionEvent arg0){
    getManager().sendMyText(subjectText.getText(), textMessage.getText(), getBavard());
  }


  public void windowOpened(WindowEvent e) {
  }


  public void windowClosing(WindowEvent e) {
    int reponse = JOptionPane.showConfirmDialog( this,
                                                "Voulez-vous vraiment vous deconnecté ?",
                                                "Quitter l'application",
                                                JOptionPane.YES_NO_OPTION);
    if (reponse == JOptionPane.YES_OPTION){
      // System.out.println("Closed");
      this.getBavard().setLogOutEvent();
      this.getManager().logOut(this.getBavard());
     }
  }


  public void windowClosed(WindowEvent e) {

  }


  public void windowIconified(WindowEvent e) {
  }


  public void windowDeiconified(WindowEvent e) {
  }


  public void windowActivated(WindowEvent e) {
  }


  public void windowDeactivated(WindowEvent e) {
  }
}
