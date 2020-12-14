package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Wall extends Gobject{

    public Wall(double x, double y) {
        super(200, "Wall", x, y);
    }

    public Wall(Pos pos) {
        super(200, "Wall", pos);
    }

    public Wall(Simple_Data data) {
        super(data);
    }

    @Override
    public List<Gobject> update(double t) {
        if(index<=0){
            using = false;
            List<Gobject> rs = new ArrayList<>();
            int i= (int) (Math.random()*135);
            if(i<20) {
                rs.add(new Poison(pos()));
            } else if(i<40) {
                rs.add(new Rage(pos()));
            } else if(i<60) {
                rs.add(new Invincible(pos()));
            } else if(i<80) {
                rs.add(new Invisible(pos()));
            } else if(i<100) {
                    rs.add(new Healing(pos()));
            } else if(i<110) {
                rs.add(new Enemy_1("enemy",pos()));
            }   else if(i<120) {
                rs.add(new Enemy_2("enemy",pos()));
            }   else if(i<125) {
                rs.add(new Enemy_3("enemy",pos()));
            }   else if(i<130) {
                rs.add(new Enemy_4("enemy",pos()));
            } else rs.add(new Enemy_5("enemy",pos()));
            return rs;
        }
        return null;
    }

    @Override
    public Image render() {
        return Data.getInstance().wall;
    }
}
