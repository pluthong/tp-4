import java.util.Map;

public class Controller {

	private static Patron p = null;
	private static Copy c = null;
	private static int idxEventDB = 0;
	private static String entityPatron = "patron";
	private static String entityCopy = "copy";
	private static String descr = "";
	private static Event event = null;
	
	// get instance of Patron
	public static Patron getPatron() {
		return p;
	}
	
	// get instance of Copy
	public static Copy getCopy() {
		return c;
	}

	static {
		
		// insert holds...
		Patron p3 = new Patron("P3", "YU");
		// init create hold for patron ID "P3"
		Hold hold = new Hold("H1",p3, "you have tuition fee to pay.", AppUtil.convertStringToDate("11/09/2017", "MM/dd/yyyy"));
		FakeDB.insertHold(hold);
		p3.addHold(hold);
		
		// init create patrons... [3 max]
		FakeDB.insertPatron(new Patron("P1", "ERIC"));
		FakeDB.insertPatron(new Patron("P2", "PAUL"));
		FakeDB.insertPatron(p3);
	
		// init insert copies... [10 max]
		FakeDB.insertCopy(new Copy("C1", "Applying UML and patterns"));
		FakeDB.insertCopy(new Copy("C2", "The Mythical MAN-MONTH"));
		FakeDB.insertCopy(new Copy("C3", "We Were the Lucky Ones"));
		FakeDB.insertCopy(new Copy("C4", "Catching Fireflies"));
		FakeDB.insertCopy(new Copy("C5", "The Cabin"));
		FakeDB.insertCopy(new Copy("C6", "In the Arena"));
		FakeDB.insertCopy(new Copy("C7", "The Wonderful Things You Will Be"));
		FakeDB.insertCopy(new Copy("C8", "The Woman Who Thought Too Much"));
		FakeDB.insertCopy(new Copy("C8", "The Beginning of Everything"));
		FakeDB.insertCopy(new Copy("C10", "The Purple Balloon"));

	}

	public static String verifyPatron(String patronID) {	
		// get patron from fake DB
		p = FakeDB.getPatron(patronID);		
		String msg = p == null ? "> Patron ID [" + patronID + "] not found" : p.toString();
		// create log event 
		logger(entityPatron,"get patron " + patronID);
		return msg;
	}
	
	public static String startCheckOut(String copyID) {
		// get copy from fake DB
		c = FakeDB.getCopy(copyID);
		String msg = c == null ? "> Copy ID [" + copyID + "] not found" : checkOutCopy(copyID);
		// create log event 
		logger(entityCopy,"get copy " + copyID);
		return msg;
	}

	private static void logger(String entity, String descr) {
		event =  new Event( "E" + ++idxEventDB, entity, descr);
		FakeDB.insertEvent(event);
	}

	public static String startCheckIn(String copyID) {
		
		// get copy from fake DB
		c = FakeDB.getCopy(copyID);
		if(c == null) {
			logger(entityCopy,"Copy ID [" + copyID + "] not found");
			return "> Copy ID [" + copyID + "] not found";
		}

		if(p == null) 
			return "> Check-in process not valid for Copy ID [" + copyID + "]";
		boolean checkedInCopy = p.checkCopyIn(c);
		String msg = checkedInCopy ? "> Checked Copy ID [" + copyID + "]" : "> Check in Copy ID [" + copyID + "] failed"; 
		logger(entityCopy,msg);
		return msg + System.lineSeparator() + p.toString();
	}

	public static String checkOutCopy(String copyID) {
		String msg = "";
		// get copy from fake DB
		c = FakeDB.getCopy(copyID);
		logger(entityCopy,"get copy " + copyID);
		// copy out to patron
		c.setOutTo(p);
		logger(entityCopy,"set copy ID: " + copyID + " out to patron ID: " + p.getPatronID());
		boolean checkedOutCopy = p.checkCopyOut(c);

		if(checkedOutCopy) {
			logger(entityPatron, "added copy ID: " + copyID + " to patron ID: " + p.getPatronID());
			msg = p.toString();
		} else {
			msg = "Checked copy ID [" + copyID + "] out failed";
			logger(entityPatron,msg);
		}
		return msg;
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
