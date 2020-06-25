package cgg.a05;

import cgtools.*;

public class Background implements Shape{
	
	Material material = null;
	
	public Background() {
		this.material = new BackgroundMaterial();
	}

	@Override
	public Hit intersect(Ray r) {
		double t = Double.POSITIVE_INFINITY;
		Point point = r.pointAt(t);
		return new Hit(t, point, Vector.direction(0, 0, 0), material);
	}
	
}