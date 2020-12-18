package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Invincible extends  Effect{

    public Invincible(Pos pos) {
        super( "Invincible"+System.nanoTime(), pos);
    }

    public Invincible(Simple_Data data) {
        super(data);
    }

    @Override
    public List<Gobject> update(double t) {
        index-=t;
        if(index<=0){
            using = false;
        }
        for(Gobject o: current_map.get(pos())){
            if (o instanceof Movable_Object){
                o.invincible =6;
                o.max_invincible_time =6;
                using = false;
                return null;
            }
        }
        return null;    }

    @Override
    public Image render() {
        return Data.get("invincible");
    }
}
