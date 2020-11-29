package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Bomb extends Gobject {
    private Image[] frame;
    private int current_frame;
    public Bomb(String name, double x, double y) {
        super(3, name, x, y);
        frame = Data.getInstance().bomb;
        current_frame=-1;

    }

    public Bomb(String name, Pos pos) {
        super(3, name, pos);
        frame = Data.getInstance().bomb;
        current_frame=-1;
    }

    @Override
    public List<Gobject> update(double t) {
        index--;
        current_frame = (current_frame+1)%frame.length;
        if (index<0){
            current_map.AddGobject(new Fire(this.pos().left()));
            current_map.AddGobject(new Fire(this.pos().right()));
            current_map.AddGobject(new Fire(this.pos().up()));
            current_map.AddGobject(new Fire(this.pos().down()));
            current_map.AddGobject(new Fire(this.pos()));
        }
        return null;
    }

    @Override
    public Image render() {
        return frame[current_frame];
    }
}
