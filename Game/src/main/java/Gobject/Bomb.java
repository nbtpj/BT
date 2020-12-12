package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Gobject {


    private final Image[] frame;
    private int current_frame;
    public Bomb(Simple_Data data) {
        super(data);
        frame = Data.getInstance().bomb;
        current_frame = -1;
    }
    public Bomb(String name, double x, double y) {
        super(3, name, x, y);
        frame = Data.getInstance().bomb;
        current_frame = -1;

    }

    public Bomb(String name, Pos pos) {
        super(3, name, pos);
        frame = Data.getInstance().bomb;
        current_frame = -1;
    }

    @Override
    public List<Gobject> update(double t) {

        if (index <= 0) using = false;
        index -= t;
        current_frame = (current_frame + 1) % frame.length;
        if (index <= 0) {
            List<Gobject> rs = new ArrayList<>();
            rs.add(new Fire(this.pos().left()));
            rs.add(new Fire(this.pos().right()));
            rs.add(new Fire(this.pos().up()));
            rs.add(new Fire(this.pos().down()));
            rs.add(new Fire(this.pos()));
            rs.add(new Enemy("enemy","Enemy16-6",this.pos()));
            return rs;
        }
        return null;
    }

    @Override
    public Image render() {
        current_frame = (current_frame + 1) % frame.length;
        return frame[current_frame];
    }


}
