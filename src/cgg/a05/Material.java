package cgg.a05;

import cgtools.*;

public interface Material {

	Color emission();
	Color albedo();
	Ray scatteredRay(Ray r, Hit h);
	
}
