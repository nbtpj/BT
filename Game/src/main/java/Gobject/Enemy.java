package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Enemy  extends Gobject{
    /**
     * constructor
     * @param name
     * @param x
     * @param y
     */
    public Enemy(String name, double x, double y) {
        super(50, name, x, y);
    }

    /**
     * constructor
     * @param name
     * @param pos
     */
    public Enemy(String name, Pos pos) {
        super(50, name, pos);
    }

    /**
     *
     * @param arg
     */
    public void Act(String arg){

    }
    @Override
    public List<Gobject> update(double t) {
        return null;
    }

    @Override
    public Image render() {
        return null;
    }
}
