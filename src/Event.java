
public class Event {
	private String eventID;
	private String entity;
	private String descr;

	// get eventID
	public String getEventID() {
		return eventID;
	}

	// set eventID
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	// get entity
	public String getEntity() {
		return this.entity;
	}

	// set entity
	public void setEntity(String entity) {
		this.entity = entity;
	}

	// get descr
	public String getDescr() {
		return this.descr;
	}

	// set descr
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	// constructor initializes event given eventID, entity, and description
	public Event(String eventID, String entity, String descr) {
		this.eventID = eventID;
		this.entity = entity;
		this.descr = descr;
	}
}
