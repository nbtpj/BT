package object;


import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import maxxam.App;

import java.util.Date;

public class Box extends Wall{
    private static final Images images = Images.brick;

    public Box(double height, double width, double x, double y) {
        super(height, width, x, y);
        node = images.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
    }

    public static Box init(double height, double width) {
        Box box = new Box(App.gameWorld.getScale(),
                App.gameWorld.getScale(),
                height * App.gameWorld.getScale(),
                width * App.gameWorld.getScale());
        App.gameWorld.spawn(box);
        return box;
    }

    @Override
    public void executeCollision() {
        for(Sprite sprite: App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Explore) {
                if (collide((Collision)sprite)) {
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
        int rand = ((int) (new Date().getTime() / 100) % 4 + 4) % 4;
        switch (rand) {
            case 0:
                PowerUpFlames.init(
                        (int) (node.getTranslateX() / App.gameWorld.getScale()),
                        (int) (node.getTranslateY() / App.gameWorld.getScale()));
                break;
            case 1:
                PowerUpSpeed.init(
                        (int) (node.getTranslateX() / App.gameWorld.getScale()),
                        (int) (node.getTranslateY() / App.gameWorld.getScale()));
                break;
            case 2:
                PowerUpBombs.init(
                        (int) (node.getTranslateX() / App.gameWorld.getScale()),
                        (int) (node.getTranslateY() / App.gameWorld.getScale()));
        }
    }

}
