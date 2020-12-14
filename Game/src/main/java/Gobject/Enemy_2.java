package Gobject;

import Support_Type.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * enemy level 2
 */
public class Enemy_2 extends Enemy{
    public Enemy_2(Simple_Data data) {
        super(data);
    }

    /**
     * constructor
     *
     * @param name
     * @param x
     * @param y
     */
    public Enemy_2(String name, double x, double y) {
        super(name, "Enemy14-1", x, y);
        index=70;
        maxIndex = 70;
    }

    /**
     * constructor
     * @param name
     * @param pos
     */
    public Enemy_2(String name,  Pos pos) {
        super(name, "Enemy14-1", pos);
        index=70;
        maxIndex =70;
    }
    protected List<Gobject> attack() {
        for(Gobject o: current_map.get(pos())){
            if(o instanceof Bomber && o.invincible<=0 && o.invincible<=0){
                o.index-= 0.02;
            }
        }
        return null;
    }
    @Override
    protected String decide() {
        v_x=default_vx;
        v_y=default_vy;
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
                if((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0){
                    v_x=1.5*default_vx;
                    v_y=1.5*default_vy;
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
                if((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0){
                    v_x=1.5*default_vx;
                    v_y=1.5*default_vy;
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
                if((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0){
                    v_x=1.5*default_vx;
                    v_y=1.5*default_vy;
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
                if((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0){
                    v_x=1.5*default_vx;
                    v_y=1.5*default_vy;
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
