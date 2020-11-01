package object;

import event.Event;
import event.Fire.*;
import java.util.ArrayList;
import java.util.List;

public class Fire extends G_Obj {
    public Fire(double x, double y, BigMap current_map) {
        super(x, y, current_map);
    }

    @Override
    public List<Event> Update(double time) {
        List<Event> result = new ArrayList<>();
        result.add(new Init(x,y));
        return null;
    }
}
