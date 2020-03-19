package shop.ui;

import shop.main.MenuEnum;
import shop.main.Control;

/**
 * @see UIMenuBuilder
 * -exclude
 */
public final class UIMenu  {
  private final String _heading;
  private final MenuEnum[] _menu;


 public  UIMenu(String heading, MenuEnum[] menu) {
    _heading = heading;
    _menu = menu;
  }
  public int size() {
    return _menu.length;
  }
  public String getHeading() {
    return _heading;
  }
  public String getPrompt(int i) {
    return _menu[i].toString();
  }
  public void runAction(int i, Control c) {
    _menu[i].run(c);
  }
public boolean checkInput(int i, String response) {
	
	if(null == _menu[i])
		return true;
	
	return _menu[i].run(response);
	
}
}
