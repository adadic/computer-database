package mapper;

import org.json.JSONObject;

public class ArrayMapper {
	
	public static String[] toArray(String str){
		
		try{
			JSONObject obj = new JSONObject(str);
			
			return new String[] {String.valueOf(obj.getInt("id")), obj.getString("name")};
		}
		catch(NullPointerException npe) {
			
			return new String[] {"0", null};
		}
	}
}
