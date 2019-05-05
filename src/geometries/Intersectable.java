package geometries;
import primitives.*;
import java.util.List;

public interface Intersectable {
    List<pointD3> findIntersections(ray R);
}
