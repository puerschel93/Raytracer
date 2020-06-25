package cgg.a08;

import cgtools.*;

public class Ray {
	
	private Point origin = null;
	private Direction direction = null;
	private double tmin = 0;
	private double tmax = 0;
	
	public Ray(Point origin, Direction direction, double tmin, double tmax) {
		this.origin = origin;
		this.direction = direction;
		this.tmin = tmin;
		this.tmax = tmax;
	}
	
	public boolean contains(double t) {
		if(t > tmin && t < tmax) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Ray [origin=" + origin + ", direction=" + direction + ", tmin=" + tmin + ", tmax=" + tmax + "]";
	}

	public Point pointAt(double t) {
		return Vector.add(origin, Vector.multiply(t, direction));
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public double getTmin() {
		return tmin;
	}

	public void setTmin(double tmin) {
		this.tmin = tmin;
	}

	public double getTmax() {
		return tmax;
	}

	public void setTmax(double tmax) {
		this.tmax = tmax;
	}
	
	

}
