package Gobject;

import Support_Type.Pos;

import java.util.List;
import java.util.Random;

/**
 * enemy level 2
 */
public class Enemy_2 extends Enemy {
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
        index = 70;
        maxIndex = 70;
    }

    /**
     * constructor
     *
     * @param name
     * @param pos
     */
    public Enemy_2(String name, Pos pos) {
        super(name, "Enemy14-1", pos);
        index = 70;
        maxIndex = 70;
    }

    protected List<Gobject> attack(double t) {
        for (Gobject o : current_map.get(pos())) {
            if (o instanceof Bomber && o.invincible <= 0 && o.invincible <= 0) {
                o.index -= base_damage*2;
            }
        }

        for (Gobject o : current_map.get(pos().left())) {
            if (o instanceof Bomber && o.invincible <= 0 && o.invincible <= 0) {
                o.index -= base_damage*2;
            }
        }
        for (Gobject o : current_map.get(pos().right())) {
            if (o instanceof Bomber && o.invincible <= 0 && o.invincible <= 0) {
                o.index -= base_damage*2;
            }
        }
        for (Gobject o : current_map.get(pos().up())) {
            if (o instanceof Bomber && o.invincible <= 0 && o.invincible <= 0) {
                o.index -= base_damage*2;
            }
        }
        for (Gobject o : current_map.get(pos().down())) {
            if (o instanceof Bomber && o.invincible <= 0 && o.invincible <= 0) {
                o.index -= base_damage*2;
            }
        }

        return null;
    }

    @Override
    protected String decide() {
        if (current_map.Check(pos().left()).equals("Invalid") && direction == "left") {
            direction = "right";
            update_time = 0;
        } else if (current_map.Check(pos().right()).equals("Invalid") && direction == "right") {
            direction = "left";
            update_time = 0;
        } else if (current_map.Check(pos().up()).equals("Invalid") && direction == "up") {
            direction = "down";
            update_time = 0;
        } else if (current_map.Check(pos().down()).equals("Invalid") && direction == "down") {
            direction = "up";
            update_time = 0;
        }

        v_x = default_vx;
        v_y = default_vy;
        Pos crr, l;
        left.clear();
        right.clear();
        up.clear();
        down.clear();
        crr = pos().left();
        while (current_map.Check(crr).equals("Valid") && left.size() < 4) {
            l = crr.left();
            crr = l;
            for (Gobject o : current_map.get(crr)) {
                if ((o instanceof Bomber || o instanceof Effect) && o.invisible <= 0) {
                    v_x = 1.5 * default_vx;
                    v_y = 1.5 * default_vy;
                    return "left";
                }
            }
            left.add(crr);
        }
        crr = pos().right();
        while (current_map.Check(crr).equals("Valid") && right.size() < 4) {
            l = crr.right();
            crr = l;
            for (Gobject o : current_map.get(crr)) {
                if ((o instanceof Bomber || o instanceof Effect) && o.invisible <= 0) {
                    v_x = 1.5 * default_vx;
                    v_y = 1.5 * default_vy;
                    return "right";
                }
            }
            right.add(crr);
        }
        crr = pos().up();
        while (current_map.Check(crr).equals("Valid") && up.size() < 4) {
            l = crr.up();
            crr = l;
            for (Gobject o : current_map.get(crr)) {
                if ((o instanceof Bomber || o instanceof Effect) && o.invisible <= 0) {
                    v_x = 1.5 * default_vx;
                    v_y = 1.5 * default_vy;
                    return "up";
                }
            }
            up.add(crr);
        }
        crr = pos().down();
        while (current_map.Check(crr).equals("Valid") && down.size() < 4) {
            l = crr.down();
            crr = l;
            for (Gobject o : current_map.get(crr)) {
                if ((o instanceof Bomber || o instanceof Effect) && o.invisible <= 0) {
                    v_x = 1.5 * default_vx;
                    v_y = 1.5 * default_vy;
                    return "down";
                }
            }
            down.add(crr);
        }

        if ((direction == "left" || direction == "right") && left.size() == 0 && right.size() == 0) {
            if (up.size() > down.size()) {
                return "up";
            } else return "down";
        }
        if ((direction == "up" || direction == "down") && up.size() == 0 && down.size() == 0) {
            if (left.size() > right.size()) {
                return "left";
            } else return "right";
        }
        int i = (new Random()).nextInt(10);
        if (update_time <= 0) {
            update_time = 2.5;

            if (left.size() > 0 && right.size() > 0 && up.size() > 0 && down.size() > 0) {
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
            }

        }
        return direction;
    }
}
