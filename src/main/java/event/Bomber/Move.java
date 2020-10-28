package event.Bomber;

import java.io.FileNotFoundException;

public class Move extends Bomber_Event {
    public String direction;

    public Move(double x, double y, String name, int heal_index, String direction) {
        super(x, y, name, heal_index);
        this.direction = direction;
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println(name+" is moving to the "+direction);
    }
}
