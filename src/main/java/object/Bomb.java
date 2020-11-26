package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

import java.util.Date;

public class Bomb extends Sprite implements Collision {
    public double deathTime = 3;
    private int power;

    public Bomb(double height, double width, double x, double y) {
        node = Images.bomb[0].getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
    }

    public Bomb(double height, double width, double x, double y, int power) {
        node = Images.bomb[0].getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
        this.power = power;
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
                this.node.getTranslateX(),
                this.node.getTranslateY(),
                0, 0, true);
        App.gameWorld.spawn(explore);

        for (int i = 1; i <= power; i++) {
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale)][(int)(this.node.getTranslateX()/scale) + i] == '#') {
                break;
            }
            if (i == power) {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX() + scale * i,
                        this.node.getTranslateY(),
                        1, 0, true);
            } else {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX() + scale * i,
                        this.node.getTranslateY(),
                        1, 0, false);
            }
            App.gameWorld.spawn(explore);
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale)][(int)(this.node.getTranslateX()/scale) + i] == '*') {
                break;
            }
        }
        for (int i = 1; i <= power; i++) {
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale)][(int)(this.node.getTranslateX()/scale) - i] == '#') {
                break;
            }
            if (i == power) {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX() - scale * i,
                        this.node.getTranslateY(),
                        -1, 0, true);
            } else {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX() - scale * i,
                        this.node.getTranslateY(),
                        -1, 0, false);
            }
            App.gameWorld.spawn(explore);
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale)][(int)(this.node.getTranslateX()/scale) - i] == '*') {
                break;
            }
        }
        for (int i = 1; i <= power; i++) {
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale) + i][(int)(this.node.getTranslateX()/scale)] == '#') {
                break;
            }
            if (i == power) {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX(),
                        this.node.getTranslateY() + scale * i,
                        0, 1, true);
            } else {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX(),
                        this.node.getTranslateY() + scale * i,
                        0, 1, false);
            }
            App.gameWorld.spawn(explore);
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale) + i][(int)(this.node.getTranslateX()/scale)] == '*') {
                break;
            }
        }
        for (int i = 1; i <= power; i++) {
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale) - i][(int)(this.node.getTranslateX()/scale)] == '#') {
                break;
            }
            if (i == power) {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX(),
                        this.node.getTranslateY() - scale * i,
                        0, -1, true);
            } else {
                explore = new Explore(scale, scale,
                        this.node.getTranslateX(),
                        this.node.getTranslateY() - scale * i,
                        0, -1, false);
            }
            App.gameWorld.spawn(explore);
            if (App.gameWorld.sprite_map[(int)(this.node.getTranslateY()/scale) - i][(int)(this.node.getTranslateX()/scale)] == '*') {
                break;
            }
        }

        App.gameWorld.destroy(this);
    }
}
