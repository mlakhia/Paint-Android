package ca.qc.johnabbott.cs603.asg3;

import ca.qc.johnabbott.cs603.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ColorChooserDialog extends Dialog {

	private SeekBar alphaSeekBar, redSeekBar, greenSeekBar, blueSeekBar;
	private int color;
	private View.OnClickListener clickListener;

	
	public ColorChooserDialog(Context context, int initialColour) {
		super(context);
		this.setContentView(R.layout.dialog_color_chooser);
		this.setTitle(R.string.colour_chooser_dialog_title);
		this.setCanceledOnTouchOutside(true);
		
		alphaSeekBar = (SeekBar) findViewById(R.id.alphaSeekBar);
		redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
		greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
		blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);		
		final ImageView colourView = (ImageView) findViewById(R.id.colourImageView);
		Button button = (Button) findViewById(R.id.colourDone);
		
		color = initialColour;
		alphaSeekBar.setProgress(Color.alpha(color));
		redSeekBar.setProgress(Color.red(color));
		greenSeekBar.setProgress(Color.green(color));
		blueSeekBar.setProgress(Color.blue(color));
		colourView.setBackgroundColor(color);
		
		OnSeekBarChangeListener seekChangeListener = new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
				color = Color.argb(alphaSeekBar.getProgress(), redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
				colourView.setBackgroundColor(color);
			}

			@Override
			public void onStartTrackingTouch(SeekBar sb) {}

			@Override
			public void onStopTrackingTouch(SeekBar sb) {}
		};
		
		alphaSeekBar.setOnSeekBarChangeListener(seekChangeListener);
		redSeekBar.setOnSeekBarChangeListener(seekChangeListener);
		greenSeekBar.setOnSeekBarChangeListener(seekChangeListener);
		blueSeekBar.setOnSeekBarChangeListener(seekChangeListener);

		colourView.setBackgroundColor(Color.argb(alphaSeekBar.getProgress(), redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(clickListener != null)
					clickListener.onClick(view);
				dismiss();
			}
		});
		
	}

	public void setOnClickListener(View.OnClickListener listener) {
		this.clickListener = listener;
	}
	
	public int getColor() {
		return color;
	}
	
	

}
