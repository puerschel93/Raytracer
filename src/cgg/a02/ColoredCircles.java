package cgg.a02;

import java.util.ArrayList;
import java.util.Collections;

import cgtools.Color;
import cgtools.Random;
import java.util.Comparator;

public class ColoredCircles {
	
	int numberOfDiscs;
	ArrayList<Circle> circles = new ArrayList<>();
	
	ColoredCircles(int numberOfDiscs, int width, int height){
		for(int i = 0; i <= numberOfDiscs; i++) {
			int radius = (int) (Math.random() * (100 - 2)) + 2;
			double r = Random.random();
			double g = Random.random();
			double b = Random.random();
			Color newColor = new Color(r,g,b);
			double centerX = (int) (Math.random() * (width));
			double centerY = (int) (Math.random() * (height));
			Circle newCircle = new Circle(radius, newColor, centerX, centerY);
			circles.add(newCircle);
		}
		circles.sort(Comparator.comparingDouble(Circle::getRadius));
	}
	
	Color isPointInCircle(double x, double y) {
		for(Circle c: circles) {
			if(c.radius * c.radius > ((c.centerX - x) * (c.centerX - x)) + ((c.centerY - y) * (c.centerY - y))) {
				return c.color;
			}
		}
		return Color.gray;
	}
	
	public Color stratifiedSampling(double x, double y, int sampleFrequence) {
		Color result = isPointInCircle(x, y);
		int sf = (int) Math.sqrt(sampleFrequence);
		for (int xi = 0; xi < sf; xi++) {
			for (int yi = 0; yi < sf; yi++) {
				double randomx = Random.random();
				double randomy = Random.random();
				double xs = x + ((xi + randomx) / sf);
				double ys = y + ((yi + randomy) / sf);
				result = Color.add(result, isPointInCircle(xs, ys));
			}
		}
		return Color.divide(result, sampleFrequence);
	}
}
