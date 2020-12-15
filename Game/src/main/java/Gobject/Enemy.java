package Gobject;

import Loader.Movable_Object_Images;
import Support_Type.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * enemy type
 */
public abstract class Enemy extends Movable_Object {
    int sight=6;
    public static double base_damage =0.1;
    String bomber_at="none";
    protected double update_time = 2.4;
    protected String direction;
    List<Pos> left = new ArrayList<>(),
            right = new ArrayList<>(),
            up = new ArrayList<>(),
            down = new ArrayList<>();
    public Enemy(Simple_Data data) {
        super(data);
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
        last_enter = pos();
        direction = "left";
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
        default_vx=1;
        default_vy=1;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
        last_enter = pos();
        direction = "left";
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
        default_vx=1;
        default_vy=1;
        this.type = type;
        frames.put("left", Movable_Object_Images.getData().get(type).get("left"));
        frames.put("right", Movable_Object_Images.getData().get(type).get("right"));
        frames.put("up", Movable_Object_Images.getData().get(type).get("back"));
        frames.put("down", Movable_Object_Images.getData().get(type).get("front"));
        current_frames = Movable_Object_Images.getData().get(type).get("front");
        last_enter = pos();
        direction = "left";
    }

    @Override
    public List<Gobject> update(double t) {
        direction = decide();
        update_time-=t;
        List<Gobject> rs= attack(t);
        if(rage>0){
            rage-=t;
            this.v_x=1.5*this.default_vx;
            this.v_y=1.5*this.default_vy;
        } else {
            v_x=default_vx;
            v_y=default_vy;
        }
        if(invincible>0){
            invincible-=t;
        }
        if(invisible>0){
            invisible-=t;
        }
        if(poisonous>0){
            index-=0.06;
            poisonous-=t;
        }
        if(healing>0){
            if(index+0.05<maxIndex){
                index+=0.05;}
            healing-=t;
        }
        if(index<=0){
            this.using = false;
        }
        move();
        return rs;
    }
    abstract protected List<Gobject> attack(double t);
    public void move() {
        if (current_frame < current_frames.length && direction!="none") {
            current_frame = (++current_frame)% current_frames.length;
            double new_x = x, new_y = y;
            current_frames = frames.get(direction);
            switch (direction) {
                case "down":
                    new_y += this.v_y;
                    break;
                case "up":
                    new_y -= this.v_y;
                    break;
                case "left":
                    new_x -= this.v_x;
                    break;
                case "right":
                    new_x += this.v_x;
                    break;
            }
            Pos new_pos = new Pos(new_x, new_y);
            if (this.pos().equals(new_pos) || !this.current_map.Check(new_pos).equals("Invalid")
                    || (this.invisible>0 && current_map.get(new_pos)!=null)) {
                last_enter = pos();
                x = new_x;
                y = new_y;
            }
        }
    }

    /**
     * allow directly change object's parameters
     *
     * @param arg
     */
    @Override
    public void Act(String arg) {
        List<Pos> rs;
        if(left.size()>right.size() && left.size()>up.size()&& left.size()>down.size() && !left.contains(last_enter)){
            rs = left;
        } else {
            if(right.size()>left.size() && right.size()>up.size()&& right.size()>down.size()&&!right.contains(last_enter)){
                rs = right;
            } else {
                if(up.size()>right.size() && up.size()>left.size()&& up.size()>down.size()&&!up.contains(last_enter)){
                    rs = up;
                } else {
                    rs = down;
                }
            }
        }
        move_2 = Pos.Pos2move(x,y,v_y*frames.get("up").length,v_y*frames.get("down").length,
                v_x*frames.get("left").length,v_x*frames.get("right").length,rs);
        System.out.println(move_2);
    }
    protected String turnBack(){
        switch (direction){
            case("left"):
                return "right";
            case("right"):
                return "left";
            case("up"):
                return "down";
            case ("down"):
                return "up";
            default:
                return "none";
        }
    }
    protected abstract String decide();
    protected HashMap<String,List<Pos>> way(){
        bomber_at = "none";
        HashMap<String,List<Pos>> rs = new HashMap<>();
        Pos cur = pos(),l,crr;
        left.clear();
        right.clear();
        up.clear();
        down.clear();
        crr = cur.left();
        while(current_map.Check(crr).equals("Valid")&& left.size()<sight){
            l =crr.left();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
                    bomber_at= "left";
                }
            }
            left.add(crr);
        }
        crr = cur.right();
        while(current_map.Check(crr).equals("Valid")&& right.size()<sight){
            l =crr.right();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
                    bomber_at= "right";
                }
            }
            right.add(crr);
        }
        crr = cur.up();
        while(current_map.Check(crr).equals("Valid")&& up.size()<sight){
            l =crr.up();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
                    bomber_at= "up";
                }
            }
            up.add(crr);
        }
        crr = cur.down();
        while(current_map.Check(crr).equals("Valid")&& down.size()<sight){
            l =crr.down();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
                    bomber_at= "down";
                }
            }
            down.add(crr);
        }
        rs.put("left",left);
        rs.put("right",right);
        rs.put("up",up);
        rs.put("down",down);
        return rs;
    }
    

}
