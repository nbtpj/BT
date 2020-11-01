package event.Bomb;

import event.Event;
import object.G_Obj;

public abstract class Bomb_Event extends Event {
    public double time;

    public Bomb_Event(double x, double y, double time) {
        super(x,y,"bomb");
        this.time = time;
    }

    /**
     * render thời gian của quả bom
     */
    public void render_time() {
        System.out.println(time);
    }

}
