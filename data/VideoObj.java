package shop.data;

public class VideoObj implements Video  {

	
	private final String _title;
	  /** @invariant greater than 1800, less than 5000 */
	  private final int    _year;
	  /** @invariant non-null, no leading or final spaces, not empty string */
	  private final String _director;

	
	  VideoObj(String title, int year, String director) {
			if((title == null)||(year <= 1800)||(year >= 5000)||(director == null)||(director==" ")||(director=="  ")||(title=="")||(title==" "))
		{
				
				throw new IllegalArgumentException();
			}
			this._title = title.trim();
			this._director = director.trim();
			this._year = year;     
		  }

	  public String director() {
 	  	  
		  // TODO    
	    return _director;
	  }

	  /**
	   * Return the value of the attribute.
	   */
	  public String title() {
	    // TODO  
	    return _title;
	  }

	  /**
	   * Return the value of the attribute.
	   */
	  public int year() {
	    // TODO  
	    return _year;
	  }
	  
	  public boolean equals(Object thatObject)
	  {
	    
		  if(!(thatObject instanceof VideoObj))
		  {
			  return false;
		  } 
		VideoObj that = (VideoObj) thatObject;
		if(!title().equals(that.title())) return false;
		if(!director().equals(that.director())) return false;
		if(year() !=(that.year())) return false;

		return true;
		  // TODO  
	  }

	  public int hashCode() {
		    // TODO  - done
			  
			  int result = 17;
			  result = 37*result + _title.hashCode();
			  result = 37*result + _year;
			  result = 37*result + _director.hashCode();
			  
		    return result;
		  }
	
	
	
	
	
	public int compareTo(VideoObj b) {
		// TODO  
		 
		  int titlediff = _title.compareTo(b.title());
		  if(titlediff != 0) {
			  return titlediff;
		  }
		  int yeardiff = Integer.compare(_year,b.year());
		  if(yeardiff != 0) {
			  return yeardiff;
		  }
		  int Directordiff = _director.compareTo(b.director());
		  if(Directordiff != 0) {
			  return Directordiff;
		  }
		  
		  return 0;
	}
	public String toString() {
	    // TODO  
		 StringBuilder buffer = new StringBuilder();
		 buffer.append(_title);
		 buffer.append(" (");
		 buffer.append(_year);
		 buffer.append(") : ");
		 buffer.append(_director);
		 return buffer.toString();
		 
		  
	    //return "El Mariachi (1996) : Rodriguez";
	  }

}
