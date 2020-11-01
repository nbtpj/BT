package event.Wall;

import object.BigMap;
import object.Wall;

import java.io.FileNotFoundException;

public class Init extends Wall_Event {
    public Init(double x, double y, String type,int heal_index) {
        super(x, y, type, heal_index);
    }

    @Override
    public void render() throws FileNotFoundException {
    }
}
