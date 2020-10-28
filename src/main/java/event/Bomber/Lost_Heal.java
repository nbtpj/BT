package event.Bomber;

import java.io.FileNotFoundException;

public class Lost_Heal extends Bomber_Event {
    public Lost_Heal(double x, double y, String name, int heal_index) {
        super(x, y, name, heal_index);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println(name+" is losing heal!");
    }
}
