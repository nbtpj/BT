package event.Bomber;

import java.io.FileNotFoundException;

public class Set_Bomb extends Bomber_Event {
    public Set_Bomb(double x, double y, String name, int heal_index) {
        super(x, y, name, heal_index);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println(name+" set bomb!");
    }
}
