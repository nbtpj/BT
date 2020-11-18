package object;

import engine.GameWorld;
import engine.ImageClass;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Circle;
import maxxam.App;

public class Player extends Sprite implements Collision {
    public final double velocity = 1;
    public boolean pressA;
    public boolean pressS;
    public boolean pressD;
    public boolean pressW;

    public Player(ImageClass imageClass, String id, double height, double width, double x, double y, double r) {
        node = imageClass.getImageView(id, height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
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
        collisionBound.setTranslateX(node.getTranslateX() + vX);
        collisionBound.setTranslateY(node.getTranslateY() + vY);
    }

    @Override
    public void executeCollision() {
        boolean collideWall = false;
        for(Sprite sprite: App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Wall) {
                if (collide((Collision)sprite)) {
                    collideWall = true;
                    break;
                }
            }
            if (sprite instanceof Explore) {
                if (collide((Collision)sprite)) {
                    App.gameWorld.destroy(sprite);
                }
            }
        }
        if (collideWall) {
            node.setTranslateX(node.getTranslateX() - vX);
            node.setTranslateY(node.getTranslateY() - vY);
            collisionBound.setTranslateX(node.getTranslateX() - vX);
            collisionBound.setTranslateY(node.getTranslateY() - vY);
        }
    }


    @Override
    public void handleDeath() {

    }
}
