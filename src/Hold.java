import java.util.Date;

public class Hold {
	private String holdID;
	private Patron patron ;
	private String descr;
	private Date placedOn;
	
	// get hold id
	public String getHoldID() {
		return holdID;
	}
	
	// get description
	public String getDescr() {
		return this.descr;
	}
	
	// get date
	public Date getPlacedOn() {
		return this.placedOn;
	}
	
	// constructor initializes hold given holdID, patronID, and description
	public Hold(String holdID, Patron patron, String descr, Date placedOn)
	{
		this.holdID = holdID;
		this.patron = patron;
		this.descr = descr;
		this.placedOn = placedOn;
	}
   
}
