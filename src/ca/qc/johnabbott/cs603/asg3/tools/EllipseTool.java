package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Ellipse;
import ca.qc.johnabbott.cs603.asg3.shapes.Line;
import ca.qc.johnabbott.cs603.asg3.shapes.Rectangle;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

public class EllipseTool extends RectangleBaseTool {

	public EllipseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		toolbox.getDrawingView().erase();
		canvas.drawOval(new RectF(x1,y1,x2,y2), toolbox.getPreviewPaint());
		
		//canvas.drawOval(new RecT(x1-y2, y1-x2, x1+x2, y1+y2), toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		Ellipse ellipse = new Ellipse(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(ellipse);
		toolbox.getDrawingView().erase();
	}

}
