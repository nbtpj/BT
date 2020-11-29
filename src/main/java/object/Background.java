package object;

import engine.Images;
import engine.Sprite;
import maxxam.App;

public class Background extends Sprite {

    public Background(double height, double width) {
        node = Images.background.getImageView(height, width);
    }

    public static Background init(double height, double width) {
        Background background = new Background(height * App.gameWorld.getScale(), width * App.gameWorld.getScale());
        App.gameWorld.spawn(background);
        return background;
    }
}
