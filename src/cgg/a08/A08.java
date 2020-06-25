package cgg.a08;

import cgtools.*;

import java.util.ArrayList;
import java.util.Collections;

import cgg.Image;
import cgg.a08.*;

public class A08 {
	    static double scatter = 0.25;
	    static final int width = 1200/2;
	    static final int height = 700/2;
	    static int sampleFrequence = 30;
	    static final String filename = "doc/a08-2.png";
	
	public static void main(String[] args) throws InterruptedException {
	    System.out.println("CREATING:\t" + filename);
	        

		Lochkamera lk = new Lochkamera(Math.PI/2, width, height, Vector.direction(-90, 0, 0), Matrix.translation(0, 10, 0));
//		Lochkamera lk = new Lochkamera(Math.PI/2, width, height, Vector.direction(0, 0, 0), Matrix.translation(0, 0, 0));

		ArrayList<Shape> base = new ArrayList<>();
        ArrayList<Shape> all = new ArrayList<>();
        ArrayList<Shape> allFigures = new ArrayList<>();
        base.add(new Background(Color.white )); 
        base.add(new Plane(Vector.direction(0, 1.8, 0), Vector.point(0.0, -1, 0.0), Double.MAX_VALUE, Color.gray, 'd'));			// FLOOR

    	ArrayList<Shape> figures = new ArrayList<>();
    	for(double z = -3; z <= 3; z++) {
	    	for(double x = -3; x <= 3; x++) {
	    		if(z == -3 || z == 3 || x == 3 || x == -3) {
			    	figures.add(new Sphere(Vector.point(x, -0.5, z), returnRandomColor(), 0.5, 'd', scatter));
	    		}
	    	}
    	}
    	figures.add(new Sphere(Vector.point(0, -1, 0), Color.beige, 0.2, 'p', scatter));
    	for(double y = 0; y <= 12; y++) {
    		allFigures.add(new Group(figures, new Transformation(Matrix.translation(0, y, 0))));
    	}

    	allFigures.add(new Group(figures, new Transformation(Matrix.translation(0,0,0))));
        all.add(new Group(base, new Transformation(Matrix.translation(0, 0, 0))));
        all.add(new Group(allFigures, new Transformation(Matrix.translation(0, 0, 0))));

        Group g_all = new Group(all, new Transformation(Matrix.translation(0,0,0)));
	    
//	    Raytracer rt = new Raytracer(g_all, width, height, sampleFrequence, lk);
// 		IMPLEMENTED MULTI THREADING FOR ASSIGNMENT 09
	    Image image = accelerator(g_all, lk);
		image.write(filename);
		System.out.println("Wrote image: " + filename);
    }
	
	public static Image accelerator(Group scene, Lochkamera lk) throws InterruptedException {
		int cores = Runtime.getRuntime().availableProcessors();
//		Image image = new Image(width, height, 2.2);
		Thread[] threads = new Thread[cores];
		Image[] images = new Image[cores];
		for (int i = 0; i != cores; i++) {
			final int core = i;
			Raytracer rt = new Raytracer(scene, width / cores, height, sampleFrequence, lk, i);
			threads[i] = new Thread() {
				public void run() {
					images[core] = rt.raytrace();
				}
			};
			threads[i].start();
		}
		
		for(Thread t: threads) {
			t.join();
		}
		
		Image fimage = new Image(width, height, 2.2);
		
		for(int i = 0; i < images.length; i++) {
			for(int x = 0; x < width / cores; x++) {
				for(int y = 0; y < height; y++) {
					Color c = i <= 1 ? Color.black : Color.blue;
					fimage.setPixel(x + (i * (width / cores)), y, images[i].getPixelColor(x, y));
				}
			}
		}
		
		return fimage;
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
//		charList.add('s');
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