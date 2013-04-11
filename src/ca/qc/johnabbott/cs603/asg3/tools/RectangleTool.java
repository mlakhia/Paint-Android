package ca.qc.johnabbott.cs603.asg3.tools;

import android.graphics.Canvas;
import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Rectangle;

public class RectangleTool extends RectangleBaseTool {
	
	public RectangleTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		toolbox.getDrawingView().erase();
		canvas.drawRect(x1, y1, x2, y2, toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		Rectangle rect = new Rectangle(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(rect);
		toolbox.getDrawingView().erase();
	}
	
}
