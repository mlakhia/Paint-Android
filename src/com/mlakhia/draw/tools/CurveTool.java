package com.mlakhia.draw.tools;

import java.util.ArrayList;
import java.util.Arrays;

import com.mlakhia.draw.ToolBox;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

public class CurveTool extends Tool {

	public CurveTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}

	@Override
	public void touchStart(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		
        
        hasPreview = false;
	}

	@Override
	public void touchMove(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		
		
		hasPreview = true;
		
		toolbox.getDrawingView().invalidate();
	}
	
	@Override
	public void touchEnd(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		
        
		hasPreview = false;
		
		addToDrawing();
		
		toolbox.getDrawingView().invalidate();
	}
	
	@Override
	public void examplePreview(Canvas canvas) {
		//com.mlakhia.draw.shapes.Path path = new com.mlakhia.draw.shapes.Path(getExamplePoints(), toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		//path.draw(new Paint(), canvas);
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		if(toolbox.isExampleDotted()){
			//for(int i = 0; i < points.size()-1; i++) {
		    //    canvas.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y, toolbox.getPreviewPaint());
		    //}
		}else{
			//com.mlakhia.draw.shapes.Path path = new com.mlakhia.draw.shapes.Path(points, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
			//path.draw(toolbox.getPreviewPaint(), canvas);
		}
	}

	@Override
	public void addToDrawing() {
		//com.mlakhia.draw.shapes.Path path = new com.mlakhia.draw.shapes.Path(points, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		//toolbox.getDrawingView().getShapes().add(path);
	}
	

}
