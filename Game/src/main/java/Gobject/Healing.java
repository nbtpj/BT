package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Healing extends Effect{
    public Healing(Simple_Data data) {
        super(data);
    }

    public Healing(Pos pos) {
        super("Healing"+System.nanoTime(), pos);
    }

    @Override
    public List<Gobject> update(double t) {
        index-=t;
        if(index<=0){
            using = false;
        }
        for(Gobject o: current_map.get(pos())){
            if (o instanceof Movable_Object){
                o.healing =6;
                o.max_healing_time =6;
                o.poisonous =0;
                using = false;
                return null;
            }
        }
        return null;
    }

    @Override
    public Image render() {
        return Data.get("healing");
    }
}
