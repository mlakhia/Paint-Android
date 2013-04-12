package com.mlakhia.draw.tools;

import com.mlakhia.draw.MainActivity;
import com.mlakhia.draw.ToolBox;
import com.mlakhia.draw.shapes.Curve;
import com.mlakhia.draw.shapes.Line;
import com.mlakhia.draw.shapes.Rectangle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.widget.Toast;

public class CurveTool extends Tool {

	private float x3, y3;
	private boolean first;
	private Path path;
	
	public CurveTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		x1 = 0;
		y1 = 0;
		x2 = -1;
		y2 = -1;
		x3 = 0;
		y3 = 0;
		first = true;
		path = new Path();
	}

	@Override
	public void touchStart(MotionEvent event) {		
		if(first){
			x1 = event.getX();
			y1 = event.getY();
		}else{
			x2 = event.getX();
			y2 = event.getY();
		}
        
        hasPreview = false;
	}

	@Override
	public void touchMove(MotionEvent event) {
		if(first){
			x3 = event.getX();
			y3 = event.getY();
		}else{
			x2 = event.getX();
			y2 = event.getY();
		}
		
		hasPreview = true;
		
		toolbox.getDrawingView().invalidate();
	}
	
	@Override
	public void touchEnd(MotionEvent event) {
		if(first){
			x3 = event.getX();
			y3 = event.getY();
			first = false; // set bool to use 2nd action
			Toast.makeText(MainActivity.context, "Touch The Screen Again To Drag Your Control Point.", Toast.LENGTH_LONG).show();
		}else{
			x2 = event.getX();
			y2 = event.getY();
			addToDrawing();
			first = true; // set bool to go back to using 1st action
		}
		
		hasPreview = false;
		toolbox.getDrawingView().invalidate();
	}
	
	@Override
	public void examplePreview(Canvas canvas) {		
		Curve curve = new Curve(100, 100, 150, 280, 300, 300, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
		curve.draw(toolbox.getPreviewPaint(), canvas);
	}
	
	@Override
	public void drawPreview(Canvas canvas) {		
		if(toolbox.isExampleDotted()){			
			if(first){
				path.reset();
				path.moveTo(x1, y1);
				path.lineTo(x3, y3);
			}else{
				path.reset();
				path.moveTo(x1, y1);
				path.quadTo(x2, y2, x3, y3);
			}
			canvas.drawPath(path, toolbox.getPreviewPaint());
		}else{			
			if(first){
				Line line = new Line(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
				line.draw(new Paint(), canvas);
			}else{
				Curve curve = new Curve(x1, y1, x2, y2, x3, y3, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
				curve.draw(new Paint(), canvas);
			}
		}
	}

	@Override
	public void addToDrawing() {
		Curve curve = new Curve(x1, y1, x2, y2, x3, y3, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(curve);
	}

}
