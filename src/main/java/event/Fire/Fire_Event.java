package event.Fire;

import event.Event;


public abstract class Fire_Event extends Event {
    public Fire_Event(double x, double y) {
        super(x, y, "fire");
    }

}
