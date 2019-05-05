package elements;

import primitives.pointD3;
import primitives.ray;
import primitives.vector;

public class Camera {
    protected pointD3 position;
    protected vector CamFront;
    protected vector CamUp;
    protected vector CamLeft;

    public Camera(){
        this.position = new pointD3(0.0,0.0,10.0);
        this.CamFront = new vector(new pointD3(0,0,1));
        this.CamUp = new vector(new pointD3(0,1,0));
        this.CamLeft = CamFront.crossProduct(CamUp);
    }

    public Camera(pointD3 point,vector ahead, vector up)
    {
        this.position = new pointD3(point);
        this.CamFront = ahead.normalize();
        this.CamUp = up.normalize();
        if(this.CamFront.dotProduct(this.CamUp) == 0) {
            this.CamLeft = CamFront.crossProduct(CamUp).normalize();
        }
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

        pointD3 centerPlane = position.add(CamFront.multScalar(distance));
        vector PlaneTop = CamUp.multScalar(height);
        vector PlaneLeft = CamLeft.multScalar(width);

        pointD3 TopOfPlane = centerPlane.add(PlaneTop.multScalar(0.5));
        pointD3 TopLeftOfPlane = TopOfPlane.add(PlaneLeft.multScalar(0.5));

        // need substract from point, so change direction of vector
        pointD3 YXonPlane = TopLeftOfPlane.add(CamUp.multScalar(CenterFromTopPixelY*(-1))).add(CamLeft.multScalar(CenterFromLeftPixelX*(-1)));

        return new ray(position,YXonPlane.substract(position));
    }

    /*Getters and Setters*/

    public pointD3 getPosition() {
        return new pointD3(position);
    }

    public void setPosition(pointD3 position) {
        this.position = new pointD3(position);
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
