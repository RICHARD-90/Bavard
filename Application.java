// import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;



public class Application extends JFrame implements ActionListener{
  private JPanel introText = new JPanel();
  // create a new panel with GridBagLayout manager
  private JPanel loginPanel = new JPanel(new GridBagLayout());
  // create a new panel with GridBagLayout manager
  private JPanel insPanel = new JPanel(new GridBagLayout());
  private JButton buttonLogin = new JButton("Login");
  private JButton buttonSubmit = new JButton("Register");

  protected Font font = new Font("Courier", Font.BOLD, 20);
  protected JLabel title= new JLabel("Welcome to the chatbook");

  protected JLabel idIns = new JLabel("Choose your id:");
  protected JTextField idInsValue = new JTextField(20);
  protected JLabel firstName = new JLabel("Enter your firstName:");
  protected JTextField firstNameValue = new JTextField(20);
  protected JLabel lastName = new JLabel("Enter your lastName:");
  protected JTextField lastNameValue = new JTextField(20);

  protected JLabel label = new JLabel("Enter your id:");
  protected JTextField id = new JTextField(20);

  protected GridBagConstraints constraints = new GridBagConstraints();
  protected GridBagConstraints constraintsIns = new GridBagConstraints();
  // Create a manager
  protected Gestionnaire manager = new Gestionnaire();

  // builder
  public Application(){
    super();
    this.setTitle("ChatBook");
    this.setSize(320, 568);
    this.setLayout(new GridLayout(3, 1));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    /**
   set the introduction part of the first page
   */
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setFont(font);
    introText.add(title);
    this.add(introText);
    /**
    set the connexion part of the first page
    */
    buttonLogin.addActionListener(this);
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);
    // add components to the panel
    constraints.gridx = 0;
    constraints.gridy = 0;
    loginPanel.add(label, constraints);
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    constraints.gridx = 1;
    loginPanel.add(id, constraints);
    //++++++++++++++++++++++++++++++++++++++++++++++++++
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    loginPanel.add(buttonLogin, constraints);
    // set border for the panel
    loginPanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Login"));
    // add the panel to this frame
    this.add(loginPanel);
    /**
    set the resgister part of the first page
    */
    buttonSubmit.addActionListener(this);
    constraintsIns.anchor = GridBagConstraints.WEST;
    constraintsIns.insets = new Insets(10, 10, 10, 10);
    // add components to the panel
    constraintsIns.gridx = 0;
    constraintsIns.gridy = 0;
    insPanel.add(idIns, constraintsIns);
    constraintsIns.gridx = 1;
    insPanel.add(idInsValue, constraintsIns);
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    constraintsIns.gridx = 0;
    constraintsIns.gridy = 1;
    insPanel.add(firstName, constraintsIns);
    constraintsIns.gridx = 1;
    insPanel.add(firstNameValue, constraintsIns);
    //++++++++++++++++++++++++++++++++++++++++++++++++
    constraintsIns.gridx = 0;
    constraintsIns.gridy = 2;
    insPanel.add(lastName, constraintsIns);
    constraintsIns.gridx = 1;
    insPanel.add(lastNameValue, constraintsIns);
    //+++++++++++++++++++++++++++++++++++++++++++++++
    constraintsIns.gridx = 0;
    constraintsIns.gridy = 3;
    constraintsIns.gridwidth = 2;
    constraintsIns.anchor = GridBagConstraints.CENTER;
    insPanel.add(buttonSubmit, constraintsIns);
    // set border for the panel
    insPanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Register"));
    // add the panel to this frame
    this.add(insPanel);

    buttonLogin.setActionCommand("buttonLogin");
    buttonSubmit.setActionCommand("buttonSubmit");
  }

  public void actionPerformed(ActionEvent arg0){
    if(arg0.getActionCommand().equals("buttonLogin"))
      manager.connectMe( getIntFromTextField(id));

    if(arg0. getActionCommand().equals("buttonSubmit")){
      manager.addMe(getIntFromTextField(idInsValue), firstNameValue.getText(), lastNameValue.getText());
      title.setText("Welcome to the chatbook \n\n\n Inscription reussie.");
    }
  }

  public static int getIntFromTextField(JTextField textField){
        String text = textField.getText();
        return Integer.parseInt(text);
    }

}
