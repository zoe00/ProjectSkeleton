package project.skeleton.globals;

import android.content.Context;
import android.content.SharedPreferences;

public class Globals {
	
	private static SharedPreferences app_data;
	private static String DEFAULT_KEY1="";

	public static void setSharedPreference(Context c, String value){	
		app_data = c.getSharedPreferences("app_data", 0);		
		SharedPreferences.Editor prefs_editor = app_data.edit();
		prefs_editor.putString("key1", value);			
		prefs_editor.commit();		
	}

	public static String getSharedPreference(Context c){
		app_data = c.getSharedPreferences("app_data", 0);
		return app_data.getString("match_detail", DEFAULT_KEY1);		
	}

}
