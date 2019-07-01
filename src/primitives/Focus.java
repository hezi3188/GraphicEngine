package primitives;

/**
 * Focus on the image
 */
public class Focus {
    //Fields
    public static boolean enable = false;
    double distance;
    double eyeOpen;

    //constructors

    /**
     * constructor that get the distanse of fucus
     * and the eyeOpen
     * @param distance The distance between the
     *                 plane to the point of the focus
     * @param eyeOpen The distance between the rays when
     *                they out from camera
     */
    public Focus(double distance, double eyeOpen) {
        this.distance = distance;
        this.eyeOpen = eyeOpen;
    }

    /**
     * Copy constructor
     * @param f Other focus
     */
    public Focus(Focus f) {
        this.distance = f.getDistance();
        this.eyeOpen = f.getEyeOpen();
    }

    /**
     * Deafult constructor
     */
    public Focus() {
        this.distance = 0;
        this.eyeOpen = 0;
    }
    @Override
    /**
     *
     */
    public String toString() {
        return "Focus{" +
                "distance=" + distance +
                ", eyeOpen=" + eyeOpen +
                '}';
    }
    //getter and setter

    /**
     *
     * @return The distance between the
     * plane to the point of the focus
     */
    public double getDistance() {
        return distance;
    }

    /**
     *
     * @param distance The distance between the
     *                 plane to the point of the focus
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     *
     * @return The distance between the rays when
     * they out from camera
     */
    public double getEyeOpen() {
        return eyeOpen;
    }

    /**
     *
     * @param eyeOpen
     */
    public void setEyeOpen(double eyeOpen) {
        this.eyeOpen = eyeOpen;
    }


}
