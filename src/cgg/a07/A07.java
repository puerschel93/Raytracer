package cgg.a07;

import cgtools.*;

import java.util.ArrayList;
import java.util.Collections;

import cgg.Image;
import cgg.a07.*;

public class A07 {
	
    static double scatter = 0.15;
	
	public static void main(String[] args) {
        final int width = 1200;
        final int height = 700;
        int sampleFrequence = 27;
	    final String filename = "doc/a07-2-4.png";
	    System.out.println("CREATING:\t" + filename);
	        

		Lochkamera lk = new Lochkamera(Math.PI/2, width, height, Vector.direction(15, -25, 0), Matrix.translation(-3,-0.5, 3));
//		Lochkamera lk = new Lochkamera(Math.PI/2, width, height, Vector.direction(0, 0, 0), Matrix.translation(0, 0, 0));
		
	    ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Background(Color.black )); 
        shapes.add(new Plane(Vector.direction(0, 1.8, 0), Vector.point(0.0, -1, 0.0), Double.MAX_VALUE, Color.white, 's'));			// FLOOR
        shapes.add(new Plane(Vector.direction(0, -180, 0), Vector.point(0.0, 3.5, 0.0), Double.MAX_VALUE, Color.white, 's'));			// FLOOR
        
        int numberOfSpheres = 21;
        double initAngleAdder = -30;
        char mat = 'c';
        for(double y = -0.85; y <= 3.65; y += 0.3) {
    		mat = 'c';
    		Color c = Color.beige;
        	for(double angle = 0; angle <= 360; angle+=(360 / numberOfSpheres)) {
        		if((angle <= 45 && angle >= 0) || (angle >= 315 && angle <= 360)) continue;
        	    double x = Math.sin(Math.toRadians(angle + initAngleAdder));
        	    double z = -10 + 8 + Math.cos(Math.toRadians(angle + initAngleAdder));
        	    shapes.add(new Sphere(Vector.point(x , y, z), c, 0.15, mat, scatter));
        	}
        	initAngleAdder += 5;
        }
        for(int x = -4; x <= 4; x+=2) {
        	if(x == 0) {
        		shapes.add(new Cylinder(Vector.point(x, -1, -2), Color.white, 0.4, 4.5, 's', 0.1));
        	} else if(x < 0) {
        		shapes.add(new Cylinder(Vector.point(x, -1, -2), Color.frog, 0.4, 4.5, 's', 0.1));
        	} else if(x > 0) {
        		shapes.add(new Cylinder(Vector.point(x, -1, -2), Color.frog, 0.4, 4.5, 's', 0.1));
        	}
        }
	    Group group = new Group(shapes);
	    
	    Raytracer rt = new Raytracer(group, width, height, filename, sampleFrequence, lk);
	    Image image = rt.raytrace();
		image.write(filename);
		System.out.println("Wrote image: " + filename);
    }
 
	public static ArrayList<Shape> generateRandomBalls(int numberOfBalls){
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Background(Color.white)); 
        int groundHeight = 1;
        shapes.add((Shape) new Plane(Vector.direction(0, 1.8, 0), Vector.point(0.0, -groundHeight, 0.0), Double.MAX_VALUE, Color.gray, 'd'));
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
		charList.add('s');
		Collections.shuffle(charList);
		return charList.get(0);
	}
	
	public static Color returnRandomColor() {
        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.orange);
        colorList.add(Color.beige);
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