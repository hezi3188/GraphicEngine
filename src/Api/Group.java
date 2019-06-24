package Api;

import geometries.Geometry;

public class Group {
    public Geometry[] G;

    Group(Geometry[] g){
        G = g;
    }

    public Geometry[] getG() {
        return G;
    }
}
