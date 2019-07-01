package primitives;
import static primitives.Util.*;

/**
 * Description of number on axis
 */


public final class Coordinate {
	//private static final double EPSILON = 0.0000001;
	protected double _coord;

	public static Coordinate ZERO = new Coordinate(0.0);
	
	/********** Constructors ***********/
	/**
	 * deafult constructor
	 * @param coord The coordinate
	 */
	public Coordinate(double coord) {
		// if it too close to zero make it zero
		_coord = alignZero(coord);
	}

	/**
	 * Copy constructor
	 * @param other Other coordinate
	 */
	public Coordinate(Coordinate other) {
		_coord = other._coord;
	}

	/************** Getters/Setters *******/
    /**
     *
     * @return The coordinate
     */
	public double get() {
		return _coord;
	}

	/*************** Admin *****************/
	@Override
    /**
     * Equals objects
     */
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Coordinate)) return false;
		return usubtract(_coord, ((Coordinate)obj)._coord) == 0.0;
	}

	@Override
    /**
     *
     */
	public String toString() {
		return "" + _coord;
	}

	/************** Operations ***************/
    /**
     *
     * @param other other coordinate
     * @return substract coordinate
     */
	public Coordinate subtract(Coordinate other) {
		return new Coordinate(usubtract(_coord, other._coord));
	}

    /**
     *add coordinate
     * @param other
     * @return this coordinate+other
     */
	public Coordinate add(Coordinate other) {
		return new Coordinate(uadd(_coord, other._coord));
	}

    /**
     * scle coordinates
     * @param num the scalar
     * @return The result of scular(double)
     */
	public Coordinate scale(double num) {
		return new Coordinate(uscale(_coord, num));
	}

    /**
     * Mult coordinates
     * @param other The other coordinate
     * @return The mult coordinate
     */
	public Coordinate multiply(Coordinate other) {
		return new Coordinate(uscale(_coord, other._coord));
	}

    /**
     * Check if the coordinate is zero
     * @return Return true if the coordinate is true.
     */
	public boolean isZero() {
		return Util.isZero(_coord);
	}
}
