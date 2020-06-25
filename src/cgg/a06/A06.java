package cgg.a06;

import cgtools.*;

import java.util.ArrayList;
import java.util.Collections;

import cgg.Image;
import cgg.a06.Background;
import cgg.a06.Plane;
import cgg.a06.*;

public class A06 {
	
    static double scatter = 0.1;
	
	public static void main(String[] args) {
        final int width = 1600/2;
        final int height = 900/2;
        int sampleFrequence = 5;
	    final String filename = "doc/a06-mirrors-glass-cube.png";
	    System.out.println("CREATING:\t" + filename);
	        
	    ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Background(Color.white)); 
        shapes.add(new Plane(Vector.direction(0, 1.8, 0), Vector.point(0.0, -1, 0.0), Double.MAX_VALUE));
	    shapes.add(new Sphere(Vector.point(-1 , -0.5, -4), Color.beige, 0.5, 'c', scatter));
	    shapes.add(new Sphere(Vector.point(1 , -0.5, -4), Color.beige, 0.5, 'c', scatter));
	    Group group = new Group(shapes);
	    Raytracer rt = new Raytracer(group, width, height, filename, sampleFrequence);
	    Image image = rt.raytrace();
		image.write(filename);
		System.out.println("Wrote image: " + filename);
    }
 
	public static ArrayList<Shape> generateRandomBalls(int numberOfBalls){
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Background(Color.white)); 
        int groundHeight = 1;
        shapes.add((Shape) new Plane(Vector.direction(0, 1.8, 0), Vector.point(0.0, -groundHeight, 0.0), Double.MAX_VALUE));
        for(int i = 0; i < numberOfBalls; i++) {
//        	double randomSize = Random.random(0.2, 0.6);
        	double randomSize = Random.random();
        	double randomX = getRandomValidX();
        	double randomZ = getRandomValidZ(randomSize);
        	shapes.add(new Sphere(Vector.point(randomX, ( -groundHeight + randomSize), randomZ), returnRandomColor(), randomSize, returnRandomMaterial(), scatter));
        }
        return shapes;
	}
	
	public static char returnRandomMaterial() {
		ArrayList<Character> charList = new ArrayList<>();
		charList.add('p');
		charList.add('c');
		charList.add('d');
		charList.add('g');
		Collections.shuffle(charList);
		return charList.get(0);
	}
	
	public static Color returnRandomColor() {
        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.lightOrange);
        colorList.add(Color.orange);
        colorList.add(Color.beige);
        colorList.add(Color.lightTurqois);
        colorList.add(Color.turqois);
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