package interfaces;

import engine.Sprite;
import javafx.scene.shape.Circle;

public interface Collision {

    default void setupCircleCBound(Circle collisionBound, double x, double y, double r) {
        collisionBound.setTranslateX(x);
        collisionBound.setTranslateY(y);
        collisionBound.setRadius(r);
    }

    default boolean collide(Collision other) {
        if (this instanceof Sprite) {
            Circle tC = ((Sprite) this).collisionBound;
            Circle oC = ((Sprite) other).collisionBound;
            double tR = tC.getRadius();
            double oR = oC.getRadius();
            double tX = tC.getTranslateX();
            double tY = tC.getTranslateY();
            double oX = oC.getTranslateX();
            double oY = oC.getTranslateY();
            System.out.println((tX - oX) + " " + (tY - oY) + " " + (tR + oR));
            return (tX - oX) * (tX - oX) + (tY - oY) * (tY - oY) < (tR + oR) * (tR + oR);
        }
        return false;
    }
}
