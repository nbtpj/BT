package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

public class PowerUpBombs extends Power{
    private static final Images images = Images.power_up_bombs;

    public PowerUpBombs(double height, double width, double x, double y) {
        node = images.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height, width);
    }

    public static PowerUpBombs init(double height, double width) {
        PowerUpBombs powerUpBombs = new PowerUpBombs(App.gameWorld.getScale(),
                App.gameWorld.getScale(),
                height*App.gameWorld.getScale(),
                width*App.gameWorld.getScale());
        App.gameWorld.sprite_map[(int) (powerUpBombs.node.getTranslateY() / App.gameWorld.getScale())][(int) (powerUpBombs.node.getTranslateX() / App.gameWorld.getScale())] = 'B';
        App.gameWorld.spawn(powerUpBombs);
        return powerUpBombs;
    }

    @Override
    public void executeCollision() {
        for (Sprite sprite : App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Player) {
                if (collide((Collision) sprite)) {
                    ((Player) sprite).power_bomb++;
                    this.handleDeath();
                    break;
                }
            }
        }
    }
}
