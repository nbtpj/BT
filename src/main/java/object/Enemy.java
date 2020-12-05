package object;

import Controller.EnemyController;
import engine.Images;
import engine.Pair;
import maxxam.App;

import java.util.Date;

public class Enemy extends Player{

    private static final Images[][] left = {
            Images.kondoria_left,
            Images.minvo_left,
            Images.doll_left,
            Images.oneal_left,
            Images.balloon_left
    };
    private static final Images[][] right = {
            Images.kondoria_right,
            Images.minvo_right,
            Images.doll_right,
            Images.oneal_right,
            Images.balloon_right
    };
    private static final Images[] dead = {
            Images.kondoria_dead,
            Images.minvo_dead,
            Images.doll_dead,
            Images.oneal_dead,
            Images.balloon_dead
    };
    private static final Images[] mob_dead = Images.mob_dead;

    public int id;

    public int action = 0;
    public boolean done_action = true;
    public double actionStatus = 0;
    public Pair pairAim;

    public Enemy(double height, double width, double x, double y, int id) {
        super(height, width, x, y);
        node = left[id][0].getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        this.id = id;
    }

    @Override
    public void update() {
        EnemyController.getController(this);
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

        time_power_speed -= 1f/App.gameWorld.getFramesPerSecond();
        if (time_power_speed < 0) {
            power_speed = 1;
        }

        vX *= power_speed;
        vY *= power_speed;

        double x = node.getTranslateX();
        double y = node.getTranslateY();

        if (vX < 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = left[this.id][(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        } else {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = right[this.id][(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        }
        node.setTranslateX(x + vX);
        node.setTranslateY(y + vY);
        collisionBound.setTranslateX(x + vX);
        collisionBound.setTranslateY(y + vY);
    }

    @Override
    public void handleDeath() {
        App.gameWorld.destroy(this);
    }

    public static Enemy init(double height, double width, int id) {
        Enemy enemy = new Enemy(App.gameWorld.getScale(),
                App.gameWorld.getScale(),
                height * App.gameWorld.getScale(),
                width * App.gameWorld.getScale(),
                id);
        App.gameWorld.spawn(enemy);
        return enemy;
    }
}
