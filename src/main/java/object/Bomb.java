package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import maxxam.App;

import java.util.Date;

public class Bomb extends Sprite implements Collision {
    public double deathTime = 3;

    public Bomb(double height, double width, double x, double y, double r) {
        node = Images.bomb[0].getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
    }

    @Override
    public void update() {
        deathTime -= 1.0 / App.gameWorld.getFramesPerSecond();
        if (deathTime < 0) {
            handleDeath();
            return;
        }

        double x = node.getTranslateX();
        double y = node.getTranslateY();
        App.gameWorld.getSceneNodes().getChildren().remove(node);
        node = Images.bomb[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
        App.gameWorld.getSceneNodes().getChildren().add(node);
        node.setTranslateX(x + vX);
        node.setTranslateY(y + vY);
        collisionBound.setTranslateX(x + vX);
        collisionBound.setTranslateY(y + vY);
    }

    public void handleDeath() {
        double scale = App.gameWorld.getScale();

        Explore explore;
        explore = new Explore(scale, scale,
                this.node.getTranslateX(), this.node.getTranslateY(), scale / 2, 1, 0, 3);
        App.gameWorld.spawn(explore);
        explore = new Explore(scale, scale,
                this.node.getTranslateX(), this.node.getTranslateY(), scale / 2, 0, 1, 3);
        App.gameWorld.spawn(explore);
        explore = new Explore(scale, scale,
                this.node.getTranslateX(), this.node.getTranslateY(), scale / 2, -1, 0, 3);
        App.gameWorld.spawn(explore);
        explore = new Explore(scale, scale,
                this.node.getTranslateX(), this.node.getTranslateY(), scale / 2, 0, -1, 3);
        App.gameWorld.spawn(explore);

        App.gameWorld.destroy(this);
    }
}
