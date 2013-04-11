package com.mlakhia.draw;

import com.mlakhia.draw.tools.CurveTool;
import com.mlakhia.draw.tools.EllipseTool;
import com.mlakhia.draw.tools.LineTool;
import com.mlakhia.draw.tools.PathTool;
import com.mlakhia.draw.tools.RectangleTool;
import com.mlakhia.draw.tools.Tool;
import com.mlakhia.draw.tools.ToolName;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

public class ToolBox {
	
	private int strokeWidth;
    private int strokeColor;
    private int fillColor;
    private DrawingView drawingView;
    private Paint previewPaint;
    private Tool currentTool;
    
    public ToolBox(DrawingView drawingView){
    	this.drawingView = drawingView;
    	
    	previewPaint = new Paint();
    	previewPaint.setStyle(Paint.Style.STROKE);
		previewPaint.setColor(Color.GRAY);
		previewPaint.setStrokeWidth(1);
		previewPaint.setStrokeCap(Paint.Cap.ROUND);
		previewPaint.setPathEffect(new DashPathEffect(new float[] { 4.0f, 4.0f }, 1.0f));

		changeTool(ToolName.LINE); // set default tool to Line tool
		
		this.fillColor = Color.GRAY;
		this.strokeColor = Color.DKGRAY;
		this.strokeWidth = 1;
    }

	public ToolName getCurrentToolName() {
		if(currentTool != null)
			return currentTool.getName();
		else
			return ToolName.NONE;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public int getFillColor() {
		return fillColor;
	}

	public void setFillColor(int fillColor) {
		this.fillColor = fillColor;
	}

	public int getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(int strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	public Paint getPreviewPaint() {
		return previewPaint;
	}

	public void setPreviewPaint(Paint previewPaint) {
		this.previewPaint = previewPaint;
	}

	public Tool getCurrentTool() {
		return currentTool;
	}

	public void setCurrentTool(Tool currentTool) {
		this.currentTool = currentTool;
	}

	public void changeTool(ToolName name) {
		switch(name){
			case LINE: currentTool = new LineTool(this, name); break;
			case RECTANGLE: currentTool = new RectangleTool(this, name); break;
			case ELLIPSE: currentTool = new EllipseTool(this, name); break;
			case PATH: currentTool = new PathTool(this, name); break;
			case CURVE: currentTool = new CurveTool(this, name); break;
		}
	}

	public DrawingView getDrawingView() {
		return drawingView;
	}

}
