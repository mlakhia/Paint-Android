package ca.qc.johnabbott.cs603.asg3;

import java.util.ArrayList;

import ca.qc.johnabbott.cs603.asg3.shapes.Rectangle;
import ca.qc.johnabbott.cs603.asg3.shapes.Shape;
import ca.qc.johnabbott.cs603.asg3.tools.ToolName;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
	
	private Paint paint;
	private ToolBox toolbox;
	ArrayList<Shape> shapes;
	
	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setAntiAlias(true);
		
		toolbox = new ToolBox(this);
		
		shapes = new ArrayList<Shape>();
		
		//shapes.add(new Rectangle(100, 100, 400, 400, Color.MAGENTA, 1, Color.CYAN));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		for (Shape s : shapes)
			s.draw(paint, canvas);
		
		if(toolbox.getCurrentTool().hasPreview()){
			toolbox.getCurrentTool().drawPreview(canvas);
		}
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
		invalidate();
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public ToolBox getToolBox() {
		return toolbox;
	}
	
}
