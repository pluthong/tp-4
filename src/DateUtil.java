import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    
    public static String convertDateToString(Date date, String formatDate) {
    	return new SimpleDateFormat(formatDate).format(date.getTime());
    }
    
    public static Date convertStringToDate(String strDate, String formatDate) {

    	Date parsedDate = null;
		try {
			 parsedDate=new SimpleDateFormat(formatDate).parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return parsedDate;
    }
    
}
