import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

	private static Patron p3,p2,p1,currentPatron = null;
	private static Copy currentCopy = null;
	private static int idxEventDB = 0;
	private static String entityPatron = "patron";
	private static String entityCopy = "copy";
	private static Event event = null;
	
	// get instance of Patron
	public static Patron getCurrentPatron() {
		return currentPatron;
	}

	// get instance of Copy
	public static Copy getCurrentCopy() {
		return currentCopy;
	}
	
	static {
		
		// insert holds...
		p3 = new Patron("P3", "YU");
		// init create hold for patron ID "P3"
		Hold hold = new Hold("H1",p3, "you have tuition fee to pay.", AppUtil.convertStringToDate("11/09/2017", "MM/dd/yyyy"));
		FakeDB.insertHold(hold);
		p3.addHold(hold);
		
		// init create patrons... [3 max]
		p1 = new Patron("P1", "ERIC");
		p2 = new Patron("P2", "PAUL");
		FakeDB.insertPatron(p1);
		FakeDB.insertPatron(p2);
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
		currentPatron = FakeDB.getPatron(patronID);
		String msg = "";
		if(currentPatron == null)
			msg = "> Patron ID [" + patronID + "] not found";
		else
			msg = currentPatron.toString();
		// create log event 
		logger(entityPatron,"get patron " + patronID);
		return msg;
	}
	
	public static String startCheckOut(String copyID) {
		// get copy from fake DB
		currentCopy = FakeDB.getCopy(copyID);
		String msg = "";
		if(currentCopy == null)
			msg = "> Copy ID [" + copyID + "] not found";
		else if(currentCopy.getOutTo() != null)
			msg = "> Failed copy ID [" + copyID + "] already checked out to patron [" + currentCopy.getOutTo().getPatronID() + "]";
		else
			msg = checkOutCopy(currentCopy);
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
		currentCopy = FakeDB.getCopy(copyID);
		if(currentCopy == null) {
			logger(entityCopy,"Copy ID [" + copyID + "] not found");
			return "> Copy ID [" + copyID + "] not found";
		}
	
		currentPatron = currentCopy.getOutTo();

		if(currentPatron == null) 
			return "> Check-in process not valid for Copy ID [" + copyID + "]";

		boolean checkedInCopy = currentPatron.checkCopyIn(currentCopy);
		String msg = "";

		currentCopy.setOutTo(null);
		FakeDB.updatePatron(currentPatron);
		FakeDB.updateCopy(currentCopy);
		msg = "> Checked Copy ID [" + copyID + "] in to patron ID [" + currentPatron.getPatronID() + "] ";

		return msg; 
	}

	public static String checkOutCopy(Copy currentCopy) {
		String msg = "";
		// current copy is already checked out
		logger(entityCopy,"get copy " + currentCopy.getCopyID());
		// copy out to patron
		currentCopy.setOutTo(currentPatron);
		logger(entityCopy,"set copy ID: " + currentCopy.getCopyID() + " out to patron ID: " + currentPatron.getPatronID());
		boolean checkedOutCopy = currentPatron.checkCopyOut(currentCopy);

		logger(entityPatron, "added copy ID: " + currentCopy.getCopyID() + " to patron ID: " + currentPatron.getPatronID());
		msg = "> Checked copy ID [" + currentCopy.getCopyID() + "] out to patron [" + currentCopy.getOutTo().getPatronID() + "]";
		FakeDB.updatePatron(currentPatron);
		FakeDB.updateCopy(currentCopy);

		return msg;
	}

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
