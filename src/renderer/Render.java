package renderer;

import elements.*;
import geometries.Geometry;
import org.w3c.dom.NodeList;
import primitives.Color;
import primitives.pointD3;
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
        ray R;
        Map<Geometry,List<pointD3>> mapOfAllCut;
        Map.Entry<Geometry,pointD3> entryClosePoint = null;
        pointD3 CloseP;
       this.imageWriter = new ImageWriter(img);
       for(int i=0;i<img.getNx();i++)
            for(int y=0;y<img.getNy();y++) {
                R = OurCam.constructRayThroughPixel(img.getNx(), img.getNx(), i, y, distance, img.getWidth(), img.getHeight());
                mapOfAllCut = IntersectionOnPixel(R);
                if(mapOfAllCut.size() > 0) {
                    entryClosePoint = getClosestPoint(mapOfAllCut);
                    imageWriter.writePixel(i, y, this.calcColor(entryClosePoint.getKey(),entryClosePoint.getValue()));
                }
                else {imageWriter.writePixel(i, y, this.Simulation.getBackColor());}
                //CloseP = this.getClosestPoint(Simulation.getImage().get(0).findIntersections(R));
                //if(CloseP != null){
                 //   imageWriter.writePixel(i,y,this.calcColor(CloseP));
                //}
            }
        printGrid(Math.min(img.getNx(),img.getNy()));
        imageWriter.writeToimage();
    }
    private Map<Geometry,List<pointD3>> IntersectionOnPixel(ray R){
        Map<Geometry,List<pointD3>> MapGeo = new HashMap<Geometry,List<pointD3>>();
        List<pointD3> Points =new ArrayList<>();
        Simulation.getImage().forEach((x)->{
            List<pointD3> Test = x.findIntersections(R);
            if(Test != null && Test.size() > 0)
                MapGeo.put((Geometry) x,Test);
        });
        return MapGeo;
    }
    private Color calcColor(Geometry g,pointD3 p){
        Color ambientLight = Simulation.getFillLight().GetIntensity();
        Color emissionLight = g.getEmmission();
        Color IO = new Color(ambientLight.getColor().getRed() + emissionLight.getColor().getRed(),ambientLight.getColor().getGreen() + emissionLight.getColor().getGreen(),
                ambientLight.getColor().getBlue() + emissionLight.getColor().getBlue());
        Color diffuseLight = new Color(0,0,0);
        Color specularLight = new Color(0,0,0);
        for (LightSource x : Simulation.getLight()) {
            diffuseLight = diffuseLight.add(calcDiffusiveComp(g.get_material().get_Kd(),g.getNormal(p),x.getL(p),x.getIntensity(p)));
            specularLight = specularLight.add(calcSpecularComp(g.get_material().get_Ks(),p.substract(Simulation.getCam().getPosition()),g.getNormal(p),x.getL(p),g.get_material().get_nShininess(),x.getIntensity(p)));
        }


        return IO.add(specularLight,diffuseLight);
        /*Color a;
        Color amissionLight=new Color(0,0,0);//=g.getEmmission();
        if(this.Simulation.getLight() != null && this.Simulation.getLight().size()>0) {
            for (Light x : Simulation.getLight()) {

                List<Boolean> IsCome = new ArrayList<>();
                if(x instanceof PointLight) {
                    Simulation.getImage().forEach((y) -> {
                        List<pointD3> l = y.findIntersections(new ray(((PointLight) x).get_position(), p));*/
                        /*if (y.equals(g) && (l == null || l.size() == 0))
                            IsCome.add(false);
                        if (l != null && l.size() > 0) {
                            for (pointD3 z : l) {
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

    private Color calcSpecularComp(double ks, vector substract, vector normal, vector l, int nShininess, Color intensity) {
       double R = substract.normalize().dotProduct(normal.normalize().multScalar(-1));
       R = Math.max(R,0);
       Color x = intensity.scale(ks*(Math.pow(R,nShininess)));
       return x;
    }

    private Color calcDiffusiveComp(double kd, vector normal, vector l, Color intensity) {
        double Cos = -normal.normalize().dotProduct(l.normalize());
        Cos = Math.max(0,Cos);
        double pwr = kd*Cos;
        Color Dif = intensity.scale(pwr);
        return Dif;
    }

    private Map.Entry<Geometry,pointD3> getClosestPoint(Map<Geometry,List<pointD3>> points){
        if(points==null) return null;

        Map.Entry<Geometry,List<pointD3>> maxEntry = null;
        double Min = -1;
        pointD3 ClosePoint= null;
        for (Map.Entry<Geometry,List<pointD3>> entry : points.entrySet())
        {
            for(pointD3 innerEntery:entry.getValue()){
                if(Min > Math.min(Min,innerEntery.distance(this.Simulation.getCam().getPosition())) || Min == -1){
                    Min = Math.min(Min,innerEntery.distance(this.Simulation.getCam().getPosition()));
                    ClosePoint = new pointD3(innerEntery);
                    maxEntry = entry;
                }
            }
        }
        Map.Entry<Geometry,pointD3> returnEntery;
        if(maxEntry != null){
            returnEntery = new AbstractMap.SimpleEntry<Geometry,pointD3>(maxEntry.getKey(),ClosePoint);
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
