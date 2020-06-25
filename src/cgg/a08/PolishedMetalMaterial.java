package cgg.a08;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Vector;

public class PolishedMetalMaterial implements Material{
	
	Color color = null;
	
	public PolishedMetalMaterial(Color color) {
		this.color = color;
	}

	@Override
	public Color emission() {
		// TODO Auto-generated method stub
		return Color.black;
	}

	@Override
	public Color albedo() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		// TODO Auto-generated method stub
		Direction d = r.getDirection();
		Direction n = h.getN();
		
		Direction reflection = Vector.subtract(d, Vector.multiply(2 * Vector.dotProduct(n, d), n));
		
		return new Ray(h.getX(), reflection, 1 * Math.pow(10, -4), Double.POSITIVE_INFINITY);
	}

}
