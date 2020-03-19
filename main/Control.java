package shop.main;

import shop.ui.UI;
import shop.ui.UIMenu;
import shop.ui.UIError;
import shop.data.Inventory;

 public class Control{
 // private static final int EXITED = 0;
 // private static final int EXIT = 1;
 // private static final int START = 2;
  private static final int NUMSTATES = 10;
  private UIMenu[] _menus;
  private StateEnum _state;

//  private UIForm _getVideoForm;
  //private UIFormTest _numberTest;
  //private UIFormTest _stringTest;
  private UIMenu _getvideoForm;  
  private Inventory _inventory;
  private UI _ui;
  
  private final MenuEnum[] arrStart = {
		  MenuEnum.Default,
		  MenuEnum.numcopiesadd,
		  MenuEnum.checkinvid,
		  MenuEnum.checkvidout,
		  MenuEnum.printinve,
		  MenuEnum.erase,
		  MenuEnum.undo,
		  MenuEnum.redo,
		  MenuEnum.printallinorder,
		  MenuEnum.exit,
		  MenuEnum.boguscontent
		  
  };
  
  private final MenuEnum[] arrExit = {
		  
		  MenuEnum.default_,
		  MenuEnum.yes,
		  MenuEnum.no

		  
  };
  
  final String WANT_TO_EXIT = "Are you sure you want to exit?";
  final String BOBS_VIDEO = "Bob's Video";
  final String ENTER_VIDEO = "Enter Video";
  final String ERROR = "UI Closed";
  
  private final MenuEnum[] testnames = {
		  
		  MenuEnum.title,
		  MenuEnum.year,
		  MenuEnum.director
  };
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new UIMenu[NUMSTATES];
    _state = StateEnum.START;
    addStatus(StateEnum.START,arrStart,BOBS_VIDEO);
    addStatus(StateEnum.EXIT,arrExit,WANT_TO_EXIT);
    set_getVideoForm(new UIMenu(ENTER_VIDEO,testnames));
  }
    
    void run() {
    	
    	try {
    		while(get_state()!= StateEnum.EXITED) {
    			get_ui().processMenu(_menus[get_state().getnumber()],this);
    		}
    	}
    	catch (UIError e)
    	{
    		get_ui().displayError(ERROR);
    		
    	}
    }
    
    public void addStatus(StateEnum statenum, MenuEnum[] arr ,String Message) {
    	
    	_menus[statenum.getnumber()] =  new UIMenu(Message,arr);
    }
    
    public UI get_ui() {
    	
    	return _ui;
    }
    
    public void set_ui(UI _ui) {
    	
        this._ui = _ui;
    }
    
    public  UIMenu get_getVideoForm() {
    	
      return _getvideoForm;
    }
    
    public void set_getVideoForm(UIMenu _getVideoForm) {
    	
        this._getvideoForm = _getVideoForm;
    }
    
    public  Inventory get_inventory() {
    	
        return _inventory;
      }
    public  StateEnum get_state() {
    	
        return _state;
      }
    
 public void set_inventory(Inventory _inventory) {
    	
        this._inventory = _inventory;
    }
 
 public void set_state(StateEnum _state) {
 	
     this._state =_state;
 }
 

    	
  
}
    
    
    /*
    UIFormTest yearTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            int i = Integer.parseInt(input);
            return i > 1800 && i < 5000;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _numberTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            Integer.parseInt(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _stringTest = new UIFormTest() {
        public boolean run(String input) {
          return ! "".equals(input.trim());
        }
      };

    UIFormBuilder f = new UIFormBuilder();
    f.add("Title", _stringTest);
    f.add("Year", yearTest);
    f.add("Director", _stringTest);
    _getVideoForm = f.toUIForm("Enter Video");
  }
  
  void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default",
      new UIMenuAction() {
        public void run() {
          _ui.displayError("doh!");
        }
      });
    m.add("Add/Remove copies of a video",
      new UIMenuAction() {
        public void run() {
          String[] result1 = _ui.processForm(_getvideoForm);
          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

          UIFormBuilder f = new UIFormBuilder();
          f.add("Number of copies to add/remove", _numberTest);
          String[] result2 = _ui.processForm(f.toUIForm(""));
                                             
          UndoableCommand c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
          if (! c.run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Check in a video",
      new UIMenuAction() {
        public void run() {
          // TODO  
        	String[] result1 = _ui.processForm(_getvideoForm);
			Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

			UndoableCommand c = Data.newInCmd(_inventory, v);
			if (!c.run()) {
				_ui.displayError("Command failed");
			   }
        }
      });
    m.add("Check out a video",
      new UIMenuAction() {
        public void run() {
          // TODO  
        	String[] result1 = _ui.processForm(_getvideoForm);
			Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

			UndoableCommand c = Data.newOutCmd(_inventory,v);
			if (!c.run()) {
				_ui.displayError("Command failed");
        	
			   }
        }
      });
    m.add("Print the inventory",
      new UIMenuAction() {
        public void run() {
          _ui.displayMessage(_inventory.toString());
        }
      });
    m.add("Clear the inventory",
      new UIMenuAction() {
        public void run() {
          if (!Data.newClearCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Undo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newUndoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Redo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newRedoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Print top ten all time rentals in order",
      new UIMenuAction() {
        public void run() {
          // TODO  
        	
        	Iterator<Record> it = _inventory.iterator((r1, r2) -> r2.numRentals() - r1.numRentals());
			int count = 10;
			StringBuilder s = new StringBuilder();
			while (it.hasNext() && count > 0) {
				Record r = it.next();
				s.append(r.toString());
				s.append("\n");
				count--;
			}
			_ui.displayMessage(s.toString());
        }
    }
  );
    m.add("Exit",
      new UIMenuAction() {
        public void run() {
          _state = EXIT;
        }
      });
    
    m.add("Initialize with bogus contents",
      new UIMenuAction() {
        public void run() {
          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Bob's Video");
  }
  private void addEXIT(int stateNum) {
    UIMenuBuilder m = new UIMenuBuilder();
    
    m.add("Default", new UIMenuAction() { public void run() {} });
    m.add("Yes",
      new UIMenuAction() {
        public void run() {
          _state = EXITED;
        }
      });
    m.add("No",
      new UIMenuAction() {
        public void run() {
          _state = START;
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
  }
}*/
