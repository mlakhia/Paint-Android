package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;
import com.mlakhia.draw.shapes.Ellipse;

import android.graphics.Canvas;
import android.graphics.RectF;

public class EllipseTool extends RectangleBaseTool {

	public EllipseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}

	@Override
	public void examplePreview(Canvas canvas) {
		canvas.drawOval(new RectF(100, 100, 300, 300), toolbox.getPreviewPaint());
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		toolbox.getDrawingView().erase();
		canvas.drawOval(new RectF(x1,y1,x2,y2), toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		Ellipse ellipse = new Ellipse(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(ellipse);
		toolbox.getDrawingView().erase();
	}

}
