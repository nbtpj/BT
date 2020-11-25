package object;

import engine.Images;
import engine.Sprite;

public class Background extends Sprite {

    public Background(double height, double width) {
        node = Images.background.getImageView(height, width);
    }
}
