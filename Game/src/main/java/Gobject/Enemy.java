package Gobject;

import Loader.Movable_Object_Images;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Enemy  extends Movable_Object{
    /**
     * constructor
     * @param name
     * @param x
     * @param y
     */
    public Enemy(String name,String type, double x, double y) {
        super(50, name, x, y);
        V_x = 3;
        V_y = 3;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
    }

    /**
     * constructor
     * @param name
     * @param pos
     */
    public Enemy(String name,String type, Pos pos) {
        super(50, name, pos);
        V_x = 3;
        V_y = 3;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
    }

    /**
     *
     * @param arg
     */
    public void Act(String arg){

    }
    @Override
    public List<Gobject> update(double t) {
        if(index<=0) using = false;
        if(move_2.isEmpty()) move_2.add("left");
        move();

        return null;
    }

}
