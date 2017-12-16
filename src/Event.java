
public class Event {
	private String eventID;
	private String entity;
	private String descr;

	// get eventID
	public String getEventID() {
		return eventID;
	}

	// get entity
	public String getEntity() {
		return this.entity;
	}

	// get descr
	public String getDescr() {
		return this.descr;
	}
	
	// constructor initializes event given eventID, entity, and description
	public Event(String eventID, String entity, String descr) {
		this.eventID = eventID;
		this.entity = entity;
		this.descr = descr;
	}
}
