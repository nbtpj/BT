package object;


import engine.Sprite;
import interfaces.Collision;
import maxxam.App;

public class Box extends Wall{
    public Box(double height, double width, double x, double y) {
        super(height, width, x, y);
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
        App.gameWorld.destroy(this);
    }

}
