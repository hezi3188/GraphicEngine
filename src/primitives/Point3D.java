package primitives;

import java.util.Objects;


/**
 * Javadoc formatted documentation
 */




/**
 * Javadoc formatted documentation
 */


/**
 * Javadoc formatted documentation
 */


// ***************** Operations ******************** //

public class Point3D extends pointD2 {
	private Coordinate z;

// ***************** Constructors ********************** //

	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x,y);
		this.z=new Coordinate(z);
	}
	public Point3D(double x, double y, double z) {
		super(new Coordinate(x),new Coordinate(y));
		this.z=new Coordinate(z);
	}
	public Point3D(Point3D p){
		super(p);
		this.z=new Coordinate(p.z);
	}
	public Point3D() {
		super();
		this.z = new Coordinate(Coordinate.ZERO);
	}

// ***************** Getters/Setters ********************** //
	public Coordinate getZ() {
		return new Coordinate(z);
	}

	public void setZ(Coordinate z) {
		this.z = new Coordinate(z);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Point3D Point3D = (Point3D) o;
		return Objects.equals(z, Point3D.z) && super.equals(o);
	}

	@Override
	public String toString() {
		return "Point3D{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

	// ***************** Administration  ******************** //


	public vector substract(Point3D p){
		return new vector(this.ReturnSubbstractVector(p));
	}

	private vector ReturnSubbstractVector(Point3D p){
		return new vector(
				new Point3D(
						this.getX().subtract(p.getX()),
						this.getY().subtract(p.getY()),
						this.getZ().subtract(p.getZ())
				)
		);
	}
	@Override
	public Point3D add(vector v){
		return (new Point3D(
				this.getX().add(v.getPoint().getX()),
				this.getY().add(v.getPoint().getY()),
				this.getZ().add(v.getPoint().getZ())
		));
	}

	public double powDistance(Point3D p){
		Coordinate x = (this.getX().subtract(p.getX())).multiply(this.getX().subtract(p.getX()));
		Coordinate y = (this.getY().subtract(p.getY())).multiply(this.getY().subtract(p.getY()));
		Coordinate z = (this.getZ().subtract(p.getZ())).multiply(this.getZ().subtract(p.getZ()));
		return x.add(y.add(z)).get();
	}

	public  double distance(Point3D p){
		return Math.sqrt(this.powDistance(p));
	}


	public boolean isZero() {
		if(getX().isZero() && getY().isZero() && getZ().isZero())return true;
		return false;
	}
}
