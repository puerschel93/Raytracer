package cgg.a03;

import cgtools.*;

public class Lochkamera {

	private double phi = 0;
	private double width = 0;
	private double height = 0;
	private Ray ray = null;
	
	public Lochkamera(double phi, double width, double height) {
		this.phi = phi;
		this.width = width;
		this.height = height;
	}
	
	public Direction direction(double x, double y) {
		double xc = x - width / 2.0;
		double yc = height / 2.0 - y;
		double zc = -((width / 2.0) / Math.tan(phi / 2.0));
		
		return Direction.normalize(Vector.direction(xc, yc, zc));
	}
	
	public Ray generateRay(double x, double y) {
		ray = new Ray(Vector.point(0, 0, 0), direction(x, y), 0, Double.MAX_VALUE);
		return ray;
	}	
}
