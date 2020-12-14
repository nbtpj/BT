package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;


public class Poison extends Effect{
    public Poison(Simple_Data data) {
        super(data);
    }


    public Poison(Pos pos) {
        super("Poison"+System.nanoTime(), pos);
    }

    @Override
    public List<Gobject> update(double t) {
        index -= t;
        if(index<=0){
            using = false;
        }
        for(Gobject o: current_map.get(pos())){
            if (o instanceof Movable_Object){
                o.poisonous = 6;
                o.max_poisonous_time =6;
                o.rage = 4;
                o.max_rage_time=4;
                o.invisible = 4;
                o.max_invisible_time =4;
                o.invincible =3;
                o.max_invincible_time =3;
                using = false;
                return null;
            }
        }
        return null;
    }
    @Override
    public Image render() {
        return Data.get("poison");
    }
}
