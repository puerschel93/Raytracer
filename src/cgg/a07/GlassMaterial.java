package cgg.a07;

import cgtools.*;

public class GlassMaterial implements Material{
	
	double n1 = 1.0;
	double n2 = 1.5;
	
	@Override
	public Color emission() {
		// TODO Auto-generated method stub
		return Color.black;
	}

	@Override
	public Color albedo() {
		// TODO Auto-generated method stub
		return Color.white;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		// TODO Auto-generated method stub
		
		Direction n = h.getN();
		Direction d = r.getDirection();
		
		if(Vector.dotProduct(n, d) > 0) {
			n = Vector.multiply(-1, n);
			double temp = n1;
			n1 = n2;
			n2 = temp;
		}
		
		Direction ref = refraction(d, n, n1, n2);
		if(ref != null ) {
			if(Random.random() > schlick(d, n, n1, n2)) {
				return new Ray(h.getX(), ref, 1 * Math.pow(10, -4), Double.POSITIVE_INFINITY);
			} else {
				return new Ray(h.getX(), reflect(d, n), 1 * Math.pow(10, -4), Double.POSITIVE_INFINITY);
			}
		}
		
		return null;
	}
	
	public Direction refraction(Direction d, Direction n, double n1, double n2) {
		
		double r = n1 / n2;
		double c = Vector.dotProduct(Vector.multiply(n, -1), d);
		double root = 1 - Math.pow(r, 2) * (1 - Math.pow(c, 2));
		
		if(root > 0) {
			return Direction.add(Vector.multiply(r, d), Vector.multiply(r * c - Math.sqrt(root), n));
		}
		
		return null;
	}
	
	public double schlick(Direction d, Direction n, double n1, double n2) {
		double reflectionFactor = Math.pow((n1 - n2) / (n1 + n2), 2);
		return reflectionFactor + (1 - reflectionFactor) * Math.pow((1 + Vector.dotProduct(n, d)), 5);
	}
	
	public Direction reflect(Direction d, Direction n) {
		return Vector.subtract(d, (Vector.multiply(2, Vector.multiply(Vector.dotProduct(n, d), n))));
	}

}
