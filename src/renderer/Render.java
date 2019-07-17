package renderer;


import elements.*;
import geometries.*;
import primitives.Color;
import primitives.Point3D;
import primitives.ray;
import primitives.vector;
import scene.Scene;

import java.util.*;

/**
 * The class to clac for every point on scene their color
 */
public class Render {

    /**
     * LevelRec is the number of recurcive clac color after mirors and transillumination elements
     */
    public static int LevelRec = 1;
    private Scene Simulation;
    private ImageWriter imageWriter;

    /**
     * constractor Render object
     * @param S define Scene to clac
     */
    public Render(Scene S) {
        this.Simulation = new Scene(S);
    }

    /**
     * very  importent func, do loop on all pixel in scene and set their color.
     * @param img ImageWriter define of rander settings
     */
    public void renderImage(ImageWriter img){
        Camera OurCam = this.Simulation.getCam();
        double distance = this.Simulation.getDisToScreen();
        String name = this.Simulation.getName();
        List<ray> RR;
        Map<Geometry,List<Point3D>> mapOfAllCut;
        Map.Entry<Geometry, Point3D> entryClosePoint = null;
        Point3D CloseP;
       this.imageWriter = new ImageWriter(img);

       for(int i=0;i<img.getNx();i++) {
           /*if(i%(img.getNx()/100) == 0)
               System.out.println((double) 100*i/img.getNx()+"%   ");*/
           for (int y = 0; y < img.getNy(); y++) {
               boolean Flag = false; //for check if even one of the ray get object else put beckground color.
               Color PixColor = new Color(0,0,0);

               RR = OurCam.constructRaysThroughPixel(img.getNx(), img.getNx(), i, y, distance, img.getWidth(), img.getHeight(),Simulation.getFocus().getDistance(),Simulation.getFocus().getEyeOpen());
               // for every ray clac color
               for (ray R: RR) {
                   mapOfAllCut = IntersectionOnPixel(R);
                   if (mapOfAllCut.size() > 0) {
                       Flag = true;
                       entryClosePoint = getClosestPoint(mapOfAllCut);
                       PixColor = PixColor.add(this.calcColor(entryClosePoint.getKey(), entryClosePoint.getValue(), R).scale((double)1/RR.size()));
                   }
               }
               // clac getReflectedColor to original ray for avoid lose foucs
               mapOfAllCut = IntersectionOnPixel(RR.get(RR.size()-1));
               if (mapOfAllCut.size() > 0) {
                   entryClosePoint = getClosestPoint(mapOfAllCut);
                   PixColor = PixColor.add(getReflectedColor(entryClosePoint.getKey(), entryClosePoint.getValue(), RR.get(RR.size()-1), 0));
               }

               if(Flag)
                   imageWriter.writePixel(i, y, PixColor);
               else
                   imageWriter.writePixel(i, y, this.Simulation.getBackColor());
           }
       }
        imageWriter.writeToimage();
    }

    /**
     * Create MapGeo to ray
     * @param R the ray
     * @return map of every geomtry and their Intersections
     */
    private Map<Geometry,List<Point3D>> IntersectionOnPixel(ray R){
        Map<Geometry,List<Point3D>> MapGeo = new HashMap<Geometry,List<Point3D>>();
        Simulation.getImage().forEach((x)->{
            List<Point3D> Test = x.findIntersections(R);
            if(Test != null && Test.size() > 0)
                MapGeo.put((Geometry) x,Test);
        });
        return MapGeo;
    }

    /**
     * clac the occluded
     * @param L the light
     * @param point the point
     * @param g the geomtry
     * @return bool value true is no occluded.
     */
    private boolean occluded(LightSource L, Point3D point, Geometry g){
        vector lightDirect = L.getL(point).multScalar(-1).normalize();

        // add epsilon for avoid float point problem!
        vector Eps = new vector(g.getNormal(point).normalize());
        Eps = Eps.multScalar(2);
        Point3D newPoint = point.add(Eps);

        ray Lightray = new ray(newPoint,lightDirect);
        Map<Geometry,List<Point3D>>instractionPoints= IntersectionOnPixel(Lightray);
        // remove the same object for avoid that he create a shadow on himself. but only for FlatGeometry
        if(g instanceof FlatGeometry && instractionPoints != null && instractionPoints.size()>0) {
            instractionPoints.remove(g);
        }
        //remove clear elemnts
        instractionPoints.entrySet().removeIf(entry -> entry.getKey().get_material().get_kt()>0);

        return instractionPoints.isEmpty();

    }

    /**
     * help func to send to calcColor with level recurcya 0.
     * @param g the geometry
     * @param p the point
     * @param inRay the origin ray
     * @return color in point
     */
    private Color calcColor(Geometry g,Point3D p,ray inRay){
        return calcColor(g,p,inRay,0);
    }
    /**
     * calcColor is function to clac the color on point the color contain:
     * RefractedColor, ReflectColor, ambientLight, emissionLight, diffuseLight, specularLight.
     * @param g the geometry
     * @param p the point
     * @param inRay the origin ray
     * @param level recurciv level
     * @return color in point
     */
    private Color calcColor(Geometry g, Point3D p,ray inRay,int level){
        Color ReflectColor = new Color(0,0,0);
        if(level < LevelRec && g.get_material().get_kr()>0) {
            ray reflectedRay = constarctReflectRay(g.getNormal(p), p, inRay);
            Map<Geometry, List<Point3D>> x = IntersectionOnPixel(reflectedRay);
            if (x.size() > 0) {
                Map.Entry<Geometry, Point3D> entryClosePoint = getClosestPoint(x,p);
                //send to calcColor with level++
                ReflectColor = ReflectColor.add(this.calcColor(entryClosePoint.getKey(), entryClosePoint.getValue(), reflectedRay, level + 1));
            }
        }


        Color RefractedColor;
        //call to RefractedColor is only from the last ray in the main func in rander.
        if(level>0) RefractedColor= getReflectedColor(g, p, inRay, level);
        else RefractedColor = new Color(0,0,0);


        Color ambientLight = Simulation.getFillLight().GetIntensity().scale(0.2);;
        Color emissionLight = g.getEmmission().scale(0.8);
        Color IO = new Color(ambientLight.getColor().getRed() + emissionLight.getColor().getRed(),
                ambientLight.getColor().getGreen() + emissionLight.getColor().getGreen(),
                ambientLight.getColor().getBlue() + emissionLight.getColor().getBlue());

        //Color IO = new Color(0,0,0);
        Color diffuseLight = new Color(0,0,0);
        Color specularLight = new Color(0,0,0);
        for (LightSource x : Simulation.getLight()) {
            if (occluded(x, p, g)) {
                diffuseLight = diffuseLight.add(calcDiffusiveComp(
                        g.get_material().get_Kd(), g.getNormal(p), x.getL(p), x.getIntensity(p)
                    ));
                specularLight = specularLight.add(calcSpecularComp(
                        g.get_material().get_Ks(), p.substract(inRay.getStart()), g.getNormal(p),
                        x.getL(p), g.get_material().get_nShininess(), x.getIntensity(p)
                    ));
            }
        }

        Color O = IO.add(diffuseLight,
                specularLight,
                ReflectColor.scale(g.get_material().get_kr()),
                RefractedColor.scale(g.get_material().get_kt()));
        return O;
    }

    /**
     * clac getReflectedColor
     * @param g the geomtry
     * @param p the point
     * @param inRay the origin ray
     * @param level the recurcive level
     * @return the Color.
     */
    private Color getReflectedColor(Geometry g, Point3D p, ray inRay, int level) {
        Color RefractedColor = new Color(0,0,0);
        if(level < LevelRec  && g.get_material().get_kt()>0) {
            ray RefractedRay = constarctReftractedRay(g.getNormal(p), p, inRay);
            Map<Geometry, List<Point3D>> x = IntersectionOnPixel(RefractedRay);
            if (x.size() > 0) {
                Map.Entry<Geometry, Point3D> entryClosePoint = getClosestPoint(x,p);
                //call to recurcive func
                RefractedColor = RefractedColor.add(this.calcColor(
                        entryClosePoint.getKey(), entryClosePoint.getValue(), RefractedRay, level + 1)
                );
            }
        }
        return RefractedColor;
    }

    /**
     * constarctReflectRay is a func to create new ray for Reflect.
     * @param ggetNormal  Normal of geometry in place
     * @param p the point
     * @param inRay the origin ray
     * @return new ray
     */
    private ray constarctReflectRay(vector ggetNormal,Point3D p,ray inRay){
        // avoid float point problem
        vector R = ggetNormal.multScalar((new vector(inRay.getPoint())).dotProduct(ggetNormal)*-2).add(inRay);
        return new ray(p.add(R.normalize().multScalar(2)),R);
    }

    /**
     *
     * constarctReftractedRay is a func to create new ray for Reftracted.
     * @param ggetNormal  Normal of geometry in place
     * @param p the point
     * @param inRay the origin ray
     * @return new ray
     */
    private ray constarctReftractedRay(vector ggetNormal,Point3D p,ray inRay){
        // avoid float point problem
        vector temp=new vector((vector)inRay.normalize());
        Point3D point=new Point3D(p);
        point = point.add(temp);
        return new ray(point,(vector)inRay);
    }

    /**
     * calcSpecularComp is a function
     * @param ks the power of Specular of material
     * @param substract vector from point to ray strat
     * @param normal normat of object
     * @param l vector from light to geomtry
     * @param nShininess level of reduce Specular
     * @param intensity the power of Specular
     * @return new color
     */
    private Color calcSpecularComp(double ks, vector substract, vector normal, vector l, int nShininess, Color intensity) {
        // avoid float point problem
       normal = normal.normalize();
       l = l.normalize();
       substract = substract.normalize();
       vector R = normal.multScalar(l.dotProduct(normal)*-2).add(l);
       double Angul = R.dotProduct(substract);
       //for avoid problem direction of normal
       Angul = Math.abs(Angul);
       Color x = intensity.scale(ks*(Math.pow(Angul,nShininess)));
       return x;
    }

    /**
     *
     * @param kd the power of Diffusive in material
     * @param normal the normal from geomtry
     * @param l vector from light to object
     * @param intensity the power of Diffusive light
     * @return
     */
    private Color calcDiffusiveComp(double kd, vector normal, vector l, Color intensity) {
        double Cos = -normal.normalize().dotProduct(l.normalize());
        Cos = Math.abs(Cos); /// must for tangle probbly if normal  oposite
        Cos = Math.max(0,Cos);
        double pwr = kd*Cos;
        Color Dif = intensity.scale(pwr);
        return Dif;
    }

    /**
     * clac ClosestPoint from map to camera
     * @param points dic of geomtry and points
     * @return Map.Entry<Geometry, Point3D> of the most close point
     */
    private Map.Entry<Geometry, Point3D> getClosestPoint(Map<Geometry,List<Point3D>> points){
        return getClosestPoint(points,this.Simulation.getCam().getPosition());
    }

    /**
     * clac ClosestPoint from map to else point
     * @param points dic of geomtry and points
     * @param From point the base distance
     * @return Map.Entry<Geometry, Point3D> of the most close point
     */
    private Map.Entry<Geometry, Point3D> getClosestPoint(Map<Geometry,List<Point3D>> points,Point3D From){
        if(points==null) return null;

        //results buffers
        Map.Entry<Geometry,List<Point3D>> maxEntry = null;
        double Min = -1;
        Point3D ClosePoint= null;

        for (Map.Entry<Geometry,List<Point3D>> entry : points.entrySet())
        {
            for(Point3D innerEntery:entry.getValue()){
                if(Min == -1) {//first iteration save the entery
                    Min = innerEntery.distance(From);
                    ClosePoint = new Point3D(innerEntery);
                    maxEntry = entry;
                }
                if(Min > Math.min(Min,innerEntery.distance(From))){
                    Min = Math.min(Min,innerEntery.distance(From));
                    ClosePoint = new Point3D(innerEntery);
                    maxEntry = entry;
                }
            }
        }
        Map.Entry<Geometry, Point3D> returnEntery;
        if(maxEntry != null){
            returnEntery = new AbstractMap.SimpleEntry<Geometry, Point3D>(maxEntry.getKey(),ClosePoint);
            return returnEntery;
        }
        return null;
    }

    /**
     * print grid
     * @param interval size of picture
     */
    private void printGrid(int interval){
        if(imageWriter == null) return;
        for(int y=0;y<interval;y+=(interval/10))
            for(int i=0;i<interval;i++){
                imageWriter.writePixel(i,y,new primitives.Color(java.awt.Color.magenta));
                imageWriter.writePixel(y,i,new primitives.Color(java.awt.Color.magenta));
            }
    }
}
