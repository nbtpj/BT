package object;

import engine.ImageClass;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Circle;
import maxxam.App;

public class Bomb extends Sprite implements Collision {
    public double deathTime = 3;

    public Bomb(ImageClass imageClass, String id, double height, double width, double x, double y, double r) {
        node = imageClass.getImageView(id, height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Circle();
        this.setupCircleCBound(collisionBound, x, y, r);
    }

    @Override
    public void update() {
        deathTime -= 1.0 / App.gameWorld.getFramesPerSecond();
        if (deathTime < 0) {
            handleDeath();
        }
    }

    public void handleDeath() {
        Explore explore = new Explore(App.gameWorld.getImageClass(),
                                    "explore",
                                    80, 80,
                                    this.node.getTranslateX() + 80,
                                    this.node.getTranslateY() + 80,
                                    40);
        App.gameWorld.spawn(explore);

        App.gameWorld.destroy(this);
    }
}
