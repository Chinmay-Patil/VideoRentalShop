package shop.main;

import shop.ui.UIFactory;
import shop.data.Data;

import shop.ui.UI;


public class Main {
	private Main() {
	}

	public static void main(String[] args) {
		
		
		

		UI ui = UIFactory.popupUi();
		//UI ui = UIFactory.textUI();
	    Control control = new Control(Data.newInventory(), ui);
		control.run();
		System.exit(0);
	}
}