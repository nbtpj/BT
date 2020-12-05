package engine;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public abstract class Sprite {

    public Node node;
    public Shape collisionBound;

    public double vX = 0;
    public double vY = 0;
    public void update() {}
    public void executeCollision() {}
    public void handleDeath() {}
}
