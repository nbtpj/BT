package object;


import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import maxxam.App;

import java.util.Date;

public class Box extends Wall{
    public Box(double height, double width, double x, double y) {
        super(height, width, x, y);
        node = Images.brick.getImageView(height, width);
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
        int rand = ((int) (new Date().getTime() / 100) % 4 + 4) % 4;
        switch (rand) {
            case 0:
                PowerUpFlames powerUpFlames = new PowerUpFlames(App.gameWorld.getScale(), App.gameWorld.getScale(),
                        node.getTranslateX(), node.getTranslateY());
                App.gameWorld.spawn(powerUpFlames);
                break;
            case 1:
                PowerUpSpeed powerUpSpeed = new PowerUpSpeed(App.gameWorld.getScale(), App.gameWorld.getScale(),
                        node.getTranslateX(), node.getTranslateY());
                App.gameWorld.spawn(powerUpSpeed);
                break;
            case 2:
                PowerUpBombs powerUpBombs = new PowerUpBombs(App.gameWorld.getScale(), App.gameWorld.getScale(),
                        node.getTranslateX(), node.getTranslateY());
                App.gameWorld.spawn(powerUpBombs);
        }
        App.gameWorld.sprite_map[(int) (node.getTranslateY() / App.gameWorld.getScale())][(int) (node.getTranslateX() / App.gameWorld.getScale())] = ' ';
        App.gameWorld.destroy(this);
    }

}
