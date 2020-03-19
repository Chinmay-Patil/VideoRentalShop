package shop.main;

public enum StateEnum {
	
	EXITED(0),
	EXIT(1),
	START(2),
	NUMSTATES(10);

	private int number;
	
	StateEnum(int i){
		
		this.number =i;
	}
	
	public int getnumber() {
		
		return number;
	}
}
