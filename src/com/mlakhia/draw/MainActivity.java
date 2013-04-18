package com.mlakhia.draw;

import java.util.ArrayList;

import com.mlakhia.draw.R;
import com.mlakhia.draw.shapes.Shape;
import com.slidingmenu.lib.SlidingMenu;

import android.support.v4.app.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//import com.actionbarsherlock.app.ActionBar
//import com.actionbarsherlock.view.Menu
//import com.actionbarsherlock.view.MenuItem
//import com.actionbarsherlock.view.MenuInflater

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final String filename = "drawandroid_save.bin";
	private DrawingView drawing;
	private Dialog current;

	public static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawing = (DrawingView) this.findViewById(R.id.drawing_view);
		MainActivity.context = this;

		
        // configure the SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //menu.setMenu(R.layout.menu);
        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_tools:
			showToolsDialog();
			return true;
		case R.id.menu_menu:
			showMenuDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showToolsDialog() {
		new ToolSettingsDialog(this, drawing.getToolBox());
	}

	private void showMenuDialog() {
		current = new Dialog(this);
		current.setContentView(R.layout.dialog_menu);
		current.setTitle("Menu");
		current.setCanceledOnTouchOutside(true);

		Button buttonErase = (Button) current.findViewById(R.id.buttonErase);
		Button buttonRestore = (Button) current
				.findViewById(R.id.buttonRestore);
		Button buttonSave = (Button) current.findViewById(R.id.buttonSave);

		buttonErase.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				drawing.erase();
				drawing.invalidate();
				current.dismiss();
			}
		});

		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// serialize and save shapes
				saveShapes(drawing.getShapes());
				current.dismiss();
			}
		});

		buttonRestore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				drawing.erase();

				// deserialize and load shapes
				ArrayList<Shape> shapes = loadSerializedShapes();
				drawing.getShapes().addAll(shapes);
				
				drawing.invalidate();
				current.dismiss();
			}
		});
		
		// delete button
		// myContext.deleteFile(fileName);

		current.show();
		
//		// show LED notification
//		int LED_NOTIFICATION_ID = 12324234;
//		NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
//	    Notification notif = new Notification();
//	    notif.ledARGB = 0xFFff0000;
//	    notif.flags = Notification.FLAG_SHOW_LIGHTS;
//	    notif.ledOnMS = 100; 
//	    notif.ledOffMS = 100; 
//	    nm.notify(LED_NOTIFICATION_ID, notif);
	}
	
	public void saveShapes(ArrayList<Shape> shapes){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(filename, Context.MODE_PRIVATE));
			oos.writeObject(shapes); // write the class as an 'object'
			oos.flush(); // flush the stream to insure all of the information was written to 'drawandroid_save.bin'
			oos.close();// close the stream
		} catch (Exception e) {
			Log.v("Serialization Save Error : ", e.getMessage());
			e.printStackTrace();
		}
   }
    
   @SuppressWarnings("unchecked")
   public ArrayList<Shape> loadSerializedShapes() {
       try
       {
           ObjectInputStream ois = new ObjectInputStream(openFileInput(filename));
           Object o = ois.readObject();
           return (ArrayList<Shape>) o;
       }
       catch(Exception ex)
       {
    	   Log.v("Serialization Read Error : ",ex.getMessage());
           ex.printStackTrace();
       }
       return null;
   }

}
