import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppUtil {
	
	// add days to Date
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    
    // convert specific format date to string
    public static String convertDateToString(Date date, String formatDate) {
    	return new SimpleDateFormat(formatDate).format(date.getTime());
    }
    
    // convert String to specific date format
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
    
    // Create event 
    public static Event createEvent(int index, String entity, String descr) {
    	String eventID = "E" + index;
    	Event event = new Event(eventID, entity, descr);
    	return event;
    }
    
	// Return the passed string without the removed last character
	public static String trimEndChar(String str) {
		return (str.length() == 0 || str == null ? "" : str.substring(0, str.length() - 1));
	}

}
