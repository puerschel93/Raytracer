package cgg.a05;

import cgtools.*;

import java.util.ArrayList;
import java.util.Collections;

import cgg.Image;

public class A05 {
	public static void main(String[] args) {
        final int width = 1600;
        final int height = 900;
        int sampleFrequence = 10;
        final String filename = "doc/a05-diffuse-spheres-test1.png";
        System.out.println("CREATING:\t" + filename);

        ArrayList<Shape> shapes = new ArrayList<>();
        
        shapes.add(new Background()); 
        shapes.add(new Plane(Vector.direction(0, 1.8, 0), Vector.point(0.0, -1, 0.0), Double.MAX_VALUE));

		shapes.add(new Sphere(Vector.point(-4, 0, -6), Vector.color(0.41, 0.82, 0.91), 1));
		shapes.add(new Sphere(Vector.point(-2, 0, -6), Vector.color(0.65, 0.86, 0.85), 1));
		shapes.add(new Sphere(Vector.point(0, 0, -8), Vector.color(0.88, 0.89, 0.80), 1));
		shapes.add(new Sphere(Vector.point(2, 0, -6), Vector.color(0.95, 0.53, 0.19), 1));
		shapes.add(new Sphere(Vector.point(4, 0, -6), Vector.color(0.98, 0.41, 0), 1));


        Group group = new Group(shapes);
        Raytracer rt = new Raytracer(group, width, height, filename, sampleFrequence);
        Image image = rt.raytrace();
		image.write(filename);
		System.out.println("Wrote image: " + filename);
    }
 
	public static ArrayList<Shape> generateRandomBalls(){
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Background());
        int groundHeight = 1;
        shapes.add(new Plane(Vector.direction(0, 1.8, 0), Vector.point(0.0, -groundHeight, 0.0), Double.MAX_VALUE));
        for(int i = 0; i < 120; i++) {
//        	double randomSize = Random.random(0.2, 0.6);
        	double randomSize = 0.2;
        	double randomX = getRandomValidX();
        	double randomZ = getRandomValidZ(randomSize);
        	shapes.add(new Sphere(Vector.point(randomX, ( -groundHeight + randomSize), randomZ), returnRandomColor(), randomSize));
        }
        return shapes;
	}
	
	public static Color returnRandomColor() {
        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Vector.color(0.41, 0.82, 0.91));
        colorList.add(Vector.color(0.65, 0.86, 0.85));
        colorList.add(Vector.color(0.88, 0.89, 0.80));
        colorList.add(Vector.color(0.95, 0.53, 0.19));
        colorList.add(Vector.color(0.98, 0.41, 0));
        Collections.shuffle(colorList);
        return colorList.get(0);
	}
	
	public static Boolean getRandomBoolean() {
		if(Random.random() < 0.5) {
			return true;
		} else {
			return false;
		}
	}
	
	public static double getRandomValidZ(double dynamer) {
		double z = -(Random.random() * 40);
		
		if(z < -(dynamer * 3) && z > -(dynamer * 10) * 40) {
			return z;
		} else {
			return getRandomValidZ(dynamer);
		}
	}
	
	public static double getRandomValidX() {
		double x = Random.random() * 10;
		if(getRandomBoolean()) {
			x = -x;
		}
		if(x > -7 && x < 7) {
			return x;
		} else {
			return getRandomValidX();
		}
	}
	
} 