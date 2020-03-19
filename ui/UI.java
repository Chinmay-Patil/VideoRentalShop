package shop.ui;
import shop.main.Control;

public interface UI {
  public void processMenu(UIMenu menu,Control c);
  public String[] processForm(UIMenu form);
  public void displayMessage(String message);
  public void displayError(String message);
}
