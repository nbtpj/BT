package object;

import engine.GameWorld;
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
        this.setupCircleCBound(collisionBound, x, y, r);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleDeath(GameWorld gameWorld) {

    }

    @Override
    public boolean collide(Collision other) {
        return false;
    }
}
