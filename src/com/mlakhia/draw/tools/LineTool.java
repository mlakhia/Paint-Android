package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;
import com.mlakhia.draw.shapes.Line;

import android.graphics.Canvas;

public class LineTool extends RectangleBaseTool {

	public LineTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}

	@Override
	public void examplePreview(Canvas canvas) {
		canvas.drawLine(100, 100, 300, 300, toolbox.getPreviewPaint());
	}

	@Override
	public void drawPreview(Canvas canvas) {
		canvas.drawLine(x1, y1, x2, y2, toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		Line line = new Line(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
		toolbox.getDrawingView().getShapes().add(line);
	}

}
