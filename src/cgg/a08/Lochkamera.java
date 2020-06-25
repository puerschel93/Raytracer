package cgg.a08;

import cgg.a08.Ray;
import cgtools.*;

public class Lochkamera {

	private double phi = 0;
	private double width = 0;
	private double height = 0;
	private Ray ray = null;
	private Direction rotation = null;
	private Matrix translation = null;
	
	public Lochkamera(double phi, double width, double height, Direction rotation, Matrix translation) {
		this.phi = phi;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.translation = translation;
	}
	
	public Direction direction(double x, double y) {
		double xc = x - width / 2.0;
		double yc = height / 2.0 - y;
		double zc = -((width / 2.0) / Math.tan(phi / 2.0));
		
		return Direction.normalize(Vector.direction(xc, yc, zc));
	}
	
	public Ray generateRay(double x, double y) {
		Matrix xr = Matrix.rotation(Vector.direction(1, 0, 0), rotation.x);
		Matrix yr = Matrix.rotation(Vector.direction(0, 1, 0), rotation.y);
		Matrix zr = Matrix.rotation(Vector.direction(0, 0, 1), rotation.z);

		Direction t = direction(x, y);

		Direction d = Matrix.multiply(xr, t);
		d = Matrix.multiply(yr, d);
		d = Matrix.multiply(zr, d);

		return new Ray(Matrix.multiply(translation, Vector.point(0, 0, 0)), d, 0, Double.POSITIVE_INFINITY);
	}	
}
