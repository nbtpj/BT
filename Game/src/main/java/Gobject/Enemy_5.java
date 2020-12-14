package Gobject;

import Support_Type.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * enemy level 5
 */
public class Enemy_5 extends Enemy {
    double wait =6;
    List<Pos> target= new ArrayList<>();
    public Enemy_5(Simple_Data data) {
        super(data);
    }

    /**
     * constructor
     *
     * @param name
     * @param x
     * @param y
     */
    public Enemy_5(String name, double x, double y) {
        super(name, "Enemy01-1", x, y);
    }

    /**
     * constructor
     *
     * @param name
     * @param pos
     */
    public Enemy_5(String name, Pos pos) {
        super(name, "Enemy01-1", pos);
    }

    @Override
    protected List<Gobject> attack(double t) {
        List<Gobject> rs = new ArrayList<>();
        wait-=t;
        if(wait<=0){
            wait=6;
            for(Pos p: target){
                rs.add(new Enemy_2(name+"'s child"+System.nanoTime(),p));
            }
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
        while(current_map.Check(crr).equals("Valid")&& left.size()<5){
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
        while(current_map.Check(crr).equals("Valid")&& right.size()<5){
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
        while(current_map.Check(crr).equals("Valid")&& up.size()<5){
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
        while(current_map.Check(crr).equals("Valid")&& down.size()<5){
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
        if(!target.isEmpty()){
            return "none";
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
