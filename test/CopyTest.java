import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class CopyTest {

	private Copy copy;
	private Patron patron;

	@Before
	public void init() {
		// create new copy c1
		copy = new Copy("C1", "Fun with Objects");	
		patron = new Patron("P1", "Paul");
	}
	
	@Test
	public void test_get_copyID() {
	
		String copyID = copy.getCopyID(); 
		assertEquals("C1", copyID);
	}
	
	@Test
	public void test_get_copyTitle() {
		String title = copy.getTitle(); 
		assertEquals("Fun with Objects", title);
	}
	
	@Test
	public void test_displayCopy_withoutOutToPatron() {
		assertThat(copy.toString(),CoreMatchers.containsString("Copy title : " + copy.getTitle()));
	}
	
	@Test
	public void test_displayCopy_withOutToPatron() {
		Patron patron = new Patron("P1", "Paul");
		copy.setOutTo(patron);
		assertThat(copy.toString(),CoreMatchers.containsString("Copy Patron ID/Name : " + copy.getOutTo().getPatronID()));
	}
	
	@Test
	public void test_setOutTo() {
		copy.setOutTo(patron);
		assertEquals("P1", copy.getOutTo().getPatronID());
	}
	
	@Test
	public void test_copyEquality() {
		boolean result = copy.equals(new Copy("C5", "The Cabin"));
		assertFalse("copy C1 is different to copy C5", result);
	}
	
	@Test
	public void test_wrongInstance() {
		boolean result = copy.equals(patron);
		assertFalse("instance copy is not patron", result);
	}

}
