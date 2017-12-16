import java.text.DateFormat;
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
    
    // This method adds minutes to Date 
    public static Date addMinutesToDate(int minutes, Date beforeTime){
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

        long curTimeInMs = beforeTime.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
        return afterAddingMins;
    }
    
    // This method adds minutes to Date 
    public static String printDate(Date date){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	return dateFormat.format(date);
    }


	// Return the passed string without the removed last character
	public static String trimEndChar(String str) {
		
		if(str.length() == 0)
			return "";
		else
			return str.substring(0, str.length() - 1);
	}

}
