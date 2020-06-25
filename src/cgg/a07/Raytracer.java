
package cgg.a07;

import cgg.Image;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Random;
import cgtools.Vector;

public class Raytracer {

	Group scene = null;
	Image image = null;
	String filename = null;
	Lochkamera lk = null;
	int width = 0;
	int height = 0;
	int sampleFrequence = 0;
	Color base = Color.black;

	
	public Raytracer(Group scene, int width, int height, String filename, int sf, Lochkamera lk) {
		this.image = new Image(width, height, 2.2);
		this.scene = scene;
		this.width = width;
		this.height = height;
		this.sampleFrequence = sf;
		this.lk = lk;
	}

	public Image raytrace() {
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
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
			int parse = x % (width / 100);
			if(parse == 0 && x != 0) {
				System.out.println((int)((double)(x) / (double)(width) * 100) + "% ");
			}
		}
		System.out.println("100%");
		return image;
	}

	public Color calculateRadiance(Shape scene, Ray ray, int depth) {
		if(depth == 0) {
			return Color.black;
		}
		
		Hit hit = scene.intersect(ray);
		if(hit.getM().scatteredRay(ray, hit) != null) {
			// splitted for debugging reasons
			Color one = hit.getM().emission();
			Color two = hit.getM().albedo();
			Color three = calculateRadiance(scene, hit.getM().scatteredRay(ray, hit), depth - 1);
			return Color.add(one, Color.multiply(two, three));
		} else {
			return hit.getM().emission();
		}
	}

	public Color getColor(double x, double y, Lochkamera lk) {
		Ray r = lk.generateRay(x, y);
		return calculateRadiance(scene, r, 10);
	}

}
