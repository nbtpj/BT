package object;

import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

public class Portal extends Sprite implements Collision {
    private static final Images images = Images.portal;

    public Portal(double height, double width, double x, double y) {
        node = images.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle)collisionBound, x, y, height, width);
    }

    public static Portal init(double height, double width) {
        Portal portal = new Portal(App.gameWorld.getScale(),
                App.gameWorld.getScale(),
                height * App.gameWorld.getScale(),
                width * App.gameWorld.getScale());
        App.gameWorld.spawn(portal);
        return portal;
    }

    @Override
    public void executeCollision() {
        if (collide(App.gameWorld.player)) {
//            App.gameWorld.start_level(App.gameWorld.stage);
            App.gameWorld.is_nx_level = true;
        }
    }
}
