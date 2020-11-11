package engine;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public abstract class Sprite {

    public Node node;
    public String tag;
    public Circle collisionBound;

    public double vX = 0;
    public double vY = 0;
    public abstract void update();
    public abstract void handleDeath(GameWorld gameWorld);

//    public void handleDeath(GameWorld gameWorld) {
//        gameWorld.getSpriteManager().addSpritesToBeRemoved(this);
//    }
}
