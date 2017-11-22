import java.util.Map;

public class Controller {

	private Patron p = null;
	private Copy c = null;
	private int idxEventDB = 0;
	private String entityPatron = "patron";
	private String entityCopy = "copy";
	private String descr = "";
	Event event = null;
	
	public Controller() {}
	
	public String getPatron(String patronID) {
		
		// get patron from fake DB
		p = FakeDB.getPatron(patronID);
		// create event
		descr = "get patron " + patronID;
		event = AppUtil.createEvent(++idxEventDB, entityPatron, descr);
		// add event to fake DB
		FakeDB.registerNewEvent(event);
		return p.toString();
	}
	
	public String getCopy(String copyID) {
		
		// get copy from fake DB
		c = FakeDB.getCopy(copyID);
		// create event
		descr = "get copy " + copyID;
		event = AppUtil.createEvent(++idxEventDB, entityCopy, descr);
		// add event to fake DB
		FakeDB.registerNewEvent(event);
		return c.toString();
	}
	
	public String checkOutCopy(String copyID) {
		String message = "";
		// get copy from fake DB
		c = FakeDB.getCopy(copyID);
		// create event
		event = AppUtil.createEvent(++idxEventDB, entityCopy, "get copy " + copyID);
		// copy out to patron
		c.setOutTo(p);
		// create event
		descr = "set copy ID: " + copyID + " out to patron ID: " + p.getPatronID();
		event = AppUtil.createEvent(++idxEventDB, entityCopy, descr);
		
		boolean checkedOutCopy = p.checkCopyOut(c);
		
		if(checkedOutCopy) {
			// create event
			descr = "added copy ID: " + copyID + " to patron ID: " + p.getPatronID();
			event = AppUtil.createEvent(++idxEventDB, entityPatron, descr);
			message = p.toString();
		} else {
			message = "Insertion copy " + c.getCopyID() + " failed";
		}
		return message;
	}

	public String getAllEvent() {
		String message ="";
		Map<String, Event> map = FakeDB.getStoreEvent();
		 for (Map.Entry<String, Event> entry : map.entrySet()) {
		        String key = entry.getKey();
		        Event event = entry.getValue();
		        message += "Event ID :" + key + " - entity :" + event.getEntity() + " - descr : " + event.getDescr();
		        message += System.lineSeparator();
		    }
		 return message;
	}
}
