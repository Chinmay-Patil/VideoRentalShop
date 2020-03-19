package shop.data;



public interface Video  {
	
	
	public String director();
	public int year();
	public String title();
	 public boolean equals(Object thatObject);
	 public int hashCode();
	 public int compareTo(VideoObj b);
	 public String toString();

}
