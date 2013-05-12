package com.mlakhia.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mlakhia.draw.PaintActivity;
import com.mlakhia.draw.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.widget.Toast;

public class ListPicturesAsyncTask extends AsyncTask<String, String, String> {

	private static final String SERVER_IP = "192.168.1.116";
	//private static final String SERVER_IP = "10.0.2.2";
	private static final String SERVER_PORT = "9999";
	
	private final Context context;
	
	private ArrayList<String> pictureList;
	
	public ListPicturesAsyncTask(Context context) {
		this.context = context;
		this.pictureList = new ArrayList<String>();
	}

	@Override
	protected String doInBackground(String... params) {
		// username is in params[0]
		try {
			URL baseURL = new URL("http://"+SERVER_IP+":"+SERVER_PORT+"/");
			URL userURL = new URL(baseURL, params[0]+"/list");
			
			URLConnection conn = userURL.openConnection();
			conn.setDoInput(true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				String pictureName = (String) inputLine;
				pictureList.add(pictureName);
			}
			in.close();
			
		} catch (MalformedURLException e) {
			// new URL() failed
			e.printStackTrace();
		} catch (IOException e) {
			// openConnection() failed
			e.printStackTrace();
		}
		
		return params[0];
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		List<String> listItems = pictureList;
		final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);
	    listItems.toArray(charSequenceItems);
	    
	    AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.list_title)
               .setPositiveButton(R.string.list_new, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   // start paint activity
						Intent intent = new Intent(context, PaintActivity.class);
						context.startActivity(intent);
                   }
               })
               
			.setItems(charSequenceItems, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "Loading Picture: "+pictureList.get(which), Toast.LENGTH_SHORT).show();
				
				// start paint activity
				Intent intent = new Intent(context, PaintActivity.class);
				intent.putExtra("pictureToLoad", pictureList.get(which));
				context.startActivity(intent);
			}
			
		});
		builder.create();
		builder.show();
		
		Toast.makeText(context, "List Loaded!", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCancelled() {
		Toast.makeText(context, "Server Load Cancelled!", Toast.LENGTH_SHORT).show();
	}
}
