package cgg.a01;

import cgtools.*;
import static cgtools.Color.*;
import cgg.Image;

import cgg.Image;

public class A01 {

    public static void main(String[] args) {
        final int width = 160*5;
        final int height = 90*5;
        final int cubeSize = GCD(width, height) / 2;
        double gamma = 2.2;
        String filename = "doc/a01-image.png";

        Image image = new Image(width, height, gamma);
        
        ConstantColor primary = new ConstantColor(gray);
        
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, primary.getColor(x, y));
            }
        }

        image.write(filename);
        System.out.println("Wrote image: " + filename);
        
        // COLOREDSQUARE
        
        ColoredSquare squareCenter = new ColoredSquare(black, red, height, width);
        filename = "doc/a01-square.png";
        
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, squareCenter.getColor(x, y));
            }
        }
        
        image.write(filename);
        System.out.println("Wrote image: " + filename);
        
        // CHECKERBOARD
        
        CheckerBoard chess = new CheckerBoard(gray, black, green, height, width);
        filename = "doc/a01-checkered-background.png";
        
        for(int x = 0; x != width; x++) {
        	for(int y = 0; y != height; y++) {
        		image.setPixel(x, y, chess.getColor(x, y));
        	}
        }
        
        image.write(filename);
        System.out.println("Wrote image: " + filename); 
        
    }
    
    // Größter gemeinsamer Teiler finden für rechteckige Quadrate
    public static int GCD(int a, int b) {
    	   if (b==0) {
    		   return a;
    	   } else {
    		   return GCD(b,a%b);
    	   }
    	}
}
