import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class PatronTest {

	private Patron patron;
	private Copy copy1;
	private Copy copy2;
	private ArrayList<Copy> copiesOut;
	private Hold hold;

	@Before
	public void init() {
		// create new patron 
		patron = new Patron("P1", "Paul");
	    copy1 = new Copy("C1", "Applying UML and patterns");
	    copy2 = new Copy("C2", "The Mythical MAN-MONTH");
		copiesOut = new ArrayList<>();
		hold = new Hold("H1",patron, "you have tuition fee to pay.", AppUtil.convertStringToDate("11/09/2017", "MM/dd/yyyy"));
	}
	
	@Test
	public void test_get_patronName() {
		String name = patron.getName(); 
		assertEquals("Paul", name);
	}
	
	@Test
	public void test_get_patronID() {
		String patronID = patron.getPatronID(); 
		assertEquals("P1", patronID);
	}
	
	@Test
	public void test_checkCopyOut_ok() {
		boolean result = patron.checkCopyOut(copy1);
		assertTrue("copy is checked out", result);
	}
	
	@Test
	public void test_checkCopyOut_ko() {
		patron.checkCopyOut(copy1);
		boolean result = patron.checkCopyOut(copy1);
		assertFalse("copy is already checked out", result);
	}
	
	@Test
	public void test_checkCopyIn_ok() {
		patron.checkCopyOut(copy1);
		boolean result = patron.checkCopyIn(copy1);
		assertTrue("copy is checked in", result);
	}
	
	@Test
	public void test_checkCopyIn_ko() {
		boolean result = patron.checkCopyIn(copy2);
		assertFalse("copy is not checked in", result);
	}
	
	@Test
	public void test_patronEquality() {
		boolean result = patron.equals(new Patron("P2", "Rob"));
		assertFalse("patron Paul is different to patron Rob", result);
	}
	
	@Test
	public void test_wrongInstance() {
		boolean result = patron.equals(copy1);
		assertFalse("instance copy is not patron", result);
	}
	
	@Test
	public void test_addHold() {
		boolean result = patron.addHold(hold);
		assertTrue("added hold to patron", result);
	}
	
	@Test
	public void test_displayPatron_withoutCopiesOut() {
		assertThat(patron.toString(),CoreMatchers.containsString("Name -> " + patron.getName()));
	}
	
	@Test
	public void test_displayPatron_withCopiesOut() {
		patron.checkCopyOut(copy1);
		assertThat(patron.toString(),CoreMatchers.containsString("#copy -> 1, Current Checkouts -> [C1]"));
	}
	
	@Test
	public void test_displayPatron_withHold() {
		patron.addHold(hold);
		assertThat(patron.toString(),CoreMatchers.containsString("#hold -> " + patron.getHolds().size()));
	}
	
}
