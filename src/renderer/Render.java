package renderer;

import elements.Camera;
import primitives.Color;
import primitives.pointD3;
import primitives.ray;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

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
        pointD3 CloseP;
       this.imageWriter = new ImageWriter(name, 500, 500, PXx, PXy);
       for(int i=0;i<PXx;i++)
            for(int y=0;y<PXy;y++) {
                R = OurCam.constructRayThroughPixel(PXx, PXy, i, y, distance, 10, 10);
                if(IntersectionOnPixel(R).size() > 0)
                    imageWriter.writePixel(i,y,this.calcColor(new pointD3(1,2,3)));
                //CloseP = this.getClosestPoint(Simulation.getImage().get(0).findIntersections(R));
                //if(CloseP != null){
                 //   imageWriter.writePixel(i,y,this.calcColor(CloseP));
                //}
            }
        printGrid(Math.min(PXx,PXy));
        imageWriter.writeToimage();
    }
    private List<pointD3> IntersectionOnPixel(ray R){
        List<pointD3> Points =new ArrayList<>();
        Simulation.getImage().forEach((x)->{
            List<pointD3> Test = x.findIntersections(R);
            if(Test != null)
                Points.addAll(Test);
        });
        return Points;
    }
    private Color calcColor(pointD3 p){
        return new primitives.Color(java.awt.Color.green);
    }
    private pointD3 getClosestPoint(List<pointD3> points){
        if(points==null) return null;
        double Min = 0;
        pointD3 ClosePoint= null;
        for (pointD3 Pos:points) {
            if(Min > Math.min(Min,Pos.distance(this.Simulation.getCam().getPosition()))){
                Min = Math.min(Min,Pos.distance(this.Simulation.getCam().getPosition()));
                ClosePoint = new pointD3(Pos);
            }
        }
        return ClosePoint;
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
