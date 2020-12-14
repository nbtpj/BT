package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Invisible extends Effect {
    public Invisible(Simple_Data data) {
        super(data);
    }

    public Invisible(Pos pos) {
        super("Invisible"+System.nanoTime(), pos);
    }

    @Override
    public List<Gobject> update(double t) {
        index-=t;
        if(index<=0){
            using = false;
        }
        for(Gobject o: current_map.get(pos())){
            if (o instanceof Movable_Object){
                o.invisible =6;
                o.max_invisible_time =6;
                using = false;
                return null;
            }
        }
        return null;
    }

    @Override
    public Image render() {
        return Data.get("invisible");
    }
}
