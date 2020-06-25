package cgg.a02;

import cgtools.*;
import static cgtools.Color.*;
import cgg.Image;

import cgg.Image;

public class A02 {
	public static void main(String[] args) {
        final int width = 160*10;
        final int height = 90*10;
        double gamma = 2.2;
        final String filename = "doc/a02-discs.png";
        final String filenameSample = "doc/a02-supersampling.png";
        Image image = new Image(width, height, gamma);
        Image sampled = new Image(width, height, gamma);
        
        ColoredCircles cc = new ColoredCircles(800, width, height);
        
	   for (int x = 0; x != width; x++) {
		   for (int y = 0; y != height; y++) {
			   image.setPixel(x, y, cc.isPointInCircle(x, y));
			   sampled.setPixel(x, y, cc.stratifiedSampling(x, y, 100));
	       }
	   	}       
       image.write(filename); 
       sampled.write(filenameSample);
       System.out.println("Wrote image: " + filename);
       System.out.println("Wrote image: " + filenameSample);
    }
}