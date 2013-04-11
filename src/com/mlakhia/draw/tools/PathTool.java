package com.mlakhia.draw.tools;

import java.util.ArrayList;
import java.util.List;

import com.mlakhia.draw.ToolBox;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;

public class PathTool extends Tool {
	
	private List<List<Point>> paths;
	private List<Point> points;
	
	public PathTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		
		paths = new ArrayList<List<Point>>();
		points = new ArrayList<Point>();
	}
	
	@Override
	public void touchStart(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		paths = new ArrayList<List<Point>>();
		points = new ArrayList<Point>();
		
		points.clear();
        points.add(point);
        
        hasPreview = true;
	}

	@Override
	public void touchMove(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		points.add(point);
		
		hasPreview = true;
		
		toolbox.getDrawingView().erase();
	}
	
	@Override
	public void touchEnd(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		points.add(point);
        paths.add(points);
        
		hasPreview = false;
		
		addToDrawing();
		
		toolbox.getDrawingView().erase();
	}
	
	@Override
	public void examplePreview(Canvas canvas) {
		// TODO PathTool examplePreview
		//canvas.drawRect(100, 100, 300, 300, toolbox.getPreviewPaint());
	}
	
	@Override
	public void drawPreview(Canvas canvas) {		
		List<Point> pointsList = null;
	    List<Path> pathList = new ArrayList<Path>();
		Point point = new Point();
		Path path = new Path();
		
		for (int i = 0; i < paths.size(); i++) {
	        pointsList = paths.get(i);

	        path.reset();
	        boolean first = true;

	        for (int j = 0; j < points.size(); j+=2 ) {
	            point = pointsList.get(j);

	            if (first) {
	                first = false;
	                path.moveTo(point.x, point.y);
	            } else if (j < pointsList.size() - 1 ) {
	                Point nextPoint = pointsList.get(j+1);
	                path.quadTo(point.x, point.y, nextPoint.x, nextPoint.y);
	            } else {
	                path.lineTo(point.x, point.y);
	            }
	        }
	        pathList.add(path);
	    }
		
		for (Path pathDraw : pathList) {
	        canvas.drawPath(pathDraw, toolbox.getPreviewPaint());
	    }
	}

	@Override
	public void addToDrawing() {
		com.mlakhia.draw.shapes.Path path = new com.mlakhia.draw.shapes.Path(paths, points, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(path);
	}

}
