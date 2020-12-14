package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Wall extends Gobject{

    public Wall(double x, double y) {
        super(10000, "Wall", x, y);
    }

    public Wall(Pos pos) {
        super(10000, "Wall", pos);
    }

    public Wall(Simple_Data data) {
        super(data);
    }

    @Override
    public List<Gobject> update(double t) {
        if(index<=0){
            using = false;
            List<Gobject> rs = new ArrayList<>();
            int i= (int) (Math.random()*5);
            switch (i){
                case (0):
                    rs.add(new Poison(pos()));
                    break;
                case(1):
                    rs.add(new Rage(pos()));
                    break;
                case(2):
                    rs.add(new Invincible(pos()));
                    break;
                case(3):
                    rs.add(new Invisible(pos()));
                    break;
                case(4):
                    rs.add(new Healing(pos()));
                    break;
            }
            return rs;
        }
        return null;
    }

    @Override
    public Image render() {
        return Data.getInstance().wall;
    }
}
