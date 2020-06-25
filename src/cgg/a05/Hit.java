package cgg.a05;

import cgtools.*;

public class Hit {
	
	private double t = 0;
	private Point x = null;
	private Direction n = null;
	private Material m = null;
	
	public Hit(double t, Point x, Direction n, Material m) {
		this.t = t;
		this.x = x;
		this.n = n;
		this.m = m;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public Point getX() {
		return x;
	}

	public void setX(Point x) {
		this.x = x;
	}

	public Direction getN() {
		return n;
	}

	public void setN(Direction n) {
		this.n = n;
	}

	public Material getM() {
		return m;
	}

	public void setM(Material m) {
		this.m = m;
	}
}
