package object;

import event.Event;

import java.util.List;

public class Enemy extends G_Obj{

    public Enemy(double x, double y, BigMap current_map) {
        super(x, y, current_map);
    }

    @Override
    public List<Event> Update(double time) {
        return null;
    }
}
