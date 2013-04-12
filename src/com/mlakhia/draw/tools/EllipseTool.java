package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;
import com.mlakhia.draw.shapes.Ellipse;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;

public class EllipseTool extends RectangleBaseTool {

	public EllipseTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
	}

	@Override
	public void examplePreview(Canvas canvas) {
		Ellipse ellipse = new Ellipse(50, 50, 350, 350, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		ellipse.draw(new Paint(), canvas);
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		if(toolbox.isExampleDotted()){
			canvas.drawOval(new RectF(x1,y1,x2,y2), toolbox.getPreviewPaint());
		}else{
			Ellipse ellipse = new Ellipse(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
			ellipse.draw(toolbox.getPreviewPaint(), canvas);
		}
		
		//x1-x2, y1-y2, x1+x2, y1+y2
		//canvas.drawOval(new RectF(x2-x1,y2-y1,x1+x2,y1+y2), toolbox.getPreviewPaint());
		
		//float width = x1+x2;
		//float height = y1+y2;
		
		//canvas.drawOval(new RectF(x1-width/2, y1-height/2, x2+width/2, y2+height/2), toolbox.getPreviewPaint());
		
		//x-width/2, y-height/2, x+width/2, y+height/2
		
		//canvas.drawOval(new RectF(x1,y1,x2,y2), toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		Ellipse ellipse = new Ellipse(x1, y1, x2, y2, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getDrawingView().getShapes().add(ellipse);
	}

}
