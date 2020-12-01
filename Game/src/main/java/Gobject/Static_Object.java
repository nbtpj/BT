package Gobject;

import Support_Type.Pos;

public abstract class Static_Object extends Gobject{

    public Static_Object(double index, String name, double x, double y) {
        super(index, name, x, y);
    }

    public Static_Object(double index, String name, Pos pos) {
        super(index, name, pos);
    }
}
