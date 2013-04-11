package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;
import com.mlakhia.draw.shapes.Rectangle;

import android.graphics.Canvas;

public class RectangleTool extends RectangleBaseTool {
	
	public RectangleTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}
	
	@Override
	public void examplePreview(Canvas canvas) {
		canvas.drawRect(100, 100, 300, 300, toolbox.getPreviewPaint());
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
