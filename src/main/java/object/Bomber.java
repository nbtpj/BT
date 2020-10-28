package object;

import java.util.HashSet;
import java.util.Set;
import event.Bomber.*;
import event.Event;
/**
 * bomber object (demo)
 */
public class Bomber extends G_Obj {
    public String name;
    public int heal;
    /**
     * đây là dãy lệnh đưa vào theo format sau
     * commands[0]= hướng di chuyển (none|left|right|top|bottom)
     * commands[1]= đặt boom (true|false)
     *
     */
    public Set<String> commands;
    public Bomber(double x, double y, BigMap current_map,String name) {
        super(x, y, current_map);
        this.name = name;
        this.heal = 1000;
    }
    public void Get_Control(Set<String> in){
        this.commands = in;
    }
    public Event Is_Attacked(int amount){
        heal -= amount;
        return new Lost_Heal(x,y,name,heal);

    }
    public Event set_bomb(boolean set){
        return new Set_Bomb(x,y,name,heal);
    }

    public Event move(String type){
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
        return new Move(x,y,name,heal,type);
    }
    public Event is_dead(){
        if (heal<=0){
            return new Dead(x,y,name,heal);
        }
        return null;
    }


    public Set<Event> Update(double time){


        int x_t = this.map_x();
        int y_t = this.map_y();
        Set<Event> new_event = new HashSet<>();
        if (heal<=0) {
            new_event.add(new Dead(x_t,y_t,"bomber A",this.heal));
            _Use = false;
        }
        /**
         * events bla bla ...
         * cập nhập x, y tại đây.(nếu có)
         */




        return new_event;
    }
}
