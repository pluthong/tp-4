import static org.junit.Assert.*;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class AppUtilTest {
	
    String strDate;
    
	@Before
	public void create_sample_date() {
		// create new copy c1
		strDate = "11/11/2017";
		System.out.println("string date initialized");
	}
	
	
	@Test
	public void test_convert_to_date() {
		Date date = AppUtil.convertStringToDate(strDate, "MM/dd/yyyy");
		assertNotNull("date is converted",date);
		//assertNotNull("date is null",date);
	}
	
	@Test
	public void test_add_days() {
		Date date = AppUtil.convertStringToDate(strDate, "MM/dd/yyyy");
		Date newDate = AppUtil.addDays(date, 5);
		String newStrDate = AppUtil.convertDateToString(newDate, "MM/dd/yyyy");
		assertEquals("expected date to be 11/16/2017", "11/16/2017", newStrDate);
	}
	
}
