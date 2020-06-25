package cgg.a04;

import cgtools.*;

public class Sphere implements Shape{
	
	@Override
	public String toString() {
		return "Sphere [position=" + position + ", color=" + color + ", radius=" + radius + ", background=" + background
				+ "]";
	}

	private Point position = null;
	private Color color = null;
	private double radius = 0;
	private Color background = Color.black;
	
	public Sphere(Point position, Color color, double radius) {
		this.position = position;
		this.color = color;
		this.radius = radius;
	}

	public Hit intersect(Ray r) {
		
		Direction np = Vector.subtract(r.getOrigin(), position);

		double a = Vector.dotProduct(r.getDirection(), r.getDirection());
		double b = 2 * Vector.dotProduct(np, r.getDirection());
		double c = Vector.dotProduct(np, np) - Math.pow(radius, 2);
		double disc = Math.pow(b, 2) - 4 * a * c;
		double t = 0;

		if (disc < 0) {
			return null;
		}

		if (disc == 0) {
			double t1 = (-(b + Math.sqrt(disc))) / (2 * a);
			double t2 = (-(b - Math.sqrt(disc))) / (2 * a);
			
			t = t1 < t2 ? t1 : t2;

			if (t > r.getTmin() && t < r.getTmax()) {
				Point point = r.pointAt(t1);
				Direction normVec = Vector.divide(Vector.subtract(point, position), radius);
				return new Hit(t, point, normVec, color);
			}
		}

		if (disc > 0) {
			double t1 = (-(b + Math.sqrt(disc))) / (2 * a);
			double t2 = (-(b - Math.sqrt(disc))) / (2 * a);

			t = t1 < t2 ? t1 : t2;

			if (t > r.getTmin() && t < r.getTmax()) {
				Point point = r.pointAt(t);
				Direction normVec = Vector.divide(Vector.subtract(point, position), radius);
				return new Hit(t, point, normVec, color);
			}
		}
		return null;
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	public Color getBackground() {
		return this.background;
	}
	
}
