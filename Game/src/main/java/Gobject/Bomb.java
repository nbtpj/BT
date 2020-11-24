package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Bomb extends Gobject {
    public Bomb(double index, String name, double x, double y) {
        super(index, name, x, y);
    }

    public Bomb(double index, String name, Pos pos) {
        super(index, name, pos);
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
