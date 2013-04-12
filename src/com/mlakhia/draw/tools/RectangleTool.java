package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;
import com.mlakhia.draw.shapes.Rectangle;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RectangleTool extends RectangleBaseTool {
	
	public RectangleTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}
	
	@Override
	public void examplePreview(Canvas canvas) {
		Rectangle rect = new Rectangle(50, 50, 350, 350, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		rect.draw(new Paint(), canvas);
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		if(toolbox.isExampleDotted()){
			canvas.drawRect(x1, y1, x2, y2, toolbox.getPreviewPaint());
		}else{
			Rectangle rect = new Rectangle(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
			rect.draw(toolbox.getPreviewPaint(), canvas);
		}
	}

	@Override
	public void addToDrawing() {
		Rectangle rect = new Rectangle(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(rect);
	}
	
}
