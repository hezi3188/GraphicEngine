package primitives;

import java.util.Objects;

public class ray extends vector {
	pointD3 start;
/**
 * Javadoc formatted documentation
 */
// ***************** Constructors ********************** //
	public ray(pointD3 strat, vector vec) {
		super(vec);
		this.start = new pointD3(strat);
	}

	public ray (ray r){
		super(r);
		this.start = new pointD3(r.getStart());
	}
	public ray(){
		super();
		start = new pointD3();
	}
// ***************** Getters/Setters ********************** //
	public pointD3 getStart() {
		return new pointD3(start);
	}

	public void setStart(pointD3 start) {
		this.start = new pointD3(start) ;
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
