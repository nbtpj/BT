package object;

import engine.ImageClass;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Circle;

public class Wall extends Sprite implements Collision {

    public Wall(ImageClass imageClass, String id, double height, double width, double x, double y, double r) {
        node = imageClass.getImageView(id, height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Circle();
        this.setupCircleCBound(collisionBound, x, y, r);
    }
}
