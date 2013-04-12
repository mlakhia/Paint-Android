package com.mlakhia.draw.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Path extends Shape {

	private List<Point> points = new ArrayList<Point>();

	public Path(List<Point> points, int strokeColor, int strokeWidth) {
		this(points, strokeColor, strokeWidth, Color.TRANSPARENT);
	}		
	
	public Path( List<Point> points, int strokeColor, int strokeWidth, int fillColor) {
		super(strokeColor, fillColor, strokeWidth);
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
		
		for(int i = 0; i < points.size()-1; i++) {
	        canvas.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y, paint);
	    }		
	}

}
