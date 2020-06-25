package cgtools;
//
// Author: Henrik Tramberend <tramberend@beuth-hochschule.de>
//

import static cgtools.Vector.*;
import static cgtools.Matrix.*;


public class BoundingBox {

    // -----------------------------------------------------------------------------------
    // Please remove this dummy declaration of class Ray, and use your own implementation.
    class Ray {
        Point origin;
        Direction direction;
        double tmin;
        double tmax;

        Point pointAt(double t){
            return Vector.zero;
        };

        boolean contains(double t) {
            return false;
        }
    }
    // -----------------------------------------------------------------------------------

    final Point min, max;
    static public boolean enabled = true;
    static public long hits = 0;
    static public long misses = 0;

    static public void reset(boolean enable) {
        enabled = enable;
        hits = 0;
        misses = 0;
    }

    public BoundingBox() {
        min = point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        max = point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    public BoundingBox(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public BoundingBox extend(BoundingBox bb) {
        return new BoundingBox(point(Math.min(min.x, bb.min.x), Math.min(min.y, bb.min.y), Math.min(min.z, bb.min.z)),
                point(Math.max(max.x, bb.max.x), Math.max(max.y, bb.max.y), Math.max(max.z, bb.max.z)));
    }

    public BoundingBox extend(Point p) {
        return new BoundingBox(point(Math.min(min.x, p.x), Math.min(min.y, p.y), Math.min(min.z, p.z)),
                point(Math.max(max.x, p.x), Math.max(max.y, p.y), Math.max(max.z, p.z)));
    }

    public BoundingBox splitLeft() {
        Direction size2 = divide(subtract(max, min), 2);
        if (size2.x >= size2.y && size2.x >= size2.z) {
            return new BoundingBox(min, point(min.x + size2.x, max.y, max.z));
        } else if (size2.y >= size2.x && size2.y >= size2.z) {
            return new BoundingBox(min, point(max.x, min.y + size2.y, max.z));
        } else {
            return new BoundingBox(min, point(max.x, max.y, min.z + size2.z));
        }
    }

    public BoundingBox splitRight() {
        Direction size2 = divide(subtract(max, min), 2);
        if (size2.x >= size2.y && size2.x >= size2.z) {
            return new BoundingBox(point(min.x + size2.x, min.y, min.z), max);
        } else if (size2.y >= size2.x && size2.y >= size2.z) {
            return new BoundingBox(point(min.x, min.y + size2.y, min.z), max);
        } else {
            return new BoundingBox(point(min.x, min.y, min.z + size2.z), max);
        }
    }

    public BoundingBox transform(Matrix xform) {
        BoundingBox result = new BoundingBox();

        result = result.extend(multiply(xform, min));
        result = result.extend(multiply(xform, point(min.x, min.y, max.z)));
        result = result.extend(multiply(xform, point(min.x, max.y, min.z)));
        result = result.extend(multiply(xform, point(min.x, max.y, max.z)));
        result = result.extend(multiply(xform, point(max.x, min.y, min.z)));
        result = result.extend(multiply(xform, point(max.x, min.y, max.z)));
        result = result.extend(multiply(xform, point(max.x, max.y, min.z)));
        result = result.extend(multiply(xform, max));

        return result;
    }

    public boolean contains(Point v) {
        return min.x <= v.x && min.y <= v.y && min.z <= v.z && max.x >= v.x && max.y >= v.y && max.z >= v.z;
    }

    public boolean contains(BoundingBox bb) {
        return min.x <= bb.min.x && min.y <= bb.min.y && min.z <= bb.min.z && max.x >= bb.max.x && max.y >= bb.max.y
                && max.z >= bb.max.z;
    }

    //
    // Adapted from
    // https://tavianator.com/cgit/dimension.git/tree/libdimension/bvh/bvh.c
    //
    public boolean intersect(Ray ray) {
        if (!enabled) {
            hits++;
            return true;
        }

        if (this.contains(ray.pointAt(ray.tmin)))
            return true;
        if (this.contains(ray.pointAt(ray.tmax)))
            return true;

        double dix = 1.0 / ray.direction.x;
        double diy = 1.0 / ray.direction.y;
        double diz = 1.0 / ray.direction.z;

        double tx1 = (min.x - ray.origin.x) * dix;
        double tx2 = (max.x - ray.origin.x) * dix;

        double tmin = Math.min(tx1, tx2);
        double tmax = Math.max(tx1, tx2);

        double ty1 = (min.y - ray.origin.y) * diy;
        double ty2 = (max.y - ray.origin.y) * diy;

        tmin = Math.max(tmin, Math.min(ty1, ty2));
        tmax = Math.min(tmax, Math.max(ty1, ty2));

        double tz1 = (min.z - ray.origin.z) * diz;
        double tz2 = (max.z - ray.origin.z) * diz;

        tmin = Math.max(tmin, Math.min(tz1, tz2));
        tmax = Math.min(tmax, Math.max(tz1, tz2));

        if (tmax >= tmin && ray.contains(tmin)) {
            hits++;
            return true;
        } else {
            misses++;
            return false;
        }
    }

    public Vector size() {
        return subtract(max, min);
    }

//    public Point center() {
//        return interplolate(max, min, 2);
//    }

//    public BoundingBox scale(double factor) {
//        Point c = center();
//        return new BoundingBox(add(multiply(factor, subtract(min, c)), c), add(multiply(factor, subtract(max, c)), c));
//    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BoundingBox))
            return false;
        if (o == this)
            return true;
        BoundingBox v = (BoundingBox) o;
        return min.equals(v.min) && max.equals(v.max);
    }

    @Override
    public String toString() {
        return String.format("(BBox: %s %s)", min, max);
    }
}
