package object;


import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import maxxam.App;

public class Box extends Wall{
    public Box(double height, double width, double x, double y) {
        super(height, width, x, y);
        node = Images.brick.getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
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
            if (sprite instanceof Explore) {
                if (collide((Collision)sprite)) {
                    App.gameWorld.destroy(sprite);
                }
            }
        }
    }

    @Override
    public void handleDeath() {
        App.gameWorld.sprite_map[(int)(node.getTranslateY()/App.gameWorld.getScale())][(int)(node.getTranslateX()/App.gameWorld.getScale())] = ' ';
        App.gameWorld.destroy(this);
    }

}
