package Gobject;

import Support_Type.Pos;

import java.util.List;
import java.util.Random;

/**
 * enemy level 1
 */
public class Enemy_1 extends Enemy{
    public Enemy_1(Simple_Data data) {
        super(data);
    }

    @Override
    protected List<Gobject> attack(double t) {
        for(Gobject o: current_map.get(pos())){
            if(o instanceof Bomber && o.invincible<=0 && o.invincible<=0){
                o.index-= 0.01;
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
    }

    /**
     * constructor
     *
     * @param name
     * @param pos
     */
    public Enemy_1(String name, Pos pos) {
        super(name, "Enemy15-1", pos);
    }
    public String decide(){
        Pos crr,l,cur = pos();
        left.clear();
        right.clear();
        up.clear();
        down.clear();
        crr = cur.left();
        while(current_map.Check(crr).equals("Valid")&& left.size()<3){
            l =crr.left();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
                    return "left";
                }
            }
            left.add(crr);
        }
        crr = cur.right();
        while(current_map.Check(crr).equals("Valid")&& right.size()<3){
            l =crr.right();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
                    return "right";
                }
            }
            right.add(crr);
        }
        crr = cur.up();
        while(current_map.Check(crr).equals("Valid")&& up.size()<3){
            l =crr.up();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
                    return "up";
                }
            }
            up.add(crr);
        }
        crr = cur.down();
        while(current_map.Check(crr).equals("Valid")&& down.size()<3){
            l =crr.down();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(o instanceof Bomber && o.invisible<=0){
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
