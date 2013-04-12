package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;
import android.view.MotionEvent;

public abstract class RectangleBaseTool extends Tool {

	public RectangleBaseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}

	@Override
	public void touchStart(MotionEvent event) {
		x1 = event.getX();
		y1 = event.getY();
		
		hasPreview = true;
	}

	@Override
	public void touchEnd(MotionEvent event) {
		x2 = event.getX();
		y2 = event.getY();

		hasPreview = false;
		addToDrawing();

		toolbox.getDrawingView().invalidate();
	}

	@Override
	public void touchMove(MotionEvent event) {
		x2 = event.getX();
		y2 = event.getY();
		
		toolbox.getDrawingView().invalidate();
	}
	
	/*
	 * Log.d("x1",""+x1); Log.d("y1",""+y1); Log.d("x2",""+x2);
	 * Log.d("y2",""+y2); Log.d("color",""+toolbox.getStrokeColor());
	 * Log.d("width",""+toolbox.getStrokeWidth());
	 * Log.d("fill",""+toolbox.getFillColor());
	 */
}
