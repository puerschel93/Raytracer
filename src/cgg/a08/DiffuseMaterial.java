package cgg.a08;

import cgtools.*;

public class DiffuseMaterial implements Material{

	Color color = null;
	
	public DiffuseMaterial(Color color) {
		this.color = color;
	}
	
	@Override
	public Color emission() {
		return Color.black;
	}

	@Override
	public Color albedo() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		// TODO Auto-generated method stub
		double xRandom = Random.random() * 2 - 1;
		double yRandom = Random.random() * 2 - 1;
		double zRandom = Random.random() * 2 - 1;
		
		while (!(Math.pow(xRandom, 2) + Math.pow(yRandom, 2) < 1)) {
			xRandom = Random.random() * 2 - 1;
			yRandom = Random.random() * 2 - 1;
			zRandom = Random.random() * 2 - 1;
		}
		
		Direction norm = Vector.normalize(Vector.add(h.getN(), Vector.direction(xRandom, yRandom, zRandom)));
		return new Ray(h.getX(), norm, 1 * Math.pow(10, -4), Double.POSITIVE_INFINITY);
	}
}
