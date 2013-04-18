package com.mlakhia.draw;

import java.io.Serializable;
import java.util.ArrayList;

import com.mlakhia.draw.shapes.Shape;

public class Picture implements Serializable {

	private static final long serialVersionUID = 1L;
	
	ArrayList<Shape> shapes;
	
	public Picture() {
		super();
		shapes = new ArrayList<Shape>();
	}
	
	public void erase() {
		shapes = new ArrayList<Shape>();
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
}
