package mapper;

import java.sql.Timestamp;
import java.util.Calendar;


public class DateMapper {
	
	public long getDate(String givenDate){
		 try{
			 Timestamp ts = Timestamp.valueOf(givenDate);
			/*String[] date = givenDate.substring(0, givenDate.indexOf(" ")).split("-");
			String[] hour = givenDate.substring(givenDate.indexOf(" ")+1).split(":");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, Integer.parseInt(date[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
			cal.set(Calendar.YEAR, Integer.parseInt(date[2]));
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(hour[1]));
			cal.set(Calendar.SECOND, 0);
			return cal.getTime().getTime();*/
			 return ts.getTime();
		}
		catch(IllegalArgumentException e) {
			return 0;
		}
		
	}

}
