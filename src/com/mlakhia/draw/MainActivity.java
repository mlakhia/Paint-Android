package com.mlakhia.draw;

import java.util.ArrayList;

import com.mlakhia.api.GetPictureAsyncTask;
import com.mlakhia.api.PostPictureAsyncTask;
import com.mlakhia.draw.R;
import com.mlakhia.draw.shapes.Shape;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
		
		/*
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
        */
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

		Button buttonLocalRestore = (Button) current.findViewById(R.id.buttonLocalRestore);
		Button buttonLocalSave = (Button) current.findViewById(R.id.buttonLocalSave);
		
		Button buttonServerRestore = (Button) current.findViewById(R.id.buttonServerRestore);
		Button buttonServerSave = (Button) current.findViewById(R.id.buttonServerSave);

		buttonErase.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				drawing.erase();
				drawing.invalidate();
				current.dismiss();
			}
		});

		buttonLocalSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// serialize and save shapes
				saveLocalShapes(drawing.getToolBox().getPicture().getShapes());
				current.dismiss();
			}
		});

		buttonLocalRestore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				drawing.erase();

				// deserialize and load shapes
				ArrayList<Shape> shapes = loadLocalSerializedShapes();
				drawing.getToolBox().getPicture().erase();
				drawing.getToolBox().getPicture().getShapes().addAll(shapes);
				
				drawing.invalidate();
				current.dismiss();
			}
		});
		
		buttonServerSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				AsyncTask<String, String, String> saveTask = 
						new PostPictureAsyncTask(getApplicationContext(), drawing.getToolBox(), "mike", "picture").execute("");
				
				current.dismiss();
			}
		});

		buttonServerRestore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				drawing.erase();

				AsyncTask<String, String, String> restoreTask = 
						new GetPictureAsyncTask(getApplicationContext(), drawing.getToolBox(), "mike", "picture").execute("");
				
				drawing.invalidate();
				current.dismiss();
			}
		});

		current.show();
	}
	
	public void saveLocalShapes(ArrayList<Shape> shapes){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(filename, Context.MODE_PRIVATE));
			oos.writeObject(shapes); // write the class as an 'object'
			oos.flush(); // flush the stream to insure all of the information was written to 'drawandroid_save.bin'
			oos.close();// close the stream
		} catch (Exception e) {
			Log.e("Serialization Write Error : ", e.getMessage());
			e.printStackTrace();
		}
   }
   
   @SuppressWarnings("unchecked")
   public ArrayList<Shape> loadLocalSerializedShapes() {
       try {
           ObjectInputStream ois = new ObjectInputStream(openFileInput(filename));
           Object o = ois.readObject();
           return (o != null) ? (ArrayList<Shape>) o : new ArrayList<Shape>();
       } catch(FileNotFoundException e){
    	   Toast.makeText(context, "Nothing to Restore! FileNotFound", Toast.LENGTH_LONG).show();
       } catch(Exception e) {
    	   Log.e("Serialization Read Error : ", e.getMessage());
           e.printStackTrace();
       }
       return new ArrayList<Shape>();
   }

}
