package cgg.a01;

import cgg.Image;
import cgtools.Color;

class CheckerBoard {

	// COLORS
	Color color1;
	Color color2;
	Color color3;
	
	// HIGHTS & WIDTHS
	int imageHeight;
	int imageWidth;
	
	// squareParameters
	int squareWidth;
	int squareHeight;
	int centerY;
	int centerX;
	
	int chessSize;
	
	CheckerBoard(Color color1, Color color2, Color color3, int height, int width){
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.imageHeight = height;
		this.imageWidth = width;
    	this.squareHeight = this.imageHeight / 2;
    	this.squareWidth = this.imageHeight / 2;
    	this.centerX = this.imageWidth / 2;
    	this.centerY = this.imageHeight / 2;
		this.chessSize = this.imageWidth / 10;
	}
	
	Color getColor(double x, double y) {
		if((x >= centerX - squareWidth / 2 && x <= centerX + squareWidth / 2) && (y >= centerY - squareHeight / 2 && y <= centerY + squareHeight / 2)) {
			return color3;
		} else {
			if((x % chessSize < chessSize / 2 && y % chessSize < chessSize / 2) || (x % chessSize >= chessSize / 2 && y % chessSize >= chessSize / 2)) {
				return color2;
			} else {
				return color1;
			}
		}
	}
}