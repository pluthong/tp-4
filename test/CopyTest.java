import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CopyTest {

	private Copy copy;

	@Before
	public void create_sample_copy() {
		// create new copy c1
		copy = new Copy("C1", "Fun with Objects");
		System.out.println("copy created");
	}
	
	@Test
	public void test_get_copyID() {
	
		String copyID = copy.getCopyID(); 
		assertEquals("CopyID is as initialized", "C1", copyID);
	}
	
	@Test
	public void test_get_copyTitle() {
	
		String title = copy.getTitle(); 
		assertEquals("title is as initialized", "Fun with Objects", title);
	}


}
