package cgg.a01;

import cgg.Image;
import cgtools.Color;

class ColoredSquare {
	
			// Background Color
            Color background;
            
            // Square Color
            Color square;

            // image Height
            int imageWidth;
            int imageHeight;
            
            // squareSize - calculated by Imagesize
            int squareWidth;
            int squareHeight;
            
            // Mittelpunkte
            int centerX;
            int centerY;
            
            ColoredSquare(Color background, Color square, int height, int width) {
            	this.background = background;
            	this.square = square;
            	this.imageHeight = height;
            	this.imageWidth = width;
            	this.squareHeight = this.imageHeight / 2;
            	this.squareWidth = this.imageHeight / 2;
            	this.centerX = width / 2;
            	this.centerY = height / 2;
            }

            Color getColor(double x, double y) {
            	if((x >= centerX - squareWidth / 2 && x <= centerX + squareWidth / 2) && (y >= centerY - squareHeight / 2 && y <= centerY + squareHeight / 2)) {
            		return square;
            	} else {
            		return background;
            	}
            }
        }