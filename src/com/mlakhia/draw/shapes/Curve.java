package com.mlakhia.draw.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Curve extends Shape {
	
	private static final long serialVersionUID = 1L;

	private float x1, y1, x2, y2, x3, y3;

	public Curve(float x1, float y1, float x2, float y2, float x3, float y3, int strokeColor, int strokeWidth) {
		this(x1, y1, x2, y2, x3, y3, strokeColor, strokeWidth, Color.TRANSPARENT);
	}
	
	public Curve(float x1, float y1, float x2, float y2, float x3, float y3, int strokeColor, int strokeWidth, int fillColor) {
		super(strokeColor, fillColor, strokeWidth);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
	}

	@Override
	public void draw(Paint paint, Canvas canvas) {
		// reset any path effect
		paint.setPathEffect(null);
							
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(strokeColor);
		paint.setStrokeWidth(strokeWidth);
		paint.setStrokeCap(Paint.Cap.ROUND);
		
		final Path path = new Path();
		path.moveTo(x1, y1);
		path.quadTo(x2, y2, x3, y3);
		canvas.drawPath(path, paint);
	}

}
