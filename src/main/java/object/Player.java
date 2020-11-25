package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

import java.util.Date;

public class Player extends Sprite implements Collision {
    public final double velocity = 1;
    public boolean pressA;
    public boolean pressS;
    public boolean pressD;
    public boolean pressW;

    public Player(double height, double width, double x, double y) {
        node = Images.player_down[0].getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height - 1, width - 6);
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

        double x = node.getTranslateX();
        double y = node.getTranslateY();
        if (vY < 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = Images.player_up[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        } else if (vY > 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = Images.player_down[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        } else if (vX < 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = Images.player_left[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        } else if (vX > 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = Images.player_right[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        }
        node.setTranslateX(x + vX);
        node.setTranslateY(y + vY);
        collisionBound.setTranslateX(x + vX);
        collisionBound.setTranslateY(y + vY);
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
