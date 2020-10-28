package event.Enemy;

import java.io.FileNotFoundException;

public class Attack extends Enemy_Event{
    String direction;

    public Attack(double x, double y, String name, int heal_index, String direction) {
        super(x, y, name, heal_index);
        this.direction = direction;
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println(name+" is attacking the "+direction+" cell!");
    }
}
