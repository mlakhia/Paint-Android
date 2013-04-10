package ca.qc.johnabbott.cs603.asg3.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.MotionEvent;
import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Rectangle;

public abstract class RectangleBaseTool extends Tool {

	protected float x1 = 0;
	protected float x2 = 0;
	protected float y1 = 0;
	protected float y2 = 0;

	public RectangleBaseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
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

	@Override
	public void drawPreview(Canvas canvas) {
		toolbox.getDrawingView().erase();
		canvas.drawRect(x1, y1, x2, y2, toolbox.getPreviewPaint());

		//canvas.drawLine(x1, y1, x2, y2, toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		/*
		 * Log.d("x1",""+x1); Log.d("y1",""+y1); Log.d("x2",""+x2);
		 * Log.d("y2",""+y2); Log.d("color",""+toolbox.getStrokeColor());
		 * Log.d("width",""+toolbox.getStrokeWidth());
		 * Log.d("fill",""+toolbox.getFillColor());
		 */

		Rectangle rect = new Rectangle(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(rect);
		toolbox.getDrawingView().erase();
	}

}
