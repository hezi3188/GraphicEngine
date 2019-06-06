package elements;

import primitives.Point3D;
import primitives.ray;
import primitives.vector;

import java.util.ArrayList;
import java.util.List;

public class Camera {
    protected Point3D position;
    protected vector CamFront;
    protected vector CamUp;
    protected vector CamLeft;

    public Camera(){
        this.position = new Point3D(0.0,0.0,0.0);
        this.CamFront = new vector(new Point3D(0,0,-1));
        this.CamUp = new vector(new Point3D(0,1,0));
        this.CamLeft = CamFront.crossProduct(CamUp);
    }

    public Camera(Point3D point, vector ahead, vector up)
    {
        this.position = new Point3D(point);
        this.CamFront = ahead.normalize();
        this.CamUp = up.normalize();
        if(this.CamFront.dotProduct(this.CamUp) == 0) {
            this.CamLeft = CamFront.crossProduct(CamUp).normalize();
        }
    }

    public Camera(Camera cam) {
        this.position = cam.getPosition();
        this.CamUp = cam.getCamUp();
        this.CamLeft = cam.getCamLeft();
        this.CamFront = cam.getCamFront();
    }


    public ray constructRayThroughPixel(int pixelsX, int pixelsY,
                                        double Rx, double Rj,
                                        double distance,
                                        double width, double height){

        double distanceX=width/pixelsX;
        double distanceY=height/pixelsY;
        double FromLeftPixelXNotUse = distanceX*Rx;
        double FromTopPixelYNotUse = distanceY*Rj;
        double CenterFromLeftPixelX = FromLeftPixelXNotUse + (distanceX/2);
        double CenterFromTopPixelY = FromTopPixelYNotUse + (distanceY/2);

        Point3D centerPlane = position.add(CamFront.multScalar(distance));
        vector PlaneTop = CamUp.multScalar(height);
        vector PlaneLeft = CamLeft.multScalar(width);

        Point3D TopOfPlane = centerPlane.add(PlaneTop.multScalar(0.5));
        Point3D TopLeftOfPlane = TopOfPlane.add(PlaneLeft.multScalar(0.5));

        // need substract from point, so change direction of vector
        Point3D YXonPlane = TopLeftOfPlane.add(CamUp.multScalar(CenterFromTopPixelY*(-1))).add(CamLeft.multScalar(CenterFromLeftPixelX*(-1)));



        return new ray(position,YXonPlane.substract(position).normalize());
    }
    public List<ray> constructRaysThroughPixel(int pixelsX, int pixelsY,
                                        double Rx, double Rj,
                                        double distance,
                                        double width, double height,double FoucsDis, double Sribua){
        double distanceX=width/pixelsX;
        double distanceY=height/pixelsY;
        double FromLeftPixelXNotUse = distanceX*Rx;
        double FromTopPixelYNotUse = distanceY*Rj;
        double CenterFromLeftPixelX = FromLeftPixelXNotUse + (distanceX/2);
        double CenterFromTopPixelY = FromTopPixelYNotUse + (distanceY/2);

        Point3D centerPlane = position.add(CamFront.multScalar(distance));
        vector PlaneTop = CamUp.multScalar(height);
        vector PlaneLeft = CamLeft.multScalar(width);

        Point3D TopOfPlane = centerPlane.add(PlaneTop.multScalar(0.5));
        Point3D TopLeftOfPlane = TopOfPlane.add(PlaneLeft.multScalar(0.5));

        // need substract from point, so change direction of vector
        Point3D YXonPlane = TopLeftOfPlane.add(CamUp.multScalar(CenterFromTopPixelY*(-1))).add(CamLeft.multScalar(CenterFromLeftPixelX*(-1)));



        new ray(position,YXonPlane.substract(position).normalize());

        return GetFocus(FoucsDis,Sribua,YXonPlane,YXonPlane.substract(position).normalize());

    }

    public List<ray> GetFocus(double FoucsDis, double Sribua, Point3D YXonPlane, vector FromCam){
        List<ray> rays =new ArrayList<>();
        if(true) { // for spear time
            rays.add(new ray(position,YXonPlane.substract(position).normalize()));
            return rays;
        }

        Point3D PointF = YXonPlane.add(FromCam.multScalar(FoucsDis));
        Point3D PL1 = YXonPlane.add(CamUp.multScalar(Sribua/2)).add(CamLeft.multScalar(Sribua/2));
        Point3D PL2 = YXonPlane.add(CamUp.multScalar(Sribua/2)).add(CamLeft.multScalar(-Sribua/2));
        Point3D PL3 = YXonPlane.add(CamUp.multScalar(-Sribua/2)).add(CamLeft.multScalar(-Sribua/2));
        Point3D PL4 = YXonPlane.add(CamUp.multScalar(-Sribua/2)).add(CamLeft.multScalar(Sribua/2));

        ray L1 = new ray(position,PointF.substract(PL1).normalize());
        ray L2 = new ray(position,PointF.substract(PL2).normalize());
        ray L3 = new ray(position,PointF.substract(PL3).normalize());
        ray L4 = new ray(position,PointF.substract(PL4).normalize());

        rays.add(L1);
        rays.add(L2);
        rays.add(L3);
        rays.add(L4);

        return rays;
    }
    /*Getters and Setters*/

    public Point3D getPosition() {
        return new Point3D(position);
    }

    public void setPosition(Point3D position) {
        this.position = new Point3D(position);
    }

    public vector getCamFront() {
        return new vector(CamFront);
    }

    public void setCamFront(vector camFront) {
        CamFront = new vector(camFront);
    }

    public vector getCamUp() {
        return new vector(CamUp);
    }

    public void setCamUp(vector camUp) {
        CamUp = new vector(camUp);
    }

    public vector getCamLeft() {
        return new vector(CamLeft);
    }

    public void setCamLeft(vector camLeft) {
        CamLeft = new vector(camLeft);
    }
}
