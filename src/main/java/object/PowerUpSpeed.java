package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

public class PowerUpSpeed extends Power{
    private static final Images images = Images.power_up_speed;

    public PowerUpSpeed(double height, double width, double x, double y) {
        node = images.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
    }

    public static PowerUpSpeed init(double height, double width) {
        PowerUpSpeed powerUpSpeed = new PowerUpSpeed(App.gameWorld.getScale(),
                App.gameWorld.getScale(),
                height*App.gameWorld.getScale(),
                width*App.gameWorld.getScale());
        App.gameWorld.sprite_map[(int) (powerUpSpeed.node.getTranslateY() / App.gameWorld.getScale())][(int) (powerUpSpeed.node.getTranslateX() / App.gameWorld.getScale())] = 'S';
        App.gameWorld.spawn(powerUpSpeed);
        return powerUpSpeed;
    }

    @Override
    public void executeCollision() {
        for (Sprite sprite : App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Player) {
                if (collide((Collision) sprite)) {
                    ((Player) sprite).power_speed = 2;
                    ((Player) sprite).time_power_speed = 10;
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
