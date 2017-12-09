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
	
	// set hold id
	public void setHoldID(String id) {
		this.holdID = id;
	}
	
	// get patron
	public Patron getPatron() {
		return this.patron;
	}
	
	// set patron
	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	
	// get description
	public String getDescr() {
		return this.descr;
	}
	
	// set description
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	// get date
	public Date getPlacedOn() {
		return this.placedOn;
	}
	
	// set date
	public void setPlacedOn(Date placedOn) {
		this.placedOn = placedOn;
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
