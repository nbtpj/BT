package Gobject;

import java.util.*;
import Support_Type.Pos;

import java.util.List;
import java.util.Random;

/**
 * enemy level 1
 */
public class Enemy_1 extends Enemy{
    public Enemy_1(Simple_Data data) {
        super(data);
        sight = 3;
    }

    @Override
    protected List<Gobject> attack(double t) {
        for(Gobject o: current_map.get(pos())){
            if(o instanceof Bomber && o.invincible<=0 && o.invincible<=0){
                o.index-= 1;
            }
        }
        return null;
    }

    /**
     * constructor
     *
     * @param name
     * @param x
     * @param y
     */
    public Enemy_1(String name, double x, double y) {
        super(name, "Enemy15-1", x, y);
        sight = 3;
    }

    /**
     * constructor
     *
     * @param name
     * @param pos
     */
    public Enemy_1(String name, Pos pos) {
        super(name, "Enemy15-1", pos);
        sight = 3;
    }
    public String decide(){

        if(current_map.Check(pos().left()).equals("Invalid") && direction=="left"){
            direction=  "none";
            update_time = 0;
        } else if(current_map.Check(pos().right()).equals("Invalid") && direction=="right"){
            direction = "none";
            update_time=0;
        } else if(current_map.Check(pos().up()).equals("Invalid") && direction=="up"){
            direction = "none";
            update_time = 0;
        } else if(current_map.Check(pos().down()).equals("Invalid") && direction=="down"){
            direction = "none";
            update_time=0;
        }

        if(update_time>0) {
            return direction;
        }
        update_time=2.4;
        Map<String,List<Pos>> temp = way();
        if(bomber_at!="none"){
            return bomber_at;
        }
        if(left.size()==0 && right.size()==0 && up.size()==0 && down.size()==0){
            return "none";
        }
        double w_l = left.size(),
                w_r = right.size(),
                w_u = up.size(),
                w_d = down.size();
        if(direction == "left") { w_l*= 0.6; w_r*=0.8;w_d*=10;w_u*=10;}
        if(direction == "right") { w_r*= 0.6;w_l*=0.8;w_d*=10;w_u*=10;}
        if(direction == "up") { w_u*= 0.6;w_d*=0.8;w_r*= 10;w_l*=10;}
        if(direction == "down") { w_d*= 0.6; w_u*=0.8;w_r*= 10;w_l*=10;}
        if(w_l>=w_r&& w_l>=w_u && w_l>=w_d){
            return "left";
        }
        if(w_r>=w_l&& w_r>=w_u && w_r>=w_d){
            return "right";
        }
        if(w_u>=w_r&& w_u>=w_l && w_u>=w_d){
            return "up";
        }
        if(w_d>=w_r&& w_d>=w_u && w_d>=w_l){
            return "down";
        }
        
        return direction;
    }
}
