package com.mlakhia.draw.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Line extends Shape {

	private float x1, x2, y1, y2;

	public Line(float x1, float y1, float x2, float y2, int strokeColor, int strokeWidth) {
		super(strokeColor, Color.TRANSPARENT, strokeWidth);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	@Override
	public void draw(Paint paint, Canvas canvas) {
		
		// reset any path effect
		paint.setPathEffect(null);
			
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(strokeColor);
		paint.setStrokeWidth(strokeWidth);
		paint.setStrokeCap(Paint.Cap.ROUND);
		canvas.drawLine(x1, y1, x2, y2, paint);
	}	

}
