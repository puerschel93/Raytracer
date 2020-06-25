package cgg.a04;

import cgtools.*;

public class Plane implements Shape{

	private Color color = null;
	private Direction normVec = null;
	private Point anchor = null;
	private double d = 0;
	
	public Plane(Color color, Direction normVec, Point anchor, double d) {
		this.color = color;
		this.normVec = normVec;
		this.anchor = anchor;
		this.d = d;
	}
	
	@Override
	public Hit intersect(Ray r) {
		double t = (Vector.dotProduct(anchor, normVec) - Vector.dotProduct(normVec, r.getOrigin()))
				/ Vector.dotProduct(r.getDirection(), normVec);
		if (t > r.getTmin() && t < r.getTmax()) {
			Point point = r.pointAt(t);
			if(Vector.length(Vector.subtract(point, anchor)) < d) {
				Hit hit = new Hit(t, point, normVec, color);
				return hit;
			} else {
				return null;
			}
			}
		return null;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Direction getNormVec() {
		return normVec;
	}

	public void setNormVec(Direction normVec) {
		this.normVec = normVec;
	}

	public Point getAnchor() {
		return anchor;
	}

	public void setAnchor(Point anchor) {
		this.anchor = anchor;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}
	
}