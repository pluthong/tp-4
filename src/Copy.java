
public class Copy
{
	private String copyID; // Identification of copy
	private String title; // copy title
	private Patron outTo; // Copy out to Patron
	private String dueDate;

	// get OutTo
	public Patron getOutTo() {
		return outTo;
	}
	
	// set OutTo
	public void setOutTo(Patron outTo) {
		this.outTo = outTo;
	}
	
	// get due date
	public String getDueDate() {
		return dueDate;
	}
	
	// set due date
	public void setDueDate(String date) {
		this.dueDate = date;
	}
	
	// get title
	public String getTitle() {
		return title;
	}

	// set Title
	public void setTitle(String title) {
		this.title = title;
	}

	// get CopyID
	public String getCopyID() {
		return copyID;
	}
	
	// set CopyID
	public void setCopyID(String copyID) {
		this.copyID = copyID;
	}
	
	// constructor initializes Copy given copyID and title
	public Copy(String copyID, String title)
	{
		this.copyID = copyID;
		this.title = title;
	}

	// generate getters and setters using Eclipse Source menu
	// returns a string representation of the object Copy
	public String toString()
	{
		String info = "";
		//info += System.lineSeparator(); // new line
		info += "Copy ID : " + this.copyID;
		info += System.lineSeparator(); 
		info += "Copy title : " + this.title;
		if(isCopyOutToPatron()) {
			info += System.lineSeparator();
			info += "Copy Patron ID/Name : " + outTo.getPatronID() + "/" + outTo.getName();
			//info += outTo.toString();
		}
			
		// correctly implement this
		return info;
	}
	
	// this method verify if the copy is out to patron
	private boolean isCopyOutToPatron() {
		return (outTo == null) ? false : true;
	}

	// this method compares if the given Object is equal to Copy
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Copy)) 
			return false;
		Copy c = (Copy)o;
		// compare c.copyID with this.copyID
		return c.copyID.equals(this.copyID);
	}

	// put test code in your main(), but realize it's not unit testing!
	public static void main(String[] args)
	{
		// create new copy c1
		Copy c1 = new Copy("C1", "Fun with Objects");
		// info c1
		System.out.println(c1.toString());
		// assertion
		//System.out.println("Is C1 in fake db ? " + c1.equals(c2) + ", expected true");
	}
}
