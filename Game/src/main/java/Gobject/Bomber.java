package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Bomber extends Movable_Object{
    public Bomber(String name, double x, double y) {
        super(100, name, x, y);
    }

    public Bomber(String name, Pos pos) {
        super(100, name, pos);
    }

    @Override
    public List<Gobject> update(double t) {
        return null;
    }

    @Override
    public Image render() {
        return null;
    }
}
