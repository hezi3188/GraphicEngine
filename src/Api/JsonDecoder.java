//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
/**
 * A func to create Scene fron Json file
 */
package Api;

import elements.*;
import geometries.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.NodeList;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static renderer.Render.LevelRec;

public class JsonDecoder {
    public static String NameFile = "abc";

    /**
     * add all geomtrys from array to scene
     * @param o arr of geomtry and groups
     * @return arr of geomtry
     */
    public static Geometry[] append(Object[] o){
        List<Geometry> list = new LinkedList<Geometry>();
        for (Object x:o){
            if(x instanceof Group){
                list.addAll(Arrays.asList(append(((Group) x).getG())));
            }
            else{
                list.add((Geometry) x);
            }
        }
        return (Geometry[]) list.toArray();
    }

    /**
     * main func to work
     * @param File Json string
     * @param name name file
     */
    public static void JsonToScene(String File, String name) {
        NameFile = name;
        Scene scene = new Scene("");
        JSONObject obj = new JSONObject(File);
        JSONArray set = getArr(obj, "settings");
        JSONArray geo = getArr(obj, "geo");
        JSONArray light = getArr(obj, "lights");

        int i;
        for(i = 0; i < geo.length(); ++i) {
            Object temp = newObj(getStr(geo.getJSONObject(i), "name"), getArr(geo.getJSONObject(i), "param"));
            if(temp instanceof Group)
                for(Object g:append(((Group) temp).getG())){
                    scene.insertImage((Geometry)temp);
                }
            else
                scene.insertImage((Geometry)temp);
        }

        for(i = 0; i < light.length(); ++i) {
            scene.insertLight((LightSource)newObj(getStr(light.getJSONObject(i), "name"), getArr(light.getJSONObject(i), "param")));
        }

        Object[] settingParams = new Object[set.length()];

        for(i = 0; i < set.length(); ++i) {
            settingParams[i] = newObj(getStr(set.getJSONObject(i), "name"), getArr(set.getJSONObject(i), "param"));
        }

        scene.setCamAndDis((Camera)settingParams[0], (Double)settingParams[3]);
        scene.setFillLight((AmbientLight)settingParams[1]);
        if(settingParams.length>4) scene.setFocus((Focus) settingParams[4]);
        ImageWriter imageWriter = (ImageWriter)settingParams[2];

        Render render = new Render(scene);
        render.renderImage(imageWriter);
    }

    /**
     * recurcve func tom convert json to object in object
     * @param name string
     * @param param json array
     * @return object from program
     */
    public static Object newObj(String name, JSONArray param) {
        if (name.equals("double")) {
            return param.getDouble(0);
        } else {
            Object[] Params = new Object[param.length()];

            for(int i = 0; i < param.length(); ++i) {
                Params[i] = newObj(getStr(param.getJSONObject(i), "name"), getArr(param.getJSONObject(i), "param"));
            }

            if (name.equals("Point3D") && param.length() == 3) {
                return new Point3D((Double)Params[0], (Double)Params[1], (Double)Params[2]);
            } else if (name.equals("Triangle") && param.length() == 5) {
                return new Triangle((Point3D)Params[0], (Point3D)Params[1], (Point3D)Params[2], (Color)Params[3],(Material)Params[4]);
            } else if (name.equals("Sphere") && param.length() == 4) {
                return new Sphere((Double)Params[0], (Point3D)Params[1], (Color)Params[2],(Material)Params[3]);
            } else if (name.equals("Plane") && param.length() == 4) {
                return new Plane((Point3D)Params[0], (vector)Params[1], (Color)Params[2],(Material)Params[3]);
            } else if (name.equals("Plane") && param.length() == 5) {
                return new Plane((Point3D)Params[0], (Point3D)Params[1], (Point3D)Params[2], (Color)Params[3],(Material)Params[4]);
            } else if (name.equals("Vector") && param.length() == 1) {
                return new vector((Point3D)Params[0]);
            } else if (name.equals("Camera") && param.length() == 3) {
                return new Camera((Point3D)Params[0], (vector)Params[1], (vector)Params[2]);
            } else if (name.equals("AmbientLight") && param.length() == 2) {
                return new AmbientLight((Color)Params[0], (Double)Params[1]);
            } else if (name.equals("ImageWriter") && param.length() == 5) {
                LevelRec = Math.min((int)((double)Params[0]),3);
                int px = Math.min(600,(int)(double)Params[3]);
                int py = Math.min(600,(int)(double)Params[4]);
                return new ImageWriter(NameFile, (Double)Params[1], (Double)Params[2], px, py);
            } else if (name.equals("Color") && param.length() == 3) {
                return new Color((Double)Params[0], (Double)Params[1], (Double)Params[2]);
            } else if (name.equals("PointLight") && param.length() == 5) {
                return new PointLight((Color)Params[0], (Point3D)Params[1], (Double)Params[2], (Double)Params[3], (Double)Params[4]);
            } else if (name.equals("SpotLight") && param.length() == 6) {
                return new SpotLight((Color)Params[0], (Point3D)Params[1], (Double)Params[2], (Double)Params[3], (Double)Params[4], (vector)Params[5]);
            } else if (name.equals("Material") && param.length() == 5) {
                return new Material((Double)Params[0], (Double)Params[1], (int)(double)Params[2], (Double)Params[3], (Double)Params[4]);
            } else if (name.equals("Quad") && param.length() == 6) {
                return new Quad((Point3D)Params[0], (Point3D)Params[1], (Point3D)Params[2], (Point3D)Params[3], (Color)Params[4],(Material)Params[5]);
            } else if (name.equals("Group")) {
                return new Group((Geometry[])Params);
            } else if (name.equals("Focus") && param.length() == 2) {
                if((double)Params[0] != 100) Focus.enable = true; else Focus.enable = false;
                return new Focus((double)Params[0], (double)Params[1]);
            } else {
                System.out.println(name + "with" + param.length() + "params - Dont find Cons");
                return null;
            }
        }
    }

    public static JSONObject getObj(JSONObject obj, String key) {
        try {
            return obj.getJSONObject(key);
        } catch (JSONException var3) {
            return null;
        }
    }

    public static JSONArray getArr(JSONObject obj, String key) {
        try {
            return obj.getJSONArray(key);
        } catch (JSONException var3) {
            return null;
        }
    }

    public static String getStr(JSONObject obj, String key) {
        try {
            return obj.getString(key);
        } catch (JSONException var3) {
            return null;
        }
    }
}
