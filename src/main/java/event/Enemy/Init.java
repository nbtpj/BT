package event.Enemy;

import java.io.FileNotFoundException;

public class Init extends Enemy_Event {
    public Init(double x, double y, String name, int heal_index) {
        super(x, y, name, heal_index);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println(name+" was born!");
    }
}
