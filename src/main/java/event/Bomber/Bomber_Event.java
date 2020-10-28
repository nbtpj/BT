package event.Bomber;

import event.Event;

public abstract class Bomber_Event extends Event {
    int heal_index;

    public Bomber_Event(double x, double y, String name, int heal_index) {
        super(x, y, name);
        this.heal_index = heal_index;
        link_to_data_file = "data\\events\\Bomber\\data.txt";
    }

    public void render_heal_index(){
        System.out.println("current heal index is "+heal_index);
    }
}
