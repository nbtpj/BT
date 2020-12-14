package Gobject;

import Support_Type.Pos;

public abstract class Effect extends Gobject{
    public Effect(Simple_Data data) {
        super(data);
    }


    public Effect(String name, Pos pos) {
        super(10, name, pos);
    }

}
