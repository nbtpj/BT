package object;

import engine.ImageClass;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Circle;

public class Wall extends Sprite implements Collision {

    public Wall(ImageClass imageClass, String id, double height, double width, double x, double y, double r) {
        node = imageClass.getImageView(id, height, width);
        node.setTranslateX(x-r);
        node.setTranslateY(y-r);
        collisionBound = new Circle();
        this.setupCircleCBound(collisionBound, x - r, y - r, r);
    }

    @Override
    public boolean collide(Collision other) {
        return false;
    }
}
