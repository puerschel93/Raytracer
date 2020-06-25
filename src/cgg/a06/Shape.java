package cgg.a06;

public interface Shape {

	public Hit intersect(Ray r);
	
	public Material getMaterial();
	
}
