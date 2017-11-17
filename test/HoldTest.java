import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class HoldTest {

	private Hold hold;

	@Before
	public void create_sample_hold() {
		// create new hold 
		hold = new Hold("H1","P1", "you have tuition fee to pay.", DateUtil.convertStringToDate("11/09/2017", "MM/dd/yyyy"));
	}
	
	// test method getHoldID
	@Test
	public void test_get_holdID() {
		String ID = hold.getHoldID(); 
		assertEquals("hold has ID H1", "H1", ID);
		System.out.println("test passed");
	}
	
	// test method placedOn
	@Test
	public void test_get_placedOn() {
	
		Date date = hold.getPlacedOn(); 
		String date_str = DateUtil.convertDateToString(date, "MM/dd/yyyy");
		assertEquals("hold date on 11/09/2017", "11/09/2017", date_str);
		System.out.println("test passed");
	}

}
