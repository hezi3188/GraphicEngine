package elements;


import primitives.Focus;
import primitives.Point3D;
import primitives.ray;
import primitives.vector;

import java.util.ArrayList;
import java.util.List;
/**
 * Camera element. place in scene and used for set the place of view of scene.
 * @author Avishai elia and hezi BenAtar
 */
public class Camera {
    protected Point3D position;
    protected vector CamFront;
    protected vector CamUp;
    protected vector CamLeft;

    /**
     * defult constractor. cam position on (0,0,0) look to z = -1
     */
    public Camera(){
        this.position = new Point3D(0.0,0.0,0.0);
        this.CamFront = new vector(new Point3D(0,0,-1));
        this.CamUp = new vector(new Point3D(0,1,0));
        this.CamLeft = CamFront.crossProduct(CamUp);
    }

    /**
     *  this constractor need ortogonal normalize vectors.
     * @param point position of camera
     * @param ahead front look of camera
     * @param up up vector of camera
     */
    public Camera(Point3D point, vector ahead, vector up)
    {
        this.position = new Point3D(point);
        this.CamFront = ahead.normalize();
        this.CamUp = up.normalize();
        this.CamLeft = CamFront.crossProduct(CamUp).normalize();
    }

    /**
     * this a copy constractor
     * @param cam old object not null.
     */
    public Camera(Camera cam) {
        this.position = cam.getPosition();
        this.CamUp = cam.getCamUp();
        this.CamLeft = cam.getCamLeft();
        this.CamFront = cam.getCamFront();
    }

    /**
     * the func create ray that out from the camera.
     * @param pixelsX pixelsX of the ray
     * @param pixelsY pixelsY of the ray
     * @param Rx num of pixelsX in picture
     * @param Rj num of pixelsY in picture
     * @param distance distance from camera to plane of picture
     * @param width width of plane
     * @param height height of plane
     * @return A ray from camera to scene.
     */
    public ray constructRayThroughPixel(int pixelsX, int pixelsY,
                                        double Rx, double Rj,
                                        double distance,
                                        double width, double height){

        double distanceX=width/pixelsX;
        double distanceY=height/pixelsY; //clac width and height of pixel
        double FromLeftPixelXNotUse = distanceX*Rx;
        double FromTopPixelYNotUse = distanceY*Rj;
        double CenterFromLeftPixelX = FromLeftPixelXNotUse + (distanceX/2);
        double CenterFromTopPixelY = FromTopPixelYNotUse + (distanceY/2); //clac the distance of pixel from top plan

        Point3D centerPlane = position.add(CamFront.multScalar(distance));
        vector PlaneTop = CamUp.multScalar(height);
        vector PlaneLeft = CamLeft.multScalar(width); // find vectors of plane

        Point3D TopOfPlane = centerPlane.add(PlaneTop.multScalar(0.5));
        Point3D TopLeftOfPlane = TopOfPlane.add(PlaneLeft.multScalar(0.5)); // find the point of pixel on plane

        // need substract from point, so change direction of vector
        Point3D YXonPlane = TopLeftOfPlane.add(CamUp.multScalar(CenterFromTopPixelY*(-1))).add(CamLeft.multScalar(CenterFromLeftPixelX*(-1)));



        return new ray(position,YXonPlane.substract(position).normalize());
    }

    /**
     * the func create List of ray that out from the camera to scene. the rays out around of point on plane of picture and meeting after FoucsDis. It is get feel of foucs
     * @param pixelsX pixelsX of the ray
     * @param pixelsY pixelsY of the ray
     * @param Rx num of pixelsX in picture
     * @param Rj num of pixelsY in picture
     * @param distance distance from camera to plane of picture
     * @param width width of plane
     * @param height height of plane
     * @param FoucsDis Distance from plane to rays meeting
     * @param Sribua The distance between rays when they out from  plane.
     * @return List of rays.
     */
    public List<ray> constructRaysThroughPixel(int pixelsX, int pixelsY,
                                        double Rx, double Rj,
                                        double distance,
                                        double width, double height,double FoucsDis, double Sribua){
        double distanceX=width/pixelsX;
        double distanceY=height/pixelsY;
        double FromLeftPixelXNotUse = distanceX*Rx;
        double FromTopPixelYNotUse = distanceY*Rj;
        double CenterFromLeftPixelX = FromLeftPixelXNotUse + (distanceX/2); //*0-1
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

    /**
     * help func to clac of fucos rays
     * @param FoucsDis Distance from plane to rays meeting
     * @param Sribua The distance between rays when they out from  plane.
     * @param YXonPlane the Point3D on place
     * @param FromCam the vector from camera
     * @return 4 rays and the FromCam ray last in 5st.
     */
    public List<ray> GetFocus(double FoucsDis, double Sribua, Point3D YXonPlane, vector FromCam){
        List<ray> rays =new ArrayList<>();
        if(!Focus.enable) { // for spear time
            rays.add(new ray(position,YXonPlane.substract(position).normalize()));
            return rays;
        }

        Point3D PointF = YXonPlane.add(FromCam.multScalar(FoucsDis));
        Point3D PL1 = YXonPlane.add(CamUp.multScalar(Sribua/2)).add(CamLeft.multScalar(Sribua/2));
        Point3D PL2 = YXonPlane.add(CamUp.multScalar(Sribua/2)).add(CamLeft.multScalar(-Sribua/2));
        Point3D PL3 = YXonPlane.add(CamUp.multScalar(-Sribua/2)).add(CamLeft.multScalar(-Sribua/2));
        Point3D PL4 = YXonPlane.add(CamUp.multScalar(-Sribua/2)).add(CamLeft.multScalar(Sribua/2));
        //clac every ray

        ray L1 = new ray(PL1,PointF.substract(PL1).normalize());
        ray L2 = new ray(PL2,PointF.substract(PL2).normalize());
        ray L3 = new ray(PL3,PointF.substract(PL3).normalize());
        ray L4 = new ray(PL4,PointF.substract(PL4).normalize());

        rays.add(L1);
        rays.add(L2);
        rays.add(L3);
        rays.add(L4);
        rays.add(new ray(position,YXonPlane.substract(position).normalize()));
        return rays;
    }
    /*Getters and Setters*/

    /**
     *
     * @return Point3D position
     */
    public Point3D getPosition() {
        return new Point3D(position);
    }

    /**
     *
     * @param position set new point
     */
    public void setPosition(Point3D position) {
        this.position = new Point3D(position);
    }

    /**
     *
     * @return vector getCamFront
     */
    public vector getCamFront() {
        return new vector(CamFront);
    }

    /**
     *
     * @param camFront set vector camFront
     */
    public void setCamFront(vector camFront) {
        CamFront = new vector(camFront);
    }

    /**
     *
     * @return vector CamUp
     */
    public vector getCamUp() {
        return new vector(CamUp);
    }

    /**
     *
     * @param camUp set the vector camUp
     */
    public void setCamUp(vector camUp) {
        CamUp = new vector(camUp);
    }

    /**
     *
     * @return vector CamLeft
     */
    public vector getCamLeft() {
        return new vector(CamLeft);
    }

    /**
     *
     * @param camLeft set the vector camLeft
     */
    public void setCamLeft(vector camLeft) {
        CamLeft = new vector(camLeft);
    }
}
