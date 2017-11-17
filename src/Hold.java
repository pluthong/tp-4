import java.util.Date;

public class Hold {
	private String holdID;
	private String patronID;
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
	public String getPatronID() {
		return this.patronID;
	}
	
	// set patron
	public void setPatron(String patronID) {
		this.patronID = patronID;
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
	public Hold(String holdID, String patronID, String descr, Date placedOn)
	{
		this.holdID = holdID;
		this.patronID = patronID;
		this.descr = descr;
		this.placedOn = placedOn;
	}
   
}
