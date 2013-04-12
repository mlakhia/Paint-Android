package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;
import com.mlakhia.draw.shapes.Line;
import com.mlakhia.draw.shapes.Rectangle;

import android.graphics.Canvas;
import android.graphics.Paint;

public class LineTool extends RectangleBaseTool {

	public LineTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}

	@Override
	public void examplePreview(Canvas canvas) {
		Line line = new Line(50, 50, 350, 350, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
		line.draw(new Paint(), canvas);
	}

	@Override
	public void drawPreview(Canvas canvas) {
		if(toolbox.isExampleDotted()){
			canvas.drawLine(x1, y1, x2, y2, toolbox.getPreviewPaint());
		}else{
			Line line = new Line(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
			line.draw(new Paint(), canvas);
		}
	}

	@Override
	public void addToDrawing() {
		Line line = new Line(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
		toolbox.getDrawingView().getShapes().add(line);
	}

}
