package com.mlakhia.draw;

import java.util.ArrayList;

import com.mlakhia.draw.shapes.Shape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
	
	private Paint paint;
	private ToolBox toolbox;
	
	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setAntiAlias(true);
		
		toolbox = new ToolBox(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(toolbox.getCurrentTool().hasPreview())
			toolbox.getCurrentTool().drawPreview(canvas);
		
		for (Shape s : toolbox.getPicture().getShapes())
			s.draw(paint, canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			toolbox.getCurrentTool().touchStart(event);
			break;
			
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			toolbox.getCurrentTool().touchEnd(event);
			break;
			
		default:
			toolbox.getCurrentTool().touchMove(event);
		}
		return true;
	}
	
	public void erase() {
		toolbox.getPicture().erase();
	}
	
	public ToolBox getToolBox() {
		return toolbox;
	}

// to delete
	public ArrayList<Shape> getShapes() {
		return toolbox.getPicture().getShapes();
	}
	
}
