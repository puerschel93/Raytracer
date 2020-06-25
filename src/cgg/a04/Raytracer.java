
package cgg.a04;

import cgg.Image;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class Raytracer {

	Group scene = null;
	Image image = null;
	String filename = null;
	Lochkamera lk = null;
	double width = 0;
	double height = 0;
	int sampleFrequence = 0;
	Color background = null;

	
	public Raytracer(Group scene, int width, int height, String filename, int sf, Color bg) {
		this.image = new Image(width, height, 2.2);
		this.scene = scene;
		this.lk = new Lochkamera(Math.PI / 2, width, height);
		this.width = width;
		this.height = height;
		this.sampleFrequence = sf;
		this.background = bg;
	}
	
	public Image raytrace() {
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				Color base = background;
				for (int xi = 0; xi < sampleFrequence; xi++) {
					for (int yi = 0; yi < sampleFrequence; yi++) {
						double rx = Random.random();
						double ry = Random.random();
						double xs = x + (xi + rx) / sampleFrequence;
						double ys = y + (yi + ry) / sampleFrequence;
						base = Color.add(base, getColor(xs, ys, lk));
					}
				}
				base = Color.divide(base, sampleFrequence * sampleFrequence);
				image.setPixel(x, y, base);
			}
		}
		return image;
	}
	
	static Color shade(Direction vector, Color color) {
	    Direction lightDir = Vector.normalize(Vector.direction(3, 0, 0.5));
	    Color ambient = Color.multiply(0.1, color);
	    Color diffuse = Color.multiply(0.9 * Double.max(0, Vector.dotProduct(lightDir, vector)), color);
	    return Color.add(ambient, diffuse);
	}

	public Color getColor(double x, double y, Lochkamera lk) {
		Ray r = lk.generateRay(x, y);
		Hit h = scene.intersect(r);
		return shade(h.getN(), h.getC());
	}

	@Override
	public String toString() {
		return "Raytracer [scene=" + scene + ", image=" + image + ", filename=" + filename + ", lk=" + lk + ", width="
				+ width + ", height=" + height + ", sampleFrequence=" + sampleFrequence + ", background=" + background
				+ "]";
	}
}
