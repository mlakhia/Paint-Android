package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Line;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class LineTool extends RectangleBaseTool {

	public LineTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawPreview(Canvas canvas) {		
		Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		//int sWid = toolbox.getStrokeWidth();
		//int sCol = toolbox.getStrokeColor();

		int sWid = Color.DKGRAY;
		int sCol = 1;
		
        myPaint.setStrokeWidth(sWid);
        myPaint.setColor(sCol); 

		canvas.drawLine(100, 100, 300, 300, myPaint);
        
        //canvas.drawLine(myArrayListOfValues.get(i), myArrayListOfValues.get(i), myArrayListOfValues.get(i-1), myArrayListOfValues.get(i-1), myPaint);       

		
		//Log.d("","any1");
		//Line newLine = new Line(50, 50, 100, 100, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
		//Log.d("","any2");
		//newLine.draw(toolbox.getPreviewPaint(), canvas);
	}

}
