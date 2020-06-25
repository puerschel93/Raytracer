package cgg.a02;

import cgtools.Color;

public class Circle {
	
	int radius;
	
	Color color;
	
	double centerX;
	double centerY;
	
	Circle(int radius,Color color, double centerX, double centerY){
		this.radius = radius;
		this.color = color;
		this.centerX = centerX;
		this.centerY = centerY;
		
	}
	
	public double getRadius() {
		return this.radius;
	}

}
