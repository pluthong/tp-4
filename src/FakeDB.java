import java.util.HashMap;
import java.util.Map;

public class FakeDB
{
	private static Map<String, Patron> patronStore; // List of patron inside Library
	private static Map<String, Copy> copyStore; // List of copy inside Library
	private static Map<String, Hold> holdStore; // List of hold inside Library
	private static Map<String, Event> eventStore; // List of event inside Library

	static // the following runs once when class is loaded: "static initializer"
	{
		patronStore = new HashMap<String, Patron>();
		copyStore = new HashMap<String, Copy>();
		holdStore = new HashMap<String, Hold>();
		eventStore = new HashMap<String, Event>();
		
		// insert holds...
		holdStore.put("H1", new Hold("H1","P1", "you have tuition fee to pay.", DateUtil.convertStringToDate("11/09/2017", "MM/dd/yyyy")));
		
		// create patrons...
		Patron p1 = new Patron("P1", "Eric");
		p1.addHold(getHold("H1"));
		patronStore.put("P1", p1);
		patronStore.put("P2",new Patron("P2", "Paul") );
		
		// insert copies...
		copyStore.put("C1", new Copy("C1", "Applying UML and patterns"));
		copyStore.put("C2", new Copy("C2", "The Mythical MAN-MONTH"));
		
		
	}

	
	public static Patron getPatron(String patronID) // return Patron by passing patronID
	{
		return patronStore.get(patronID);
	}

	public static Copy getCopy(String copyID) // return Copy by passing copyID
	{
		return copyStore.get(copyID);
	}
	
	public static Hold getHold(String holdID) // return Hold by passing holdID
	{
		return holdStore.get(holdID);
	}
	
	public static void registerEvent(String eventID, String entity, String descr) // set 
	{
		eventStore.put(eventID, new Event(eventID, entity,descr));
	}
	
	public static Map<String, Event> getStoreEvent() 
	{
		return eventStore;
	}

}
