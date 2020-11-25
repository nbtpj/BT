package object;

import engine.Images;
import interfaces.Collision;
import maxxam.App;

import java.util.Date;

public class Explore extends Bomb implements Collision {
    private double sX;
    private double sY;
    private boolean is_terminal;

    public Explore(double height, double width, double x, double y, double sX, double sY, boolean is_terminal) {
        super(height, width, x, y);
        this.sX = sX;
        this.sY = sY;
        this.is_terminal = is_terminal;
        this.deathTime = 1;

        if (sX == 0 && sY == 0) {
            node = Images.bomb_exploded[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
        } else if (sX == 0) {
            if (!is_terminal) {
                node = Images.explosion_vertical[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            } else {
                if (sY < 0) {
                    node = Images.explosion_vertical_top_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                } else {
                    node = Images.explosion_vertical_down_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                }
            }
        } else if (sY == 0) {
            if (!is_terminal) {
                node = Images.explosion_horizontal[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            } else {
                if (sX < 0) {
                    node = Images.explosion_horizontal_left_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                } else {
                    node = Images.explosion_horizontal_right_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                }
            }
        }

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

        if (sX == 0 && sY == 0) {
            node = Images.bomb_exploded[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
        } else if (sX == 0) {
            if (!is_terminal) {
                node = Images.explosion_vertical[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            } else {
                if (sY < 0) {
                    node = Images.explosion_vertical_top_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                } else {
                    node = Images.explosion_vertical_down_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                }
            }
        } else if (sY == 0) {
            if (!is_terminal) {
                node = Images.explosion_horizontal[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            } else {
                if (sX < 0) {
                    node = Images.explosion_horizontal_left_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                } else {
                    node = Images.explosion_horizontal_right_last[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
                }
            }
        }
        node.setTranslateX(x + vX);
        node.setTranslateY(y + vY);

        App.gameWorld.getSceneNodes().getChildren().add(node);
    }

    public void handleDeath() {
        App.gameWorld.destroy(this);
    }
}
