package shop.main;

import java.util.Iterator;

import shop.command.UndoableCommand;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.ui.UIMenu;
import shop.ui.UIFormTest;




public enum MenuEnum {
	
	yeartest("yearTest"),
	numberTest("numberTest"),
	stringTest("stringTest"),
	title("Title"),
	year("Year"),
	director("Director"),
	Default("Default"),
	numcopiesadd("Please enter number of copies you want to add"),
	checkinvid("Check In Video"),
	checkvidout("Check out Video"),
	printinve("Print Inventory"),
	erase("Clear Inventory"),
	undo("undo"),
	redo("redo"),
	printallinorder("Print top ten all time rentals in order"),
	exit("Exit"),
	boguscontent("Initialize With Bogus Content"),
	default_("default"),
	yes("Yes"),
	no("No");
	
	String name;
	
	MenuEnum(String val){
		name = val;
		
	}
	public String toString() {
		
		return name;
	} 
		 public boolean run(String input) {
	       return UIFormTest.run(input, this);
	        }
		 
		 public void run(Control c) {
			 
			 switch (this)
			 {
			 case Default :  c.get_ui().displayError("doh!"); break;
			case boguscontent:
			    	for (char m = 'a'; m<='z';m++)
			    	{
			    		Data.newAddCmd(c.get_inventory(), Data.newVideo(""+m,2000 , "p"), (int)(m - 'a' +1)).run();
			    	}
		
				break;
			case checkinvid:
				String[] result1 =c.get_ui().processForm(c.get_getVideoForm());
				Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

				UndoableCommand c1 = Data.newInCmd(c.get_inventory(), v);
				if (!c1.run()) {
					c.get_ui().displayError("Command failed");
				   }
				break;
			case checkvidout:
				String[] result12 = c.get_ui().processForm(c.get_getVideoForm());
				Video v2 = Data.newVideo(result12[0], Integer.parseInt(result12[1]), result12[2]);

				UndoableCommand c3 = Data.newOutCmd(c.get_inventory(),v2);
				if (!c3.run()) {
					c.get_ui().displayError("Command failed");
	        	
				   }
				break;
			case default_:
				c.set_state(StateEnum.EXIT);
				break;
			
			case erase:
				 if (!Data.newClearCmd(c.get_inventory()).run()) {
			            c.get_ui().displayError("Command failed");
			          }
				break;
			case exit:
				c.set_state(StateEnum.EXIT);
				break;
			case no:
				c.set_state(StateEnum.START);
				break;
			
			case numcopiesadd:
				  String[] r1 = c.get_ui().processForm(c.get_getVideoForm());
		          Video vm = Data.newVideo(r1[0], Integer.parseInt(r1[1]), r1[2]);

		          
		          MenuEnum[]f = {numcopiesadd};
		          String[] result2 = c.get_ui().processForm(new UIMenu("",f));
		                                             
		          UndoableCommand co = Data.newAddCmd(c.get_inventory(), vm, Integer.parseInt(result2[0]));
		          if (! co.run()) {
		            c.get_ui().displayError("Command failed");
		          }
				
				
				break;
			case printinve:
				c.get_ui().displayMessage(c.get_inventory().toString());
				break;
			case redo:
				
				if (!Data.newRedoCmd(c.get_inventory()).run()) {
		            c.get_ui().displayError("Command failed");
		          }
				break;
			
			
			case undo:
				if (!Data.newUndoCmd(c.get_inventory()).run()) {
		            c.get_ui().displayError("Command failed");
		          }
				break;
				
			case printallinorder:
				
				Iterator<Record> it = c.get_inventory().iterator((rp, r2) -> r2.numRentals() - rp.numRentals());
				int count = 10;
				StringBuilder s = new StringBuilder();
				while (it.hasNext() && count > 0) {
					Record r = it.next();
					s.append(r.toString());
					s.append("\n");
					count--;
				}
				c.get_ui().displayMessage(s.toString());
			 
			 break;
			case yes:
				c.set_state(StateEnum.EXITED);
				break;
			default:
				break;
			 
			 
			 }
			 
		 }
		
		
	}
	
	


