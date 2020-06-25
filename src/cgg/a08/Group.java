package cgg.a08;

import java.util.ArrayList;

public class Group implements Shape{
	private ArrayList<Shape> group = new ArrayList<>();
	public Transformation transformation = null;
	
	public Group(ArrayList<Shape> group, Transformation transformation) {
		this.group = group;
		this.transformation = transformation;
	}

	@Override
	public Hit intersect(Ray r) {
		Hit h = null;
		r = transformation.rayTransformation(r);
		for (Shape s : group) {
			Hit hit = s.intersect(r);
			if (hit == null) {
				continue;
			} else {
				if(h == null) {
					h = hit;
				} else {
					if(hit.getT() < h.getT() ) {
						h = hit;
					}
				}
			}
		}
		h = h != null ? transformation.hitTransformation(h) : null;
		return h;
	}

	@Override
	public String toString() {
		return "Group [group=" + group + "]";
	}
	
	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return null;
	}
}