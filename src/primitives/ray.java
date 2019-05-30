package primitives;

public class ray extends vector {
	Point3D start;
/**
 * Javadoc formatted documentation
 */
// ***************** Constructors ********************** //
	public ray(Point3D strat, vector vec) {
		super(vec);
		this.start = new Point3D(strat);
	}

	public ray(Point3D strat, Point3D End) {
		super(End.substract(strat).normalize());
		this.start = new Point3D(strat);
	}

	public ray (ray r){
		super(r);
		this.start = new Point3D(r.getStart());
	}
	public ray(){
		super();
		start = new Point3D();
	}
// ***************** Getters/Setters ********************** //
	public Point3D getStart() {
		return new Point3D(start);
	}

	public void setStart(Point3D start) {
		this.start = new Point3D(start) ;
	}

// ***************** Administration  ******************** //

	@Override
	public String toString() {
		return "ray{" +
				"start=" + start +
				", point=" + point +
				'}';
	}

// ***************** Operations ******************** //
	private vector RayToVec(){
		return new vector(start.add((vector)this));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ray ray = (ray) o;
		return start.equals(ray.start) && super.equals(o);
	}

}
