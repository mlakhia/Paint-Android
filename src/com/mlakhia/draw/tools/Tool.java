package com.mlakhia.draw.tools;

import com.mlakhia.draw.ToolBox;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class Tool {

	protected ToolBox toolbox;
	protected ToolName name;
	protected boolean hasPreview;
	protected float x1, y1, x2, y2;
	
	public Tool(ToolBox toolbox, ToolName name) {
		super();
		this.toolbox = toolbox;
		this.name = name;
		
		x1 = 0;
		x2 = 0;
		y1 = 0;
		y2 = 0;
		
		hasPreview = false;
	}
	
	public boolean hasPreview() {
		return hasPreview;
	}

	public ToolName getName() {
		return name;
	}

	public abstract void touchStart(MotionEvent event);
	public abstract void touchEnd(MotionEvent event);
	public abstract void touchMove(MotionEvent event);
	
	public abstract void examplePreview(Canvas canvas); //draws example image
	
	public abstract void drawPreview(Canvas canvas); //draws user ontouchdown preview
	public abstract void addToDrawing(); // adds drawn shape to shapes array
}
