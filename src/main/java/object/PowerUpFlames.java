package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

public class PowerUpFlames extends Power {
    public PowerUpFlames(double height, double width, double x, double y) {
        node = Images.power_up_flames.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
    }

    @Override
    public void executeCollision() {
        for (Sprite sprite : App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Player) {
                if (collide((Collision) sprite)) {
                    ((Player) sprite).power_flames++;
                    this.handleDeath();
                    break;
                }
            }
        }
    }

    @Override
    public void handleDeath() {
        App.gameWorld.destroy(this);
    }
}
