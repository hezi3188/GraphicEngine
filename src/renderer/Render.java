package renderer;

import elements.Camera;
import geometries.Geometry;
import primitives.Color;
import primitives.pointD3;
import primitives.ray;
import scene.Scene;

import java.util.*;

public class Render {
    private Scene Simulation;
    private ImageWriter imageWriter;


    public Render(Scene S) {
        this.Simulation = new Scene(S);
    }

    public void renderImage(int PXx, int PXy){
        Camera OurCam = this.Simulation.getCam();
        double distance = this.Simulation.getDisToScreen();
        String name = this.Simulation.getName();
        ray R;
        Map<Geometry,List<pointD3>> mapOfAllCut;
        Map.Entry<Geometry,pointD3> entryClosePoint = null;
        pointD3 CloseP;
       this.imageWriter = new ImageWriter(name, 500, 500, PXx, PXy);
       for(int i=0;i<PXx;i++)
            for(int y=0;y<PXy;y++) {
                R = OurCam.constructRayThroughPixel(PXx, PXy, i, y, distance, 10, 10);
                mapOfAllCut = IntersectionOnPixel(R);
                if(mapOfAllCut.size() > 0) {
                    entryClosePoint = getClosestPoint(mapOfAllCut);
                    imageWriter.writePixel(i, y, this.calcColor(entryClosePoint.getKey()));
                }
                //CloseP = this.getClosestPoint(Simulation.getImage().get(0).findIntersections(R));
                //if(CloseP != null){
                 //   imageWriter.writePixel(i,y,this.calcColor(CloseP));
                //}
            }
        printGrid(Math.min(PXx,PXy));
        imageWriter.writeToimage();
    }
    private Map<Geometry,List<pointD3>> IntersectionOnPixel(ray R){
        Map<Geometry,List<pointD3>> MapGeo = new HashMap<Geometry,List<pointD3>>();
        List<pointD3> Points =new ArrayList<>();
        Simulation.getImage().forEach((x)->{
            List<pointD3> Test = x.findIntersections(R);
            if(Test != null)
                MapGeo.put((Geometry) x,Test);
        });
        return MapGeo;
    }
    private Color calcColor(Geometry g){
        return new primitives.Color(java.awt.Color.green);
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
