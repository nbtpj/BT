package event.Enemy;

import java.io.FileNotFoundException;

public class Attack extends Enemy_Event{

    public Attack(double x, double y, String name, int heal_index) {
        super(x, y, name, heal_index);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println(name+" is attacking the bomber!");
    }
}
