package cgg.a08;

import cgtools.Color;

public class BackgroundMaterial implements Material{
	
	Color color = null;
	
	public BackgroundMaterial(Color color) {
		this.color = color;
	}
	
	@Override
	public Color emission() {
		return color;
	}

	@Override
	public Color albedo() {
		return Color.black;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		return null;
	}
	
	

}
