package object;

import java.util.HashSet;
import java.util.Set;

/**
 * bomber object (demo)
 */
public class Bomber extends Sprite {
    public String Name;
    public boolean Set_Bomb = false;
    public int heal = 1000;
    public void Is_Attacked(int ammount){
        heal -= ammount;

    }
    public void Set_Bomb(boolean set){
        this.Set_Bomb = set;
    }

    public void move(String type){
        switch (type){
            case("top"):
                Vx = 0;
                Vy = -1;
                break;
            case("bottom"):
                Vx = 0;
                Vy = 1;
                break;
            case("left"):
                Vx = -1;
                Vy = 0;
                break;
            case("right"):
                Vx = 1;
                Vy = 0;
                break;
            default:
                Vx = 0;
                Vy = 0;
        }

    }

    public Set<Event> Update(double time){
        int x_t = this.map_x();
        int y_t = this.map_y();
        Set<Event> new_event = new HashSet<>();
        if (heal<=0) new_event.add(new Dead(x_t,y_t));
        /**
         * events bla bla ...
         * cập nhập x, y tại đây.(nếu có)
         */




        return new_event;
    }
}
