package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

import java.util.Date;

public class Explore extends Sprite implements Collision {
    private static final Images[] images_bomb_exploded = Images.bomb_exploded;
    private static final Images[] images_explosion_vertical = Images.explosion_vertical;
    private static final Images[] images_explosion_vertical_top_last = Images.explosion_vertical_top_last;
    private static final Images[] images_explosion_vertical_down_last = Images.explosion_vertical_down_last;
    private static final Images[] images_explosion_horizontal = Images.explosion_horizontal;
    private static final Images[] images_explosion_horizontal_left_last = Images.explosion_horizontal_left_last;
    private static final Images[] images_explosion_horizontal_right_last = Images.explosion_horizontal_right_last;

    private double sX;
    private double sY;
    private boolean is_terminal;
    private double deathTime = 1;

    public Explore(double height, double width, double x, double y, double sX, double sY, boolean is_terminal) {
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
        this.sX = sX;
        this.sY = sY;
        this.is_terminal = is_terminal;

        setNode();
        node.setTranslateX(x);
        node.setTranslateY(y);
    }

    @Override
    public void update() {
        deathTime -= 1.0 / App.gameWorld.getFramesPerSecond();
        if (deathTime < 0) {
            handleDeath();
            return;
        }
        App.gameWorld.getSceneNodes().getChildren().remove(node);
        double x = node.getTranslateX();
        double y = node.getTranslateY();

        setNode();
        node.setTranslateX(x + vX);
        node.setTranslateY(y + vY);

        App.gameWorld.getSceneNodes().getChildren().add(node);
    }

    public void handleDeath() {
        App.gameWorld.destroy(this);
    }

    private void setNode() {
        if (sX == 0 && sY == 0) {
            node = images_bomb_exploded[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
        } else if (sX == 0) {
            if (!is_terminal) {
                node = images_explosion_vertical[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            } else {
                if (sY < 0) {
                    node = images_explosion_vertical_top_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                } else {
                    node = images_explosion_vertical_down_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                }
            }
        } else if (sY == 0) {
            if (!is_terminal) {
                node = images_explosion_horizontal[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            } else {
                if (sX < 0) {
                    node = images_explosion_horizontal_left_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                } else {
                    node = images_explosion_horizontal_right_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                }
            }
        }
    }
}
