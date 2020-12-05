package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

public class PowerUpFlames extends Power {
    private static final Images images = Images.power_up_flames;

    public PowerUpFlames(double height, double width, double x, double y) {
        node = images.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
    }

    public static PowerUpFlames init(double height, double width) {
        PowerUpFlames powerUpFlames = new PowerUpFlames(App.gameWorld.getScale(),
                App.gameWorld.getScale(),
                height*App.gameWorld.getScale(),
                width*App.gameWorld.getScale());
        App.gameWorld.sprite_map[(int) (powerUpFlames.node.getTranslateY() / App.gameWorld.getScale())][(int) (powerUpFlames.node.getTranslateX() / App.gameWorld.getScale())] = 'F';
        App.gameWorld.spawn(powerUpFlames);
        return powerUpFlames;
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
        App.gameWorld.sprite_map[(int) (node.getTranslateY() / App.gameWorld.getScale())][(int) (node.getTranslateX() / App.gameWorld.getScale())] = ' ';
        App.gameWorld.destroy(this);
    }
}
