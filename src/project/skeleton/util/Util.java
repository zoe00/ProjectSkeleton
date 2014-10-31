package project.skeleton.util;

import java.lang.reflect.Method;

import project.skeleton.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Util {
	
	public static final void setAppFont(ViewGroup mContainer, Typeface mFont, boolean reflect)
	{
		if (mContainer == null || mFont == null) return;

		final int mCount = mContainer.getChildCount();

		// Loop through all of the children.
		for (int i = 0; i < mCount; ++i)
		{
			final View mChild = mContainer.getChildAt(i);
			if (mChild instanceof TextView)
			{
				// Set the font if it is a TextView.
				((TextView) mChild).setTypeface(mFont);
			}
			else if (mChild instanceof ViewGroup)
			{
				// Recursively attempt another ViewGroup.
				setAppFont((ViewGroup) mChild, mFont, reflect);
			}
			else if (reflect)
			{
				try {
					Method mSetTypeface = mChild.getClass().getMethod("setTypeface", Typeface.class);
					mSetTypeface.invoke(mChild, mFont); 
				} catch (Exception e) { /* Do something... */ }
			}
		}
	}
	
	public static boolean isNetworkAvailable(Context c) {
		try{
			ConnectivityManager connectivityManager= (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
			return activeNetworkInfo != null;			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}	

	public static void showInfoDialog(final Context context, String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(context.getResources().getString(R.string.app_name));
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		builder.create();
		builder.show();
	}

	public static void showWifiDialog(final Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Oops");
		builder.setMessage("Internet not available.");
		builder.setCancelable(true);
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("Turn Wifi ON", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
				((Activity) context).startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
			}
		});
		builder.create();
		builder.show();
	}
	
	public static float convertPixelsToDp(float px, Context context){
	    return px * context.getResources().getDisplayMetrics().density;
	}
}
