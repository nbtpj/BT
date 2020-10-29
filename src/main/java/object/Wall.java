package object;

import event.Event;

import java.util.List;

public class Wall extends G_Obj {

    public Wall(double x, double y, BigMap current_map) {
        super(x, y, current_map);
    }

    @Override
    public List<Event> Update(double time) {
        return null;
    }
}
