package cgg.a03;

import cgtools.*;

public class Sphere {
	
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
	
	static Color shade(Direction vector, Color color) {
	    Direction lightDir = Vector.normalize(Vector.direction(3, 0, 0.5));
	    Color ambient = Color.multiply(0.1, color);
	    Color diffuse = Color.multiply(0.9 * Double.max(0, Vector.dotProduct(lightDir, vector)), color);
	    return Color.add(ambient, diffuse);
	}
	
	public Color getColor(double x, double y, Lochkamera lk, Sphere sphere) {
		Ray r = lk.generateRay(x, y);
		Hit h = sphere.intersect(r);
		if(h != null) {
			return shade(h.getN(), h.getC());
		} else {
			return background;
		}
	}
	
	public Color stratifiedSampling(double x, double y, Lochkamera lk,Sphere sphere, int sampleFrequence) {
		Color result = getColor(x, y, lk, sphere);
			int sf = (int) Math.sqrt(sampleFrequence);
			for (int xi = 0; xi < sf; xi++) {
				for (int yi = 0; yi < sf; yi++) {
					double randomx = Random.random();
					double randomy = Random.random();
					double xs = x + ((xi + randomx) / sf);
					double ys = y + ((yi + randomy) / sf);
					result = Color.add(result, getColor(xs, ys, lk, sphere));
				}
			}
		return Color.divide(result, sampleFrequence);
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	public Color getBackground() {
		return this.background;
	}
	
}
