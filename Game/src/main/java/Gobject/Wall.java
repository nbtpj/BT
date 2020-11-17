package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Wall extends Gobject{

    public Wall(double index, String name, double x, double y) {
        super(10000, "Wall", x, y);
    }

    public Wall(double index, String name, Pos pos) {
        super(10000, "Wall", pos);
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
