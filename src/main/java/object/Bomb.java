package object;

import event.Event;
import event.Bomb.*;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends G_Obj{
    public double time;

    public Bomb(double x, double y, BigMap current_map) {
        super(x, y, current_map);
        this.time = 4;
    }

    public Bomb(Pos pos) {
        super(pos);
    }

    @Override
    public List<Event> Update(double t) {
        this.time -=t;
        List<Event> result = new ArrayList<>();
        if(time>0){
            result.add(new Init(x,y,time));
        } else {
            result.add(new Explose(x,y));
        }
        return result;
    }
}
