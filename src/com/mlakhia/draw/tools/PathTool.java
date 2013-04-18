package com.mlakhia.draw.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mlakhia.draw.ToolBox;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

public class PathTool extends Tool {

	private List<Point> points;
	
	public PathTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		
		points = new ArrayList<Point>();
	}
	
	@Override
	public void touchStart(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		points = new ArrayList<Point>();
		
		points.clear();
        points.add(point);
        
        hasPreview = false;
	}

	@Override
	public void touchMove(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		points.add(point);
		
		hasPreview = true;
		
		toolbox.getDrawingView().invalidate();
	}
	
	@Override
	public void touchEnd(MotionEvent event) {
		Point point = new Point();
		point.x = (int) event.getX();
		point.y = (int) event.getY();
		
		points.add(point);
        
		hasPreview = false;
		
		addToDrawing();
		
		toolbox.getDrawingView().invalidate();
	}
	
	@Override
	public void examplePreview(Canvas canvas) {
		com.mlakhia.draw.shapes.Path path = new com.mlakhia.draw.shapes.Path(getExamplePoints(), toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		path.draw(new Paint(), canvas);
	}
	
	@Override
	public void drawPreview(Canvas canvas) {
		if(toolbox.isExampleDotted()){
			for(int i = 0; i < points.size()-1; i++) {
		        canvas.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y, toolbox.getPreviewPaint());
		    }
		}else{
			com.mlakhia.draw.shapes.Path path = new com.mlakhia.draw.shapes.Path(points, toolbox.getStrokeColor(), toolbox.getStrokeWidth());
			path.draw(toolbox.getPreviewPaint(), canvas);
		}
	}

	@Override
	public void addToDrawing() {
		com.mlakhia.draw.shapes.Path path = new com.mlakhia.draw.shapes.Path(points, toolbox.getStrokeColor(), toolbox.getStrokeWidth(), toolbox.getFillColor());
		toolbox.getPicture().getShapes().add(path);
	}

	private ArrayList<Point> getExamplePoints(){
		return new ArrayList<Point>(Arrays.asList(
				new Point(61, 250), new Point(60, 249), new Point(60, 248), new Point(60, 247), new Point(61, 245), new Point(62, 240), new Point(66, 232), new Point(70, 221), new Point(76, 207), new Point(86, 190), new Point(94, 171), new Point(103, 152), new Point(112, 135), new Point(121, 117), new Point(129, 102), new Point(138, 85), new Point(144, 74), new Point(148, 67), new Point(151, 62), new Point(153, 59), new Point(154, 56), new Point(155, 54), new Point(156, 52), new Point(157, 51), new Point(157, 50), new Point(157, 49), new Point(157, 48), new Point(157, 47), new Point(157, 46), new Point(157, 47), new Point(158, 48), new Point(159, 49), new Point(160, 51), new Point(160, 53), new Point(162, 55), new Point(164, 58), new Point(166, 63), new Point(168, 68), new Point(171, 75), new Point(173, 82), new Point(174, 89), new Point(176, 95), new Point(178, 102), new Point(179, 108), new Point(181, 113), new Point(183, 119), new Point(184, 125), new Point(186, 132), new Point(187, 140), new Point(188, 148), new Point(189, 156), new Point(191, 163), new Point(192, 169), new Point(192, 174), new Point(193, 179), new Point(194, 184), new Point(195, 189), new Point(196, 195), new Point(197, 201), new Point(197, 208), new Point(199, 214), new Point(200, 220), new Point(200, 224), new Point(201, 227), new Point(202, 229), new Point(202, 231), new Point(202, 233), new Point(202, 235), new Point(202, 236), new Point(202, 238), new Point(202, 240), new Point(202, 241), new Point(202, 242), new Point(202, 243), new Point(202, 244), new Point(202, 245), new Point(202, 246), new Point(203, 246), new Point(203, 246), new Point(204, 245), new Point(206, 243), new Point(208, 241), new Point(213, 236), new Point(221, 226), new Point(231, 213), new Point(241, 201), new Point(252, 188), new Point(262, 175), new Point(271, 162), new Point(280, 150), new Point(287, 140), new Point(294, 131), new Point(301, 122), new Point(307, 114), new Point(314, 106), new Point(320, 98), new Point(324, 92), new Point(328, 86), new Point(332, 82), new Point(335, 79), new Point(338, 75), new Point(339, 73), new Point(340, 72), new Point(340, 71), new Point(341, 70), new Point(342, 69), new Point(343, 69), new Point(343, 68), new Point(343, 67), new Point(343, 68), new Point(344, 68), new Point(345, 70), new Point(346, 74), new Point(346, 87), new Point(346, 97), new Point(346, 105), new Point(346, 113), new Point(344, 128), new Point(344, 136), new Point(343, 145), new Point(342, 161), new Point(341, 167), new Point(340, 172), new Point(340, 181), new Point(339, 185), new Point(339, 189), new Point(339, 197), new Point(339, 200), new Point(339, 203), new Point(339, 207), new Point(339, 208), new Point(339, 210), new Point(339, 212), new Point(339, 210), new Point(341, 206), new Point(352, 192), new Point(360, 182), new Point(379, 158), new Point(389, 147), new Point(398, 137), new Point(417, 120), new Point(425, 112), new Point(441, 97), new Point(448, 91), new Point(455, 86), new Point(465, 78), new Point(468, 76), new Point(473, 73), new Point(474, 72), new Point(476, 70)
		));
	}
}
