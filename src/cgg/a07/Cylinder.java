package cgg.a07;

import cgtools.*;

public class Cylinder implements Shape{

	private Point position = null;
	private Color color = null;
	private double radius = 0;
	private double height = 0;
	private Color background = Color.black;
	Material material = null;
	
	public Cylinder(Point position, Color color, double radius, double height, char mat, double scatter) {
		this.position = position;
		this.color = color;
		this.radius = radius;
		this.height = height;
		switch(mat) {
			case 'd': this.material = new DiffuseMaterial(color); break;
			case 'p': this.material = new PolishedMetalMaterial(color); break;
			case 'c': this.material = new CutMetalMaterial(color, scatter); break;
			case 's': this.material = new ShiningMaterial(color); break;
			case 'g': this.material = new GlassMaterial(); break;
			default: this.material = new DiffuseMaterial(color);
		}
	}
	
	/**
	 * Source: http://woo4.me/wootracer/cylinder-intersection/
	 */
	@Override
	public Hit intersect(Ray r) {
		
		Direction np = Vector.subtract(r.getOrigin(), position);

		double a = Math.pow(r.getDirection().x, 2) + Math.pow(r.getDirection().z, 2);
		double b = 2 * (np.x * r.getDirection().x + np.z * r.getDirection().z);
		double c = Math.pow(np.x, 2) + Math.pow(np.z, 2) - Math.pow(radius, 2);

		double disc = Math.sqrt(Math.pow(b, 2) - (4 * a * c));

		if (disc < 0) {
			return null;
		}

		double t1 = (-b + disc) / (2 * a);
		double t2 = (-b - disc) / (2 * a);
		double t0 = t1;

		if (t1 > t2) {
			t1 = t2;
		} 

		t2 = t0;
		double y1 = np.y + t1 * r.getDirection().y;
		double y2 = np.y + t2 * r.getDirection().y;

		if (y1 < -height) {
			if (y2 < -height)
				return null;
			else {
				double t = t1 + (t2 - t1) * (y1 + height) / (y1 - y2);
				if (!(r.contains(t))) {
					return null;
				} else {
					Point point = r.pointAt(t);
					Direction normVec = Vector.divide(Vector.subtract(point, position), radius);
					return new Hit(t, point, normVec, material);
				}

			}
		}
		if (y1 >= -height && y1 <= height) {
			if (!(r.contains(t1))) {
				return null;
			} else {
				Point point = r.pointAt(t1);
				Direction normVec = Vector.divide(Vector.subtract(point, position), radius);
				return new Hit(t1, point, normVec, material);
			}
		}
		if (y1 > height) {
			if (y2 > height) {
				return null;
			} else {
				double t = t1 + (t2 - t1) * (y1 - height) / (y1 - y2);
				if (!(r.contains(t))) {
					return null;
				} else {
					Point point = r.pointAt(t);
					Direction normVec = Vector.divide(Vector.subtract(point, position), radius);
					return new Hit(t, point, normVec, material);
				}
			}
		}
		return null;
	}

	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

}
