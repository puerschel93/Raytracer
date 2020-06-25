package cgg.a08;

import cgtools.*;

public class Transformation {

	public Matrix toWorld = null;
	public Matrix fromWorld = null;
	public Matrix toWorldN = null;
	
	public Transformation(Matrix transformation) {
		this.toWorld = transformation;
		this.fromWorld = Matrix.invert(transformation);
		this.toWorldN = Matrix.transpose(transformation);
	}
	
	public Ray rayTransformation(Ray r) {
		return new Ray(Matrix.multiply(fromWorld ,r.getOrigin()), Matrix.multiply(fromWorld, r.getDirection()), r.getTmin(), r.getTmax());
	}

	public Hit hitTransformation(Hit h) {
		return new Hit(h.getT(), Matrix.multiply(toWorld, h.getX()), Matrix.multiply(toWorldN, h.getN()), h.getM());
	}
	
}
