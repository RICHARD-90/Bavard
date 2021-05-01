// import
import java.util.ArrayList;

public class Gestionnaire{
  // attributes
  // list of people with access to the message
  ArrayList<BavardListener> listeners = new ArrayList<BavardListener>();

  public static void main(String[] args){
    BavardListener pers1 = new BavardListener(1, "Blaise", "Pascale");
    BavardListener pers2 = new BavardListener(2, "Blaise", "Junior");
    BavardListener pers3 = new BavardListener(3, "RICHARD", "Dupont");

    PapotageSource classroom1 = new PapotageSource();

    classroom1.addPapotageListener(pers1);
    classroom1.addPapotageListener(pers2);

    classroom1.generateMessage("PRISE DE CONTACT", "Salut et bienvenue dans cette salle.", pers1);

    System.out.println(pers1.getHistorical());
    System.out.println(pers2.getHistorical());
    System.out.println(pers3.getHistorical());

    classroom1.addPapotageListener(pers3);

    classroom1.generateMessage("PRISE DE CONTACT", "Coucou, merci pour l'accueil. Comment allez-vous?", pers2);

    System.out.println(pers1.getHistorical());
    System.out.println(pers2.getHistorical());
    System.out.println(pers3.getHistorical());
  }
}
