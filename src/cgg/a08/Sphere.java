package cgg.a08;

import cgtools.*;

public class Sphere implements Shape{
	
	private Point position = null;
	private Color color = null;
	private double radius = 0;
	private Color background = Color.black;
	Material material = null;
	
	public Sphere(Point position, Color color, double radius, char mat, double scatter) {
		this.position = position;
		this.color = color;
		this.radius = radius;
		switch(mat) {
			case 'd': this.material = new DiffuseMaterial(color); break;
			case 'p': this.material = new PolishedMetalMaterial(color); break;
			case 'c': this.material = new CutMetalMaterial(color, scatter); break;
			case 'g': this.material = new GlassMaterial(); break;
			case 's': this.material = new ShiningMaterial(color, scatter); break;
			default: this.material = new DiffuseMaterial(color);
		}
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
				return new Hit(t, point, normVec, material);
			}
		}

		if (disc > 0) {
			double t1 = (-(b + Math.sqrt(disc))) / (2 * a);
			double t2 = (-(b - Math.sqrt(disc))) / (2 * a);

			t = t1 < t2 ? t1 : t2;

			if (t > r.getTmin() && t < r.getTmax()) {
				Point point = r.pointAt(t);
				Direction normVec = Vector.divide(Vector.subtract(point, position), radius);
				if (r.contains(t1)) {
					return new Hit(t1, point, normVec, material);
				} else if(r.contains(t2)){
					return new Hit(t2, point, normVec, material);
				} else {
					return null;
				}
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
	
	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return this.material;
	}
}
