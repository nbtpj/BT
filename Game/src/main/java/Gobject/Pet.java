package Gobject;

import Support_Type.Pos;

import java.util.List;

public class Pet extends Movable_Object{
    public Pet(double index, String name, double x, double y) {
        super(index, name, x, y);
    }

    public Pet(int i, String name, Pos pos) {
        super(i, name, pos);
    }

    /**
     * allow directly change object's parameters
     *
     * @param arg
     */
    @Override
    public void Act(String arg) {

    }

    @Override
    public List<Gobject> update(double t) {
        return null;
    }
}
