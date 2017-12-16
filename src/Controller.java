import java.util.Map;

public class Controller {

	private static Patron currentPatron = null;
	private static Copy currentCopy = null;
	private static int idxEventDB = 0;
	private static String entityPatron = "patron";
	private static String entityCopy = "copy";
	private static Event event = null;
	
	// get instance of current Patron
	public static Patron getCurrentPatron() {
		return currentPatron;
	}

	// get instance of current Copy
	public static Copy getCurrentCopy() {
		return currentCopy;
	}
	
	// initialize data to use when the application is running
	static {
		// create patron ID "P3"
		Patron p3 = new Patron("P3", "YU");
		// create hold and add to patron ID "P3"
		Hold hold = new Hold("H1",p3, "you have tuition fee to pay.", AppUtil.convertStringToDate("11/09/2017", "MM/dd/yyyy"));
		// insert hold to fake database
		FakeDB.insertHold(hold);
		// patron assigned hold
		p3.addHold(hold);
		// insert 3 patrons to fake database 
		FakeDB.insertPatron(new Patron("P1", "ERIC"));
		FakeDB.insertPatron(new Patron("P2", "PAUL"));
		FakeDB.insertPatron(p3);
		// insert 10 copies to fake database 
		FakeDB.insertCopy(new Copy("C1", "Applying UML and patterns"));
		FakeDB.insertCopy(new Copy("C2", "The Mythical MAN-MONTH"));
		FakeDB.insertCopy(new Copy("C3", "We Were the Lucky Ones"));
		FakeDB.insertCopy(new Copy("C4", "Catching Fireflies"));
		FakeDB.insertCopy(new Copy("C5", "The Cabin"));
		FakeDB.insertCopy(new Copy("C6", "In the Arena"));
		FakeDB.insertCopy(new Copy("C7", "The Wonderful Things You Will Be"));
		FakeDB.insertCopy(new Copy("C8", "The Woman Who Thought Too Much"));
		FakeDB.insertCopy(new Copy("C9", "The Beginning of Everything"));
		FakeDB.insertCopy(new Copy("C10", "The Purple Balloon"));
	}
	
	// method returns a message whether or not the copy exists 
	public static String searchCopy(String copyID) {
		// get copy from fake DB
		currentCopy = FakeDB.getCopy(copyID);
		return (currentCopy == null) ? notFoundCopy(copyID) : foundCopy();
	}

	// method returns decorative message for copy found
	private static String foundCopy() {
		logger(entityCopy,"get copy " + currentCopy.getCopyID());
		return currentCopy.toString();
	}

	// method returns decorative message for copy not found
	private static String notFoundCopy(String copyID) {
		logger(entityCopy,"Copy ID [" + copyID + "] not found");
		return "> Copy ID [" + copyID + "] not found";
	}

	// method returns a message whether or not the patron exists 
	public static String searchPatron(String patronID) {
		// get patron from fake DB
		currentPatron = FakeDB.getPatron(patronID);
		return (currentPatron == null) ? notFoundPatron(patronID) : foundPatron();
	}

	// method returns decorative message for patron found
	private static String foundPatron() {
		logger(entityPatron,"get patron " + currentPatron.getPatronID());
		return currentPatron.toString();
	}

	// method returns decorative message for patron not found
	private static String notFoundPatron(String patronID) {
		logger(entityPatron,"Patron ID [" + patronID + "] not found");
		return "> Patron ID [" + patronID + "] not found";
	}
	
	// method returns decorative message for processing check-out
	public static String startCheckOut(String copyID) {
		// get copy from fake DB
		currentCopy = FakeDB.getCopy(copyID);
		String msg = "";
		if(currentCopy == null)
			msg = notFoundCopy(copyID);
		else if(currentCopy.getOutTo() != null)
			msg = "Failed, copy ID [" + copyID + "] already checked out to patron [" + currentCopy.getOutTo().getPatronID() + "]";
		else
			msg = checkOutCopy(currentCopy);
		// create log event 
		logger(entityCopy,"get copy " + copyID);
		return msg;
	}

	// insert log event in the fake database
	private static void logger(String entity, String descr) {
		event =  new Event( "E" + ++idxEventDB, entity, descr);
		FakeDB.insertEvent(event);
	}

	// method returns decorative message for processing check-in
	public static String startCheckIn(String copyID) {
		
		// get copy from fake DB -> duplication
		currentCopy = FakeDB.getCopy(copyID);
		
		if(currentCopy == null) 
			return notFoundCopy(copyID);
		
		currentPatron = currentCopy.getOutTo();

		if(currentPatron == null) 
			return "> Check-in process failed for Copy ID [" + copyID + "]";

		currentPatron.checkCopyIn(currentCopy);
		currentCopy.setOutTo(null);
		FakeDB.updatePatron(currentPatron);
		FakeDB.updateCopy(currentCopy);
		return checkedInCopyMessage(copyID);
	}
	
	// method returns decorative message when successful check-in
	private static String checkedInCopyMessage(String copyID) {
		String msg = "";
		msg = "Checked in copy ID -> " + copyID + ", patron ID -> " + currentPatron.getPatronID() + ", ";
		msg += "Current Checkouts -> ";
		
		if(!currentPatron.getCopiesOut().isEmpty()) {
			msg += "[";
			for (Copy copy : currentPatron.getCopiesOut()) 
				msg += copy.getCopyID() + ", ";
			msg = AppUtil.trimEndChar(msg.trim());
			msg += "]";
		} else {
			msg += "empty";
		}
		
		return msg;
	}
	
	// method returns decorative message for check-in
	public static String checkOutCopy(Copy currentCopy) {
		//String msg = "";
		// current copy is already checked out
		logger(entityCopy,"get copy " + currentCopy.getCopyID());
		// copy out to patron
		currentCopy.setOutTo(currentPatron);
		logger(entityCopy,"set copy ID: " + currentCopy.getCopyID() + " out to patron ID: " + currentPatron.getPatronID());
		currentPatron.checkCopyOut(currentCopy);

		logger(entityPatron, "added copy ID: " + currentCopy.getCopyID() + " to patron ID: " + currentPatron.getPatronID());
		//msg = "> Checked copy ID [" + currentCopy.getCopyID() + "] out to patron [" + currentCopy.getOutTo().getPatronID() + "]";
		FakeDB.updatePatron(currentPatron);
		FakeDB.updateCopy(currentCopy);

		return checkedOutCopyMessage(currentCopy.getCopyID());
	}
	
	// method returns decorative message when successful check-out
	private static String checkedOutCopyMessage(String copyID) {
		String msg = "";
		msg = "Checked copy ID -> " + copyID + ", patron ID -> " + currentPatron.getPatronID() + ", ";
		msg += "Current Checkout(s) -> ";
		msg += "[";
		for (Copy copy : currentPatron.getCopiesOut()) 
			msg += copy.getCopyID() + ", ";
		msg = AppUtil.trimEndChar(msg.trim());
		msg += "]";
		return msg;
	}

	// method returns message showing all the events in the fake database
	public static String getAllEvent() {
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
