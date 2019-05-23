package primitives;

import java.util.Arrays;
import java.util.Objects;

public class pointD2 {
		protected Coordinate x;
		protected Coordinate y;

		// ***************** Constructors ********************** //
		public pointD2(Coordinate x, Coordinate y) {
			this.x=new Coordinate(x);
			this.y=new Coordinate(y);
		}

		public pointD2() {
			setX(new Coordinate(Coordinate.ZERO)) ;
			setY(new Coordinate(Coordinate.ZERO));
		}
		public pointD2(pointD2 p){
			setX(p.getX());
			setY(p.getY());
		}

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
		public pointD2 substract(pointD2 p){
			return new pointD2(this.getX().subtract(p.getY()),this.getY().subtract(p.getY()));//I think that there is mistake
		}
		public pointD2 add(vector v){
			return new pointD2(
					this.getX().add(v.getPoint().getX()),
					this.getY().add(v.getPoint().getY()));
		}
		public double distance(pointD2 p){
			return Math.sqrt(this.powDistance(p));
		}
		public double powDistance(pointD2 p){
			Coordinate x = (this.getX().subtract(p.getX())).multiply(this.getX().subtract(p.getX()));
			Coordinate y = (this.getY().subtract(p.getY())).multiply(this.getY().subtract(p.getY()));
			return x.add(y).get();
		}

}