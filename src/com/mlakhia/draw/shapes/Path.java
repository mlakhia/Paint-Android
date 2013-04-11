package com.mlakhia.draw.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Path extends Shape {

	private List<List<Point>> paths = new ArrayList<List<Point>>();
	private List<Point> points = new ArrayList<Point>();

	public Path(List<List<Point>> paths, List<Point> points, int strokeColor, int strokeWidth) {
		this(paths, points, strokeColor, strokeWidth, Color.TRANSPARENT);
	}		
	
	public Path(List<List<Point>> paths, List<Point> points, int strokeColor, int strokeWidth, int fillColor) {
		super(strokeColor, fillColor, strokeWidth);
		this.paths = paths;
		this.points = points;
	}

	@Override
	public void draw(Paint paint, Canvas canvas) {
		// reset any path effect
		paint.setPathEffect(null);
							
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(strokeColor);
		paint.setStrokeWidth(strokeWidth);
		paint.setStrokeCap(Paint.Cap.ROUND);
		//canvas.drawLine(x1, y1, x2, y2, paint);
		
		List<Point> pointsList;
	    List<android.graphics.Path> pathList = new ArrayList<android.graphics.Path>();
	    Point point = new Point();
	    android.graphics.Path path = new android.graphics.Path();
		
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
		
		for (android.graphics.Path pathDraw : pathList) {
	        canvas.drawPath(pathDraw, paint);
	    }
		
		/*
		if(fillColor != Color.TRANSPARENT) {
			paint.setColor(fillColor);
			paint.setStyle(Style.FILL);
			canvas.drawRect(x1, y1, x2, y2, paint);
		}
		if(strokeColor != Color.TRANSPARENT && strokeWidth > 0) {
			paint.setStyle(Style.STROKE);
			paint.setColor(strokeColor);
			paint.setStrokeWidth(strokeWidth);
			paint.setStrokeCap(Paint.Cap.ROUND);
			canvas.drawRect(x1, y1, x2, y2, paint);
		}*/
		
	}

}
