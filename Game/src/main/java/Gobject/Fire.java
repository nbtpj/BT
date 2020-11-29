package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Fire extends Gobject {
    public Fire( double x, double y) {
        super(1, "fire", x, y);
    }

    public Fire( Pos pos) {
        super(1, "fire", pos);
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
