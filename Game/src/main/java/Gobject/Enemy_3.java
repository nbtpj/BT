package Gobject;

import Support_Type.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * enemy level 3
 */
public class Enemy_3 extends Enemy{
    List<Pos> target= new ArrayList<>();
    public Enemy_3(Simple_Data data) {
        super(data);
    }

    /**
     * constructor
     *
     * @param name
     * @param x
     * @param y
     */
    public Enemy_3(String name, double x, double y) {
        super(name, "Enemy05-1", x, y);
        index=100;
        maxIndex=100;
    }

    /**
     * constructor
     *
     * @param name
     * @param pos
     */
    public Enemy_3(String name, Pos pos) {
        super(name, "Enemy05-1", pos);
        index=100;
        maxIndex=100;
    }
    protected List<Gobject> attack() {
        List<Gobject> rs = new ArrayList<>();
        for(Pos p: target){
            rs.add(new Bomb(name+"'s bomb"+System.nanoTime(),p));
        }
        return rs;
    }
    @Override
    protected String decide() {
        target.clear();
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
                if(((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0)){
                    if(o instanceof Bomber) {target.add(crr);}
                    else direction = "left";
                }
            }
            left.add(crr);
        }
        crr = pos().right();
        while(current_map.Check(crr).equals("Valid")){
            l =crr.right();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0)){
                    if(o instanceof Bomber) {target.add(crr);}
                    else direction = "right";
                }
            }
            right.add(crr);
        }
        crr = pos().up();
        while(current_map.Check(crr).equals("Valid")){
            l =crr.up();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0)){
                    if(o instanceof Bomber) {target.add(crr);}
                    else direction = "up";
                }
            }
            up.add(crr);
        }
        crr = pos().down();
        while(current_map.Check(crr).equals("Valid")){
            l =crr.down();
            crr=l;
            for (Gobject o:current_map.get(crr)){
                if(((o instanceof Bomber|| o instanceof Effect)&& o.invisible<=0)){
                    if(o instanceof Bomber) {target.add(crr);}
                    else direction = "down";
                }
            }
            down.add(crr);
        }
        int i = (new Random()).nextInt(10);
        if(update_time<0 ) {
            update_time = 5;

            if (left.size() > 0 && right.size() > 0 && up.size() > 0 && down.size() > 0) {
                /**
                 * crossroads
                 */
                switch (direction) {
                    case ("left"):
                        if (i == 0) {
                            return "left";
                        } else {
                            if (i > 0 && i < 4) {
                                return "right";
                            } else {
                                if (i > 3 && i < 7) {
                                    return "up";
                                } else {
                                    return "down";
                                }
                            }
                        }
                    case ("right"):
                        if (i == 0) {
                            return "right";
                        } else {
                            if (i > 0 && i < 4) {
                                return "left";
                            } else {
                                if (i > 3 && i < 7) {
                                    return "up";
                                } else {
                                    return "down";
                                }
                            }
                        }
                    case ("up"):
                        if (i == 0) {
                            return "up";
                        } else {
                            if (i > 0 && i < 4) {
                                return "left";
                            } else {
                                if (i > 3 && i < 7) {
                                    return "right";
                                } else {
                                    return "down";
                                }
                            }
                        }
                    case ("down"):
                        if (i == 0) {
                            return "down";
                        } else {
                            if (i > 0 && i < 4) {
                                return "left";
                            } else {
                                if (i > 3 && i < 7) {
                                    return "up";
                                } else {
                                    return "right";
                                }
                            }
                        }
                }
            } else {
                /**
                 * fork way
                 */
                if((Math.random()*10)>5){
                    if(direction=="left"|| direction=="right"){
                        if(up.size()>down.size()){
                            return "up";
                        } else {
                            if(down.size()>0){
                                return "down";
                            }
                        }
                    } else {
                        if(left.size()>right.size()){
                            return "left";
                        } else {
                            if(right.size()>0){
                                return "right";
                            }
                        }
                    }
                }
            }
        }
        return direction;
    }
}
