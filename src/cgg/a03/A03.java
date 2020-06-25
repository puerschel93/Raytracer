package cgg.a03;

import cgtools.*;
import java.util.ArrayList;

import cgg.Image;

public class A03 {
	public static void main(String[] args) {
        final int width = 1600;
        final int height = 900;
        double gamma = 2.2;
        final String filename = "doc/a03-one-sphere.png";
        Image image = new Image(width, height, gamma);
        ArrayList<Sphere> spheres = new ArrayList<Sphere>();
        
        double max = 5;
        int iterate = 0;
        
        Lochkamera lk = new Lochkamera(Math.PI / 2, image.getWidth(), image.getHeight());
        for(int i = (int) -max; i <= max; i += 1) {
        	Color newColor = new Color(1,(1 / (max*2)) * iterate,(1 / (max*2)) * iterate);
    		spheres.add(new Sphere(Vector.point(i, 0, -6), newColor, 0.4));
    		iterate++;
        }
		
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				Color finalColor = null;
				for(Sphere s : spheres) {
					finalColor = s.stratifiedSampling(x, y, lk, s, 100);
					if(!finalColor.equals(s.getBackground())) {
						break;
					}
				}
//				Color c = sphere.getColor(x, y, lk, sphere);
				image.setPixel(x, y, finalColor);
			}
		}
		
//	   
//		CAMERA - TEST
//		Lochkamera lk = new Lochkamera(Math.PI / 2, 10, 10);
//		System.out.println(lk.direction(0, 0));
//		System.out.println(lk.direction(5, 5));
//		System.out.println(lk.direction(10, 10));
	   
//		INTERSECT() - TEST
//		TEST 1
//	   	Sphere sphere1 = new Sphere(Vector.point(0, 0, -2), Color.black, 1);
//	   	Ray r1 = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 0, -1), 0, Double.MAX_VALUE);
//		Hit hit1 = sphere1.intersect(r1);
//		System.out.println("Trefferpunkt:\t" + hit1.getX() + "\tNormalenvektor:\t" + hit1.getN());
//		TEST 2
//	   	Sphere sphere2 = new Sphere(Vector.point(0, 0, -2), Color.black, 1);
//	   	Ray r2 = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 1, -1), 0, Double.MAX_VALUE);
//		Hit hit2 = sphere2.intersect(r2);
//		System.out.println("Trefferpunkt:\t" + hit2.getX() + "\tNormalenvektor:\t" + hit2.getN());
//		TEST 3
//	   	Sphere sphere3 = new Sphere(Vector.point(0, -1, -2), Color.black, 1);
//	   	Ray r3 = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 0, -1), 0, Double.MAX_VALUE);
//		Hit hit3 = sphere3.intersect(r3);
//		System.out.println("Trefferpunkt:\t" + hit3.getX() + "\tNormalenvektor:\t" + hit3.getN());
//		TEST 4
//	   	Sphere sphere4 = new Sphere(Vector.point(0, 0, -2), Color.black, 1);
//	   	Ray r4 = new Ray(Vector.point(0, 0, -4), Vector.direction(0, 0, -1), 0, Double.MAX_VALUE);
//		Hit hit4 = sphere4.intersect(r4);
//		System.out.println("Trefferpunkt:\t" + hit4.getX() + "\tNormalenvektor:\t" + hit4.getN());
//		TEST 4
//	   	Sphere sphere5 = new Sphere(Vector.point(0, 0, -4), Color.black, 1);
//	   	Ray r5 = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 0, -1), 0, 2);
//		Hit hit5 = sphere5.intersect(r5);
//		System.out.println("Trefferpunkt:\t" + hit5.getX() + "\tNormalenvektor:\t" + hit5.getN());
//		TEST ZUSATZ ZUM AUSPROBIEREN
//	   	Sphere spherez = new Sphere(Vector.point(0, 3, -6), Color.black, 3);
//	   	Ray rz = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 0, -1), 0, Double.MAX_VALUE);
//		Hit hitz = spherez.intersect(rz);
//		System.out.println("Trefferpunkt:\t" + hitz.getX() + "\tNormalenvektor:\t" + hitz.getN());

		image.write(filename);
		System.out.println("Wrote image: " + filename);
    }
}