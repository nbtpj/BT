package object;

import engine.ImageClass;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Circle;
import maxxam.App;

public class Explore extends Bomb implements Collision {

    public Explore(ImageClass imageClass, String id, double height, double width, double x, double y, double r) {
        super(imageClass, id, height, width, x, y, r);
        deathTime = 1;
    }

    public void handleDeath() {
        App.gameWorld.destroy(this);
    }
}
