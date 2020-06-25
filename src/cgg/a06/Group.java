package cgg.a06;

import java.util.ArrayList;

public class Group implements Shape{
	private ArrayList<Shape> group = new ArrayList<>();
	
	public Group(ArrayList<Shape> group) {
		this.group = group;
	}

	@Override
	public Hit intersect(Ray r) {
		Hit h = null;
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