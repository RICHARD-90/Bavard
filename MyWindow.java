// import
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.GridLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;


public class MyWindow extends JFrame implements ActionListener, ListSelectionListener{
  // synchronization list
  ArrayList<String> synListe = new ArrayList<String>();
  protected BavardListener bavard;
  protected Gestionnaire manager;

  protected DefaultListModel model = new DefaultListModel();
  protected JList list = new JList(model);


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

  protected JLabel authorContent = new JLabel("Aucun");
  protected JLabel subjectContent = new JLabel("");
  protected JLabel bodyContent = new JLabel("");

  //builder
  public MyWindow(BavardListener bavard, Gestionnaire manager){
    this.manager = manager;
    this.bavard = bavard;

    this.setTitle("ChatBook");
    this.setSize(320, 568);
    this.setLayout(new GridLayout(3, 1));
    this.setLocationRelativeTo(null);
    this.setVisible(true);
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

    this.add(introText);
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
    BorderFactory.createEtchedBorder(), "textArea"));

    // add the panel to this frame
    this.add(writerPanel);
  }

  public DefaultListModel getModel(){
    return model;
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


  public void valueChanged(ListSelectionEvent event){
    content(list.getSelectedIndex());
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

}
