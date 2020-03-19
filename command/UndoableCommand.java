package shop.command;

public interface UndoableCommand{
	
	public boolean run();
	public void redo();
	public void undo();

}
