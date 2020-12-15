package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Appear extends Gobject{
    public Appear(Simple_Data data) {
        super(data);
    }

    public Appear(String name, double x, double y) {
        super(1, name, x, y);
    }

    public Appear(String name, Pos pos) {
        super(1, name, pos);
    }

    @Override
    public List<Gobject> update(double t) {
        index-=t;
        if(index<=0){
            using = false;
            int r = (new Random()).nextInt(100);
            List<Gobject> rs = new ArrayList<>();
            if(r<20){
                rs.add(new Enemy_1(name+"'s enemy",pos()));
                return rs;
            } else {
                if(r<40){
                    rs.add(new Enemy_2(name+"'s enemy",pos()));
                    return rs;
                } else {
                    if(r<60){
                        rs.add(new Enemy_3(name+"'s enemy",pos()));
                        return rs;
                    } else {
                        if(r<80){
                            rs.add(new Enemy_4(name+"'s enemy",pos()));
                            return rs;
                        } else {
                            rs.add(new Enemy_5(name+"'s enemy",pos()));
                            return rs;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Image render() {
        return Data.get("appear");
    }
}
