package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Enemy  extends Gobject{
    public Enemy(String name, double x, double y) {
        super(100, name, x, y);
    }

    public Enemy(String name, Pos pos) {
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
