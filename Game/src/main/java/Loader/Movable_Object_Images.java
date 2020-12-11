package Loader;

import javafx.scene.image.Image;

import java.util.*;

public class Movable_Object_Images {
    public String name;
    public Map<String,Image[]> ct;
    private static Map<String,Movable_Object_Images> data = null;

    public Movable_Object_Images(String name) {
        this.name = name;
        ct = new HashMap<>();
    }
    public Image[] get(String key){
        return ct.get(key);
    }


    public static Map<String,Movable_Object_Images> getData() {
        if (data == null) {
            try {
                data = new HashMap<>();
                Map<String, Image> temp = Data.getInstance().img_map;
                Set<String> name_ob = new HashSet<>();
                for (String name : temp.keySet()) {
                    if (name.contains("_front_") || name.contains("_back") || name.contains("_right_") || name.contains("_left_")) {
                        name_ob.add(name.substring(0, name.indexOf('_')));
                    }
                }
                List<String> dir = new ArrayList<>();
                dir.add("left");
                dir.add("right");
                dir.add("back");
                dir.add("front");
                for (String name : name_ob) {
                    Movable_Object_Images g = new Movable_Object_Images(name);
                    List<Image> t = new ArrayList<>();
                    for(String di :dir){
                        int i =0;

                        while(temp.containsKey(name+"_"+di+"_"+i)){
                            System.out.println(i);
                            t.add(temp.get(name+"_"+di+"_"+i));
                            i++;
                        }
                        g.ct.put(di,t.toArray(new Image[t.size()]));
                        t.clear();
                    }
                    data.put(name,g);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return data;
    }
    public static void main(String[] args){
        Map<String,Movable_Object_Images> a = Movable_Object_Images.getData();
        for(String key: a.keySet()){
            System.out.println(key);
            System.out.println("left: "+ a.get(key).get("left").length);
            System.out.println("right: "+ a.get(key).get("right").length);
            System.out.println("front: "+ a.get(key).get("front").length);
            System.out.println("back: "+ a.get(key).get("back").length);
        }
    }


}
