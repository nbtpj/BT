package object;

import engine.GameWorld;
import engine.ImageClass;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Circle;

public class Player extends Sprite implements Collision {
    public final double velocity = 10;
    public boolean pressA;
    public boolean pressS;
    public boolean pressD;
    public boolean pressW;

    public Player(ImageClass imageClass, String id, double height, double width, double x, double y, double r) {
        node = imageClass.getImageView(id, height, width);
        node.setTranslateX(x - r);
        node.setTranslateY(y - r);
        collisionBound = new Circle();
        this.setupCircleCBound(collisionBound, x, y, r);
    }

    @Override
    public void update() {
        vX = 0;
        vY = 0;
        if (pressA) {
            vX -= velocity;
        }
        if (pressW) {
            vY -= velocity;
        }
        if (pressD) {
            vX += velocity;
        }
        if (pressS) {
            vY += velocity;
        }

        node.setTranslateX(node.getTranslateX() + vX);
        node.setTranslateY(node.getTranslateY() + vY);
        System.out.println("x: " + node.getTranslateX() + " ; y: " + node.getTranslateY());
    }

    @Override
    public void handleDeath(GameWorld gameWorld) {

    }

    @Override
    public boolean collide(Collision other) {
        Circle tC = this.collisionBound;
        Circle oC = ((Sprite) other).collisionBound;
        double tR = tC.getRadius();
        double oR = oC.getRadius();
        double tX = tC.getTranslateX();
        double tY = tC.getTranslateY();
        double oX = oC.getTranslateX();
        double oY = oC.getTranslateY();
        return (tX - oX) * (tX - oX) + (tY - oY) * (tY - oY) < (tR + oR) * (tR + oR);
    }
}
