package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;

public class Wall extends Sprite implements Collision {

    public Wall(double height, double width, double x, double y) {
        node = Images.wall.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle)collisionBound, x, y, height, width);
    }
}
