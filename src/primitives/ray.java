package primitives;

/**
 * Vector with start point 3d
 */
public class ray extends vector {
	Point3D start;

// ***************** Constructors ********************** //

	/**
	 * Constractor that get vector an start point
	 * @param strat
	 * @param vec
	 */
	public ray(Point3D strat, vector vec) {
		super(vec);
		this.start = new Point3D(strat);
	}

	/**
	 * Constructor that get 3d point start and the end
	 * is 3d point to new ray
	 * @param strat Start
	 * @param End End
	 */
	public ray(Point3D strat, Point3D End) {
		super(End.substract(strat).normalize());
		this.start = new Point3D(strat);
	}

	/**
	 * Copy constructor
	 * @param r Other ray
	 */
	public ray (ray r){
		super(r);
		this.start = new Point3D(r.getStart());
	}

	/**
	 * Deafult constructor
	 */
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

	/**
	 * Make ray to vector
	 * @return Return the ray that represents as vector
	 */
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
