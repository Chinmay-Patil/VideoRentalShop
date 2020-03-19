package shop.ui;
import shop.main.MenuEnum;

public interface UIFormTest {
  public static boolean run(String input, MenuEnum flag ) {
  
  switch (flag) {

case director: return _stringTest(input);
case numberTest: return _numberTest(input);
	
case numcopiesadd: return _numberTest(input);
	
case stringTest:return _stringTest(input);
	
case title:return _stringTest(input);
	
case year:return _yearTest(input);
	
case yeartest:return _yearTest(input);
	
default:
	break;
  
  
  }
  return false;
}

private static boolean _yearTest(String input) {
	// TODO Auto-generated method stub
	try {
		int i = Integer.parseInt(input);
		return i>1800 && i<5000;
		} 
	    catch(IllegalArgumentException e ) {
	return false;
	    }
}

private static boolean _numberTest(String input) {
	try {
	  Integer.parseInt(input);
		return true;
		} 
	    catch(IllegalArgumentException e ) {
	return false;
	    }
}

private static boolean _stringTest(String input) {
	// TODO Auto-generated method stub
	return !"".equals(input.trim());
}
}