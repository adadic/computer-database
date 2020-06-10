package mapper;

import java.sql.Timestamp;


public class DateMapper {
	
	public long getDate(String givenDate){
		
		 try{
			 Timestamp ts = Timestamp.valueOf(givenDate + " 00:00:00");
			 
			 return ts.getTime();
		}
		catch(IllegalArgumentException e) {
			
			return 0;
		}
	}
}
