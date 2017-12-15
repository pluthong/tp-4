import static org.junit.Assert.*;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class AppUtilTest {
	
    String strDate;
    
	@Before
	public void init() {
		strDate = "11/11/2017";
	}
	
	@Test
	public void test_convert_to_date() {
		Date date = AppUtil.convertStringToDate(strDate, "MM/dd/yyyy");
		assertNotNull("date is converted",date);
	}
	
	@Test  
	public void test_throwsExceptionWhenDateWrong() {
		strDate = "11/11/2013";
		Date date = AppUtil.convertStringToDate(strDate, "MMd/yyyy");
		assertNull("date is null",date);
	}
	
	@Test
	public void test_add_days() {
		Date date = AppUtil.convertStringToDate(strDate, "MM/dd/yyyy");
		Date newDate = AppUtil.addDays(date, 5);
		String newStrDate = AppUtil.convertDateToString(newDate, "MM/dd/yyyy");
		assertEquals("11/16/2017", newStrDate);
	}
	
	@Test  
	public void test_trimEndChar() {
		String input = "trim the last character@";
		String result = AppUtil.trimEndChar(input);
		assertEquals("trim the last character", result);
	}
	
	@Test  
	public void test_trimEndCharWithEmptyString() {
		String result = AppUtil.trimEndChar("");
		assertEquals("", result);
	}
	
}
