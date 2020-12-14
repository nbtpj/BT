package Gobject;

import Loader.Movable_Object_Images;
import Support_Type.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Movable_Object {
    private double damage;
    private double update_time = 5;
    private double max_heal=50;
    private String direction;
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
            if(index+0.05<max_heal){
                index+=0.05;}
            healing-=t;
        }
        direction = decide();
        update_time-=t;
        if(current_map.Check(pos().left()).equals("Invalid") && direction=="left"){
            direction=  "right";
        } else if(current_map.Check(pos().right()).equals("Invalid") && direction=="right"){
            direction = "left";
        } else if(current_map.Check(pos().up()).equals("Invalid") && direction=="up"){
            direction = "down";
        } else if(current_map.Check(pos().down()).equals("Invalid") && direction=="down"){
            direction = "up";
        }

        move();
        return null;
    }
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
            if (this.pos().equals(new_pos) || !this.current_map.Check(new_pos).equals("Invalid")) {
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

     //   System.out.println("domewkn");
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
    private String turnBack(){
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
    private String decide(){
        Pos crr,l;
        left.clear();
        right.clear();
        up.clear();
        down.clear();
        crr = pos().left();
        while(current_map.Check(crr).equals("Valid")){
            l =crr.left();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber){
                    return "left";
                }
            }
            left.add(crr);
        }
        crr = pos().right();
        while(current_map.Check(crr).equals("Valid")){
            l =crr.right();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber){
                    return "right";
                }
            }
            right.add(crr);
        }
        crr = pos().up();
        while(current_map.Check(crr).equals("Valid")){
            l =crr.up();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber){
                    return "up";
                }
            }
            up.add(crr);
        }
        crr = pos().down();
        while(current_map.Check(crr).equals("Valid")){
            l =crr.down();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber){
                    return "down";
                }
            }
            down.add(crr);
        }
        int i = (new Random()).nextInt(10);
        if(update_time<0 &&left.size()>0 && right.size()>0 && up.size()>0 && down.size()>0){
            update_time =5;
            switch (direction){
            case ("left"):
                if(i==0){
                    return "left";
                } else {
                    if(i>0 && i<4){
                        return "right";
                    } else {
                        if(i>3 && i<7){
                            return "up";
                        } else {
                            return "down";
                        }
                    }
                }
            case ("right"):
                if(i==0){
                    return "right";
                } else {
                    if(i>0 && i<4){
                        return "left";
                    } else {
                        if(i>3 && i<7){
                            return "up";
                        } else {
                            return "down";
                        }
                    }
                }
            case ("up"):
                if(i==0){
                    return "up";
                } else {
                    if(i>0 && i<4){
                        return "left";
                    } else {
                        if(i>3 && i<7){
                            return "right";
                        } else {
                            return "down";
                        }
                    }
                }
            case ("down"):
                if(i==0){
                    return "down";
                } else {
                    if(i>0 && i<4){
                        return "left";
                    } else {
                        if(i>3 && i<7){
                            return "up";
                        } else {
                            return "right";
                        }
                    }
                }
        }
        }
        return direction;
    }



}
