package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Line;
import ca.qc.johnabbott.cs603.asg3.shapes.Rectangle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class LineTool extends RectangleBaseTool {

	public LineTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}

	@Override
	public void drawPreview(Canvas canvas) {
		toolbox.getDrawingView().erase();
		canvas.drawLine(x1, y1, x2, y2, toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		Line line = new Line(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
		toolbox.getDrawingView().getShapes().add(line);
		toolbox.getDrawingView().erase();
	}

}
