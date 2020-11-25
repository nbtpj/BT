package object;

import engine.Images;
import interfaces.Collision;
import maxxam.App;

public class Explore extends Bomb implements Collision {
    private double velocity = 1;

    public Explore(double height, double width, double x, double y, double r, double vX, double vY, double velocity) {
        super(height, width, x, y, r);
        node = Images.explosion_horizontal2.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        deathTime = 1;
        this.vX = vX;
        this.vY = vY;
        this.velocity = velocity;
    }

    @Override
    public void update() {
        super.update();
        node.setTranslateX(node.getTranslateX() + vX * velocity);
        node.setTranslateY(node.getTranslateY() + vY * velocity);
        collisionBound.setTranslateX(node.getTranslateX() + vX * velocity);
        collisionBound.setTranslateY(node.getTranslateY() + vY * velocity);
    }

    public void handleDeath() {
        App.gameWorld.destroy(this);
    }
}
