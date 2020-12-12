package Gobject;

import Loader.Movable_Object_Images;
import Support_Type.Pos;

import java.util.List;

public class Enemy extends Movable_Object {
    private double damage;

    public Enemy(Simple_Data data) {
        super(data);
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
    }

    /**
     * constructor
     *
     * @param name
     * @param x
     * @param y
     */
    public Enemy(String name, String type, double x, double y) {
        super(50, name, x, y);
        this.v_x = 1;
        this.v_y = 1;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
    }

    /**
     * constructor
     *
     * @param name
     * @param pos
     */
    public Enemy(String name, String type, Pos pos) {
        super(50, name, pos);
        this.v_x = 1;
        this.v_y = 1;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
    }

    @Override
    public List<Gobject> update(double t) {
        if(current_map.Check(pos().left()).equals("Invalid") && move_2.size()<2){
            move_2.clear();
            move_2.add("right");
        } else if(current_map.Check(pos().right()).equals("Invalid") && move_2.size()<2){
            move_2.add("left");
        } else if(current_map.Check(pos().up()).equals("Invalid") && move_2.size()<2){
            move_2.add("down");
        } else if(current_map.Check(pos().down()).equals("Invalid") && move_2.size()<2){
            move_2.add("up");
        }
        move();
        return null;
    }

    /**
     * allow directly change object's parameters
     *
     * @param arg
     */
    @Override
    public void Act(String arg) {

    }


}
