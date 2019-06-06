package renderer;

import elements.*;
import geometries.*;
import primitives.Color;
import primitives.Point3D;
import primitives.ray;
import primitives.vector;
import scene.Scene;

import java.util.*;

public class Render {
    private Scene Simulation;
    private ImageWriter imageWriter;


    public Render(Scene S) {
        this.Simulation = new Scene(S);
    }

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
           if(i%(img.getNx()/100) == 0)
               System.out.println((double) 100*i/img.getNx()+"%   ");
           for (int y = 0; y < img.getNy(); y++) {
               boolean Flag = false;
               Color PixColor = new Color(0,0,0);
               RR = OurCam.constructRaysThroughPixel(img.getNx(), img.getNx(), i, y, distance, img.getWidth(), img.getHeight(),100,1);

               for (ray R: RR) {
                   mapOfAllCut = IntersectionOnPixel(R);
                   if (mapOfAllCut.size() > 0) {
                       Flag = true;
                       entryClosePoint = getClosestPoint(mapOfAllCut);
                       PixColor = PixColor.add(this.calcColor(entryClosePoint.getKey(), entryClosePoint.getValue(), R).scale((double)1/RR.size()));
                   }
               }

               if(Flag)
                   imageWriter.writePixel(i, y, PixColor);
               else
                   imageWriter.writePixel(i, y, this.Simulation.getBackColor());
               //CloseP = this.getClosestPoint(Simulation.getImage().get(0).findIntersections(R));
               //if(CloseP != null){
               //   imageWriter.writePixel(i,y,this.calcColor(CloseP));
               //}
           }
       }
        printGrid(Math.min(img.getNx(),img.getNy()));
        imageWriter.writeToimage();
    }
    private Map<Geometry,List<Point3D>> IntersectionOnPixel(ray R){
        Map<Geometry,List<Point3D>> MapGeo = new HashMap<Geometry,List<Point3D>>();
        List<Point3D> Points =new ArrayList<>();
        Simulation.getImage().forEach((x)->{
            List<Point3D> Test = x.findIntersections(R);
            if(Test != null && Test.size() > 0)
                MapGeo.put((Geometry) x,Test);
        });
        return MapGeo;
    }
    private boolean occluded(LightSource L, Point3D point, Geometry g){
        vector lightDirect = L.getL(point).multScalar(-1).normalize();
        vector Eps = new vector(g.getNormal(point).normalize());
        Eps = Eps.multScalar(2);
        Point3D newPoint = point.add(Eps);
        ray Lightray = new ray(newPoint,lightDirect);
        Map<Geometry,List<Point3D>>instractionPoints= IntersectionOnPixel(Lightray);
        if(g instanceof FlatGeometry && instractionPoints != null && instractionPoints.size()>0) {
            instractionPoints.remove(g);

        }
        return instractionPoints.isEmpty();

    }
    private Color calcColor(Geometry g,Point3D p,ray inRay){
        return calcColor(g,p,inRay,0);
    }
    private Color calcColor(Geometry g, Point3D p,ray inRay,int level){
        if(g instanceof Sphere && level==0){
            g.get_material();
        }
        Color ReflectColor = new Color(0,0,0);
        if(level < 1) {
            ray reflectedRay = constarctReflectRay(g.getNormal(p), p, inRay);
            Map<Geometry, List<Point3D>> x = IntersectionOnPixel(reflectedRay);
            if (x.size() > 0) {
                Map.Entry<Geometry, Point3D> entryClosePoint = getClosestPoint(x);
                ReflectColor = ReflectColor.add(this.calcColor(entryClosePoint.getKey(), entryClosePoint.getValue(), reflectedRay, level + 1));
            }
        }

        Color ambientLight = Simulation.getFillLight().GetIntensity().scale(0.2);;
        Color emissionLight = g.getEmmission().scale(0.8);
        Color IO = new Color(ambientLight.getColor().getRed() + emissionLight.getColor().getRed(),ambientLight.getColor().getGreen() + emissionLight.getColor().getGreen(),
                ambientLight.getColor().getBlue() + emissionLight.getColor().getBlue());

        //Color IO = new Color(0,0,0);
        Color diffuseLight = new Color(0,0,0);
        Color specularLight = new Color(0,0,0);
        for (LightSource x : Simulation.getLight()) {
            if (occluded(x, p, g)) {
                diffuseLight = diffuseLight.add(calcDiffusiveComp(g.get_material().get_Kd(), g.getNormal(p), x.getL(p), x.getIntensity(p)));
                specularLight = specularLight.add(calcSpecularComp(g.get_material().get_Ks(), p.substract(Simulation.getCam().getPosition()), g.getNormal(p), x.getL(p), g.get_material().get_nShininess(), x.getIntensity(p)));
            }
        }

        Color O = IO.add(diffuseLight,specularLight,ReflectColor.scale(g.get_material().get_kr()));
        if(O.getColor().getBlue() == 0)
            O.getColor();
        return O;
        /*Color a;
        Color amissionLight=new Color(0,0,0);//=g.getEmmission();
        if(this.Simulation.getLight() != null && this.Simulation.getLight().size()>0) {
            for (Light x : Simulation.getLight()) {

                List<Boolean> IsCome = new ArrayList<>();
                if(x instanceof PointLight) {
                    Simulation.getImage().forEach((y) -> {
                        List<Point3D> l = y.findIntersections(new ray(((PointLight) x).get_position(), p));*/
                        /*if (y.equals(g) && (l == null || l.size() == 0))
                            IsCome.add(false);
                        if (l != null && l.size() > 0) {
                            for (Point3D z : l) {
                                if ((((PointLight) x).get_position().distance(z)) < ((PointLight) x).get_position().distance(p) && !z.equals(p)) {
                                    //IsCome.add(false);
                                }
                            }
                        }*/
                  /*  });
                    /*if( IsCome.size()==0 &&
                        g.findIntersections(new ray(this.Simulation.getCam().getPosition(),((PointLight) x).get_position()))!= null &&
                        g.findIntersections(new ray(this.Simulation.getCam().getPosition(),((PointLight) x).get_position())).size()>0)
                            IsCome.add(false);*/
               // }

                /*if(IsCome.size()==0) {
                    if(x instanceof SpotLight)
                        a = ((SpotLight)x).getIntensity(p, g,g.getNormal(p));
                    else
                        a = x.getIntensity(p, g);
                    amissionLight = amissionLight.add(a);
                    //amissionLight = amissionLight.add(new Color(java.awt.Color.white));
                }*//*
            }
        }
        else {
            amissionLight=g.getEmmission();
            amissionLight.add(Simulation.getFillLight().GetIntensity());
            return amissionLight;
        }
        return new primitives.Color(amissionLight);*/
    }

    private ray constarctReflectRay(vector ggetNormal,Point3D p,ray inRay){
        vector R = ggetNormal.multScalar((new vector(inRay.getPoint())).dotProduct(ggetNormal)*-2).add(inRay);
        return new ray(p,R);
    }

    private Color calcSpecularComp(double ks, vector substract, vector normal, vector l, int nShininess, Color intensity) {
       normal = normal.normalize();
       l = l.normalize();
       substract = substract.normalize();
       vector R = normal.multScalar(l.dotProduct(normal)*-2).add(l);
       double Angul = R.dotProduct(substract);
       Angul = Math.abs(Angul);
       Color x = intensity.scale(ks*(Math.pow(Angul,nShininess)));
       return x;
    }

    private Color calcDiffusiveComp(double kd, vector normal, vector l, Color intensity) {
        double Cos = -normal.normalize().dotProduct(l.normalize());
        Cos = Math.abs(Cos); /// must for tangle probbly if normal  oposite
        Cos = Math.max(0,Cos);
        double pwr = kd*Cos;
        Color Dif = intensity.scale(pwr);
        return Dif;
    }

    private Map.Entry<Geometry, Point3D> getClosestPoint(Map<Geometry,List<Point3D>> points){
        if(points==null) return null;

        Map.Entry<Geometry,List<Point3D>> maxEntry = null;
        double Min = -1;
        Point3D ClosePoint= null;
        if(points.size()>1){
            points.size();
        }
        for (Map.Entry<Geometry,List<Point3D>> entry : points.entrySet())
        {
            for(Point3D innerEntery:entry.getValue()){
                if(Min == -1) {//first iteration
                    Min = innerEntery.distance(this.Simulation.getCam().getPosition());
                    ClosePoint = new Point3D(innerEntery);
                    maxEntry = entry;
                }
                if(Min > Math.min(Min,innerEntery.distance(this.Simulation.getCam().getPosition()))){
                    Min = Math.min(Min,innerEntery.distance(this.Simulation.getCam().getPosition()));
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
    private void printGrid(int interval){
        if(imageWriter == null) return;
        for(int y=0;y<interval;y+=(interval/10))
            for(int i=0;i<interval;i++){
                imageWriter.writePixel(i,y,new primitives.Color(java.awt.Color.magenta));
                imageWriter.writePixel(y,i,new primitives.Color(java.awt.Color.magenta));
            }
    }
}
