package cgg.a06;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class CutMetalMaterial implements Material{
	
	Color color = null;
	double scatter = 0;
	
	public CutMetalMaterial(Color color, double scatter) {
		this.color = color;
		this.scatter = scatter;
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
