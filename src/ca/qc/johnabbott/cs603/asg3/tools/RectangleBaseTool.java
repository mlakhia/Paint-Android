package ca.qc.johnabbott.cs603.asg3.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.MotionEvent;
import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Rectangle;

public abstract class RectangleBaseTool extends Tool {

	protected float x1, y1, x2, y2;

	public RectangleBaseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		x1 = 0;
		x2 = 0;
		y1 = 0;
		y2 = 0;
	}

	@Override
	public void touchStart(MotionEvent event) {
		event.getAction();
		x1 = event.getX();
		y1 = event.getY();
		
		hasPreview = true;
	}

	@Override
	public void touchEnd(MotionEvent event) {
		event.getAction();
		x2 = event.getX();
		y2 = event.getY();

		hasPreview = false;
		addToDrawing();
	}

	@Override
	public void touchMove(MotionEvent event) {
		event.getAction();
		x2 = event.getX();
		y2 = event.getY();
		
		toolbox.getDrawingView().erase();
	}
	
	/*
	 * Log.d("x1",""+x1); Log.d("y1",""+y1); Log.d("x2",""+x2);
	 * Log.d("y2",""+y2); Log.d("color",""+toolbox.getStrokeColor());
	 * Log.d("width",""+toolbox.getStrokeWidth());
	 * Log.d("fill",""+toolbox.getFillColor());
	 */
}
