package primitives;

import java.util.Arrays;
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

public class pointD3 extends pointD2 {
	private Coordinate z;

// ***************** Constructors ********************** //

	public pointD3(Coordinate x,Coordinate y,Coordinate z) {
		super(x,y);
		this.z=new Coordinate(z);
	}
	public pointD3(double x,double y,double z) {
		super(new Coordinate(x),new Coordinate(y));
		this.z=new Coordinate(z);
	}
	public  pointD3(pointD3 p){
		super(p);
		this.z=new Coordinate(p.z);
	}
	public pointD3() {
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
		pointD3 pointD3 = (pointD3) o;
		return Objects.equals(z, pointD3.z) && super.equals(o);
	}

	@Override
	public String toString() {
		return "pointD3{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

	// ***************** Administration  ******************** //


	public vector substract(pointD3 p){
		return new vector(this.ReturnSubbstractVector(p));
	}

	private vector ReturnSubbstractVector(pointD3 p){
		return new vector(
				new pointD3(
						this.getX().subtract(p.getX()),
						this.getY().subtract(p.getY()),
						this.getZ().subtract(p.getZ())
				)
		);
	}
	@Override
	public pointD3 add(vector v){
		return (new pointD3(
				this.getX().add(v.getPoint().getX()),
				this.getY().add(v.getPoint().getY()),
				this.getZ().add(v.getPoint().getZ())
		));
	}

	public double powDistance(pointD3 p){
		Coordinate x = (this.getX().subtract(p.getX())).multiply(this.getX().subtract(p.getX()));
		Coordinate y = (this.getY().subtract(p.getY())).multiply(this.getY().subtract(p.getY()));
		Coordinate z = (this.getZ().subtract(p.getZ())).multiply(this.getZ().subtract(p.getZ()));
		return x.add(y.add(z)).get();
	}

	public  double distance(pointD3 p){
		return Math.sqrt(this.powDistance(p));
	}


	public boolean isZero() {
		if(getX().isZero() && getY().isZero() && getZ().isZero())return true;
		return false;
	}
}
