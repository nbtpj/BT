package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

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
        return null;
    }

    @Override
    public Image render() {
        return Data.getInstance().wall;
    }
}
