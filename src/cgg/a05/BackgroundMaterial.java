package cgg.a05;

import cgtools.Color;

public class BackgroundMaterial implements Material{
	
	@Override
	public Color emission() {
		return Color.white;
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
