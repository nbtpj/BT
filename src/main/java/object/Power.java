package object;

import engine.Sprite;
import interfaces.Collision;
import maxxam.App;

public abstract class Power extends Sprite implements Collision {
    @Override
    public void handleDeath() {
        App.gameWorld.getSoundManager().playSound("eaten");
        App.gameWorld.sprite_map[(int) (node.getTranslateY() / App.gameWorld.getScale())][(int) (node.getTranslateX() / App.gameWorld.getScale())] = ' ';
        App.gameWorld.destroy(this);
    }
}
