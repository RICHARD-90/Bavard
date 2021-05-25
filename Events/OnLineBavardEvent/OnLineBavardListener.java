

// import
import Bavard.*;

public interface OnLineBavardListener {
  public void turnCapteurOn();

  public void iAmConnected();

  public OnLineBavardEvent getConnectionEvent();

  public void notificateConnection(BavardListener bavard);
}
