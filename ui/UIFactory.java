package shop.ui;


public class UIFactory {
  private UIFactory() {}
 // static private UI popuoUI = new PopupUI();
 // static private UI textUI = new TextUI();
 
public static UI textUI() {
	// TODO Auto-generated method stub
	return new TextUI();
}
public static UI popupUi() {
	// TODO Auto-generated method stub
	return new PopupUI();
}

}
