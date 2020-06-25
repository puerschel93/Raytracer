package cgg.a08;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class ShiningMaterial implements Material{
	
	Color color = null;
	double scatter = 0;
	
	public ShiningMaterial(Color color, double scatter) {
		this.color = color;
		this.scatter = scatter;
	}
	
	@Override
	public Color emission() {
		return color;
	}

	@Override
	public Color albedo() {
		return Color.black;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		Direction d = r.getDirection();
		Direction n = h.getN();
		
		Direction reflection = Vector.subtract(d, Vector.multiply(2 * Vector.dotProduct(n, d), n));
		if(scatter > 0) {
			double xRandom = Random.random() * 2 - 1;
			double yRandom = Random.random() * 2 - 1;
			double zRandom = Random.random() * 2 - 1;
			
			while (!(Math.pow(xRandom, 2) + Math.pow(yRandom, 2) < 1)) {
				xRandom = Random.random() * 2 - 1;
				yRandom = Random.random() * 2 - 1;
				zRandom = Random.random() * 2 - 1;
			}

			Direction randomDirection = Vector.direction(xRandom, yRandom, zRandom);
			reflection = Vector.add(reflection, Vector.multiply(scatter, randomDirection));
		}
		
		return new Ray(h.getX(), reflection, 1 * Math.pow(10, -4), Double.POSITIVE_INFINITY);
	}
	
	

}
