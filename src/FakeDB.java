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

	public static void insertEvent(Event event) 
	{
		eventStore.put(event.getEventID(), event);
	}
	
	public static void insertPatron(Patron patron) 
	{
		patronStore.put(patron.getPatronID(), patron);
	}
	
	public static void insertCopy(Copy copy) 
	{
		copyStore.put(copy.getCopyID(), copy);
	}
	
	public static void insertHold(Hold hold) 
	{
		holdStore.put(hold.getHoldID(), hold);
	}

	public static Map<String, Event> getStoreEvent() 
	{
		return eventStore;
	}

}
