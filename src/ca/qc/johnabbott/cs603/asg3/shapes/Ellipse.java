package ca.qc.johnabbott.cs603.asg3.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;

public class Ellipse extends Shape {

	private float cx, cy, rx, ry;
	
	public Ellipse(float cx, float cy, float rx, float ry, int strokeColor, int strokeWidth) {
		this(cx, cy, rx, ry, strokeColor, strokeWidth, Color.TRANSPARENT);
	}
	
	public Ellipse(float cx, float cy, float rx, float ry, int strokeColor, int strokeWidth, int fillColor) {
		super(strokeColor, fillColor, strokeWidth);
		this.cx = cx;
		this.cy = cy;
		this.rx = rx;
		this.ry = ry;
	}


	@Override
	public void draw(Paint paint, Canvas canvas) {
		
		// reset any path effect
		paint.setPathEffect(null);
				
		if(fillColor != Color.TRANSPARENT) {
			paint.setColor(fillColor);
			paint.setStyle(Style.FILL);
			canvas.drawOval(new RectF(cx-rx, cy-ry, cx+rx, cy+ry), paint);
		}
		if(strokeColor != Color.TRANSPARENT && strokeWidth > 0) {
			paint.setStyle(Style.STROKE);
			paint.setColor(strokeColor);
			paint.setStrokeWidth(strokeWidth);
			canvas.drawOval(new RectF(cx-rx, cy-ry, cx+rx, cy+ry), paint);
		}
		
	}

}
