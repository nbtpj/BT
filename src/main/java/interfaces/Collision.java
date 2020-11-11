package interfaces;

import engine.Sprite;
import javafx.scene.shape.Circle;

public interface Collision {

    default void setupCircleCBound(Circle collisionBound, double x, double y, double r) {
        collisionBound.setTranslateX(x);
        collisionBound.setTranslateY(y);
        collisionBound.setRadius(r);
    }

    boolean collide(Collision other);
}
