package cgg.a04;

import cgtools.*;

public class Background implements Shape{
	
	private Color backgroundColor = Color.black;
	
	public Background(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public Hit intersect(Ray r) {
		double t = Double.POSITIVE_INFINITY;
		Point point = r.pointAt(t);
		return new Hit(t, point, Vector.direction(0, 0, 0), backgroundColor);
	}
	
}