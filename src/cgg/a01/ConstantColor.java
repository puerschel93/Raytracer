package cgg.a01;

import cgtools.Color;

class ConstantColor {
	Color color;
	
	ConstantColor(Color color) {
		this.color = color;
	}
            
	Color getColor(double x, double y) {
		return color;
	}
}