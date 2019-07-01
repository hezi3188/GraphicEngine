package primitives;

import java.util.Arrays;
import java.util.Objects;

/**
 * Point With 2 coordinates
 */
public class pointD2 {
		protected Coordinate x;
		protected Coordinate y;

		// ***************** Constructors ********************** //

	/**
	 * Constructor that get 2 coordinates
	 * @param x Coordinate x
	 * @param y Coordinate y
	 */
		public pointD2(Coordinate x, Coordinate y) {
			this.x=new Coordinate(x);
			this.y=new Coordinate(y);
		}

	/**
	 * Deafult constructor
	 */
		public pointD2() {
			setX(new Coordinate(Coordinate.ZERO)) ;
			setY(new Coordinate(Coordinate.ZERO));
		}

	/**
	 * Copy constructor
	 * @param p Other point
	 */
		public pointD2(pointD2 p){
			setX(p.getX());
			setY(p.getY());
		}

	/**
	 * Constructor that get 2 doubles
	 * @param x Double x
	 * @param y Double y
	 */
	public pointD2(double x, double y) {
			setX(new Coordinate(x));
			setY(new Coordinate(y));
	}

	// ***************** Operations ******************** //
	@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			pointD2 pointD2 = (pointD2) o;
			return Objects.equals(x, pointD2.x) &&
					Objects.equals(y, pointD2.y);
		}

		// ***************** Getters/Setters ********************** //
		public Coordinate getY() {
			return new Coordinate(y);
		}

		public void setY(Coordinate y) {
			this.y =new Coordinate(y);
		}

		public Coordinate getX() {
			return new Coordinate(x);
		}

		public void setX(Coordinate x) {
			this.x =new Coordinate(x);
		}

		@Override
		public String toString() {
			return "{" + x +',' + y +'}';
		}
		// ***************** Administration  ******************** //

	/**
	 * Substruct points
	 * @param p Other point
	 * @return The substruct point
	 */
		public pointD2 substract(pointD2 p){
			return new pointD2(this.getX().subtract(p.getY()),this.getY().subtract(p.getY()));//I think that there is mistake
		}

	/**
	 * Add vector to own point
	 * @param v
	 * @return Return new add point
	 */
		public pointD2 add(vector v){
			return new pointD2(
					this.getX().add(v.getPoint().getX()),
					this.getY().add(v.getPoint().getY()));
		}

	/**
	 * The distance point from point p
	 * @param p
	 * @return return the double between 2 points
	 */
		public double distance(pointD2 p){
			return Math.sqrt(this.powDistance(p));
		}

	/**
	 * The pow distance between 2 points
	 * @param p
	 * @return The double of 2 points
	 */
		public double powDistance(pointD2 p){
			Coordinate x = (this.getX().subtract(p.getX())).multiply(this.getX().subtract(p.getX()));
			Coordinate y = (this.getY().subtract(p.getY())).multiply(this.getY().subtract(p.getY()));
			return x.add(y).get();
		}

}