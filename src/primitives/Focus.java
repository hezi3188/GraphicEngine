package primitives;

public class Focus {
    public static boolean enable = false;
    double distance;
    double eyeOpen;

    @Override
    public String toString() {
        return "Focus{" +
                "distance=" + distance +
                ", eyeOpen=" + eyeOpen +
                '}';
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEyeOpen() {
        return eyeOpen;
    }

    public void setEyeOpen(double eyeOpen) {
        this.eyeOpen = eyeOpen;
    }

    public Focus(double distance, double eyeOpen) {
        this.distance = distance;
        this.eyeOpen = eyeOpen;
    }
    public Focus(Focus f) {
        this.distance = f.getDistance();
        this.eyeOpen = f.getEyeOpen();
    }
    public Focus() {
        this.distance = 0;
        this.eyeOpen = 0;
    }
}
