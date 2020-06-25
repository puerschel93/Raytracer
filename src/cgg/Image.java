package cgg;

import cgtools.*;

public class Image {

	int width = 0;
	int height = 0;
	public double[] data = null;
	double gamma;
	
    public Image(int width, int height, double gamma) {
    	this.width = width;
    	this.height = height;
    	this.gamma = gamma;
    	this.data = new double[(width * height) * 3];
    }

    // AUFGABE 2.2
    // Math.pow(color.r, 1 / gamma)
    // Gammakorrektur: rgb-wert^(1/2.2) 
    public void setPixel(int x, int y, Color color) {
    	int indexStart = ((y * width) + x) * 3;
    	data[indexStart + 0] = Math.pow(color.r, 1 / gamma);
    	data[indexStart + 1] = Math.pow(color.g, 1 / gamma);
    	data[indexStart + 2] = Math.pow(color.b, 1 / gamma);
    }
    
    public Color getPixelColor(int x, int y) {
    	int indexStart = ((y * width) + x) * 3;
    	return new Color(data[indexStart + 0], data[indexStart + 1], data[indexStart + 2]);
    }
       
    public void write(String filename) {
        ImageWriter.write(filename, data, width, height);
    }

    public int getHeight(){
    	return this.height;
    }
    
    public int getWidth() {
    	return this.width;
    }
    
    public void setWidth(int width) {
    	this.width = width;
    }
    
    public void setGamma(double gamma) {
    	this.gamma = gamma;
    }
}
