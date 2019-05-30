package primitives;

import java.util.Objects;

public class vector {
	Point3D point;
// ***************** Constructors ********************** //

	public vector() {
		this.point = new Point3D();
	}
	public vector(vector vec){
		this.point = new Point3D(vec.getPoint()) ;
	}
	public vector(Point3D point) {
		this.point = new Point3D(point) ;
	}
	public vector(double x, double y,double z) {
		this.point = new Point3D(x,y,z) ;
	}

	public Point3D getPoint() {
		return new Point3D(point) ;
	}
// ***************** Getters/Setters ********************** //

	public void setPoint(Point3D point) {
		this.point = new Point3D(point);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        vector vector = (vector) o;
        return point.equals(vector.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
	public String toString() {
		return "vector [point=" + point + "]";
	}

	// ***************** Administration  ******************** //
	public vector add(vector vec){
		return new vector(new Point3D(this.getPoint().add(vec)));
	}
	public vector substract(vector vec){
		return  new vector(
					new Point3D(
							this.getPoint().getX().subtract(
									new Coordinate(vec.getPoint().getX())
							),
							this.getPoint().getY().subtract(
									new Coordinate(vec.getPoint().getY())
							),
							this.getPoint().getZ().subtract(
									new Coordinate(vec.getPoint().getZ())
							)
					)
		);
	}
	public double dotProduct(vector vec){
		Coordinate tempX=this.getPoint().getX().multiply(
								vec.getPoint().getX());

        Coordinate tempY=this.getPoint().getY().multiply(
                vec.getPoint().getY());

        Coordinate tempZ=this.getPoint().getZ().multiply(
                vec.getPoint().getZ());

		return new Coordinate(
                tempX.add(
                        tempY.add(
                                tempZ)
                )
        ).get();
	}
	public vector multScalar(double scalar){
		Coordinate Scal = new Coordinate(scalar);
		return  new vector(
				new Point3D(
						this.getPoint().getX().multiply(Scal),
						this.getPoint().getY().multiply(Scal),
						this.getPoint().getZ().multiply(Scal)
				)
		);
	}

	public static Boolean isZero(vector vec){
		if(vec.getPoint().isZero()) return true;
		return false;
	}

	public double length (){
		return  this.getPoint().distance(new Point3D());
	}
	public vector crossProduct(vector vec){
		Coordinate x1,y1,z1,x2,y2,z2,newX,newY,newZ;
		x1=new Coordinate(this.getPoint().getX());
		y1=new Coordinate(this.getPoint().getY());
		z1=new Coordinate(this.getPoint().getZ());
		x2=new Coordinate(vec.getPoint().getX());
		y2=new Coordinate(vec.getPoint().getY());
		z2=new Coordinate(vec.getPoint().getZ());
		newX=new Coordinate(
				(y1.multiply(z2)).subtract(z1.multiply(y2))
		);
		newY=new Coordinate(
				(z1.multiply(x2)).subtract(x1.multiply(z2))
		);
		newZ=new Coordinate(
				(x1.multiply(y2)).subtract(y1.multiply(x2))
		);
		return  new vector(
				new Point3D(newX,newY,newZ)
		);
	}
	public vector normalize(){
		double normal=length();
		Coordinate x,y,z;
		x=new Coordinate(this.getPoint().getX());
		y=new Coordinate(this.getPoint().getY());
		z=new Coordinate(this.getPoint().getZ());
		return new vector(
				new Point3D(
						x.scale(1/normal),
						y.scale(1/normal),
						z.scale(1/normal)
				)
		);
	}

}
