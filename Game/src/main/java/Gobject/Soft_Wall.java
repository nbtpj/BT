package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

public class Soft_Wall extends Wall {

    public Soft_Wall(double x, double y) {
        super(x, y);
        index = 10;
    }

    public Soft_Wall(Pos pos) {
        super(pos);
        index = 10;
    }

    public Soft_Wall(Simple_Data data) {
        super(data);
        index = 10;
    }

    public Image render() {
        return Data.get("soft_wall.jpg");
    }
}
