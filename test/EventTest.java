import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EventTest {

	private Event event;
	
	@Before
	public void init() {
		// create new event 
		event = new Event("E1","Patron","patron event_p1");
		System.out.println("event created");
	}
	
	// test method getEventID
	@Test
	public void test_get_EventID() {
		String ID = event.getEventID(); 
		assertEquals("E1", ID);
	}
	
	// test method getEntity
	@Test
	public void test_get_Entity() {
		String entity = event.getEntity(); 
		assertEquals("Patron", entity);
	}
	
	// test method getDescr
	@Test
	public void test_getDescr() {
		String descr = event.getDescr(); 
		assertEquals("patron event_p1", descr);
	}

}
