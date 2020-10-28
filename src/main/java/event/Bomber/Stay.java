package event.Bomber;

import event.Event;

import java.io.FileNotFoundException;

public class Stay extends Bomber_Event {
    public Stay(double x, double y, String name, int heal_index) {
        super(x, y, name, heal_index);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println(name+" is not moving");
    }
}
