import java.util.Date;

public class Copy
{
	private String copyID; // Identification of copy
	private String title; // copy title
	private Patron outTo; // Copy out to Patron
	private Date dueDate;

	// get OutTo
	public Patron getOutTo() {
		return outTo;
	}
	
	// get dueDate
	public Date getDueDate() {
		return dueDate;
	}

	// set dueDate
	public void setDueDate(Date date) {
		this.dueDate = date;
	}

	// set OutTo
	public void setOutTo(Patron outTo) {
		this.outTo = outTo;
	}
	
	// get title
	public String getTitle() {
		return title;
	}

	// get CopyID
	public String getCopyID() {
		return copyID;
	}
	
	// constructor initializes Copy given copyID and title
	public Copy(String copyID, String title)
	{
		this.copyID = copyID;
		this.title = title;
		this.outTo = null;
		this.dueDate= null;
	}

	// generate getters and setters using Eclipse Source menu
	// returns a string representation of the object Copy
	public String toString()
	{
		String info = "";
		info += "Copy ID -> " + this.copyID;
		info += System.lineSeparator(); 
		info += "Copy title -> " + this.title;
		if(isCopyOutToPatron()) {
			info += System.lineSeparator();
			info += "Copy Patron ID/Name -> " + outTo.getPatronID() + "/" + outTo.getName();
			if(dueDate != null) {
				info += ", due date -> " + AppUtil.printDate(dueDate);
			}
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

}
