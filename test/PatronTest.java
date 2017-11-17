import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PatronTest {

	private Patron patron;

	@Before
	public void create_sample_patron() {
		// create new patron 
		patron = new Patron("P1", "Paul");
		System.out.println("patron created");
	}
	
	@Test
	public void test_get_patronName() {
	
		String name = patron.getName(); 
		assertEquals("patron has name Paul", "Paul", name);
	}
	
	@Test
	public void test_get_patronID() {
	
		String patronID = patron.getPatronID(); 
		assertEquals("patronID is P1", "P1", patronID);
	}

}
