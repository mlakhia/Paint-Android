package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class Tool {

	protected ToolBox toolbox;
	protected ToolName name;
	protected boolean hasPreview;
	
	public Tool(ToolBox toolbox, ToolName name) {
		super();
		this.toolbox = toolbox;
		this.name = name;
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
	
	public abstract void drawPreview(Canvas canvas);
	public abstract void addToDrawing();
}
