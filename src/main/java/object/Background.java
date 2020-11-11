package object;

import engine.GameWorld;
import engine.ImageClass;
import engine.Sprite;
import javafx.scene.image.ImageView;

public class Background extends Sprite {

    public Background(ImageClass imageClass, String id, double height, double width) {
        node = imageClass.getImageView(id, height, width);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleDeath(GameWorld gameWorld) {

    }
}
