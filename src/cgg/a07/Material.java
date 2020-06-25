package cgg.a07;

import cgtools.*;

public interface Material {

	Color emission();
	Color albedo();
	Ray scatteredRay(Ray r, Hit h);
	
}
