package cgg.a04;

import cgtools.*;

import java.util.ArrayList;

import cgg.Image;

public class A04 {
	public static void main(String[] args) {
        final int width = 1600;
        final int height = 900;
        int sampleFrequence = 10;
        Color backgroundColor = Color.black;
        
        // a04-3-spheres.png
        
//        final String filename = "doc/a04-3-spheres.png";
//        ArrayList<Shape> shapes = new ArrayList<>();
//        shapes.add(new Background(backgroundColor));
//        shapes.add(new Plane(Color.gray, Vector.direction(0, -1, 0), Vector.point(0.0, -0.5, 0.0), Double.MAX_VALUE));
//        shapes.add(new Sphere(Vector.point(-1.0, -0.25, -2.5), Color.red, 0.7));
//        shapes.add(new Sphere(Vector.point(0.0, -0.25, -2.5), Color.green, 0.5));
//        shapes.add(new Sphere(Vector.point(1.0, -0.25, -2.5), Color.blue, 0.7));
//        Group group = new Group(shapes);
//        Raytracer rt = new Raytracer(group, width, height, filename, sampleFrequence, Color.black);
//        Image image = rt.raytrace();
//		image.write(filename);
//		System.out.println("Wrote image: " + filename);
		
		// a04-scene.png
		
        final String filenameScene = "doc/a04-scene.png";
        Color backgroundColorScene = Color.peach;
        ArrayList<Shape> shapesScene = new ArrayList<>();
        shapesScene.add(new Background(backgroundColorScene));
        shapesScene.add(new Plane(Color.gray, Vector.direction(0, -1, 0), Vector.point(0.0, -0.5, 0.0), Double.POSITIVE_INFINITY));
	    shapesScene.add(new Sphere(Vector.point(0, -0.5, -7), Color.red, 1.5));
	    shapesScene.add(new Sphere(Vector.point(0, -0.5, -4.6), Color.white, 0.3));
	    shapesScene.add(new Sphere(Vector.point(-2, -0.5, -5), Color.green, 0.5));
	    shapesScene.add(new Sphere(Vector.point(2, -0.5, -5), Color.green, 0.5));
	    shapesScene.add(new Sphere(Vector.point(0, -0.5, -2), Color.blue, 0.5));
	    shapesScene.add(new Sphere(Vector.point(-0.5, -0.5, -1.7), Color.white, 0.2));
	    shapesScene.add(new Sphere(Vector.point(0.5, -0.5, -1.7), Color.white, 0.2));
        Group groupScene = new Group(shapesScene);
        Raytracer rt = new Raytracer(groupScene, width, height, filenameScene, sampleFrequence, backgroundColorScene);
        Image imageScene = rt.raytrace();
		imageScene.write(filenameScene);
		System.out.println("Wrote image: " + filenameScene);
    }
}