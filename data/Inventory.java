package shop.data;

import java.util.Comparator;
import java.util.Iterator;

import shop.command.RerunnableCommand;

public interface Inventory {

	public Record get(Video v);

	 public  int size();

	 public Iterator<Record> iterator(Comparator<Record> comparator);
	 
	 public Iterator <Record> iterator();
	 
	 
	 public RerunnableCommand undo();
	 
	 public RerunnableCommand redo();
	 

}
