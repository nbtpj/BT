package object;

import java.util.HashSet;
import java.util.Set;

public class Bomber extends Sprite {
    public String Name;


    public Set<Event> Update(double time){
        int x_t = this.map_x();
        int y_t = this.map_y();

        Set<Event> new_event = new HashSet<>();

        return new_event;
    }
}
