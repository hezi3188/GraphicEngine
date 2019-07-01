package primitives;

import java.util.Objects;

/**
 * Poind with 3 coordinates
 */

public class Point3D extends pointD2 {
	private Coordinate z;

// ***************** Constructors ********************** //

	/**
	 * Constructor that get 3 coordinate
	 * @param x Coordinate x
	 * @param y Coordinate Y
	 * @param z Coordinate z
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x,y);
		this.z=new Coordinate(z);
	}

	/**
	 * Constructor that get 3 doubles
	 * @param x Double x
	 * @param y Double Y
	 * @param z Double z
	 */
	public Point3D(double x, double y, double z) {
		super(new Coordinate(x),new Coordinate(y));
		this.z=new Coordinate(z);
	}

	/**
	 * Copy constructor
	 * @param p Other pointD3
	 */
	public Point3D(Point3D p){
		super(p);
		this.z=new Coordinate(p.z);
	}

	/**
	 * Deafult constructor
	 */
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
		if(this.getX().get() == ((Point3D)o).getX().get() && this.getY().get() == ((Point3D)o).getY().get() && this.getZ().get() == ((Point3D)o).getZ().get()) return true;
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

	/**
	 * Substract pointD3 from pointD3
	 * and return the vector between them
	 * @param p The poindD3
	 * @return Substruct vector
	 */
	public vector substract(Point3D p){
		return new vector(this.ReturnSubbstractVector(p));
	}

	/**
	 * Help function to substract
	 * @param p pointD3
	 * @return Substruct vector
	 */
	private vector ReturnSubbstractVector(Point3D p){
		return new vector(
				new Point3D(
						this.getX().subtract(p.getX()),
						this.getY().subtract(p.getY()),
						this.getZ().subtract(p.getZ())
				)
		);
	}

	/**
	 * Add vector to PointD3
	 * @param v Vector
	 * @return Return the pointD3 of the adding
	 */
	@Override
	public Point3D add(vector v){
		return (new Point3D(
				this.getX().add(v.getPoint().getX()),
				this.getY().add(v.getPoint().getY()),
				this.getZ().add(v.getPoint().getZ())
		));
	}

	/**
	 * The pow distance between
	 * point to own point
	 * @param p The other pointD3
	 * @return The pow distance
	 */
	public double powDistance(Point3D p){
		Coordinate x = (this.getX().subtract(p.getX())).multiply(this.getX().subtract(p.getX()));
		Coordinate y = (this.getY().subtract(p.getY())).multiply(this.getY().subtract(p.getY()));
		Coordinate z = (this.getZ().subtract(p.getZ())).multiply(this.getZ().subtract(p.getZ()));
		return x.add(y.add(z)).get();
	}

	/**\
	 * The distance between
	 * p to own point
	 * @param p The other point
	 * @return The distance
	 */
	public  double distance(Point3D p){
		return Math.sqrt(this.powDistance(p));
	}

	/**
	 * Check if the point is zero
	 * @return Return true if the point is zero
	 */
	public boolean isZero() {
		if(getX().isZero() && getY().isZero() && getZ().isZero())return true;
		return false;
	}
}
