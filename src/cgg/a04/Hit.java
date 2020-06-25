package cgg.a04;

import cgtools.*;

public class Hit {
	
	@Override
	public String toString() {
		return "Hit [t=" + t + ", x=" + x + ", n=" + n + ", c=" + c + "]";
	}

	private double t = 0;
	private Point x = null;
	private Direction n = null;
	private Color c = null;
	
	public Hit(double t, Point x, Direction n, Color c) {
		this.t = t;
		this.x = x;
		this.n = n;
		this.c = c;
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

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
}
