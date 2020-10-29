package object;

import event.Event;

import java.util.List;

public class Item extends G_Obj{
    public Item(double x, double y, BigMap current_map) {
        super(x, y, current_map);
    }

    @Override
    public List<Event> Update(double time) {
        return null;
    }
}
