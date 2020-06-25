import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cgg.a03.Lochkamera;
import cgg.a03.Sphere;
import cgtools.Color;
import cgtools.Vector;

class SphereTest {

	Sphere sphere = new Sphere(Vector.point(0, 0, -3), Color.blue, 3);
    Lochkamera lk = new Lochkamera(Math.PI / 2, 1600, 900);
    
	@Test
	void test() {
		System.out.println(sphere.getColor(800, 450, lk, sphere));
	}

}
