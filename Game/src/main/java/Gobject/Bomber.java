package Gobject;

import Loader.Data;
import Loader.Movable_Object_Images;
import Support_Type.Pos;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Movable_Object {
    private boolean set_bomb;

    /**
     * constructor
     * Initialize a new bomber name: name, at position (x,y) on map
     *
     * @param name
     * @param x
     * @param y
     */
    public Bomber(String name,String type, double x, double y) {
        super(100, name, x, y);
        V_x = 3;
        V_y = 3;
        set_bomb = false;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");

    }

    /**
     * constructor
     * Initialize a new bomber name: name, at position pos on map
     *
     * @param name
     * @param pos
     */
    public Bomber(String name,String type, Pos pos) {
        super(100, name, pos);
        V_x = 3;
        V_y = 3;
        set_bomb = false;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
    }

    public void Act(String arg) {
        switch (arg) {
            case ("set_bomb"):
                set_bomb = true;
                break;
            case ("left"):
            case ("right"):
            case ("up"):
            case ("down"):
                move_2.clear();
                move_2.add(arg);
                current_frame = -1;
                current_frames = frames.get(arg);
                break;
        }
    }

    @Override
    public List<Gobject> update(double t) {
        if(index<=0) using = false;
        List<Gobject> rs = new ArrayList<>();
        if (set_bomb) {
            rs.add(new Bomb(name + "'s bomb", pos()));
            set_bomb = false;
        }
        move();
        return rs;
    }

}
