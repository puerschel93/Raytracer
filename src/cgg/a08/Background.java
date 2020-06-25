package cgg.a08;

import cgtools.*;

public class Background implements Shape{
	
	Material material = null;
	
	public Background(Color color) {
		this.material = new BackgroundMaterial(color);
	}

	@Override
	public Hit intersect(Ray r) {
		double t = Double.POSITIVE_INFINITY;
		Point point = r.pointAt(t);
		return new Hit(t, point, Vector.direction(0, 0, 0), material);
	}
	
	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return this.material;
	}
	
}